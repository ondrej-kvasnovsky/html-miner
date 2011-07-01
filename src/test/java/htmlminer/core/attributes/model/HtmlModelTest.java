/**
 * HtmlModelTest.java, 1.7.2011 10:00:00 
 */
package htmlminer.core.attributes.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author kvasnond
 */
public class HtmlModelTest {

    private HtmlModel<String> htmlModel;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.htmlModel = new HtmlModel<String>("test data");
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.model.HtmlModel#HtmlModel(java.lang.Object)}
     * .
     */
    @Test
    public void testHtmlModel() {
        assertNotNull(new HtmlModel<String>("test data"));
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.model.HtmlModel#HtmlModel(java.lang.Object)}
     * .
     */
    @Test
    public void testHtmlModelNull() {
        assertNotNull(new HtmlModel<String>(null));
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.model.HtmlModel#getData()}.
     */
    @Test
    public void testGetData() {
        assertEquals("test data", this.htmlModel.getData());
    }

    /**
     * Test method for
     * {@link htmlminer.core.attributes.model.HtmlModel#setData(java.lang.Object)}
     * .
     */
    @Test
    public void testSetData() {
        this.htmlModel.setData("other test data");
        assertEquals("other test data", this.htmlModel.getData());
    }

}
