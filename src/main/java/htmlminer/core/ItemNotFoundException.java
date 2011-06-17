/**
 * ItemNotFoundException 28.6.2008
 */
package htmlminer.core;

/**
 * 
 * @author Ondrej Kvasnovsky
 */
public class ItemNotFoundException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3500166977965033693L;

    /**
    * 
    */
    public ItemNotFoundException() {
        super();
    }

    /**
     * @param arg0
     */
    public ItemNotFoundException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public ItemNotFoundException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ItemNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
}
