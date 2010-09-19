/**
 * ParseHtml 27.6.2008
 */
package htmlminer.core.attributes.parser;

import htmlminer.core.attributes.model.HtmlModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;


/**
 * Basic HTML parser.
 * 
 * @author Ondrej Kvasnovsky
 */
public abstract class AbstractParser<T> extends HTMLEditorKit.ParserCallback {

   /**
    * charset
    */
   private final String charset;
   /**
    * url
    */
   private final URL url;

   /**
    * 
    * @param url
    * @param charset
    */
   public AbstractParser(final URL url, final String charset) {
      this.url = url;
      this.charset = charset;
   }

   /**
    * 
    */
   public abstract HtmlModel<T> getModel();

   /**
    * 
    * @param htmlText
    * @throws IOException vytvori novy ParserDelegator a vola metodu pro parsovani
    */
   public void parse(final Reader htmlText) throws IOException {
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
