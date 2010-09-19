/**
 * SearchCriteria 21.7.2008
 */
package htmlminer.core.customclass.mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Class full of restrictions. :)
 * 
 * @author Ondrej Kvasnovsky
 */
public class SearchCriteria {

   private final String htmlPath;
   private final String regex;
   private final List<Attribute> attributes = new ArrayList<Attribute>();

   /**
    * @param e
    * @return
    * @see java.util.List#add(java.lang.Object)
    */
   public boolean add(Attribute e) {
      return this.attributes.add(e);
   }

   /**
    * @param htmlPath
    * @param regex
    */
   public SearchCriteria(String htmlPath, String regex) {
      this.htmlPath = htmlPath;
      this.regex = regex;
   }

   /**
    * Returns the htmlPath.
    * 
    * @return the htmlPath
    */
   public String getHtmlPath() {
      return this.htmlPath;
   }

   /**
    * Returns the regex.
    * 
    * @return the regex
    */
   public String getRegex() {
      return this.regex;
   }

   /**
    * Returns the attributes.
    * 
    * @return the attributes
    */
   public List<Attribute> getAttributes() {
      return this.attributes;
   }
}
