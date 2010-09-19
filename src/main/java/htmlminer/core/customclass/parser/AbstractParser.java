/**
 * XParser 21.7.2008
 */
package htmlminer.core.customclass.parser;

import htmlminer.core.customclass.mapping.Entry;
import htmlminer.core.customclass.mapping.SearchCriteria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.parser.ParserDelegator;


/**
 * 
 * @author Ondrej Kvasnovsky
 */
public abstract class AbstractParser extends HTMLEditorKit.ParserCallback {

   /**
    * charset
    */
   private final String charset;
   /**
    * url
    */
   private final URL url;
   /**
    * searchCriterias
    */
   protected List<Entry> entries;
   /**
    * path - more like temp (only to store html path, like "html body table")
    */
   protected StringBuilder path = new StringBuilder();

   /**
    * 
    * @param url
    * @param charset
    */
   public AbstractParser(final URL url, final String charset, List<Entry> searchCriterias) {
      this.url = url;
      this.charset = charset;
      this.entries = searchCriterias;
   }

   /**
    * 
    * @param htmlText
    * @throws IOException
    */
   private void parse(final Reader htmlText) throws IOException {
      ParserDelegator delegator = new ParserDelegator();
      delegator.parse(htmlText, this, true);
   }

   /**
    * Starts parsing process.
    * 
    * @throws IOException
    */
   public void start() throws IOException {
      BufferedReader htmlPage = new BufferedReader(new InputStreamReader(this.url.openStream(), this.charset));
      this.parse(htmlPage);
   }
}
