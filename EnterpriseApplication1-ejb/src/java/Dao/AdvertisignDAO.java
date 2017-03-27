package Dao;

import JavaBeans.Advertising;
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
public class AdvertisignDAO extends Dao implements Serializable, IDAORemoteAdv  {

    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static String sql;

    @Override
    public void create(Advertising a) {
        try {
            connect();
            sql = "INSERT INTO `advertising` VALUES (?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getName());
            pstmt.setString(2, a.getCategory());
            pstmt.setInt(3, a.getPrice());
            pstmt.setString(4, a.getBriefDescription());
            pstmt.setString(5, a.getFullDescription());
            pstmt.setString(6, a.getCampaignName());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdvertisignDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
        }
    }

    @Override
    public void update(Advertising a) {
        try {
            connect();
            sql = "UPDATE `advertising` SET category=?, price=?, briefDescription=?, fullDescription=?, campaignName=? WHERE name=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getCategory());
            pstmt.setInt(2, a.getPrice());
            pstmt.setString(3, a.getBriefDescription());
            pstmt.setString(4, a.getFullDescription());
            pstmt.setString(5, a.getCampaignName());
            pstmt.setString(6, a.getName());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdvertisignDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
        }
    }

    public void delete(Advertising a)  {
        try {
            connect();
            sql = "DELETE FROM `advertising` WHERE name=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getName());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdvertisignDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
        }
    }

    public List<Advertising> getAll() {
        List<Advertising> list = new ArrayList<>();
        try {
            connect();
            sql = "SELECT * FROM `advertising`";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();            
            while (rs.next()) {
                Advertising a = new Advertising();
                a.setName(rs.getString("name"));
                a.setCategory(rs.getString("category"));
                a.setPrice(rs.getInt("price"));
                a.setBriefDescription(rs.getString("briefDescription"));
                a.setFullDescription(rs.getString("fullDescription"));
                a.setCampaignName(rs.getString("campaignName"));
                list.add(a);            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdvertisignDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
            return list;
        }
    }

    @Override
    public Advertising getByName(String name) {
        Advertising a = new Advertising();
        try {
            connect();
            sql = "SELECT * FROM `advertising` WHERE name = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            rs.next();            
            a.setName(rs.getString("name"));
            a.setCategory(rs.getString("category"));
            a.setPrice(rs.getInt("price"));
            a.setBriefDescription(rs.getString("briefDescription"));
            a.setFullDescription(rs.getString("fullDescription"));
            a.setCampaignName(rs.getString("campaignName"));            
        } catch (SQLException ex) {
            Logger.getLogger(AdvertisignDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
            return a;
        }
    }
}
