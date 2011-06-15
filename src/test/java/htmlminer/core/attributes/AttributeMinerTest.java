/**
 * AttributeMinerTest.java, 15.6.2011 20:48:52 
 */
package htmlminer.core.attributes;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for {@link AttributeMiner}.
 * 
 * @author Kvasnovsky Ondrej
 */
public class AttributeMinerTest {
    private AttributeMiner attributeMiner;
    private URL urlAddress;

    /**
     * Set up the test inputs.
     * 
     * @throws MalformedURLException
     *             when
     */
    @Before
    public final void setUp() throws MalformedURLException {
        this.urlAddress = this.getClass().getResource("employee.html");
        this.attributeMiner = new AttributeMiner(urlAddress);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeMiner#AttributeMiner(java.lang.String)}
     * .
     * 
     * @throws MalformedURLException
     */
    @Test
    public void testAttributeMinerString() throws MalformedURLException {
        AttributeMiner attributeMiner = new AttributeMiner(urlAddress.toString());
        assertNotNull(attributeMiner);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeMiner#AttributeMiner(java.lang.String, java.lang.String)}
     * .
     * 
     * @throws MalformedURLException
     */
    @Test
    public void testAttributeMinerStringString() throws MalformedURLException {
        AttributeMiner attributeMiner = new AttributeMiner(urlAddress.toString(), "UTF-8");
        assertNotNull(attributeMiner);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeMiner#AttributeMiner(java.net.URL)}
     * .
     */
    @Test
    public void testAttributeMinerURL() {
        AttributeMiner attributeMiner = new AttributeMiner(urlAddress);
        assertNotNull(attributeMiner);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeMiner#getAttribute(java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testGetAttributeString() throws IOException {
        String attribute = this.attributeMiner.getAttribute(".*");
        assertNotNull(attribute);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeMiner#getAttribute(java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testGetAttributeStringStringString() throws IOException {
        String attribute = this.attributeMiner.getAttribute("html body div", "id", ".*");
        assertNotNull(attribute);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeMiner#getAttributes(java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testGetAttributesString() throws IOException {
        List<String> attributes = this.attributeMiner.getAttributes(".*");
        assertNotNull(attributes);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeMiner#getAttributes(java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testGetAttributesStringStringString() throws IOException {
        List<String> attributes = this.attributeMiner.getAttributes("html body div", "id", ".*");
        assertNotNull(attributes);
    }

}
