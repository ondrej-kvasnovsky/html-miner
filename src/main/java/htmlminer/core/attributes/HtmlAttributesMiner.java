/**
 * HtmlData 27.6.2008
 */
package htmlminer.core.attributes;

import htmlminer.core.ItemNotFoundException;
import htmlminer.core.attributes.model.HtmlModel;
import htmlminer.core.attributes.parser.AbstractParser;
import htmlminer.core.attributes.parser.HtmlAttributesParser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides operations on fetched attributes. Attributes are fetched from HTML page.
 * <p>
 * Assume there is the following HTML page: </br> <code>
 * &lthtml></br>
 * &lthead></br>
 * &lttitle>Title&lttitle></br>
 * &lthead></br>
 * &ltbody background="background.jpg"><br/>
 * &ltp>&lta href="http://www.qiiip.org/htmlminer/public/images/linkimage.png">&lt/a></br>
 * &ltp></br>
 * &ltbody></br>
 * &lthtml></br>
 * </code></br> The follwing code will search link to image
 * (http://www.qiiip.org/htmlminer/public/images/linkimage.png).<br/>
 * <code>
 * HtmlAttributesMiner attibutesCore = new HtmlAttributesMiner(new URL("http://www.qiiip.org/htmlminer/"));</br>
 * String foundString = HtmlAttributesMiner.findString("http://.+\\." + png + ".*");
 * </code>
 * </p>
 * 
 * @author Ondrej Kvasnovsky
 */
public final class HtmlAttributesMiner {

   /**
    * htmlParser
    */
   private final AbstractParser abstractParser;
   /**
    * htmlModel
    */
   private HtmlModel<List<String>> htmlModel;

   /**
    * Constructor. Default encoding is UTF-8.
    * 
    * @param urlAddress URL address
    * @throws MalformedURLException when address is not valid
    */
   public HtmlAttributesMiner(final String urlAddress) throws MalformedURLException {
      if (urlAddress == null) {
         throw new IllegalArgumentException("URL address can't be null.");
      }
      final String charset = "UTF-8";
      this.abstractParser = new HtmlAttributesParser(new URL(urlAddress), charset);
   }

   /**
    * Constructor. Default encoding is UTF-8.
    * 
    * @param urlAddress url address
    */
   public HtmlAttributesMiner(final URL urlAddress) {
      if (urlAddress == null) {
         throw new IllegalArgumentException("URL address can't be null.");
      }
      final String charset = "UTF-8";
      this.abstractParser = new HtmlAttributesParser(urlAddress, charset);
   }

   /**
    * Constructor.
    * 
    * @param urlAddress
    * @param charset
    */
   public HtmlAttributesMiner(final URL urlAddress, final String charset) {
      if (urlAddress == null) {
         throw new IllegalArgumentException("URL address can't be null.");
      }
      if (charset == null) {
         throw new IllegalArgumentException("Charset can't be null.");
      }
      this.abstractParser = new HtmlAttributesParser(urlAddress, charset);
   }

   /**
    * Find a string according to regular expression.
    * 
    * @param regex regular expression
    * @return found string
    * @throws ItemNotFoundException is thrown when item is not found
    * @throws IOException can be thrown when HTML page is fetching
    */
   public String findString(final String regex) throws ItemNotFoundException, IOException {
      if (regex == null || "".equals(regex)) {
         throw new IllegalArgumentException("Regex can't be empty.");
      }
      String ret = null;
      if (htmlModel == null) {
         this.abstractParser.start();
         htmlModel = this.abstractParser.getModel();
      }
      List<String> list = htmlModel.getData();
      for (String string : list) {
         if (string.matches(regex)) {
            ret = string;
            break;
         }
      }
      if (ret == null) {
         throw new ItemNotFoundException();
      }
      return ret;
   }

   /**
    * Find all strings according to regular expression.
    * 
    * @param regex regular expression
    * @return collection of found strings
    * @throws ItemNotFoundException is thrown when item is not found
    * @throws IOException can be thrown when HTML page is fetching
    */
   public List<String> findStrings(final String regex) throws ItemNotFoundException, IOException {
      if (regex == null || "".equals(regex)) {
         throw new IllegalArgumentException("Regex can't be empty.");
      }
      List<String> ret = new ArrayList<String>();
      if (htmlModel == null) {
         this.abstractParser.start();
         htmlModel = this.abstractParser.getModel();
      }
      List<String> list = htmlModel.getData();
      for (String string : list) {
         if (string.matches(regex)) {
            ret.add(string);
         }
      }
      if (ret.isEmpty()) {
         throw new ItemNotFoundException();
      }
      return ret;
   }

   /**
    * Fetch item according to regular expression and and item will be on given position.
    * 
    * @param regex regular expression
    * @param position start from number zero. If you want to fetch fist item, you have to pass to this method number 0.
    * @return a found item (represented by String)
    * @throws ItemNotFoundException is thrown when item is not found
    * @throws IOException can be thrown when HTML page is fetching
    */
   public String findString(final String regex, final int position) throws ItemNotFoundException, IOException {
      if (regex == null || "".equals(regex)) {
         throw new IllegalArgumentException("Regex can't be empty.");
      }
      if (position < 0) {
         throw new IllegalArgumentException("Position can't be less then 0.");
      }
      String ret = null;
      if (htmlModel == null) {
         this.abstractParser.start();
         htmlModel = this.abstractParser.getModel();
      }
      final List<String> list = htmlModel.getData();
      int index = 0;
      for (String string : list) {
         if (string.matches(regex)) {
            if (position == index) {
               ret = string;
               break;
            }
            index++;
         }
      }
      if (ret == null) {
         throw new ItemNotFoundException();
      }
      return ret;
   }

   /**
    * Find a file in all attributes of tags contained in HTML page according to given inputs.
    * <p>
    * Methods follow the following pattern: [protocol]://[a text].[extension]. For example http://yourpage.com/image.jpg
    * </p>
    * 
    * @param protocol e.g. "http"
    * @param fileExtension e.g. "jpg"
    * @return a found file
    * @throws IOException can be thrown when HTML page is fetching
    * @throws ItemNotFoundException when item is not found
    */
   public File findFile(final String protocol, final String fileExtension) throws ItemNotFoundException, IOException {
      // try to find string
      final String foundPath = findString(protocol + "://.+\\." + fileExtension + ".*");
      // when is found, create new file
      final File ret = new File(foundPath);
      return ret;
   }

   /**
    * Find a files in all attributes of tags contained in HTML page according to given inputs.
    * <p>
    * Methods follow the following pattern: [protocol]://[a text].[extension]. For example http://yourpage.com/image.jpg
    * </p>
    * 
    * @param protocol e.g. "http"
    * @param fileExtension e.g. "jpg"
    * @return a found file
    * @throws IOException can be thrown when HTML page is fetching
    * @throws ItemNotFoundException when item is not found
    */
   public List<File> findFiles(final String protocol, final String fileExtension) throws ItemNotFoundException,
         IOException {
      // try to find strings
      final List<String> foundPaths = findStrings(protocol + "://.+\\." + fileExtension + ".*");
      // when are found, create new files
      final List<File> ret = new ArrayList<File>(foundPaths.size());
      for (String path : foundPaths) {
         ret.add(new File(path));
      }
      return ret;
   }

   /**
    * Find a files in all tags contained in HTML page according to given inputs.
    * <p>
    * Methods follow the following pattern: [protocol]://[a text].[extension]. For example http://yourpage.com/image.jpg
    * </p>
    * 
    * @param protocol e.g. "http"
    * @param fileExtension e.g. "jpg"
    * @param position start from number zero. If you want to fetch fist item, you have to pass to this method number 0.
    * @return a found file
    * @throws IOException can be thrown when HTML page is fetching
    * @throws ItemNotFoundException when item is not found
    */
   public File findFile(final String protocol, final String fileExtension, final int position)
         throws ItemNotFoundException, IOException {
      // try to find strings
      final String foundPath = findString(protocol + "://.+\\." + fileExtension + ".*", position);
      // when are found, create new files
      final File ret = new File(foundPath);
      return ret;
   }
}
