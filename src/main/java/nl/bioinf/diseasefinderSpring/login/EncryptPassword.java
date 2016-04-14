/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by henridupon on 3/1/2016.
 */
package nl.bioinf.diseasefinderSpring.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Encrypts the input password from the user.
 */
public class EncryptPassword {


    /**
     * Method that encrypts the user password.
     *
     * @param password from the user.
     * @return a hashed password.
     */
    public static String EncryptPassword(String password) {

        if (password.length() < 5 || password.length() > 30) {
            throw new IndexOutOfBoundsException();
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            return hashedPassword;
        }
    }
}
