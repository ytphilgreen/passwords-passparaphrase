/**
 * 
 */
package edu.cnm.deepdive.security.command;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

/**
 * @author Yolanda Philgreen
 * @version 1.3
 *
 * Use of constants and bundles in this method for the use in giving user end options of the password and the
 * passphrase. The end user will encounter warnings and error messages with implementation.
 */
public class Options {
  
  public static final int MAXIMUM_RECOMMENDED_PASSPHRASE_LENGTH = 10;

  public static final int MINIMUM_RECOMMENDED_PASSPHRASE_LENGTH = 6;

  public static final int MAXIMUM_RECOMMENDED_PASSWORD_LENGTH = 16;

  public static final int MINIMUM_RECOMMENDED_PASSWORD_LENGTH = 8;

  public static final String JAR_FILE_NAME = "gaurd.jar";
  
  private static final String INVALID_DELIMITERS_REGEX = "^.*[<>&|*?^]+.*$";
  
  private static final String OPTIONS_DESCRIPTION_BUNDLE = "resources/options";
  private static final String MESSAGES_BUNDLE = "resources/messages";
  
  private static final String PASSWORD_EXTREME_LENGTH_WARNING = "pp.warning.extremelength.message";
  private static final String PASSPHRASE_EXTREME_LENGTH_WARNING = "pp.warning.extremelength.message";
  private static final String PASSPHRASE_LENGTH_WARNING = "pp.warning.length.message";
  private static final String PASSWORD_LENGTH_WARNING = "pw.warning.length.message";
  
  private static final String AMBIGUOUS_CHARACTER_WARNING = "pw.warning.ambiguous.message";
  private static final String LENGTH_ERROR = "error.length.message";
  private static final String RESERVED_CHARACTER_ERROR = "pp.error.reserved.message";
  
  private static final String PASSWORD_OPTIONS_CONFLICT_WARNING = "pw.opts.conflict.warning.message";
  private static final String PASSPHRASE_OPTIONS_CONFLICT_WARNING = "pp.opts.conflict.warning.message";

  private static final String FATAL_MESSAGE = "not able to load messages bundle";
  private static final String BAD_OPTION_KEY = "pp.error.option.message";
  private static final String BAD_ARGUMENT_KEY = "error.badargument.message";
  private static final String MISSING_ARGUMENT_KEY = "error.missingargument.message";
  private static final String MISSING_OPTIONS_BUNDLE_KEY = "res.error.options.message";
  
  
  private static final String WORD_LIST_ERROR = "pp.error.list.message";
  
  private static final String HELP_OPTION_KEY = "help.option";
  private static final String LENGTH_OPTION_KEY = "length.option";
  private static final String DELIMITER_OPTION_KEY = "delimiter.option";
  private static final String WORDLIST_OPTION_KEY = "word-list.option";
  private static final String MODE_OPTION_KEY = "password-mode.option";
  private static final String UPPER_OPTION_KEY = "exclude-upper.option";
  private static final String LOWER_OPTION_KEY = "exclude-lower.option";
  private static final String DIGITS_OPTION_KEY = "exclude-digits.option";
  private static final String PUNCTUATION_OPTION_KEY = "exclude-punctuation.option";
  private static final String AMBIGUOUS_OPTION_KEY = "include-ambiguous.option";
  private static String usageMessage = String.format("java -jar %s [options]", JAR_FILE_NAME);
  
  /*
   * @exception this try loop catches the missing resource bundle and will give a fatal message if the message
   * bundle from the commons isn't found. this fatal message will return to user.
   */
  static HashMap<String, Object> getOptions(String[] args) {
    org.apache.commons.cli.Options opts = null;
    ResourceBundle messageBundle = null;   
    try {
      messageBundle = ResourceBundle.getBundle(MESSAGES_BUNDLE);
    } catch (MissingResourceException ex) {
      System.out.println(FATAL_MESSAGE);
      return null;
    }
    try {
      opts = buildOptions();

      HashMap<String, Object> map = parseCommandLine(args, opts);
      if (map != null) {
        validateCommandLine (map, messageBundle);       
      }
//      int length = ((Number) map.get("L")).intValue();
//      if (cmdLine.hasOption("m")) {
//        if (length < 5) {
//          
//        }
//      }
      return map;
    } catch (MissingArgumentException ex) {
      Option missing = ex.getOption();
      String optName = missing.getOpt();
       displayError(messageBundle, MISSING_ARGUMENT_KEY, opts, optName);
      return null;
    } catch (UnrecognizedOptionException ex) {
      String optName = ex.getOption();
      displayError(messageBundle, BAD_OPTION_KEY, opts, optName);
      String message = messageBundle.getString(BAD_OPTION_KEY);
      message = String.format(message, optName);
      display (message, usageMessage, opts);
      return null;
    } catch (ParseException ex) {
       displayError(messageBundle, BAD_ARGUMENT_KEY, opts, ex.getLocalizedMessage());
      return null; 
    } catch (MissingResourceException ex) {
      displayError (messageBundle, MISSING_OPTIONS_BUNDLE_KEY, opts, OPTIONS_DESCRIPTION_BUNDLE);
      return null;
    } catch (IllegalArgumentException ex){
      displayError (messageBundle, RESERVED_CHARACTER_ERROR, opts, null);
      return null;
    } catch (NegativeArraySizeException ex) {
      displayError(messageBundle, LENGTH_ERROR, opts, null);
      return null;
    } catch (FileNotFoundException ex) {
      displayError (messageBundle, WORD_LIST_ERROR, opts, ex.getMessage());
      return null;
    }
  }
  
  private static void validateCommandLine(HashMap<String, Object>map, ResourceBundle messageBundle) 
      throws FileNotFoundException, IllegalArgumentException, NegativeArraySizeException {
    if (map.containsKey("m")) {
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        switch (entry.getKey()) {
          case "L" :
            int length = ((Number) entry.getValue()).intValue();
            if (length <= 0) {
              throw new NegativeArraySizeException();
            }
            if (length < MINIMUM_RECOMMENDED_PASSWORD_LENGTH) {
              System.out.printf(messageBundle.getString(PASSWORD_LENGTH_WARNING), MINIMUM_RECOMMENDED_PASSWORD_LENGTH);
            } else if (length > MAXIMUM_RECOMMENDED_PASSWORD_LENGTH){
              System.out.printf(messageBundle.getString(PASSWORD_EXTREME_LENGTH_WARNING),MAXIMUM_RECOMMENDED_PASSWORD_LENGTH);
            }
            break;
          case"a" :
            System.out.printf(messageBundle.getString(AMBIGUOUS_CHARACTER_WARNING));
            break;
          case "d" :
          case "w" :
            System.out.printf(messageBundle.getString(PASSWORD_OPTIONS_CONFLICT_WARNING));
            break;     
            default :
              // Do Nothing
              break;
        }
      }
    } else {
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        switch (entry.getKey()) {
          case "L" :
            int length = ((Number) entry.getValue()).intValue();
            if (length <= 0) {
              throw new NegativeArraySizeException();
            }
            if (length < MINIMUM_RECOMMENDED_PASSPHRASE_LENGTH) {
              System.out.printf(messageBundle.getString(PASSPHRASE_LENGTH_WARNING),MINIMUM_RECOMMENDED_PASSPHRASE_LENGTH );
            } else if (length > MAXIMUM_RECOMMENDED_PASSPHRASE_LENGTH) {
              System.out.printf(messageBundle.getString(PASSPHRASE_EXTREME_LENGTH_WARNING),MAXIMUM_RECOMMENDED_PASSPHRASE_LENGTH);
            }
            break;
          case "d" :
            String delimiter = (String) entry.getValue();
            if (delimiter.matches(INVALID_DELIMITERS_REGEX)) {
              throw new IllegalArgumentException();
            }
            break;
          case "w" :
            String wordListFile = (String) entry.getValue();
            try {
             ResourceBundle.getBundle(wordListFile); 
            } catch (MissingResourceException ex){
              throw new FileNotFoundException(wordListFile);
              
            }
            break;
          
            default :
              System.out.printf(messageBundle.getString(PASSPHRASE_OPTIONS_CONFLICT_WARNING ));
            break;
        }
      }
    }
  }

  private static HashMap<String, Object> parseCommandLine(String[] args,
      org.apache.commons.cli.Options opts) throws ParseException {
    DefaultParser parser = new DefaultParser();
    HashMap<String, Object> map = new HashMap<>();
    CommandLine cmdLine = parser.parse(opts, args);
    if (cmdLine.hasOption("help")){
      display (null, usageMessage, opts);
      return null;
    }
    
    for (Option option : cmdLine.getOptions()) {
      String opt = option.getOpt();
      map.put(opt, cmdLine.getParsedOptionValue(opt));
    }
    return map;
  }

  private static org.apache.commons.cli.Options buildOptions() 
    throws MissingResourceException {
    ResourceBundle bundle = ResourceBundle.getBundle(OPTIONS_DESCRIPTION_BUNDLE);

    Option helpOption = Option.builder("?").longOpt("help").hasArg(false)
        .desc(bundle.getString(HELP_OPTION_KEY)).build();

    Option lengthOption =
        Option.builder("L").argName("length").hasArg()
                                             .desc(bundle
                                             .getString(LENGTH_OPTION_KEY))
                                             .longOpt("length")
                                             .numberOfArgs(1)
                                             .type(Number.class)
                                             .build();
    Option delimiterOption = Option.builder("d").argName("delimiter")
                                                .hasArg()
                                                .desc(bundle.getString(DELIMITER_OPTION_KEY))
                                                .longOpt("delimiter")
                                                .numberOfArgs(1)
                                                .optionalArg(true).type(String.class).build();
    Option wordListOption = Option.builder("w").argName("path-to-list-file").hasArg()
        .desc(bundle.getString(WORDLIST_OPTION_KEY)).longOpt("word-list").numberOfArgs(1)
        .type(String.class).build();
    Option modeOption = Option.builder("m").longOpt("password-mode").hasArg(false)
        .desc(bundle.getString(MODE_OPTION_KEY)).build();

    Option excludeUpperOption = Option.builder("b").longOpt("exclude-upper").hasArg(false)
        .desc(bundle.getString(UPPER_OPTION_KEY)).build();
    Option excludeLowerOption = Option.builder("s").longOpt("exclude-lower").hasArg(false)
        .desc(bundle.getString(LOWER_OPTION_KEY)).build();
    Option excludeDigitsOption = Option.builder("n").longOpt("exclude-digits").hasArg(false)
        .desc(bundle.getString(DIGITS_OPTION_KEY)).build();
    Option excludePunctuationOption = Option.builder("p").longOpt("exclude-punctuation")
        .hasArg(false).desc(bundle.getString(PUNCTUATION_OPTION_KEY)).build();
    Option includeAmbiguousOption = Option.builder("a").longOpt("include-ambiguous").hasArg(false)
        .desc(bundle.getString(AMBIGUOUS_OPTION_KEY)).build();

    org.apache.commons.cli.Options opts1 = new org.apache.commons.cli.Options()
        .addOption(lengthOption).addOption(helpOption).addOption(delimiterOption)
        .addOption(wordListOption).addOption(modeOption).addOption(excludeUpperOption)
        .addOption(excludeLowerOption).addOption(excludeDigitsOption)
        .addOption(excludePunctuationOption).addOption(includeAmbiguousOption);
    return opts1;
  }

  private static void displayError(ResourceBundle messageBundle, String  messageKey,
      org.apache.commons.cli.Options opts, String optName) {
    String message = messageBundle.getString(messageKey);
    message = String.format(message, optName);
    display (message, usageMessage, opts);
  }
  
  private static void display (String message, String usage, org.apache.commons.cli.Options opts) {
    if (message != null) {
      System.out.println(message);
    }
    if (opts != null) {
      new HelpFormatter().printHelp(usage,  opts);
      
    }
  }

}
