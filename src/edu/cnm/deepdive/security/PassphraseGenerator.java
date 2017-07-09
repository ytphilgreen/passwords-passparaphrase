/**
 * 
 */
package edu.cnm.deepdive.security;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author Yolanda Philgreen
 *
 */
public class PassphraseGenerator {

  public static final String DEFAULT_WORD_LIST = "resources/wordlist";
  public static final String DEFAULT_DELIMITER = " ";
  public static final int  DEFAULT_LENGTH = 6;
  
  private String wordList = DEFAULT_WORD_LIST;
  private String delimiter = DEFAULT_DELIMITER;
  private int length = DEFAULT_LENGTH;
  private Random rng = null;
  private ArrayList<String> pool = null;
  /**
   * calling the super class
   */
  public PassphraseGenerator() {
    super();
  }
  /**
   * gathering the word list from the bundle in the resource bundle, so it can be based on location.
   */
  protected void setupPool() {
    ResourceBundle bundle = ResourceBundle.getBundle(wordList);
    pool = new ArrayList<>();
    Enumeration<String> keyEnum = bundle.getKeys();
    while ( keyEnum.hasMoreElements()){
      String key = keyEnum.nextElement();
      String word = bundle.getString(key);
      pool.add(word);     
    }
  }
  /**
   * calling the secure random password and passphrase generation
   */
  protected void setupRng(){
    rng = new SecureRandom();
  }
  /**
   * using the string builder to set our pool of words, numbers and charters for the use of password/passphrase
   * @return
   */
  public String generate() {
    if (pool == null){
      setupPool();
    }
    if (rng == null) {
      setupRng();
    }
    StringBuilder builder = new StringBuilder();
    String word = pool.get(rng.nextInt(pool.size()));
    builder.append(word);
    for (int i = 0; i < length - 1; i++) {
      word = pool.get(rng.nextInt(pool.size()));
      builder.append(delimiter + word);
    }
    return builder.toString().trim();   
  }
  
  /**
   * @return the wordList
   */
  public String getWordList() {
    return wordList;
  }
  
  /**
   * @param wordList the wordList to set
   */
  public void setWordList(String wordList) {
    this.wordList = wordList;
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
   * @return the length
   */
  public int getLength() {
    return length;
  }
  
  /**
   * @param length the length to set
   */
  public void setLength(int length) {
    this.length = length;
  }
  
  /**
   * @return the random list
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
