package JavaBeans;

import java.io.Serializable;

/**
 *
 * @author Yakov
 */
public class User implements Serializable {
    protected String login;
    protected String password;
    protected String role;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
