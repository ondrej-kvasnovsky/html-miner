/**
 * HtmlCompoundParser 29.6.2008
 */
package htmlminer.drivers.compound;

import htmlminer.core.attributes.model.HtmlModel;
import htmlminer.core.attributes.parser.AbstractParser;

import java.net.URL;
import java.util.Enumeration;
import java.util.Queue;
import java.util.Stack;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTML;
import javax.swing.text.html.CSS.Attribute;
import javax.swing.text.html.HTML.Tag;


/**
 * Compound parser.
 * 
 * @author Ondrej Kvasnovsky
 */
public final class HtmlCompoundParser extends AbstractParser<HtmlFile> {

   /**
    * Configuration of parser. Holds information about what will be indexed.
    * 
    * @author Ondrej Kvasnovsky
    */
   public final class Config {

      /**
       * indexComment
       */
      private boolean indexComment = false;
      /**
       * indexEndOfLineString
       */
      private boolean indexEndOfLineString = false;
      /**
       * indexError
       */
      private boolean indexError = false;
      /**
       * indexSimpleTag
       */
      private boolean indexSimpleTag = true;
      /**
       * indexStartTag
       */
      private boolean indexStartTag = true;
      /**
       * indexText
       */
      private boolean indexText = false;

      /**
       * Returns the indexComment.
       * 
       * @return the indexComment
       */
      public boolean isIndexComment() {
         return this.indexComment;
      }

      /**
       * Returns the indexEndOfLineString.
       * 
       * @return the indexEndOfLineString
       */
      public boolean isIndexEndOfLineString() {
         return this.indexEndOfLineString;
      }

      /**
       * Returns the indexError.
       * 
       * @return the indexError
       */
      public boolean isIndexError() {
         return this.indexError;
      }

      /**
       * Returns the indexSimpleTag.
       * 
       * @return the indexSimpleTag
       */
      public boolean isIndexSimpleTag() {
         return this.indexSimpleTag;
      }

      /**
       * Returns the indexStartTag.
       * 
       * @return the indexStartTag
       */
      public boolean isIndexStartTag() {
         return this.indexStartTag;
      }

      /**
       * Returns the indexText.
       * 
       * @return the indexText
       */
      public boolean isIndexText() {
         return this.indexText;
      }
   }

   /**
    * config
    */
   private final Config config = new Config();
   /**
    * model
    */
   private final HtmlModel<HtmlFile> model = new HtmlModel<HtmlFile>(new HtmlFile());

   /**
    * Constructor.
    * 
    * @param url url
    * @param charset charset
    */
   public HtmlCompoundParser(final URL url, final String charset) {
      super(url, charset);
   }

   /*
    * (non-Javadoc)
    * 
    * @see htmlminer.core.HtmlParser#getModel()
    */
   @Override
   public HtmlModel<HtmlFile> getModel() {
      return model;
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleComment(char[], int)
    */
   @Override
   public void handleComment(final char[] arg0, final int arg1) {
      if (config.isIndexComment()) {
         System.out.print("Comment: ");
         StringBuilder stringBuilder = new StringBuilder();
         for (char c : arg0) {
            stringBuilder.append(c);
         }
         String text = stringBuilder.toString();
         System.out.println(text);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleEndOfLineString(java.lang.String)
    */
   @Override
   public void handleEndOfLineString(final String arg0) {
      if (config.isIndexEndOfLineString()) {
         System.out.println("EndOfLineString: " + arg0);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleEndTag(javax.swing.text.html.HTML.Tag, int)
    */
   @Override
   public void handleEndTag(final Tag t, final int pos) {
      if (config.isIndexStartTag()) {
         final int pathLength = path.toString().length();
         final int tagLength = t.toString().length();
         path.delete(pathLength - tagLength - 1, pathLength);
         // System.out.println(path);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleError(java.lang.String, int)
    */
   @Override
   public void handleError(final String arg0, final int arg1) {
      if (config.isIndexError()) {
         System.out.println("Error: " + arg0);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleSimpleTag(javax.swing.text.html.HTML.Tag,
    *      javax.swing.text.MutableAttributeSet, int)
    */
   @Override
   public void handleSimpleTag(final Tag arg0, final MutableAttributeSet arg1, final int arg2) {
      if (config.isIndexSimpleTag()) {
         System.out.println(path.toString() + arg0.toString());
      }
   }

   /**
    * path
    */
   private StringBuilder path = new StringBuilder();

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleStartTag(javax.swing.text.html.HTML.Tag,
    *      javax.swing.text.MutableAttributeSet, int)
    */
   @Override
   public void handleStartTag(final Tag tag, final MutableAttributeSet arg1, final int arg2) {
      if (config.isIndexStartTag()) {
         path.append(tag.toString());
         path.append(" ");
         final String string = path.toString();
         System.out.println(path + ": " + string.hashCode());
         final Enumeration<Object> attributeNames = (Enumeration<Object>) arg1.getAttributeNames();
         for (; attributeNames.hasMoreElements();) {
            final Object nextElement = attributeNames.nextElement();
            if (nextElement instanceof CSS.Attribute) {
               CSS.Attribute attribute = (CSS.Attribute) nextElement;
               System.out.print(", CSS.Attribute: " + attribute + " * " + attribute.getDefaultValue() + ", ");
            } else if (nextElement instanceof HTML.Attribute) {
               HTML.Attribute s = (HTML.Attribute) nextElement;
               System.out.print(", HTML.Attribute: " + s);
            }
         }
         HtmlTag htmlTag = new HtmlTag(string);
         model.getData().put(Long.valueOf(string.hashCode()), htmlTag);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleText(char[], int)
    */
   @Override
   public void handleText(final char[] arg0, final int arg1) {
      if (config.isIndexText()) {
         System.out.print("Text: ");
         StringBuilder stringBuilder = new StringBuilder();
         for (char c : arg0) {
            stringBuilder.append(c);
         }
         String text = stringBuilder.toString();
         System.out.println(text);
      }
   }
}
