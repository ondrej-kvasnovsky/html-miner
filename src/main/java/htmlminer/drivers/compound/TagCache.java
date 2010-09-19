/**
 * TagCache 29.6.2008
 */
package htmlminer.drivers.compound;

import java.util.HashMap;

/**
 * 
 * @author Ondrej Kvasnovsky
 */
public class TagCache {

   /**
    * cache
    */
   private final HashMap<String, HtmlTag> cache = new HashMap<String, HtmlTag>();
   /**
    * INSTANCE
    */
   private static final TagCache INSTANCE = new TagCache();

   /**
    * Singleton.
    */
   private TagCache() {
      super();
   }

   /**
    * @return
    */
   public static TagCache getInstance() {
      return TagCache.INSTANCE;
   }

   /**
    * @param key
    * @return
    * @see java.util.HashMap#containsKey(java.lang.Object)
    */
   private boolean containsKey(final Object key) {
      return this.cache.containsKey(key);
   }

   /**
    * @param key
    * @return
    * @see java.util.HashMap#get(java.lang.Object)
    */
   private HtmlTag get(final Object key) {
      return this.cache.get(key);
   }

   /**
    * @param key
    * @param value
    * @return
    * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
    */
   private HtmlTag put(final String key, final HtmlTag value) {
      return this.cache.put(key, value);
   }

   /**
    * @param rawData
    * @return
    */
   public HtmlTag getTag(final String rawData) {
      if (containsKey(rawData)) {
         // return found item
         return get(rawData);
      }
      // is not found, create and put new one
      HtmlTag htmlTag = new HtmlTag(rawData);
      put(rawData, htmlTag);
      return htmlTag;
   }
}
