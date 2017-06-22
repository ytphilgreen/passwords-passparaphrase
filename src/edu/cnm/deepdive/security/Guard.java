/**
 * Guard.java 
 */
package edu.cnm.deepdive.security;

import java.util.Arrays;

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
  CommandLine cmdLine = getOptions(args);
  String artifact = generateArtifact(cmdLine);
  emitArtifact(artifact);
  }
  static CommandLine getOptions(String[] args){
    try {
      Option lengthOption = Option.builder("k").argName("length")
                                               .hasArg()
                                               .longOpt("length")
                                               .numberOfArgs(1)
                                               .type(Integer.class)
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
  Options opts =new Options().addOption(lengthOption)
                             .addOption(delimiterOption)
                             .addOption(wordListOption);

  DefaultParser parser = new DefaultParser();
  CommandLine cmdLine = parser.parse(opts, args);
  Object test = cmdLine.getParsedOptionValue("k");   // FIXME debug statement
  return cmdLine;
    } catch (ParseException ex) {
      return null; //TODO Handle this exception with a usage display. 
    }
        
  }

  static String generateArtifact(CommandLine cmdLine) {
    return null; // FIXME
    
  }
  static void emitArtifact(String artifact) {
    
  }
}
