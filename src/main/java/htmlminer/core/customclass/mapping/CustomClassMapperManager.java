/**
 * ConfigManager 21.7.2008
 */
package htmlminer.core.customclass.mapping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Config manager - loads and saves configuration class (Config)
 * 
 * @author Ondrej Kvasnovsky
 */
public final class CustomClassMapperManager {

   /**
    * config file
    */
   private final File configFile;
   /**
    * xStream
    */
   private XStream xStream = new XStream(new DomDriver());

   /**
    * Constructor.
    * 
    * @param configFile configuration file
    */
   public CustomClassMapperManager(final File configFile) {
      this.configFile = configFile;
      // set XStream aliases - classes
      xStream.alias("user-class-mapping", CustomClassMapper.class);
      xStream.alias("entry-set", EntrySet.class);
      xStream.alias("entry", Entry.class);
      xStream.alias("attribute", Attribute.class);
      // set XStream aliases - attributes
      xStream.aliasAttribute(CustomClassMapper.class, "userClass", "name");
      xStream.aliasAttribute(Entry.class, "searchCriteria", "search-criteria");
      xStream.aliasField("html-path", SearchCriteria.class, "htmlPath");
      // usage of attributes for classes
      xStream.useAttributeFor(CustomClassMapper.class, "userClass");
      xStream.useAttributeFor(EntrySet.class, "parser");
      // set XStream collections
      xStream.addImplicitCollection(CustomClassMapper.class, "entrySets");
   }

   public CustomClassMapper loadConfig() throws IOException {
      final FileInputStream fileInputStream = new FileInputStream(this.configFile);
      CustomClassMapper ret = (CustomClassMapper) xStream.fromXML(fileInputStream);
      return ret;
   }

   public void saveConfig(final CustomClassMapper customClassMapper) throws IOException {
      String xml = xStream.toXML(customClassMapper);
      FileWriter fileWriter = new FileWriter(this.configFile);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(xml);
      bufferedWriter.close();
   }
}
