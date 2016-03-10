package nl.bioinf.diseasefinderSpring.login.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by henridupon on 3/1/2016.
 */
public class EncryptPassword {

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, IllegalStateException, IllegalBlockSizeException, BadPaddingException, IOException {

        String cryptedPassword = new BCryptPasswordEncoder().encode("password");
        System.out.println(cryptedPassword);

    }
}
