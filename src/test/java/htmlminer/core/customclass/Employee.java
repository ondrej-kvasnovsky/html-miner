/**
 * Employee.java 19.9.2010
 */
package htmlminer.core.customclass;

/**
 * Test custom class.
 * 
 * @author Ondrej Kvasnovsky
 */
public class Employee {

   private String lastName;

   public Employee() {
   }

   public final String getLastName() {
      return lastName;
   }

   public final void setLastName(String lastName) {
      this.lastName = lastName;
   }
}
