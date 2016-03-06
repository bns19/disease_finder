package login.password;

/**
 * Created by henridupon on 3/1/2016.
 */
public class PasswordPass {

    public static String PasswordPass(String password) {
        String password1 = null;
        password1 = password.replace("[{username=", "");
        password = password1.replaceAll("\\}\\]", "");
        System.out.println(password);
        return password;
    }
}

