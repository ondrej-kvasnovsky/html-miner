/**
 * Entry 21.7.2008
 */
package htmlminer.core.customclass.mapping;

/**
 * 
 * @author Ondrej Kvasnovsky
 */
public class Entry {

    private final String property;
    private final SearchCriteria searchCriteria;
    /**
     * found result or null
     */
    private String result;

    /**
     * @param field
     * @param searchCriteria
     */
    public Entry(String field, SearchCriteria searchCriteria) {
        this.property = field;
        this.searchCriteria = searchCriteria;
    }

    /**
     * Returns the field.
     * 
     * @return the field
     */
    public String getProperty() {
        return this.property;
    }

    /**
     * Returns the searchCriteria.
     * 
     * @return the searchCriteria
     */
    public SearchCriteria getSearchCriteria() {
        return this.searchCriteria;
    }

    /**
     * @param text
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Returns the result.
     * 
     * @return the result
     */
    public String getResult() {
        return this.result;
    }
}
