/**
 * HtmlAttributesMinerTest.java 19.9.2010
 */
package htmlminer.core.attributes;

import static org.junit.Assert.fail;

import htmlminer.core.ItemNotFoundException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

/**
 * @author Kvasnovsky Ondrej
 * 
 */
@Deprecated
public class HtmlAttributesMinerTest {

    /**
     * Test method for
     * {@link htmlminer.core.attributes.HtmlAttributesMiner#HtmlAttributesCore(java.net.URL)}
     * .
     */
    @Test
    public final void testHtmlAttributesCoreURL() {
        // fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.HtmlAttributesMiner#HtmlAttributesCore(java.net.URL, java.lang.String)}
     * .
     */
    @Test
    public final void testHtmlAttributesCoreURLString() {
        // fail("Not yet implemented"); // TODO
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
    public final void testFindStringString() throws ItemNotFoundException, IOException {
        HtmlAttributesMiner attibutesCore = new HtmlAttributesMiner(new URL("http://www.oracle.com/index.html"));
        String result = attibutesCore.getAttribute(".+.png");
        System.out.println(result);
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
    public final void testFindStrings() throws ItemNotFoundException, IOException {
        HtmlAttributesMiner attibutesCore = new HtmlAttributesMiner("http://www.oracle.com/index.html");
        List<String> result = attibutesCore.getAttributes(".+.png");
        System.out.println(result);
    }
}
