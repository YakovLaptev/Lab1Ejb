package ManageBeans;

import JavaBeans.Campaign;
import Dao.CampaignDAO;
import Dao.IDAORemote;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Yakov
 */
public class CampReqBean {

    private FacesMessage msg;
    private List<Campaign> campaigns;
    @EJB
    private IDAORemote dao;
    private List<Campaign> selectedCampaigns;
    private Campaign selectedCampaign;

    public CampReqBean() {
        this.dao = new CampaignDAO();
    }

    public void delete() throws ParseException {
        try {
            dao.delete(getSelectedCampaign());
            FacesContext.getCurrentInstance().getExternalContext().redirect("./managerIndex.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UsersReqBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        Campaign campaign = campaigns.get(event.getRowIndex());
        if (newValue != null && !newValue.equals(oldValue)) {
            dao.update(campaign);
        }
    }

    @PostConstruct
    public void allCampaigns() {
        campaigns = new ArrayList<>();
        for (Campaign c : dao.getAll()) {
            if (!"Нет акции".equals(c.getName())) {
                campaigns.add(c);
            }
        }
    }

    public FacesMessage getMsg() {
        return msg;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public List<Campaign> getSelectedCampaigns() {
        return selectedCampaigns;
    }

    public void setACampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public void setSelectedAdvertisings(List<Campaign> selectedCampaigns) {
        this.selectedCampaigns = selectedCampaigns;
    }

    public Campaign getSelectedCampaign() {
        return selectedCampaign;
    }

    public void setSelectedCampaign(Campaign selectedCampaign) {
        this.selectedCampaign = selectedCampaign;
    }

}
