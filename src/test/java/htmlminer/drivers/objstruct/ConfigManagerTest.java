/**
 * ConfigManagerTest 21.7.2008
 */
package htmlminer.drivers.objstruct;

import static org.junit.Assert.*;

import htmlminer.core.customclass.dummy.MyDataFromPage;
import htmlminer.core.customclass.mapping.Attribute;
import htmlminer.core.customclass.mapping.CustomClassMapping;
import htmlminer.core.customclass.mapping.CustomClassMapperManager;
import htmlminer.core.customclass.mapping.Entry;
import htmlminer.core.customclass.mapping.EntrySet;
import htmlminer.core.customclass.mapping.SearchCriteria;
import htmlminer.core.customclass.parser.TextParser;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * 
 * @author Ondrej Kvasnovsky
 */
public class ConfigManagerTest {

   /**
    * Test method for {@link htmlminer.core.customclass.mapping.CustomClassMapperManager#ConfigManager(java.io.File)}.
    */
   @Test
   public final void testConfigManager() {
      fail("Not yet implemented"); // TODO
   }

   /**
    * Test method for {@link htmlminer.core.customclass.mapping.CustomClassMapperManager#loadConfig(java.io.File)}.
    * 
    * @throws IOException
    */
   @Test
   public final void testLoadConfig() throws IOException {
      final String file = getClass().getResource("TestConfig.xml").getFile();
      CustomClassMapperManager customClassMapperManager = new CustomClassMapperManager(new File(file));
      CustomClassMapping customClassMapper = customClassMapperManager.loadConfig();
      assertNotNull(customClassMapper);
   }

   /**
    * Test method for
    * {@link htmlminer.core.customclass.mapping.CustomClassMapperManager#saveConfig(htmlminer.core.customclass.mapping.CustomClassMapping, java.io.File)}
    * .
    * 
    * @throws IOException
    * @throws ClassNotFoundException
    * @throws NoSuchFieldException
    * @throws SecurityException
    */
   @Test
   public final void testSaveConfig() throws IOException, ClassNotFoundException, SecurityException,
         NoSuchFieldException {
      final String file = getClass().getResource("TestConfig.xml").getFile();
      CustomClassMapperManager customClassMapperManager = new CustomClassMapperManager(new File(file));
      CustomClassMapping customClassMapper = new CustomClassMapping();
      customClassMapper.setUserClass(MyDataFromPage.class);
      // entry set 1
      EntrySet entrySet1 = new EntrySet(TextParser.class);
      Entry entry1 = new Entry("title", new SearchCriteria("html head title", ".*"));
      entrySet1.add(entry1);
      customClassMapper.addEntrySet(entrySet1);
      // entry set 2
      final SearchCriteria searchCriteria2 = new SearchCriteria("html body div div div a", ".*");
      Attribute attribute21 = new Attribute("href", "/index");
      searchCriteria2.add(attribute21);
      Entry entry2 = new Entry("bgtextDiv", searchCriteria2);
      entrySet1.add(entry2);
      customClassMapperManager.saveConfig(customClassMapper);
      System.out.println(customClassMapper);
   }
}
