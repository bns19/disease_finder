/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Henri du Pon on 3/1/2016.
 */
package nl.bioinf.diseasefinderSpring.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Encrypts the input password from the user.
 */
public final class EncryptPassword {

    /**
     * private constructor (utility).
     */
    private EncryptPassword() { }

    /**
     * Method that encrypts the user password.
     *
     * @param password from the user.
     * @return a hashed password.
     */
    public static String encryptPassword(final String password) {

        if (password.length() < 5 || password.length() > 30) {
            throw new IndexOutOfBoundsException();
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            return hashedPassword;
        }
    }
}
