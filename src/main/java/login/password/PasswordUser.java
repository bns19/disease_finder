package login.password;

/**
 * Created by henridupon on 3/1/2016.
 */
public class PasswordUser {

    public static String PasswordUser(String user) {
        user = user.replaceAll("\\[\\{password=", "");
        user = user.replaceAll("\\}\\]", "");
        System.out.println(user);
        return user;
    }
}
