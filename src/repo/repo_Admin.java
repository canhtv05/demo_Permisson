/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.sql.*;
import dao.DBContext;
import java.util.ArrayList;
import model.model_Login;
import model.model_Permissons;
import model.model_User;

public class repo_Admin extends repo_User {

    private String sql = null;
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public repo_Admin() {
        try {
            this.conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<model_User> findAllUsers() {
        ArrayList<model_User> list = new ArrayList<>();
        sql = "SELECT id, username FROM dbo.tb_User";

        try {
            ps = conn.prepareStatement(sql);
            ps.execute();

            rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                list.add(new model_User(id, username));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<model_Login> findAllAccountUsers() {
        ArrayList<model_Login> list = new ArrayList<>();
        sql = "select username, password from tb_Login where role = 0";

        try {
            ps = conn.prepareStatement(sql);
            ps.execute();

            rs = ps.getResultSet();
            while (rs.next()) {
                String password = rs.getString(2);
                String username = rs.getString(1);
                list.add(new model_Login(username, password));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePermissons(String user, model_Permissons permissons) {
        sql = "UPDATE dbo.tb_Permissions "
                + "SET can_add = ?, can_remove = ?, can_update = ? "
                + "WHERE USER_ID IN (SELECT id FROM dbo.tb_User WHERE username = ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, permissons.getCan_add());
            ps.setInt(2, permissons.getCan_remove());
            ps.setInt(3, permissons.getCan_update());
            ps.setString(4, user);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(model_Login login) {
        sql = "INSERT INTO tb_Login (username, password, role) VALUES (?, ?, 0);"
                + "INSERT INTO tb_User (username) VALUES (?);"
                + "DECLARE @P3INSERT INT;"
                + "SELECT @P3INSERT = id FROM dbo.tb_User WHERE username = ? "
                + "INSERT INTO tb_Permissions (user_id, can_add, can_remove, can_update) "
                + "VALUES (@P3INSERT, 0, 0, 0);";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            ps.setString(3, login.getUsername());
            ps.setString(4, login.getUsername());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(model_Login login) {
        sql = "UPDATE dbo.tb_Login "
                + "SET username = ?, password = ?, role = 0 WHERE username = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            ps.setString(3, login.getUsername());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(String username) {
        sql = "DELETE FROM dbo.tb_Permissions "
                + "WHERE user_id IN (SELECT id FROM dbo.tb_User WHERE username = ?);"
                + "DELETE FROM dbo.tb_User "
                + "WHERE username = ?;"
                + "DELETE FROM dbo.tb_Login "
                + "WHERE username = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, username);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
