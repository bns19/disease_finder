package nl.bioinf.diseasefinderSpring.login.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by henridupon on 3/1/2016.
 */
public class EncryptPassword {


    public static String EncryptPassword(String password){

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            return hashedPassword;

        }
}
