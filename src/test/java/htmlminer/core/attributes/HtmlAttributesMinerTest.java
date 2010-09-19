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
 * @author gerades
 * 
 */
public class HtmlAttributesMinerTest {

   /**
    * Test method for {@link htmlminer.core.attributes.HtmlAttributesMiner#HtmlAttributesCore(java.net.URL)}.
    */
   @Test
   public final void testHtmlAttributesCoreURL() {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for
    * {@link htmlminer.core.attributes.HtmlAttributesMiner#HtmlAttributesCore(java.net.URL, java.lang.String)}.
    */
   @Test
   public final void testHtmlAttributesCoreURLString() {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link htmlminer.core.attributes.HtmlAttributesMiner#findString(java.lang.String)}.
    * 
    * @throws IOException
    * @throws ItemNotFoundException
    */
   @Test
   public final void testFindStringString() throws ItemNotFoundException, IOException {
      HtmlAttributesMiner attibutesCore = new HtmlAttributesMiner(new URL("http://www.oracle.com/index.html"));
      String result = attibutesCore.findString(".+.png");
      System.out.println(result);
   }

   /**
    * Test method for {@link htmlminer.core.attributes.HtmlAttributesMiner#findStrings(java.lang.String)}.
    * @throws IOException 
    * @throws ItemNotFoundException 
    */
   @Test
   public final void testFindStrings() throws ItemNotFoundException, IOException {
      HtmlAttributesMiner attibutesCore = new HtmlAttributesMiner("http://www.oracle.com/index.html");
      List<String> result = attibutesCore.findStrings(".+.png");
      System.out.println(result);
   }

   /**
    * Test method for {@link htmlminer.core.attributes.HtmlAttributesMiner#findString(java.lang.String, int)}.
    */
   @Test
   public final void testFindStringStringInt() {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link htmlminer.core.attributes.HtmlAttributesMiner#findFile(java.lang.String, java.lang.String)}.
    */
   @Test
   public final void testFindFileStringString() {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link htmlminer.core.attributes.HtmlAttributesMiner#findFiles(java.lang.String, java.lang.String)}
    * .
    */
   @Test
   public final void testFindFiles() {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for
    * {@link htmlminer.core.attributes.HtmlAttributesMiner#findFile(java.lang.String, java.lang.String, int)}.
    */
   @Test
   public final void testFindFileStringStringInt() {
      fail("Not yet implemented"); // TODO
   }
}
