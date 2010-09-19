/**
 * Config 21.7.2008
 */
package htmlminer.core.customclass.mapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * In this class is mapped what data (via a search criteria) is fetched to custom class.
 * 
 * @author Ondrej Kvasnovsky
 */
public final class CustomClassMapping {

   /**
    * clazz - user's class
    */
   private Class userClass;
   /**
    * mapping
    */
   private List<EntrySet> entrySets = new ArrayList<EntrySet>();

   /**
    * @param e
    * @return
    * @see java.util.List#add(java.lang.Object)
    */
   public boolean addEntrySet(EntrySet e) {
      return this.entrySets.add(e);
   }

   /**
    * @return
    * @see java.util.List#iterator()
    */
   public Iterator<EntrySet> iteratorEntrySet() {
      return this.entrySets.iterator();
   }

   /**
    * Returns the clazz.
    * 
    * @return the clazz
    */
   public Class getUserClass() {
      return this.userClass;
   }

   /**
    * Sets the clazz.
    * 
    * @param clazz the clazz to set
    */
   public void setUserClass(Class clazz) {
      this.userClass = clazz;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return super.toString() + ", " + this.userClass;
   }
}
