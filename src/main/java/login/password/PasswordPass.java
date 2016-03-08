package login.password;

/**
 * Created by henridupon on 3/1/2016.
 *
 * This class removes pieces of the input string so that only the password remains.
 */
public class PasswordPass {

    public static String PasswordPass(String password) {
        String password1 = null;
        password1 = password.replace("[{password=", "");
        password = password1.replaceAll("\\}\\]", "");
        return password;
    }
}

