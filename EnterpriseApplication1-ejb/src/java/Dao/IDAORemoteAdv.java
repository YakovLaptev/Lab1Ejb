package Dao;

import JavaBeans.Advertising;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Yakov
 */
@Remote
public interface IDAORemoteAdv {
    public void create(Advertising a);
    public void update(Advertising a);
    public void delete(Advertising a);
    public List<Advertising> getAll();
    public Advertising getByName(String name);
}
