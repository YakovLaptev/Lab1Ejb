package JavaBeans;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Yakov
 */
public class Сustomer extends User implements Serializable {
    private String fio;
    private Date registrationDate;
    private String email;
    private User user;

    public String getFio() {
        return fio;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getEmail() {
        return email;
    }
    
    public User getUser() {
        return user;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
