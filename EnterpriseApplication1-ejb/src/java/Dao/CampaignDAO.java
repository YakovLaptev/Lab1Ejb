package Dao;

import JavaBeans.Campaign;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Yakov
 */
@Stateless
public class CampaignDAO extends Dao  implements Serializable, IDAORemote {

    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static String sql;

    @Override
    public void create(Campaign a) {
        try {
            connect();
            sql = "INSERT INTO `campaign` VALUES (?,?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getName());
            pstmt.setString(2, a.getAbout());
            pstmt.setDate(3, new java.sql.Date(a.getStartDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(a.getEndDate().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
        }
    }

    @Override
    public void update(Campaign a) {
        try {
            connect();
            sql = "UPDATE `campaign` SET about=?, startDate=?, endDate=? WHERE name=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getAbout());
            pstmt.setDate(2, new java.sql.Date(a.getStartDate().getTime()));
            pstmt.setDate(3, new java.sql.Date(a.getEndDate().getTime()));
            pstmt.setString(4, a.getName());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
        }
    }

    @Override
    public void delete(Campaign a)  {
        try {
            connect();
            sql = "DELETE FROM `campaign` WHERE name=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getName());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Campaign> getAll() {
        List<Campaign> list = new ArrayList<>();
        try {
            connect();
            sql = "SELECT * FROM `campaign`";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();            
            while (rs.next()) {
                Campaign a = new Campaign();
                a.setName(rs.getString("name"));
                a.setAbout(rs.getString("about"));
                a.setStartDate(rs.getDate("startDate"));
                a.setEndDate(rs.getDate("endDate"));
                list.add(a);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
            return list;
        }
    }

    @Override
    public Campaign getByName(String name)  {
        Campaign a = new Campaign();
        try {
            connect();
            sql = "SELECT * FROM `campaign` WHERE name = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            rs.next();            
            a.setName(rs.getString("name"));
            a.setAbout(rs.getString("about"));
            a.setStartDate(rs.getDate("startDate"));
            a.setEndDate(rs.getDate("endDate"));            
        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
            return a;
        }
    }
}
