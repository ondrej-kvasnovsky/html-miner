/**
 * HtmlAttributesNameValueMiner.java, 15.6.2011 16:26:33 
 */
package htmlminer.core.attributes;

import htmlminer.core.attributes.model.HtmlModel;
import htmlminer.core.attributes.parser.AbstractParser;
import htmlminer.core.attributes.parser.AttributeComplexParser;
import htmlminer.core.attributes.parser.AttributeComplexParser.AttributeEntry;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This miner takes the following inputs:
 * <p>
 * <li>path to the HTML tag</li>
 * <li>name of the attribute</li>
 * <li>regular expression</li>
 * </p>
 * 
 * The miner provides the following functionalities:
 * <p>
 * <li>search for single value in an attribute of a tag</li>
 * <li>search for list of values matching given criteria</li>
 * </p>
 * 
 * @author Ondrej Kvasnovsky
 */
class AttributeComplexMiner {

    /**
     * abstract parser
     */
    private final AbstractParser<List<AttributeEntry>> abstractParser;
    /**
     * html model
     */
    private HtmlModel<List<AttributeEntry>> htmlModel;

    /**
     * Constructor. Default encoding is UTF-8.
     * 
     * @param urlAddress
     *            URL address
     * @throws MalformedURLException
     *             when a malformed URL has occurred
     */
    public AttributeComplexMiner(final String urlAddress) throws MalformedURLException {
        this(new URL(urlAddress), "UTF-8");
    }

    /**
     * Constructor.
     * 
     * @param urlAddress
     *            URL address
     * @param charset
     *            char-set
     * @throws MalformedURLException
     *             when a malformed URL has occurred
     */
    public AttributeComplexMiner(final String urlAddress, final String charset) throws MalformedURLException {
        this(new URL(urlAddress), charset);
    }

    /**
     * Constructor. Default encoding is UTF-8.
     * 
     * @param urlAddress
     *            URL address
     */
    public AttributeComplexMiner(final URL urlAddress) {
        this(urlAddress, "UTF-8");
    }

    /**
     * Constructor.
     * 
     * @param urlAddress
     *            URL address
     * @param charset
     *            charset
     */
    public AttributeComplexMiner(final URL urlAddress, final String charset) {
        if (urlAddress == null) {
            throw new IllegalArgumentException("URL address can't be null.");
        }
        if ((charset == null) || "".equals(charset)) {
            throw new IllegalArgumentException("Charset can't be null.");
        }
        this.abstractParser = new AttributeComplexParser(urlAddress, charset);
    }

    /**
     * Returns a value of a specific attribute on given path, matching the regex
     * expression.
     * 
     * @param path
     *            path, e.g. "html body div"
     * @param attributeName
     *            attribute name, e.g. "href"
     * @param regex
     *            regular expression, e.g. ".*"
     * @return returns the list of found values, e.g. if looking for value in
     *         href="index_home.ini", returned value is "index_home.ini"
     * @throws IOException
     *             is thrown when file operations fail
     */
    public String getAttribute(final String path, final String attributeName, final String regex) throws IOException {
        this.validateArguments(path, attributeName, regex);
        AttributeEntry attributeEntry = this.getAttributeEntry(path, regex);
        String ret = null;
        if (attributeEntry != null) {
            String name = attributeEntry.getName();
            if (attributeName.equals(name)) {
                ret = attributeEntry.getValue();
            }
        } else {
            ret = "";
        }
        return ret;
    }

    /**
     * Returns all values of a specific attribute on given path, matching the
     * regex expression.
     * 
     * @param path
     *            path, e.g. "html body div"
     * @param attributeName
     *            attribute name, e.g. "href"
     * @param regex
     *            regular expression, e.g. ".*"
     * @return returns the list of found values, e.g. if looking for value in
     *         href="index_home.ini", returned value is "index_home.ini"
     * @throws IOException
     *             is thrown when file operations fail
     */
    private List<AttributeEntry> getAttributeEntries(final String path, final String regex) throws IOException {
        this.initModel();
        List<AttributeEntry> list = this.htmlModel.getData();
        List<AttributeEntry> ret = new ArrayList<AttributeEntry>();
        for (AttributeEntry entry : list) {
            if (path.equals(entry.getPath())) {
                ret.add(entry);
            }
        }
        return ret;
    }

    /**
     * Returns a value of a specific attribute on given path, matching the regex
     * expression.
     * 
     * @param path
     *            path, e.g. "html body div"
     * @param attributeName
     *            attribute name, e.g. "href"
     * @param regex
     *            regular expression, e.g. ".*"
     * @return returns the list of found values, e.g. if looking for value in
     *         href="index_home.ini", returned value is "index_home.ini"
     * @throws IOException
     *             is thrown when file operations fail
     */
    private AttributeEntry getAttributeEntry(final String path, final String regex) throws IOException {
        this.initModel();
        List<AttributeEntry> list = this.htmlModel.getData();
        AttributeEntry attributeEntry = null;
        for (AttributeEntry entry : list) {
            String value = entry.getValue();
            if (path.equals(entry.getPath()) && value.matches(regex)) {
                attributeEntry = entry;
                break;
            }
        }
        return attributeEntry;
    }

    /**
     * Returns all values of a specific attribute on given path, matching the
     * regex expression.
     * 
     * @param path
     *            path, e.g. "html body div"
     * @param attributeName
     *            attribute name, e.g. "href"
     * @param regex
     *            regular expression, e.g. ".*"
     * @return returns the list of found values, e.g. if looking for value in
     *         href="index_home.ini", returned value is "index_home.ini"
     * @throws IOException
     *             is thrown when file operations fail
     */
    public List<String> getAttributes(final String path, final String attributeName, final String regex)
            throws IOException {
        this.validateArguments(path, attributeName, regex);
        List<String> ret = new ArrayList<String>();
        List<AttributeEntry> attributeEntries = this.getAttributeEntries(path, regex);
        for (AttributeEntry entry : attributeEntries) {
            String name = entry.getName();
            String value = entry.getValue();
            if (attributeName.equals(name) && value.matches(regex)) {
                ret.add(value);
            }
        }
        return ret;
    }

    /**
     * Initialize the model.
     * 
     * @throws IOException
     */
    private void initModel() throws IOException {
        if (this.htmlModel == null) {
            this.abstractParser.start();
            this.htmlModel = this.abstractParser.getModel();
        }
    }

    /**
     * Validates given input arguments.
     * 
     * @param path
     *            path, e.g. "html body div"
     * @param attributeName
     *            attribute name, e.g. "href"
     * @param regex
     *            regular expression, e.g. ".*"
     */
    private void validateArguments(final String path, final String attributeName, final String regex) {
        if ((path == null) || "".equals(path)) {
            throw new IllegalArgumentException("Path can't be empty.");
        }
        if ((regex == null) || "".equals(regex)) {
            throw new IllegalArgumentException("Regex can't be empty.");
        }
        if ((attributeName == null) || "".equals(attributeName)) {
            throw new IllegalArgumentException("Attribute name can't be empty.");
        }
    }
}
