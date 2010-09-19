/**
 * HtmlFile 29.6.2008
 */
package htmlminer.drivers.compound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * HTML file
 * 
 * @author Ondrej Kvasnovsky
 */
public class HtmlFile {

   /**
    * Hash-table that allows to store duplicates.
    * 
    * @author Ondrej Kvasnovsky
    */
   class ComponentsTable extends Hashtable {

      /**
       * Associate yet another value with a key in a Hashtable that allows duplicates. Also use to put the first
       * key/value.
       * 
       * @param key Key to lookup by
       * @param value yet another associated value for this key.
       */
      public void putDuplicate(final Object key, final Object value) {
         // duplicate values are stored as single objects, pairs in an array or
         // multiples in an ArrayList
         // Why not just use ArrayLists for everything? To conserve RAM.
         Object existing = get(key);
         if (existing == null) {
            put(key, value);
         } else if (existing instanceof Object[]) {
            // was a pair in array, make into an ArrayList of 3.
            ArrayList a = new ArrayList();
            a.addAll(Arrays.asList((Object[]) existing));
            a.add(value);
            // the entire ArrayList goes into the Hashtable as a single key
            // replacing the array
            put(key, a);
         } else if (existing instanceof ArrayList) {
            // just add to tail end of existing ArrayList
            ((ArrayList) existing).add(value);
         } else {
            /* was a single object. Make into a pair, replacing original single */
            put(key, new Object[] { existing, value });
         }
      }

      /**
       * Get from a Hashtable that allows duplicates
       * 
       * @param key Key to lookup by
       * @return array of values associated with this key. Note the result is an Object[] array not String[] array, even
       *         if contents were Strings. Null if none found. Returns values in same order they were inserted.
       */
      public Object[] getDuplicates(final Object key) {
         // values are stored as single objects, pairs in an array or
         // multiples in an ArrayList
         Object match = get(key);
         if (match == null) {
            return null;
         } else if (match instanceof Object[]) {
            return (Object[]) match;
         } else if (match instanceof ArrayList) {
            ArrayList a = (ArrayList) match;
            return a.toArray(new Object[a.size()]);
         } else {
            /* was a single object */
            return new Object[] { match };
         }
      }
   }

   /**
    * components TODO: create own collection (allow to store duplicates)
    */
   private final ComponentsTable components = new ComponentsTable();

   /**
    * Constructor.
    */
   public HtmlFile() {
      super();
   }

   /**
    * @param key
    * @param value
    * @return
    * @see java.util.Hashtable#put(java.lang.Object, java.lang.Object)
    */
   public void put(Long key, HtmlTag value) {
      this.components.putDuplicate(key, value);
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   public String toString() {
      // TODO Auto-generated method stub
      return super.toString() + this.components;
   }
}
