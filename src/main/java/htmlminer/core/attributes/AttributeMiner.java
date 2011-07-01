/**
 * AttributeMiner.java, 15.6.2011 20:28:21 
 */
package htmlminer.core.attributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Html file: <code><br/>
 * &lthtml><br/>
 * &ltbody><br/>
 * &ltdiv id="firstname">John&lt/div><br/>
 * &lt/body><br/>
 * &lt/html>
 * </code> <br/>
 * Java code: <code></code> <br/>
 * The result: <code></code> </p> <br/>
 * <p>
 * <h3>How to get multiple result:</h3>
 * Html file: <code></code> <br/>
 * Java code: <code></code> <br/>
 * The result: <code></code>
 * </p>
 * <br/>
 * 
 * @author Ondrej Kvasnovsky
 */
public final class AttributeMiner {
    /**
     * attributeComplexMiner
     */
    private AttributeComplexMiner attributeComplexMiner;
    /**
     * attributeSimpleMiner
     */
    private AttributeSimpleMiner attributeSimpleMiner;

    /**
     * Creates new instance. Default encoding is UTF-8.
     * 
     * @param urlAddress
     *            URL address
     * @throws MalformedURLException
     *             when a malformed URL has occurred
     */
    public AttributeMiner(final String urlAddress) throws MalformedURLException {
        this.attributeComplexMiner = new AttributeComplexMiner(urlAddress);
        this.attributeSimpleMiner = new AttributeSimpleMiner(urlAddress);
    }

    /**
     * Creates new instance.
     * 
     * @param urlAddress
     *            URL address
     * @param charset
     *            char-set
     * @throws MalformedURLException
     *             when a malformed URL has occurred
     */
    public AttributeMiner(final String urlAddress, final String charset) throws MalformedURLException {
        this.attributeComplexMiner = new AttributeComplexMiner(urlAddress, charset);
        this.attributeSimpleMiner = new AttributeSimpleMiner(urlAddress, charset);
    }

    /**
     * Creates new instance. Default encoding is UTF-8.
     * 
     * @param urlAddress
     *            url address
     */
    public AttributeMiner(final URL urlAddress) {
        this.attributeComplexMiner = new AttributeComplexMiner(urlAddress);
        this.attributeSimpleMiner = new AttributeSimpleMiner(urlAddress);
    }

    /**
     * Delegates to method .AttributeMiner getAttribute.
     * 
     * @param regex
     * @return
     * @throws IOException
     * @see htmlminer.core.attributes.AttributeSimpleMiner#getAttribute(java.lang.String)
     */
    public String getAttribute(String regex) throws IOException {
        return this.attributeSimpleMiner.getAttribute(regex);
    }

    /**
     * Delegates to method .AttributeMiner getAttribute.
     * 
     * @param path
     * @param attributeName
     * @param regex
     * @return
     * @throws IOException
     * @see htmlminer.core.attributes.AttributeComplexMiner#getAttribute(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public String getAttribute(String path, String attributeName, String regex) throws IOException {
        return this.attributeComplexMiner.getAttribute(path, attributeName, regex);
    }

    /**
     * Find a string according to regular expression.
     * 
     * @param regex
     *            regular expression
     * @return found string
     * @throws IOException
     *             can be thrown when HTML page is fetching
     * @see htmlminer.core.attributes.AttributeSimpleMiner#getAttributes(java.lang.String)
     */
    public List<String> getAttributes(String regex) throws IOException {
        return this.attributeSimpleMiner.getAttributes(regex);
    }

    /**
     * Delegates to method .AttributeMiner getAttributes.
     * 
     * @param path
     * @param attributeName
     * @param regex
     * @return
     * @throws IOException
     * @see htmlminer.core.attributes.AttributeComplexMiner#getAttributes(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List<String> getAttributes(String path, String attributeName, String regex) throws IOException {
        return this.attributeComplexMiner.getAttributes(path, attributeName, regex);
    }

    /**
     * Sets the attributeComplexMiner.
     * 
     * @param attributeComplexMiner
     *            the attributeComplexMiner to set
     */
    void setAttributeComplexMiner(AttributeComplexMiner attributeComplexMiner) {
        this.attributeComplexMiner = attributeComplexMiner;
    }

    /**
     * Sets the attributeSimpleMiner.
     * 
     * @param attributeSimpleMiner
     *            the attributeSimpleMiner to set
     */
    void setAttributeSimpleMiner(AttributeSimpleMiner attributeSimpleMiner) {
        this.attributeSimpleMiner = attributeSimpleMiner;
    }

}
