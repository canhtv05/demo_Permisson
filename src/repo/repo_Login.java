/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

/**
 *
 * @author CanhPC
 */

import java.sql.*;
import dao.DBContext;
import model.model_Login;

public class repo_Login {
    private String sql = null;
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public repo_Login() {
        try {
            this.conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public model_Login checkLogin(model_Login login) {
        sql = "SELECT username, password, role FROM dbo.tb_Login where username = ? and password = ?";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            ps.execute();
            
            rs = ps.getResultSet();
            while(rs.next()) {
                String username = rs.getString(1);
                String password = rs.getString(2);
                int role = rs.getInt(3);
                
                return new model_Login(username, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
