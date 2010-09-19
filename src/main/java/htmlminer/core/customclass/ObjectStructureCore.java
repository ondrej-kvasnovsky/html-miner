/**
 * ObjectStructureCore 21.7.2008
 */
package htmlminer.core.customclass;

import htmlminer.core.customclass.dummy.MyDataFromPage;
import htmlminer.core.customclass.mapping.CustomClassMapper;
import htmlminer.core.customclass.mapping.CustomClassMapperManager;
import htmlminer.core.customclass.mapping.Entry;
import htmlminer.core.customclass.mapping.EntrySet;
import htmlminer.core.customclass.mapping.SearchCriteria;
import htmlminer.core.customclass.parser.AbstractParser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Fetchs only data that you have declared in XML (or mapping class) configuration file. In XML configuration you must
 * declare what data are fetched to. The miner will fetch that data and parse that fethed data to user's object.
 * 
 * @author Ondrej Kvasnovsky
 */
public class ObjectStructureCore {

   /**
    * mapping
    */
   private final CustomClassMapper mapping;

   /**
    * @param mapping
    */
   public ObjectStructureCore(final CustomClassMapper mapping) {
      this.mapping = mapping;
   }

   private void compile(final URL url, final String charset) throws IOException, IllegalArgumentException, SecurityException, InstantiationException,
         IllegalAccessException, InvocationTargetException, NoSuchMethodException {
      Iterator<EntrySet> entrySetIterator = this.mapping.iteratorEntrySet();
      // go though entry set and run parsers
      while (entrySetIterator.hasNext()) {
         EntrySet entrySet = entrySetIterator.next();
         // get search criteria
         List<Entry> entryIterator = entrySet.iterator();
         // fetch and get results of searching from web page
         final Constructor constructor = entrySet.getParser().getConstructor(URL.class, String.class, List.class);
         final AbstractParser abstractParser = (AbstractParser) constructor.newInstance(url, charset, entryIterator);
         abstractParser.start();
      }
   }

   public Object getObject(final URL url, final String charset) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException,
         IOException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
      // get results
      compile(url, charset);
      // 
      Class usersClass = this.mapping.getUserClass();
      // create new instance of user's class
      Object instance = usersClass.newInstance();
      Iterator<EntrySet> entrySetIterator = this.mapping.iteratorEntrySet();
      // go though entry set and run parsers
      while (entrySetIterator.hasNext()) {
         EntrySet entrySet = entrySetIterator.next();
         // get search criteria
         List<Entry> entryIterator = entrySet.iterator();
         for (Entry entry : entryIterator) {
            // get search criteria
            final String field = entry.getProperty();
            // parse name and make first letter upper case
            final String firstLetter = (field.substring(0, 1).toUpperCase());
            final String anotherLetters = (field.substring(1, field.length()));
            // get setter method
            Method method = usersClass.getMethod("set" + (firstLetter + anotherLetters), String.class);
            method.setAccessible(true);
            // call setter
            method.invoke(instance, entry.getResult());
         }
      }
      return instance;
   }

   public static void main(String... args) throws IOException, InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException,
         IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
      CustomClassMapperManager customClassMapperManager = new CustomClassMapperManager(new File("test/qiiip/htmlminer/drivers/objstruct/TestConfig.xml"));
      CustomClassMapper customClassMapper = customClassMapperManager.loadConfig();
      ObjectStructureCore core = new ObjectStructureCore(customClassMapper);
      MyDataFromPage object = (MyDataFromPage) core.getObject(new URL("http://qiiip.org/"), "UTF-8");
      System.out.println("Title: " + object.getTitle());
      System.out.println("BgtextDiv: " + object.getBgtextDiv());
   }
}
