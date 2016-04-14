package nl.bioinf.diseasefinderSpring.login;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by henridupon on 4/14/2016.
 */
public class EncryptPasswordTest {
    /**
     * test input with an empty String list
     * @throws Exception
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void TestEncryptPasswordInputEmptyString() throws Exception {
        System.out.println("input = empty string");
        String password = "";
        EncryptPassword encryptpw = new EncryptPassword();
        encryptpw.EncryptPassword(password);
    }

    /**
     * test input with numbers only
     * @throws Exception
     */
    @Test
    public void TestEncryptPasswordInputNumbers() throws Exception {
        System.out.println("input = numbers");
        String password = "12345678910";
        EncryptPassword encryptpw = new EncryptPassword();
        encryptpw.EncryptPassword(password);

        String expected = "";
        String results = encryptpw.EncryptPassword(password);

        assertNotEquals(expected, results);
    }

    /**
     * test input with only strange characters
     * @throws Exception
     */
    @Test
    public void TestEncryptPasswordStrangeSymbols() throws Exception {
        System.out.println("input = strange symbols");
        String password = "&*^!@*(!^#(*!*^#(!^(#(!^(#*!(^";
        EncryptPassword encryptpw = new EncryptPassword();
        encryptpw.EncryptPassword(password);

        String results = encryptpw.EncryptPassword(password);
    }

    /**
     * test input with a normal password (numbers, letters, characters)
     * @throws Exception
     */
    @Test
    public void TestEncryptPasswordNormal() throws Exception {
        System.out.println("input = normal password");
        String password = "password12345@&";
        EncryptPassword encryptpw = new EncryptPassword();
        encryptpw.EncryptPassword(password);

        String expected = "";
        String results = encryptpw.EncryptPassword(password);

        assertNotEquals(expected, results);
    }

    /**
     * test input with less than 5 characters
     * @throws Exception
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void TestEncryptPasswordLessThanFive() throws Exception {
        System.out.println("input = less than 5");
        String password = "1234";
        EncryptPassword encryptpw = new EncryptPassword();
        encryptpw.EncryptPassword(password);

        String results = encryptpw.EncryptPassword(password);
    }

    /**
     * test input with less than 5 characters
     * @throws Exception
     */
    @Test
    public void TestEncryptPasswordisFiveLong() throws Exception {
        System.out.println("input = 5 characters long");
        String password = "12345";
        EncryptPassword encryptpw = new EncryptPassword();
        encryptpw.EncryptPassword(password);

        String results = encryptpw.EncryptPassword(password);

        assertNotEquals(password, results);
    }

    /**
     * test input is more than 30 characters
     * @throws Exception
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void TestEncryptPasswordMoreThanThirty() throws Exception {
        System.out.println("input = less than 30");
        String password = "1234567890123456789012345678901";
        EncryptPassword encryptpw = new EncryptPassword();
        encryptpw.EncryptPassword(password);

        String results = encryptpw.EncryptPassword(password);
    }
}
