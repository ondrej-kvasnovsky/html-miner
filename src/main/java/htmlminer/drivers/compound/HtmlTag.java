/**
 * Tag 29.6.2008
 */
package htmlminer.drivers.compound;

import javax.swing.text.html.HTML.Tag;

/**
 * 
 * @author Ondrej Kvasnovsky
 */
public class HtmlTag {

   /**
    * @param rawData
    * @return
    */
   public static HtmlTag parse(final String rawData) {
      final TagCache tagCache = TagCache.getInstance();
      HtmlTag ret = tagCache.getTag(rawData);
      return ret;
   }

   /**
    * raw data
    */
   private final String tag;

   /**
    * @param tag
    */
   HtmlTag(final String tag) {
      this.tag = tag;
   }

   /**
    * Returns the rawData.
    * 
    * @return the rawData
    */
   public final String getRawData() {
      return this.tag;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return super.toString() + " - " + tag;
   }
}
