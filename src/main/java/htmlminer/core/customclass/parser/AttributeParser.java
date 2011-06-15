/**
 * AttributeParser.java, 14.6.2011 10:20:42 
 */
package htmlminer.core.customclass.parser;

import htmlminer.core.customclass.mapping.Entry;

import java.net.URL;
import java.util.List;

/**
 * @author Ondrej Kvasnovsky
 */
public class AttributeParser extends AbstractParser {

    /**
     * Creates new instance.
     * 
     * @param url
     * @param charset
     * @param searchCriterias
     */
    public AttributeParser(URL url, String charset, List<Entry> searchCriterias) {
        super(url, charset, searchCriterias);
    }

}
