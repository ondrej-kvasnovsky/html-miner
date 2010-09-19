/**
 * Tags 29.6.2008
 */
package htmlminer.drivers.criteria;

import htmlminer.drivers.compound.HtmlTag;

import java.util.Arrays;
import java.util.List;


/**
 * Tags class represents chain of tags (tags are ordered in given order). It means how those were parsed to parse
 * method.
 * 
 * @author Ondrej Kvasnovsky
 */
public class Tags {

   /**
    * Parse input to Tags class.
    * 
    * @param rawData raw data, for example "div, div, table, tr, td, img"
    * @return parsed Tags
    */
   public static Tags parse(final String rawData) {
      if (rawData == null) {
         throw new NullPointerException("Tags: parsed input can't be null.");
      }
      if ("".equals(rawData)) {
         throw new IllegalArgumentException("Tags: parsed input can't be empty.");
      }
      // split raw data (e.g. "div, table")
      final String[] splitedTags = rawData.split(", ");
      final int length = splitedTags.length;
      // convert those to tags
      final HtmlTag[] tags = new HtmlTag[length];
      for (int i = 0; i < length; i++) {
         tags[i] = HtmlTag.parse(splitedTags[i]);
      }
      // create new Tags and return it as parsed data
      final Tags ret = new Tags(tags);
      return ret;
   }

   /**
    * tags
    */
   private final HtmlTag[] htmlTags;

   /**
    * Private constructor. To construct instance of Tags class use method Tags.parse(String[]).
    * 
    * @param tags parsed tags
    */
   private Tags(final HtmlTag[] tags) {
      this.htmlTags = tags;
   }

   /**
    * Returns copy tags.
    * 
    * @return tags
    */
   public List<HtmlTag> getTags() {
      return Arrays.asList(htmlTags);
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      final String ret = super.toString() + " - " + Arrays.toString(htmlTags);
      return ret;
   }
}
