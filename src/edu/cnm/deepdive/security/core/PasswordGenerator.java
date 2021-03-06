/*
 * PasswordGenerator.java
 * 
 */

package edu.cnm.deepdive.security.core;

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
  private int minLength = 6;
  private int maxLength = 12;
  private Random rng = null;
  private boolean upperCaseInclude = true;
  private boolean lowerCaseInclude = true;
  private boolean numbersInclude = true;
  private boolean punctuationInclude = true;
  private boolean ambiguousExclude = true;
  private String delimiter = "";
 
  public PasswordGenerator() {}
  /**
   * maximum length of the password is selected.
   * 
   * @return the maxLength
   */
  public int getMaxLength() {
    return maxLength;
  }

  /**
   * 
   * @param maxLength max length of the password is selected.
   */
  public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
  }

  /**
   * @return the minLength is returned.
   */
  public int getMinLength() {
    return minLength;
  }

  /**
   * @param minLength the minimum Length of password selected. pool of characters based on the inclusion/exclusion flags.
   */
  public void setMinLength(int minLength) {
    this.minLength = minLength;
  }

  private void setupPool() {

      StringBuilder builder = new StringBuilder();
      if (isLowerCaseInclude()) {
        builder.append(LOWERCASE);
      }
      if (isUpperCaseInclude()) {
        builder.append(UPPERCASE);
      }
      
      if (isNumbersInclude()) {
        builder.append(NUMBERS);
      }
      if (isPunctuationInclude()) {
        builder.append(PUNCTUATION);
      }
      String work = builder.toString();
      if (isAmbiguousExclude()) {
        work.replaceAll(AMBIGUOUS, "");
      }
      pool = work.toCharArray();
    
  }

  /**
   * setting up the random generator
   */
  protected void setupRng() {
    if (rng == null) {
      rng = new Random();
    }
  }

  /**
   * 
   * @return the maximum length of the password is set.
   */
  public String generate() {
    setupPool();
    setupRng();
    int passwordLength = minLength + getRng().nextInt(maxLength - minLength + 1);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < passwordLength; i++) {
      char selection = pool[getRng().nextInt(pool.length)];
      builder.append(selection);
    }
    return builder.toString();
  }

  /**
   * @return the upperCaseInclude
   */
  public boolean isUpperCaseInclude() {
    return upperCaseInclude;
  }

  /**
   * @param upperCaseInclude the upperCaseInclude to set
   */
  public void setUpperCaseInclude(boolean upperCaseInclude) {
    this.upperCaseInclude = upperCaseInclude;
  }

  /**
   * @return the lowerCaseInclude
   */
  public boolean isLowerCaseInclude() {
    return lowerCaseInclude;
  }

  /**
   * @param lowerCaseInclude the lowerCaseInclude to set
   */
  public void setLowerCaseInclude(boolean lowerCaseInclude) {
    this.lowerCaseInclude = lowerCaseInclude;
  }

  /**
   * @return the punctuationInclude
   */
  public boolean isPunctuationInclude() {
    return punctuationInclude;
  }

  /**
   * @param punctuationInclude the punctuationInclude to set
   */
  public void setPunctuationInclude(boolean punctuationInclude) {
    this.punctuationInclude = punctuationInclude;
  }

  /**
   * @return the numbersInclude
   */
  public boolean isNumbersInclude() {
    return numbersInclude;
  }

  /**
   * @param numbersInclude the numbersInclude to set
   */
  public void setNumbersInclude(boolean numbersInclude) {
    this.numbersInclude = numbersInclude;
  }

  /**
   * @return the ambiguousExclude
   */
  public boolean isAmbiguousExclude() {
    return ambiguousExclude;
  }

  /**
   * @param ambiguousExclude the ambiguousExclude to set
   */
  public void setAmbiguousExclude(boolean ambiguousExclude) {
    this.ambiguousExclude = ambiguousExclude;
  }

  /**
   * @return the delimiter
   */
  public String getDelimiter() {
    return delimiter;
  }

  /**
   * @param delimiter the delimiter to set
   */
  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }

  /**
   * @return the random
   */
  protected Random getRng() {
    return rng;
  }

  /**
   * @param random the random to set
   */
  protected void setRng(Random rng) {
    this.rng = rng;
  }
}
