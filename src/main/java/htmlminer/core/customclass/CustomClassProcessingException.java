/**
 * CustomClassProcessingException.java 19.9.2010
 */
package htmlminer.core.customclass;

/**
 * @author Ondrej Kvasnovsky
 */
public class CustomClassProcessingException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = -9123420552105561422L;

   /**
    * 
    */
   public CustomClassProcessingException() {
      super();
   }

   /**
    * @param message
    */
   public CustomClassProcessingException(String message) {
      super(message);
   }

   /**
    * @param cause
    */
   public CustomClassProcessingException(Throwable cause) {
      super(cause);
   }

   /**
    * @param message
    * @param cause
    */
   public CustomClassProcessingException(String message, Throwable cause) {
      super(message, cause);
   }
}
