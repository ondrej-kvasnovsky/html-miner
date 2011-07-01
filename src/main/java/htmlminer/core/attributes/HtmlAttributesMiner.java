/**
 * HtmlData 27.6.2008
 */
package htmlminer.core.attributes;

import htmlminer.core.attributes.model.HtmlModel;
import htmlminer.core.attributes.parser.AbstractParser;
import htmlminer.core.attributes.parser.HtmlAttributesParser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides operations on fetched attributes. Attributes are fetched from HTML
 * page.
 * <p>
 * More info is on this page: http://code.google.com/p/html-miner/wiki/
 * QuickIntroductionOfHTMLAttributeMiner
 * </p>
 * 
 * @author Ondrej Kvasnovsky
 */
@Deprecated
public final class HtmlAttributesMiner {

    /**
     * abstract parser
     */
    private final AbstractParser<List<String>> abstractParser;
    /**
     * html model
     */
    private HtmlModel<List<String>> htmlModel;

    /**
     * Constructor. Default encoding is UTF-8.
     * 
     * @param urlAddress
     *            URL address
     * @throws MalformedURLException
     *             when a malformed URL has occurred
     */
    public HtmlAttributesMiner(final String urlAddress) throws MalformedURLException {
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
    public HtmlAttributesMiner(final String urlAddress, final String charset) throws MalformedURLException {
        this(new URL(urlAddress), charset);
    }

    /**
     * Constructor. Default encoding is UTF-8.
     * 
     * @param urlAddress
     *            url address
     */
    public HtmlAttributesMiner(final URL urlAddress) {
        this(urlAddress, "UTF-8");
    }

    /**
     * Constructor.
     * 
     * @param urlAddress
     * @param charset
     */
    public HtmlAttributesMiner(final URL urlAddress, final String charset) {
        if (urlAddress == null) {
            throw new IllegalArgumentException("URL address can't be null.");
        }
        if (charset == null) {
            throw new IllegalArgumentException("Charset can't be null.");
        }
        this.abstractParser = new HtmlAttributesParser(urlAddress, charset);
    }

    /**
     * Find a string according to regular expression.
     * 
     * @param regex
     *            regular expression
     * @return found string
     * @throws IOException
     *             can be thrown when HTML page is fetching
     */
    public String getAttribute(final String regex) throws IOException {
        if (regex == null || "".equals(regex)) {
            throw new IllegalArgumentException("Regex can't be empty.");
        }
        String ret = null;
        if (htmlModel == null) {
            this.abstractParser.start();
            htmlModel = this.abstractParser.getModel();
        }
        List<String> list = htmlModel.getData();
        for (String string : list) {
            if (string.matches(regex)) {
                ret = string;
                break;
            }
        }
        return ret;
    }

    /**
     * Find all strings according to regular expression.
     * 
     * @param regex
     *            regular expression
     * @return collection of found strings or empty collection
     * @throws IOException
     *             can be thrown when HTML page is fetching
     */
    public List<String> getAttributes(final String regex) throws IOException {
        if (regex == null || "".equals(regex)) {
            throw new IllegalArgumentException("Regex can't be empty.");
        }
        List<String> ret = new ArrayList<String>();
        if (htmlModel == null) {
            this.abstractParser.start();
            htmlModel = this.abstractParser.getModel();
        }
        List<String> list = htmlModel.getData();
        for (String string : list) {
            if (string.matches(regex)) {
                ret.add(string);
            }
        }
        return ret;
    }
}
