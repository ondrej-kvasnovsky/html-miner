/**
 * AttributesNameValueParser.java, 14.6.2011 10:41:41 
 */
package htmlminer.core.attributes.parser;

import htmlminer.core.attributes.model.HtmlModel;
import htmlminer.core.attributes.parser.AttributeComplexParser.AttributeEntry;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.html.HTML.Tag;

/**
 * @author Ondrej Kvasnovsky
 */
public class AttributeComplexParser extends AbstractParser<List<AttributeEntry>> {

    /**
     * Represents one attribute entry. {@link AttributeEntry} contans path to an
     * attribute, its name and value. E.g.
     * "html head meta, content, text/html; charset=UTF-8".
     * 
     * @author Ondrej Kvasnovsky
     */
    public static class AttributeEntry {
        /**
         * name of an attribute
         */
        private String name;

        /**
         * path to an attribute, e.g. "html head meta"
         */
        private String path;

        /**
         * value contained in the attribute
         */
        private String value;

        /**
         * Creates new instance.
         * 
         * @param path
         *            path to an attribute
         * @param name
         *            name of an attribute
         * @param value
         */
        public AttributeEntry(final String path, final String name, final String value) {
            super();
            this.path = path;
            this.name = name;
            this.value = value;
        }

        /**
         * Returns the name.
         * 
         * @return the name
         */
        public String getName() {
            return this.name;
        }

        /**
         * Returns the path.
         * 
         * @return the path
         */
        public String getPath() {
            return this.path;
        }

        /**
         * Returns the value.
         * 
         * @return the value
         */
        public String getValue() {
            return this.value;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return this.path + ", " + this.name + ", " + this.value;
        }
    }

    /**
     * model <path, [attribute name, attribute value]
     */
    private HtmlModel<List<AttributeEntry>> model = new HtmlModel<List<AttributeEntry>>(new ArrayList<AttributeEntry>());

    /**
     * path - more like temp (only to store html path, like "html body table")
     */
    protected StringBuilder path = new StringBuilder();

    /**
     * Creates new instance.
     * 
     * @param url
     *            location of the file
     * @param charset
     *            charset that will be used
     */
    public AttributeComplexParser(URL url, String charset) {
        super(url, charset);
    }

    /**
     * {@inheritDoc}
     * 
     * @see htmlminer.core.attributes.parser.AbstractParser#getModel()
     */
    @Override
    public HtmlModel<List<AttributeEntry>> getModel() {
        return this.model;
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleEndTag(javax
     *      .swing.text.html.HTML.Tag, int)
     */
    @Override
    public void handleEndTag(final Tag t, final int pos) {
        this.removeLastTag(t);
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.swing.text.html.HTMLEditorKit$ParserCallback#handleSimpleTag(javax
     *      .swing.text.html.HTML.Tag, javax.swing.text.MutableAttributeSet,
     *      int)
     */
    @Override
    public void handleSimpleTag(final HTML.Tag tag, final MutableAttributeSet atr, final int pos) {
        this.processTag(tag, atr);
        this.removeLastTag(tag);
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.swing.text.html.HTMLEditorKit.ParserCallback#handleStartTag(javax
     *      .swing.text.html.HTML.Tag, javax.swing.text.MutableAttributeSet,
     *      int)
     */
    @Override
    public void handleStartTag(final Tag tag, final MutableAttributeSet atr, final int pos) {
        this.processTag(tag, atr);
    }

    /**
     * Processes a tag.
     * 
     * @param tag
     *            tag
     * @param atr
     *            attributes
     */
    private void processTag(final HTML.Tag tag, final MutableAttributeSet atr) {
        // collect received data
        this.path.append(tag.toString());
        this.path.append(" ");
        final Enumeration<?> attributeNames = atr.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final Object objElement = attributeNames.nextElement();
            if (objElement instanceof Attribute) {
                final Attribute nextElement = (Attribute) objElement;
                String name = nextElement.toString();
                final String value = (String) atr.getAttribute(nextElement);
                this.model.getData().add(new AttributeEntry(this.path.toString().trim(), name, value));
            } else {
                // TODO: what else can come here?
                // model.getData().add(objElement.toString());
            }
        }
    }

    /**
     * Removes the last tag (like in a queue).
     * 
     * @param t
     *            tag
     */
    private void removeLastTag(final Tag t) {
        final int pathLength = this.path.toString().length();
        final int tagLength = t.toString().length();
        this.path.delete(pathLength - tagLength - 1, pathLength);
    }
}
