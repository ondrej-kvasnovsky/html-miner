/**
 * CustomClassMinerTest.java 19.9.2010
 */
package htmlminer.core.customclass;

import static org.junit.Assert.*;

import htmlminer.core.customclass.criteria.SearchCriteria;
import htmlminer.core.customclass.mapping.Attribute;
import htmlminer.core.customclass.mapping.CustomClassMapping;
import htmlminer.core.customclass.mapping.Entry;
import htmlminer.core.customclass.mapping.EntrySet;

import org.junit.Test;

/**
 * Test for CustomClassMiner.
 * 
 * @author Ondrej Kvasnovsky
 */
public class CustomClassMinerTest {

    /**
     * Test method for
     * {@link htmlminer.core.customclass.CustomClassMiner#CustomClassMiner(htmlminer.core.customclass.mapping.CustomClassMapping)}
     * .
     * 
     * @throws CustomClassProcessingException
     */
    @Test
    public final void testCustomClassMiner() throws CustomClassProcessingException {
        // create mapping class
        CustomClassMapping mapping = new CustomClassMapping();
        // setup Employee class as the custom class
        mapping.setUserClass(Employee.class);
        // fill the mapping with mapping which is needed
        EntrySet lastNameEntrySet = new EntrySet(htmlminer.core.customclass.parser.TextParser.class);
        // create new entry, entry is exact mapping to the field in the custom
        // class
        // search criteria says where to get and data and how to find them
        // (regex)
        final Entry entry = new Entry("lastName", new SearchCriteria("html body div", ".*"));
        // finalize the setup
        lastNameEntrySet.add(entry);
        mapping.addEntrySet(lastNameEntrySet);
        // create the miner
        CustomClassMiner miner = new CustomClassMiner(mapping);
        assertNotNull(miner);
        // and mine the data!
        final Employee employee = (Employee) miner.getObject(getClass().getResource("employee.html"), "UTF-8");
        assertEquals("Kvasnovsky", employee.getLastName());
    }

    /**
     * Test method for
     * {@link htmlminer.core.customclass.CustomClassMiner#getObject(java.net.URL, java.lang.String)}
     * .
     * 
     * @throws CustomClassProcessingException
     */
    @Test
    public final void testGetObject() throws CustomClassProcessingException {
        // create mapping class
        CustomClassMapping mapping = new CustomClassMapping();
        // setup Employee class as the custom class
        mapping.setUserClass(Employee.class);
        // fill the mapping with mapping which is needed
        EntrySet lastNameEntrySet = new EntrySet(htmlminer.core.customclass.parser.TextParser.class);
        // create new entry, entry is exact mapping to the field in the custom
        // class
        // search criteria says where to get and data and how to find them
        // (regex)
        SearchCriteria searchCriteria = new SearchCriteria("html body div", ".*");
        Attribute attribute = new Attribute("id", ".*");
        searchCriteria.add(attribute);
        final Entry entry = new Entry("lastName", searchCriteria);
        // finalize the setup
        lastNameEntrySet.add(entry);
        mapping.addEntrySet(lastNameEntrySet);
        // create the miner
        CustomClassMiner miner = new CustomClassMiner(mapping);
        assertNotNull(miner);
        // and mine the data!
        final Employee employee = (Employee) miner.getObject(getClass().getResource("employee.html"), "UTF-8");
        assertEquals("Kvasnovsky", employee.getLastName());
    }
}
