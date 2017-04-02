package ManageBeans;

import Singleton.SltBean;
import JavaBeans.Advertising;
import Dao.IDAORemoteAdv;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

/**
 *
 * @author Yakov
 */
@Stateful
@Named(value = "DetailAdvBean")
@ConversationScoped
public class DetailAdvBean implements IDetailAdvBean, Serializable {

    @Inject
    private Conversation conv;
    @EJB
    private IDAORemoteAdv advdao;
    private Advertising selectedAdvertising;
    private String selectedName;

    public void makeSelection() {
        selectedAdvertising = advdao.getByName(selectedName);
        if (!conv.isTransient()) {
            conv.end();
        }
    }

    @Override
    @Interceptors(SltBean.class)
    public Advertising getSelectedAdvertising() {
        makeSelection();
        return selectedAdvertising;
    }

    @Override
    public void setSelectedName(String name) {
        if (conv.isTransient()) {
            conv.begin();
        }
        this.selectedName = name;
    }
}
