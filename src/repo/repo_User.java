/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.sql.*;
import dao.DBContext;
import model.model_Permissons;

public class repo_User {

    private String sql = null;
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public repo_User() {
        try {
            this.conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public model_Permissons checkPermissons(String user) {
        sql = "SELECT can_add, can_remove, can_update FROM dbo.tb_Permissions "
                + "WHERE user_id IN (SELECT id FROM dbo.tb_User WHERE username = ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.execute();

            rs = ps.getResultSet();
            while (rs.next()) {
                int add = rs.getInt(1);
                int remove = rs.getInt(2);
                int update = rs.getInt(3);

                return new model_Permissons(remove, update, add);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
