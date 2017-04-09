package ManageBeans;

import Singleton.SltBean;
import JavaBeans.Advertising;
import Dao.IDAORemoteAdv;
import JavaBeans.SimpleEvent;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
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
    @Inject
    private Event<SimpleEvent> event;
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
        SimpleEvent sevent = new SimpleEvent();
        sevent.setMessage("Был роведен просмотр подробностей о \"" + selectedName + "\"");
        sevent.setDate(new Date(System.currentTimeMillis()));
        event.fire(sevent);
    }
}
