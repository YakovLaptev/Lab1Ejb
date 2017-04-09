package JavaBeans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Yakov
 */
public class SimpleEvent implements Serializable {
    private String message;
    private Date date;

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}
