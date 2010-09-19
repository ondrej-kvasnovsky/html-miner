/**
 * EntrySet 21.7.2008
 */
package htmlminer.core.customclass.mapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Ondrej Kvasnovsky
 */
public class EntrySet {

   /**
    * mapping
    */
   private List<Entry> mapping = new ArrayList<Entry>();
   /**
    * parser
    */
   private final Class parser;

   /**
    * @param parser
    */
   public EntrySet(Class parser) {
      this.parser = parser;
   }

   /**
    * @param e
    * @return
    * @see java.util.List#add(java.lang.Object)
    */
   public boolean add(Entry e) {
      return this.mapping.add(e);
   }

   /**
    * Returns the parser.
    * 
    * @return the parser
    */
   public Class getParser() {
      return this.parser;
   }

   /**
    * @return
    * @see java.util.List#iterator()
    */
   public List<Entry> iterator() {
      return this.mapping;
   }
}
