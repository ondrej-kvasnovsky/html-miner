/**
 * HtmlCompoundMiner 29.6.2008
 */
package htmlminer.drivers.compound;

import htmlminer.core.ItemNotFoundException;
import htmlminer.core.attributes.parser.AbstractParser;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * 
 * @author Ondrej Kvasnovsky
 */
public class HtmlCompoundDriver {

   /**
    * htmlParser
    */
   private final AbstractParser abstractParser;

   /**
    * Constructor.
    * 
    * @param abstractParser HTML parser
    */
   public HtmlCompoundDriver(final AbstractParser abstractParser) {
      if (abstractParser == null) {
         throw new IllegalArgumentException("HtmlParser can't be null.");
      }
      this.abstractParser = abstractParser;
   }

   /*
    * (non-Javadoc)
    * 
    * @see htmlminer.drivers.Driver#findFile(java.lang.String, java.lang.String)
    */
   public File findFile(String protocol, String fileExtension) throws ItemNotFoundException, IOException {
      // TODO Auto-generated method stub
      return null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see htmlminer.drivers.Driver#findFile(java.lang.String, java.lang.String, int)
    */
   public File findFile(String protocol, String fileExtension, int position) throws ItemNotFoundException, IOException {
      // TODO Auto-generated method stub
      return null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see htmlminer.drivers.Driver#findFiles(java.lang.String, java.lang.String)
    */
   public List<File> findFiles(String protocol, String fileExtension) throws ItemNotFoundException, IOException {
      // TODO Auto-generated method stub
      return null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see htmlminer.drivers.Driver#findString(java.lang.String)
    */
   public String findString(String regex) throws ItemNotFoundException, IOException {
      // TODO Auto-generated method stub
      return null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see htmlminer.drivers.Driver#findString(java.lang.String, int)
    */
   public String findString(String regex, int position) throws ItemNotFoundException, IOException {
      // TODO Auto-generated method stub
      return null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see htmlminer.drivers.Driver#findStrings(java.lang.String)
    */
   public List<String> findStrings(String regex) throws ItemNotFoundException, IOException {
      // TODO Auto-generated method stub
      return null;
   }
}
