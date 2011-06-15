/**
 * HtmlAttributesMinerTest.java 19.9.2010
 */
package htmlminer.core.attributes;

import htmlminer.core.ItemNotFoundException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Kvasnovsky Ondrej
 */
public class AttributeSimpleMinerTest {
    /**
     * htmlAtrributesMiner
     */
    private AttributeSimpleMiner htmlAtrributesMiner;

    /**
     * Set up the test inputs.
     * 
     * @throws MalformedURLException
     *             when
     */
    @Before
    public final void setUp() throws MalformedURLException {
        URL urlAddress = this.getClass().getResource("large-test-file.htm");
        htmlAtrributesMiner = new AttributeSimpleMiner(urlAddress);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.HtmlAttributesMiner#getAttribute(java.lang.String)}
     * .
     * 
     * @throws IOException
     * @throws ItemNotFoundException
     */
    @Test
    public final void testGetAttributeString() throws ItemNotFoundException, IOException {
        String result = htmlAtrributesMiner.getAttribute(".+.gif");
        Assert.assertEquals("./TDD Anti-Patterns   James Carr_files/icon_smile.gif", result);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.HtmlAttributesMiner#getAttributes(java.lang.String)}
     * .
     * 
     * @throws IOException
     * @throws ItemNotFoundException
     */
    @Test
    public final void testGetAttibutesString() throws ItemNotFoundException, IOException {
        List<String> result = htmlAtrributesMiner.getAttributes(".+.gif");
        // there are 16 gifs in the HTML file
        Assert.assertEquals(16, result.size());

    }
}
