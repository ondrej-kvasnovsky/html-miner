/**
 * CustomClassMiner 21.7.2008
 */
package htmlminer.core.customclass;

import htmlminer.core.customclass.dummy.MyDataFromPage;
import htmlminer.core.customclass.mapping.CustomClassMapping;
import htmlminer.core.customclass.mapping.CustomClassMapperManager;
import htmlminer.core.customclass.mapping.Entry;
import htmlminer.core.customclass.mapping.EntrySet;
import htmlminer.core.customclass.parser.AbstractParser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 * Fetches only data that are declared in XML (or mapping class) configuration file. In XML configuration you must
 * declare what data are fetched to. The miner will fetch that data and parse that fetched data to user's object.
 * 
 * @author Ondrej Kvasnovsky
 */
public class CustomClassMiner {

   /**
    * mapping of custom class
    */
   private final CustomClassMapping mapping;

   /**
    * Creates new miner.
    * 
    * @param mapping of custom class
    */
   public CustomClassMiner(final CustomClassMapping mapping) {
      this.mapping = mapping;
   }

   /**
    * @param url
    * @param charset
    * @throws IOException
    * @throws IllegalArgumentException
    * @throws SecurityException
    * @throws InstantiationException
    * @throws IllegalAccessException
    * @throws InvocationTargetException
    * @throws NoSuchMethodException
    */
   private void compile(final URL url, final String charset) throws IOException, SecurityException,
         NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException,
         InvocationTargetException {
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

   public Object getObject(final URL url, final String charset) throws CustomClassProcessingException {
      // get results
      try {
         compile(url, charset);
      } catch (SecurityException e) {
         throw new CustomClassProcessingException(e);
      } catch (IllegalArgumentException e) {
         throw new CustomClassProcessingException(e);
      } catch (NoSuchMethodException e) {
         throw new CustomClassProcessingException(e);
      } catch (InstantiationException e) {
         throw new CustomClassProcessingException(e);
      } catch (IllegalAccessException e) {
         throw new CustomClassProcessingException(e);
      } catch (InvocationTargetException e) {
         throw new CustomClassProcessingException(e);
      } catch (IOException e) {
         throw new CustomClassProcessingException(e);
      }
      // create new instance of user's class
      Object instance = null;
      try {
         // get custom class
         Class usersClass = this.mapping.getUserClass();
         instance = usersClass.newInstance();
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
      } catch (InstantiationException e) {
         throw new CustomClassProcessingException(e);
      } catch (IllegalAccessException e) {
         throw new CustomClassProcessingException(e);
      } catch (SecurityException e) {
         throw new CustomClassProcessingException(e);
      } catch (NoSuchMethodException e) {
         throw new CustomClassProcessingException(e);
      } catch (IllegalArgumentException e) {
         throw new CustomClassProcessingException(e);
      } catch (InvocationTargetException e) {
         throw new CustomClassProcessingException(e);
      }
      return instance;
   }
}
