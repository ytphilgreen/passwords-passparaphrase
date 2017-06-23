/**
 * Guard.java 
 */
package edu.cnm.deepdive.security;

import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * A program that generates random passwords and passphrases.
 * 
 * Generation uses a cryptographically secure random number generator
 * to select words from a list or characters from an pool.
 * 
 * @author Yolanda Philgreen
 *
 */
public class Guard {

  /**
   * Parse command line arguments to a parser from the Apache Commons CLI library,
   * then instantiate and invoke the appropriate classes and methods to 
   * generate the requested artifact. 
   * 
   * @param args   Command line arguments, specifying generation options. 
   */
  public static void main(String[] args) {
  HashMap<String, Object> map = getOptions(args);
  String artifact = generateArtifact(map);
  emitArtifact(artifact);
  }
  static HashMap<String, Object> getOptions(String[] args){
    try {
      Option lengthOption = Option.builder("L").argName("length")
                                               .hasArg()
                                               .longOpt("length")
                                               .numberOfArgs(1)
                                               .type(Number.class)
                                               .build();
  Option delimiterOption = Option.builder("d").argName("delimiter")
                                              .hasArg()
                                              .longOpt("delimiter")
                                              .numberOfArgs(1)
                                              .optionalArg(true)
                                              .type(String.class)
                                              .build();
  Option wordListOption = Option.builder("w").argName("path-to-list-file")
                                             .hasArg()
                                             .longOpt("word-list")
                                             .numberOfArgs(1)
                                             .type(String.class)
                                             .build();
  Option modeOption = Option.builder("m").longOpt("password-mode")
                                         .hasArg(false)
                                         .build();
                             
Option excludeUpperOption = Option.builder("b").longOpt("exclude-upper")
                                               .hasArg(false)
                                               .build();
Option excludeLowerOption = Option.builder("s").longOpt("exclude-lower")
                                              .hasArg(false)
                                              .build();
Option excludeDigitsOption = Option.builder("n").longOpt("exclude-digits")
                                               .hasArg(false)
                                               .build();
Option excludePunctuationOption = Option.builder("p").longOpt("exclude-punctuation")
                                                     .hasArg(false)
                                                     .build();
Option includeAmbiguousOption = Option.builder("a").longOpt("include-ambiguous")
                                                   .hasArg(false)
                                                   .build();

Options opts =new Options().addOption(lengthOption)
.addOption(delimiterOption)
.addOption(wordListOption)
.addOption(modeOption)
.addOption(excludeUpperOption)
.addOption(excludeLowerOption)
.addOption(excludeDigitsOption)
.addOption(excludePunctuationOption)
.addOption(includeAmbiguousOption);

  DefaultParser parser = new DefaultParser();
  HashMap <String, Object>map = new HashMap<>();
  CommandLine cmdLine = parser.parse(opts, args);
  for (Option option :cmdLine.getOptions()) {
    String opt = option.getOpt();
    map.put(opt, cmdLine.getParsedOptionValue(opt));
    }
  return map;
    } catch (ParseException ex) {
      return null; //TODO Handle this exception with a usage display. 
    }
        
  }

  static String generateArtifact(HashMap<String, Object> map) {
    if(map.containsKey("m")) {
      PasswordGenerator gen = new SecurePasswordGenerator();
      //TODO Set fields for all specified options. 
      return gen.generate();
    }
    return null; 
    
  }
  static void emitArtifact(String artifact) {
    System.out.println(artifact);
    
  }
}
