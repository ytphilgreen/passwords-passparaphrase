/**
 * Guard.java 
 */
package edu.cnm.deepdive.security;


import java.util.HashMap;



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
      //TODO Set fields for all specified options. 
      return gen.generate();
    }
    return null; 
    
  }
  static void emitArtifact(String artifact) {
    System.out.println(artifact);
    
  }
}
