/**
 * XParser 21.7.2008
 */
package htmlminer.core.customclass.parser;

import htmlminer.core.customclass.mapping.Entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * Abstract parser providing basics for parser implementations.
 * 
 * @author Ondrej Kvasnovsky
 */
public abstract class AbstractParser extends HTMLEditorKit.ParserCallback {

   /**
    * searchCriterias
    */
   protected List<Entry> entries;
   /**
    * charset
    */
   private final String charset;
   /**
    * path - more like temp (only to store html path, like "html body table")
    */
   protected StringBuilder path = new StringBuilder();
   /**
    * url
    */
   private final URL url;

   /**
    * Creates new parser.
    * 
    * @param url URL address
    * @param charset charset
    */
   public AbstractParser(final URL url, final String charset, List<Entry> searchCriterias) {
      this.url = url;
      this.charset = charset;
      this.entries = searchCriterias;
   }

   /**
    * Parses HTML text from {@link java.io.Reader}
    * 
    * @param htmlText HTML text
    * @throws IOException when parsing the HTML text
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
