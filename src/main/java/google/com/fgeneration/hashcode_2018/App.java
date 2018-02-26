/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.fgeneration.hashcode_2018;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * Hello world!
 *
 * @author <a href="mailto:${developer-email}">${developer-name}</a>
 * @version 1.0-SNAPSHOT
 **/
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(final String[] args) {
    LOGGER.info("Welcome to application!");
    final List<String> newList = Lists.newLinkedList();
    LOGGER.info("This is an empty list '{}'", newList);
    final Pair<String, String> stringPairs = Pair.of("left", "right");
    LOGGER.info("This is a pair of strings: '{}'", stringPairs);
  }

}