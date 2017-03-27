package Dao;

import JavaBeans.Campaign;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Yakov
 */
@Remote
public interface IDAORemote {
    public void create(Campaign a);
    public void update(Campaign a);
    public void delete(Campaign a);
    public List<Campaign> getAll();
    public Campaign getByName(String name);
}
