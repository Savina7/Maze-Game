package detyrekursi;
import java.util.*;
import java.sql.*;

import javax.swing.JOptionPane;
class Control1 {
    private String DbUsername = "java";
    private String DbPassword = "1";
    private String DbUrl = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    
    public void Kontrollo(String emri, String mbiemri,int player_id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        

        
        try {
            
            Class.forName("oracle.jdbc.OracleDriver");

            
            con = DriverManager.getConnection(DbUrl, DbUsername, DbPassword);
            if (con != null) {
                System.out.println("Connection established!");
            }

           
            con.setAutoCommit(false);

            
            String checkQuery = "SELECT COUNT(*) FROM LOGIN WHERE EMRI = ? AND MBIEMRI = ?";
            stmt = con.prepareStatement(checkQuery);
            stmt.setString(1, emri);
            stmt.setString(2, mbiemri);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);

                if (count > 0) {
                    
                    System.out.println("Ju sapo u loguat.");
                    JOptionPane.showMessageDialog(null, "Ju sapo u loguat", "LOGIN", JOptionPane.INFORMATION_MESSAGE);
                    String insertQuery = "INSERT INTO HISTORIA (EMRI, MBIEMRI,PLAYER_ID) VALUES (?, ?, ?)";
                    stmt = con.prepareStatement(insertQuery);
                    stmt.setString(1, emri);
                    stmt.setString(2, mbiemri);
                    stmt.setInt(3, player_id);
                    stmt.executeUpdate();
                    con.commit();
                    Loja loja = new Loja();
                } else {
                   
                    System.out.println("Ju duhet te krijoni nje adrese per tu loguar.");
                    JOptionPane.showMessageDialog(null, "Ju duhet te krijoni nje adrese per tu loguar.", "LOGIN", JOptionPane.ERROR_MESSAGE);
                    Create create=new Create();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    System.out.println("Rolling back transaction...");
                    con.rollback();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } finally {
           
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
