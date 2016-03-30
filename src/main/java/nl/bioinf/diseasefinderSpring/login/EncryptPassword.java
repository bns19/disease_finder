package nl.bioinf.diseasefinderSpring.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by henridupon on 3/1/2016.
 */
public class EncryptPassword {


    /**
     * Method that encrypts the user password
     * @param password
     * @return a hashed password
     */
    public static String EncryptPassword(String password){

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            return hashedPassword;

        }
}
