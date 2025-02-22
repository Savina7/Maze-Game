package detyrekursi;
import javax.swing.*;
import java.sql.*;

class Control2 {
    private String DbUsername = "java";
    private String DbPassword = "1";
    private String DbUrl = "jdbc:oracle:thin:@localhost:1521/XEPDB1";

    public void Kontrollo(String emri, String mbiemri, int player_id) {
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
                    System.out.println("Historia juaj..\nLoading....");
                    JOptionPane.showMessageDialog(null, "Piket tuaja do shfaqen ", "POINTS", JOptionPane.INFORMATION_MESSAGE);

                    String query = "SELECT EMRI, MBIEMRI, PIKET, STATUSI, DATA FROM HISTORIA WHERE EMRI=? AND MBIEMRI=? ORDER BY PIKET";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, emri);
                    stmt.setString(2, mbiemri);

                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        String emriFromDb = rs.getString("EMRI");
                        String mbiemriFromDb = rs.getString("MBIEMRI");
                        int piket = rs.getInt("PIKET");
                        String statusi = rs.getString("STATUSI");
                        Date data = rs.getDate("DATA");

                        
                        System.out.println("Emri: " + emriFromDb + " Mbiemri: " + mbiemriFromDb + " Pikët: " + piket + " Statusi: " + statusi + " Data: " + data);
                        Login log = new Login();
                    }

                   
                } else {
                    System.out.println("Kjo adrese nuk ekzistion.");
                    JOptionPane.showMessageDialog(null, "Kjo adrese nuk ekzistion.", "POINTS", JOptionPane.ERROR_MESSAGE);
                    Create create = new Create();
                    
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

