/**
 * HtmlCompoundParserTest 29.6.2008
 */
package htmlminer.drivers.compound;

import static org.junit.Assert.*;

import htmlminer.core.attributes.model.HtmlModel;
import htmlminer.drivers.compound.HtmlCompoundParser;
import htmlminer.drivers.compound.HtmlFile;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;


/**
 * HtmlCompoundParserTest
 * 
 * @author Ondrej Kvasnovsky
 */
public class HtmlCompoundParserTest {

   /**
    * Test method for {@link htmlminer.drivers.compound.HtmlCompoundParser#getModel()}.
    * 
    * @throws IOException
    */
   @Test
   public final void testGetModel() throws IOException {
      // HtmlCompoundParser parser = new HtmlCompoundParser(
      // "https://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/ViewFilteredProducts-SingleVariationTypeFilter",
      // "iso-8859-1");
      // parser.start();
      // final HtmlModel<HtmlFile> model = parser.getModel();
      // final HtmlFile data = model.getData();
   }

   /**
    * Test method for {@link htmlminer.core.attributes.parser.AbstractParser#start()}.
    * 
    * @throws IOException
    */
   @Test
   public final void testStart() throws IOException {
      HtmlCompoundParser parser = new HtmlCompoundParser(new URL("http://qiiip.org/"), "iso-8859-1");
      parser.start();
      final HtmlModel<HtmlFile> model = parser.getModel();
      final HtmlFile data = model.getData();
      System.out.println(data);
   }
}
