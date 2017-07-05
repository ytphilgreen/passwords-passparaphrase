/**
 * Guard.java 
 */
package edu.cnm.deepdive.security;


import java.util.HashMap;
import java.util.Map;



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
  HashMap<String, Object> map = Options.getOptions(args);
  if (map!= null) {
    String artifact = generateArtifact(map);
    emitArtifact(artifact);
     }
  
  }
 
  static String generateArtifact(HashMap<String, Object> map) {
    if(map.containsKey("m")) {
      PasswordGenerator gen = new SecurePasswordGenerator();
      for (Map.Entry<String, Object> entry : map.entrySet()) {
       switch (entry.getKey()) {
         case "L" :
          int length = ((Number) entry.getValue()).intValue();
          gen.setMinLength(length);
          gen.setMaxLength(length);
          break;
         case "a" :
           gen.setAmbiguousExclude(false);
           break;
         case "b" :
           gen.setUpperCaseInclude (false);
           break;
         case "s" :
           gen.setLowerCaseInclude (false);
           break;
         case "n":
           gen.setNumberIncluded(false);
           break;
         case "p":
           gen.setNumbersInclude(false);
           break;
           default:
             break;
       }
      }
      //TODO set field for all specified options. 
      return gen.generate();
    } else {
      PassphraseGenerator gen = new PassphraseGenerator();
      for (Map.Entry<String,Object> entry : map.entrySet()) {
        switch (entry.getKey()) {
          case "L":
          int Length = ((Number) entry.getValue()).intValue();
          gen.setLength (Length);
          break;
          case "d":
            String delimiter = (String) entry.getValue();
            gen.setDelimiter(delimiter);
          case "w":
            String wordListFile = (String) entry.getValue();
            gen.setWordList(wordListFile);
            break;
            default:
              break;
              
        }
      }
      return gen.generate();
    } 
  }
  static void emitArtifact(String artifact) {
    //TODO make this smarter may check for a verbose option.
    System.out.println(artifact);
    
  }
}
