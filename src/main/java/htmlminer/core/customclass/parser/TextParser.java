/**
 * TextParser 21.7.2008
 */
package htmlminer.core.customclass.parser;

import htmlminer.core.customclass.mapping.Attribute;
import htmlminer.core.customclass.mapping.Entry;
import htmlminer.core.customclass.mapping.SearchCriteria;

import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Tag;


/**
 * Special parser to get text from HTML tags (&lttitle>Text that will be taken...&lt/title>).
 * 
 * @author Ondrej Kvasnovsky
 */
public class TextParser extends AbstractParser {

   /**
    * found search criteria (only helper variable)
    */
   private Entry foundEntry;

   /**
    * Constructor.
    * 
    * @param url url
    * @param charset char set
    * @param searchCriterias list of search criterias
    */
   public TextParser(URL url, String charset, List<Entry> searchCriterias) {
      super(url, charset, searchCriterias);
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleEndTag(javax.swing.text.html.HTML.Tag, int)
    */
   @Override
   public void handleEndTag(final Tag t, final int pos) {
      // remove last tag (like in queue)
      final int pathLength = path.toString().length();
      final int tagLength = t.toString().length();
      path.delete(pathLength - tagLength - 1, pathLength);
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleStartTag(javax.swing.text.html.HTML.Tag,
    *      javax.swing.text.MutableAttributeSet, int)
    */
   @Override
   public void handleStartTag(final Tag tag, final MutableAttributeSet attributeSet, final int arg2) {
      // reset found item
      this.foundEntry = null;
      // collect received data
      this.path.append(tag.toString());
      this.path.append(" ");
      // try to find search criteria by HTML path
      for (Entry entry : this.entries) {
         final SearchCriteria searchCriteria = entry.getSearchCriteria();
         // is HTML Path the same?
         final String htmlPath = searchCriteria.getHtmlPath();
         boolean isHtmlPathTheSame = false;
         if (htmlPath == null || "".equals(htmlPath)) {
            isHtmlPathTheSame = true;
         } else {
            isHtmlPathTheSame = htmlPath.equals(path.toString().trim());
         }
         // are attributes the same?
         boolean areAttributesTheSame = true;
         final List<Attribute> attributes = searchCriteria.getAttributes();
         // continue only if attributes are given
         if (attributes != null) {
            final int attributesSize = attributes.size();
            final int attributeSetCount = attributeSet.getAttributeCount();
            if (attributeSetCount <= 0 && attributesSize <= 0) {
               // all sets are empty - it can be true
               areAttributesTheSame = true;
            } else {
               // go though all attributes and compare those
               for (int i = 0; i < attributesSize; i++) {
                  final Attribute attribute = attributes.get(i);
                  final String attributeName = attribute.getName();
                  final Enumeration<?> attributeNames = attributeSet.getAttributeNames();
                  Object foundAttribute = null;
                  while (attributeNames.hasMoreElements()) {
                     final Object nextElement = attributeNames.nextElement();
                     // compare attributes names - if equals store it
                     if (nextElement.toString().equals(attributeName)) {
                        foundAttribute = nextElement;
                        break;
                     }
                  }
                  // break if that has not been found
                  if (foundAttribute == null) {
                     return;
                  }
                  // value of attribute
                  final Object valueOfAttribute = attributeSet.getAttribute(foundAttribute);
                  if (valueOfAttribute == null) {
                     // is empty then break
                     return;
                  }
                  // attribute is found, try to matche regex
                  String regex = attribute.getRegex();
                  final Pattern pattern = Pattern.compile(regex);
                  final String string = valueOfAttribute.toString();
                  final Matcher matcher = pattern.matcher(string);
                  // try to matche regex
                  if (matcher.matches()) {
                     areAttributesTheSame = true;
                     // break; // TODO: compare all attributes, do not break the loop
                  } else {
                     areAttributesTheSame = false;
                  }
               }
            }
         }
         if (isHtmlPathTheSame && areAttributesTheSame) {
            // it was found, set it to foundSearchCriteria to let parser know that a criteria was found
            // parse will take this information in handleText method.
            this.foundEntry = entry;
         }
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleText(char[], int)
    */
   @Override
   public void handleText(final char[] arg0, final int arg1) {
      if (this.foundEntry != null) {
         // get received data
         final StringBuilder stringBuilder = new StringBuilder();
         for (char c : arg0) {
            stringBuilder.append(c);
         }
         final String text = stringBuilder.toString();
         // use regex or not?
         final String regex = this.foundEntry.getSearchCriteria().getRegex();
         if (regex == null || "".equals(regex)) {
            // regex is not given
            foundEntry.setResult(text);
         } else {
            // regex has been given
            final Pattern pattern = Pattern.compile(regex);
            final Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
               foundEntry.setResult(text);
            }
         }
         this.foundEntry = null;
      }
   }
}
