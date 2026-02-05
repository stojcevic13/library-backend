package demo.simple.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSqlServerConnection {
    public static void main(String[] args) {
        // Podesi parametre za konekciju
        String url = "jdbc:sqlserver://localhost:1433;databaseName=library;encrypt=true;trustServerCertificate=true";

        String user = "sa";
        String password = "Test1234!";

        try {
            // Pokušaj konekciju
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Konekcija uspešna!");

            // Probaj da izvršiš jedan upit
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 5 * FROM author");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " " +
                                rs.getString("name") + " " +
                                rs.getString("surname")
                );
            }

            // Zatvori konekciju
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Greška pri konekciji: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

