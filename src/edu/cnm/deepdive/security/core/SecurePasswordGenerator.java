package edu.cnm.deepdive.security.core;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 * Interface of the cryprographic secure password
 * @author Yolanda Philgreeen
 *
 *@version 1.4
 */
public class SecurePasswordGenerator extends PasswordGenerator {
/**
 * Invokes {@link PasswordGenerator#PasswordGenerator(int, int)  constructor}
 */
  public SecurePasswordGenerator() {
    super();
  }
@Override
  protected void setupRng()  {
  try {
    setRng(SecureRandom.getInstanceStrong());
  } catch (NoSuchAlgorithmException ex) {
    throw new RuntimeException(ex);
    
  }
  
  }
}
