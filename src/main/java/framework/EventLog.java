package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

public class EventLog {

    public static void createEventLog(int userID, String action, String accountNumber, String message) {
        try {
            String url = "jdbc:mysql://localhost:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. EventLog");
            }
            PreparedStatement event = conn.prepareStatement("insert into event_log values (?,?,?,?,?)");
            event.setString(1, null);
            event.setInt(2, userID);
            event.setString(3, action);
            event.setString(4, accountNumber);
            event.setString(5, message);
            event.executeUpdate();
            conn.close();
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " EventLog" );
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
