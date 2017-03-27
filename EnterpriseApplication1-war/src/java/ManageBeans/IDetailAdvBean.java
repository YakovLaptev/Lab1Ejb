package ManageBeans;

import JavaBeans.Advertising;
import javax.ejb.Local;

/**
 *
 * @author Yakov
 */
@Local
public interface IDetailAdvBean {
    public Advertising getSelectedAdvertising();
    public void setSelectedAdvertising(String name);
}
