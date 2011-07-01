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
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Test for {@link AttributeMiner}.
 * 
 * @author Kvasnovsky Ondrej
 */
public class AttributeMinerTest {
    /**
     * attributeComplexMiner
     */
    @Mock
    private AttributeComplexMiner attributeComplexMiner;
    /**
     * attributeSimpleMiner
     */
    @Mock
    private AttributeSimpleMiner attributeSimpleMiner;
    /**
     * attributeMiner
     */
    private AttributeMiner attributeMiner;
    /**
     * urlAddress
     */
    private URL urlAddress;

    /**
     * Set up the test inputs.
     * 
     * @throws MalformedURLException
     *             when
     */
    @Before
    public final void setUp() throws MalformedURLException {
        MockitoAnnotations.initMocks(this);
        this.urlAddress = this.getClass().getResource("employee.html");
        this.attributeMiner = new AttributeMiner(urlAddress);
        this.attributeMiner.setAttributeComplexMiner(attributeComplexMiner);
        this.attributeMiner.setAttributeSimpleMiner(attributeSimpleMiner);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.AttributeMiner#AttributeMiner(java.lang.String)}
     * .
     * 
     * @throws IOException
     */
    @Test
    public void testAttributeMinerString() throws IOException {
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
        this.attributeMiner.getAttribute(".*");
        Mockito.verify(this.attributeSimpleMiner).getAttribute(".*");
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
        this.attributeMiner.getAttribute("html body div", "id", ".*");
        Mockito.verify(this.attributeComplexMiner).getAttribute("html body div", "id", ".*");
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
        this.attributeMiner.getAttributes(".*");
        Mockito.verify(this.attributeSimpleMiner).getAttributes(".*");
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
        this.attributeMiner.getAttributes("html body div", "id", ".*");
        Mockito.verify(this.attributeComplexMiner).getAttributes("html body div", "id", ".*");
    }

}
