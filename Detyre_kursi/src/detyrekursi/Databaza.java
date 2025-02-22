package detyrekursi;

import java.sql.*;

public class Databaza {
    private static String DbUsername = "java";
    private static String DbPassword = "1";
    private static String DbUrl = "jdbc:oracle:thin:@localhost:1521/XEPDB1";

    public static void databaza(int piket,String statusi,int player_id)  {
        ShtoTeDHena(piket,statusi,player_id);
    }
    public static void ShtoTeDHena(int piket,String statusi,int player_id) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(DbUrl, DbUsername, DbPassword);
            if (con != null) {
                System.out.println("Connection established!");
            }
            con.setAutoCommit(false);

            String insert = "INSERT INTO LOJA (PIKET,STATUSI) VALUES (?, ?)";
            stmt = con.prepareStatement(insert);

            stmt.setInt(1, piket);
            stmt.setString(2, statusi);
            int rows = stmt.executeUpdate();
            String update = "UPDATE HISTORIA SET PIKET = ?, STATUSI = ? WHERE PLAYER_ID = ?";
            stmt = con.prepareStatement(update);
            stmt.setInt(1, piket);
            stmt.setString(2, statusi);
            stmt.setInt(3, player_id);
            stmt.executeUpdate();


            
            con.commit();

            
            System.out.println("Numri i rreshtave te shtuar: " + rows);

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
