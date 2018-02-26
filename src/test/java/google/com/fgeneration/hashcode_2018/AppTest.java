/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.fgeneration.hashcode_2018;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test
 */
public class AppTest {
  private static String fieldString;
  private static int fieldInt;

  @BeforeClass
  public static void bringUp() {
    fieldString = "string";
  }
  
  @Before
  public void eachTest() {
	fieldInt = 3;
  }

  @Test
  public void testScratch() {
    Assert.assertTrue(fieldString.length() == 6);
    Assert.assertEquals(fieldString, "string");
    Assert.assertNotNull(fieldInt);
    Assert.assertEquals(fieldInt, 3);
  }
  
  @Test
  public void testStringList() {
	List<String> actual = Arrays.asList("a", "b", "c");
    List<String> expected = Arrays.asList("a", "b", "c");
	
    Assert.assertEquals(expected, actual);
    Assert.assertTrue(actual.contains(new String("a")));
    Assert.assertEquals(new String("a"),actual.get(0));
    Assert.assertNotEquals('a',actual.get(0));
    Assert.assertFalse(actual.isEmpty());
  }
}
