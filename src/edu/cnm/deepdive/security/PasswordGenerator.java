/*
 * PasswordGenerator.java
 * 
 */

package edu.cnm.deepdive.security;

import java.util.Random;

/**
 * Implementation of a simple password generator.
 * 
 * @author Yolanda Philgreen
 * @version 1.0
 */
public class PasswordGenerator {
  
  private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE = UPPERCASE.toLowerCase();
  private static final String NUMBERS = "0123456789";
  private static final String PUNCTUATION = "!@#$%&*,.";
  private static final String AMBIGUOUS = "[Ol]";
  private char[] pool = null;
  private int minLength =6;
  private int maxLength =12;
  protected Random rng;
  private boolean includeUpperCase = true;
  private boolean includeLowerCase = true;
  private boolean includeNumbers = true;
  private boolean includePunctuation = false;
  private boolean excludeAmbiguous = true;
  
  
/**
 * Test rig for generating passwords.
 * 
 * @param args Command-line parameters for password generation options.
 */
  public static void main(String[] args) {
    
  }
  /**
   * 
   */
 public PasswordGenerator() {
 }
 /**
  * Length of password selection using a psudo password generation. 
  * @param minLength
  * @param maxLength
  */
 public PasswordGenerator (int minLength, int maxLength) {
   this();
   this.minLength = minLength;
   this.maxLength = maxLength;
   
 }
 /**
  * 
  * 
  * @param minLength minimum length of password to be generated.
  * @param maxLength maximum length of password to be generated.
  * @param includeUpperCase  Case sensitive password to be generated with use of Upper case.
  * @param includeLowerCase  Case sensitive password to be generated with use of Lower case.
  * @param includeNumbers  Case sensitive password to be generated with use of Numbers.
  * @param includePunctuation  Case sensitive password to be generated with use of Punctuation.
  * @param excludeAmbiguous  Case sensitive password to be generated with no use of Ambiguous.
  */
 public PasswordGenerator(int minLength, int maxLength, boolean includeUpperCase,
     boolean includeLowerCase, boolean includeNumbers, boolean includePunctuation,
     boolean excludeAmbiguous) {
   System.out.println("In another overloaded constructor");
   this.includeUpperCase = includeUpperCase;
   this.includeLowerCase = includeLowerCase;
   this.includeNumbers = includeNumbers;
   this.includePunctuation = includePunctuation;
   this.excludeAmbiguous = excludeAmbiguous;
       
 }
 
/**
 * maximum length of the password is selected.
 * @return the maxLength
 */
public int getMaxLength() {
  return maxLength;
}
/**
 * 
 * @param maxLength  max length of the password is selected.
 */
protected void setMaxLength(int maxLength) {
  this.maxLength = maxLength;
}
/**
 * @return the minLength is returned.
 */
public int getMinLength() {
  return minLength;
}
/**
 * @param minLength the minimum Length of password selected.
 */
protected void setMinLength(int minLength) {
  this.minLength = minLength;
}
private void setupPool() {
  if (pool == null) {
   StringBuilder builder = new StringBuilder();
   if (includeLowerCase) {
     builder.append(LOWERCASE);
   }
   if (includeUpperCase) {
     builder.append(NUMBERS);
   }
   if (includePunctuation) {
     builder.append(PUNCTUATION);
   }
   String work = builder.toString();
   if (excludeAmbiguous) {
    work.replaceAll(AMBIGUOUS, "");
   }
   pool = work.toCharArray();
  }
}
/**
 * 
 * @return the maximum length of the password is set.
 */
public String generate() {
  setupPool();
  int passwordLength = minLength + rng.nextInt(maxLength - minLength +1);
  StringBuilder builder = new StringBuilder();
  for (int i = 0; i <passwordLength; i++) {
    char selection = pool[rng.nextInt(pool.length)];
    builder.append(selection);
  }
  return builder.toString();
}

}
