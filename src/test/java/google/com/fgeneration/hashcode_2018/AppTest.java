/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.fgeneration.hashcode_2018;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test
 */
public class AppTest {
  private static String fieldString;

  @BeforeClass
  public static void bringUp() {
    fieldString = "string";

  }

  @Test
  public void testScratch() {
    Assert.assertTrue(fieldString.length() == 6);
    Assert.assertEquals(fieldString, "string");
  }
}
