/**
 * HtmlModel 28.6.2008
 */
package htmlminer.core.attributes.model;

/**
 * Basic html mode.
 * 
 * @author Ondrej Kvasnovsky
 */
public class HtmlModel<T> {

    /**
     * data
     */
    private T data;

    /**
     * @param data
     */
    public HtmlModel(T data) {
        this.data = data;
    }

    /**
     * Returns the data.
     * 
     * @return the data
     */
    public T getData() {
        return this.data;
    }

    /**
     * Sets the data.
     * 
     * @param data
     *            the data to set
     */
    public void setData(T data) {
        this.data = data;
    }
}
