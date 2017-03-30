package ManageBeans;

import Dao.IDAORemoteAdv;
import JavaBeans.Advertising;
import Singleton.SltBean;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.faces.context.FacesContext;
import javax.interceptor.Interceptors;

/**
 *
 * @author Yakov
 */
public class AdvSelectSessionBean {

    @EJB
    private IDetailAdvBean advBean;
    @EJB
    private SltBean singleton;
    private String selectedAdvName;
    private Advertising selectedAdvertising;

    
    public void setSelectedAdvName(String selectedAdvName) {
        this.selectedAdvName = selectedAdvName;
        advBean.setSelectedName(selectedAdvName);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./advertisingDetails.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AdvReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getNumberOfCalls() {
        return singleton.getCount();
    }

    public void setSelectedAdvertising(Advertising selectedAdvertising) {
        this.selectedAdvertising = selectedAdvertising;
    }

    public String getSelectedAdvName() {
        return selectedAdvName;
    }

    public Advertising getSelectedAdvertising() {
        return advBean.getSelectedAdvertising();
    }

}
