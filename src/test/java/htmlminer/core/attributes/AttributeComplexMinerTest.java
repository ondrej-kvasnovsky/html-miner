/**
 * HtmlAttributesNameValueMinerTest.java, 15.6.2011 16:41:57 
 */
package htmlminer.core.attributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link AttributeComplexMiner}
 * 
 * @author Ondrej Kvasnovsky
 */
public class AttributeComplexMinerTest {

    /**
     * htmlAtrributesMiner
     */
    private AttributeComplexMiner htmlAtrributesMiner;

    /**
     * Set up testing environment.
     * 
     * @throws MalformedURLException
     */
    @Before
    public final void setUp() throws MalformedURLException {
        URL urlAddress = getClass().getResource("employee.html");
        htmlAtrributesMiner = new AttributeComplexMiner(urlAddress);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#HtmlAttributesNameValueMiner(java.lang.String)}
     * .
     * 
     * @throws MalformedURLException
     */
    @Test(expected = MalformedURLException.class)
    public void testHtmlAttributesNameValueMinerString() throws MalformedURLException {
        new AttributeComplexMiner("employee.html");
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#HtmlAttributesNameValueMiner(java.lang.String, java.lang.String)}
     * .
     * 
     * @throws MalformedURLException
     */
    @Test(expected = MalformedURLException.class)
    public void testHtmlAttributesNameValueMinerStringString() throws MalformedURLException {
        new AttributeComplexMiner("employee.html", "UTF-8");
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#HtmlAttributesNameValueMiner(java.net.URL)}
     * .
     */
    @Test
    public void testHtmlAttributesNameValueMinerURL() {
        AttributeComplexMiner miner = new AttributeComplexMiner(getClass().getResource("employee.html"));
        Assert.assertNotNull(miner);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#HtmlAttributesNameValueMiner(java.net.URL, java.lang.String)}
     * .
     */
    @Test
    public void testHtmlAttributesNameValueMinerURLString() {
        AttributeComplexMiner miner = new AttributeComplexMiner(getClass().getResource("employee.html"), "UTF-8");
        Assert.assertNotNull(miner);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#HtmlAttributesNameValueMiner(java.net.URL, java.lang.String)}
     * .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testHtmlAttributesNameValueMinerURLStringEmptyCharset() {
        new AttributeComplexMiner(getClass().getResource("employee.html"), "");
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#HtmlAttributesNameValueMiner(java.net.URL, java.lang.String)}
     * .
     * 
     * @throws MalformedURLException
     */
    @Test(expected = MalformedURLException.class)
    public void testHtmlAttributesNameValueMinerURLStringEmptyStrings() throws MalformedURLException {
        new AttributeComplexMiner("", "");
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#getAttribute(java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testGetAttribute() throws IOException {
        String result = htmlAtrributesMiner.getAttribute("html body div", "id", ".*");
        Assert.assertEquals("firstname", result);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#getAttribute(java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testGetAttributeRegex() throws IOException {
        String result = htmlAtrributesMiner.getAttribute("html body div", "id", "s.*");
        Assert.assertEquals("surname", result);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#getAttribute(java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAttributePathNull() throws IOException {
        htmlAtrributesMiner.getAttribute(null, null, ".*");
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#getAttribute(java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAttributeRegexNull() throws IOException {
        htmlAtrributesMiner.getAttribute("html", null, ".*");
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#getAttributes(java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testGetAttributes() throws IOException {
        List<String> result = htmlAtrributesMiner.getAttributes("html body div", "id", ".*");
        Assert.assertEquals(3, result.size());
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeComplexMiner#getAttributes(java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testGetAttributesRegex() throws IOException {
        List<String> result = htmlAtrributesMiner.getAttributes("html body div", "id", "f.*");
        Assert.assertEquals(1, result.size());
    }
}
