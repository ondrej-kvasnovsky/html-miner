/**
 * TagAttribute 22.7.2008
 */
package htmlminer.core.customclass.mapping;

/**
 * 
 * @author Ondrej Kvasnovsky
 */
public class Attribute {

   /**
    * name
    */
   private final String name;
   /**
    * regex
    */
   private final String regex;

   /**
    * @param name
    * @param regex
    */
   public Attribute(String name, String regex) {
      this.name = name;
      this.regex = regex;
   }

   /**
    * Returns the name.
    * 
    * @return the name
    */
   public String getName() {
      return this.name;
   }

   /**
    * Returns the regex.
    * 
    * @return the regex
    */
   public String getRegex() {
      return this.regex;
   }
}
