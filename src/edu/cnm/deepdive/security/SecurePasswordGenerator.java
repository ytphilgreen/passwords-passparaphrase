package edu.cnm.deepdive.security;

import java.security.SecureRandom;
/**
 * Interface of the cryprographic secure password
 * @author Yolanda Philgreeen
 *
 */
public class SecurePasswordGenerator extends PasswordGenerator {
/**
 * Invokes {@link PasswordGenerator#PasswordGenerator(int, int)  constructor}
 */
  public SecurePasswordGenerator() {
    super();
    rng = new SecureRandom();
  }
/**
 * 
 * @param minLength minimum length to be generated for password.
 * @param maxLength maxinum length to be generated for password.
 */
  public SecurePasswordGenerator(int minLength, int maxLength) {
    super(minLength, maxLength);
  }
/**
 * security parameters set.
 * @param minLength minLength minimum length to be generated for password.
 * @param maxLength maxLength maximum length to be generated for password.
 * @param includeUpperCase Case sensitive password to be generated with use of Upper case.
 * @param includeLowerCase Case sensitive password to be generated with use of Lower case.
 * @param includeNumbers Case sensitive password to be generated with use of Number.
 * @param includePunctuation Case sensitive password to be generated with use of Punctuation.
 * @param excludeAmbiguous Case sensitive password to be generated with non use of Ambiguous.
 */
  public SecurePasswordGenerator(int minLength, int maxLength, boolean includeUpperCase,
      boolean includeLowerCase, boolean includeNumbers, boolean includePunctuation,
      boolean excludeAmbiguous) {
    super(minLength, maxLength, includeUpperCase, includeLowerCase, includeNumbers,
        includePunctuation, excludeAmbiguous);
  }
}
