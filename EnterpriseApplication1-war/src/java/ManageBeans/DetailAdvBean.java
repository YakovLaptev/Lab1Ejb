package ManageBeans;

import JavaBeans.Advertising;
import Dao.IDAORemoteAdv;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

    @Override
    public Advertising getSelectedAdvertising() {
        return selectedAdvertising;
    }

    @Override
    public void setSelectedAdvertising(String name) {
        conv.begin();
        this.selectedAdvertising = advdao.getByName(name);
        conv.end();
    }
}
