/**
 * TagsTest 29.6.2008
 */
package htmlminer.drivers.criteria;

import static org.junit.Assert.*;

import htmlminer.drivers.criteria.Tags;

import java.util.Arrays;

import org.junit.Test;


/**
 * 
 * @author Ondrej Kvasnovsky
 */
public class TagsTest {

   /**
    * Test method for {@link htmlminer.drivers.criteria.Tags#parse(java.lang.String)}.
    */
   @Test
   public final void testParse() {
      Tags tags = Tags.parse("div, div, table, tr, td, img");
      assertNotNull(tags);
   }

   /**
    * Test method for {@link htmlminer.drivers.criteria.Tags#parse(java.lang.String)}.
    */
   @Test
   public final void testParseContent() {
      Tags tags = Tags.parse("div, div, table, tr, td, img");
      System.out.println(tags);
   }

   /**
    * Test method for {@link htmlminer.drivers.criteria.Tags#getTags()}.
    */
   @Test
   public final void testGetTags() {
      Tags tags = Tags.parse("div, div, table, tr, td, img");
      assertNotNull(tags.getTags());
   }
}
