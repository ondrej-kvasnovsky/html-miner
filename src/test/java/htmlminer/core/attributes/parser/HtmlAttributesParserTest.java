/**
 * HtmlAttributesParserTest.java, 1.7.2011 10:31:01 
 */
package htmlminer.core.attributes.parser;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import htmlminer.core.attributes.model.HtmlModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author kvasnond
 */
public class HtmlAttributesParserTest {
    /**
     * model
     */
    @Mock
    private HtmlModel<List<String>> model;
    private HtmlAttributesParser parser;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.parser = new HtmlAttributesParser(any(URL.class), "UTF-8");
        this.parser.setModel(this.model);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.parser.HtmlAttributesParser#HtmlAttributesParser(java.net.URL, java.lang.String)}
     * .
     */
    @Test
    public void testHtmlAttributesParser() {
        HtmlAttributesParser parser = new HtmlAttributesParser(any(URL.class), "UTF-8");
        assertNotNull(parser);
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.parser.HtmlAttributesParser#getModel()}.
     */
    @Test
    public void testGetModel() {
		Assert.assertNotNull(parser.getModel());
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.parser.HtmlAttributesParser#handleSimpleTag(javax.swing.text.html.HTML.Tag, javax.swing.text.MutableAttributeSet, int)}
     * .
     */
    @Test
    public void testHandleSimpleTagTagMutableAttributeSetInt() {
        fail("Not yet implemented");
    }

}
