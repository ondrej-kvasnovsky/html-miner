/**
 * ParseHtmlAttributes 27.6.2008
 */
package htmlminer.core.attributes.parser;

import htmlminer.core.attributes.model.HtmlModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Attribute;

/**
 * Parse only attributes from HTML file.
 * 
 * @author Ondrej Kvasnovsky
 */
public final class HtmlAttributesParser extends AbstractParser<List<String>> {

   /**
    * model
    */
   private HtmlModel<List<String>> model = new HtmlModel<List<String>>(new ArrayList<String>());

   /**
    * Constructor.
    * 
    * @param url url
    * @param charset charset
    */
   public HtmlAttributesParser(final URL url, final String charset) {
      super(url, charset);
   }

   /*
    * (non-Javadoc)
    * 
    * @see htmlminer.core.HtmlParser#getModel()
    */
   @Override
   public HtmlModel<List<String>> getModel() {
      return this.model;
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit$ParserCallback#handleSimpleTag(javax.swing.text.html.HTML.Tag,
    * javax.swing.text.MutableAttributeSet, int)
    */
   @Override
   public void handleSimpleTag(final HTML.Tag tag, final MutableAttributeSet atr, final int pos) {
      final Enumeration<?> attributeNames = atr.getAttributeNames();
      if (attributeNames instanceof Enumeration) {
         while (attributeNames.hasMoreElements()) {
            final Object objElement = attributeNames.nextElement();
            if (objElement instanceof Attribute) {
               final Attribute nextElement = (Attribute) objElement;
               final String attribute = (String) atr.getAttribute(nextElement);
               model.getData().add(attribute.toString());
            } else {
               model.getData().add(objElement.toString());
            }
         }
      }
   }
}
