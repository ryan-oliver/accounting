package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Message {

    private static String id;
    private static String from;
    private static String to;
    private static String subject;
    private static String date;
    private static String message;

    public static void setMessage(String mid) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. Message.setMessage()");
            }

            PreparedStatement loadMsg = conn.prepareStatement("select * from `message` where idmessage = (?)");
            loadMsg.setInt(1, Integer.valueOf(mid));
            ResultSet rs = loadMsg.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Setting global message parameters");
            while (rs.next()) {
                id = String.valueOf(rs.getInt(1));
                from = rs.getString(2);
                to = rs.getString(3);
                date = String.valueOf(rs.getDate(4));
                subject = rs.getString(5);
                message = rs.getString(6);
            }

            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. Account.setAccount()");
        }
        catch (Exception ex) {
            System.out.println("Error connecting to db");
            System.out.println("[FATAL ERROR] " + new Date().toString() + " Message.setMessage()");
            ex.printStackTrace();
        }

    }

    public static void clearMessage() {
        id = null;
        from = null;
        to = null;
        subject = null;
        date = null;
        message = null;
    }

    public static String getId() {
        return id;
    }

    public static String getFrom() {
        return from;
    }

    public static String getTo() {
        return to;
    }

    public static String getSubject() {
        return subject;
    }

    public static String getDate() {
        return date;
    }

    public static String getMessage() {
        return message;
    }


}
