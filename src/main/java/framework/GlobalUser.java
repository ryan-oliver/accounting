package framework;

import java.sql.*;

import java.util.Date;

/**
 * static class to allow persistent user data when changing password or when signed in to application.
 */

public class GlobalUser {

    private static Integer idUsers;
    private static Integer admin;
    private static Integer manager;
    private static Integer accountant;
    private static Integer active;
    private static Integer passwordAtt;


    private static String userName;
    private static String fName;
    private static String lName;
    private static String password;
    private static String address;
    private static String secretQAnswer;
    private static String secretQ;
    private static String email;

    private static Date dob;
    private static Date suspendEnd;
    private static Date passwordExpired;

    /**
     * This method will set the current static user of the application.
     * Uses iduser from database for unique identification.
     * @param iduser
     */
    public static void setUser(int iduser) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. GlobalUser.setUSer()");
            }
            PreparedStatement pstmt = conn.prepareStatement("select userName, fName, lName, password, dob, address, email, admin, manager, " +
                    "accountant, active, suspendEnd, passwordAtt, secretQAnswer, secretQ, passwordExpired from users where idusers = (?)");
            pstmt.setInt(1, iduser);
            ResultSet rs = pstmt.executeQuery();

            idUsers = iduser;
            while (rs.next()) {
                userName = rs.getString(1);
                fName = rs.getString(2);
                lName = rs.getString(3);
                password = rs.getString(4);
                dob = rs.getDate(5);
                address = rs.getString(6);
                email = rs.getString(7);
                admin = rs.getInt(8);
                manager = rs.getInt(9);
                accountant = rs.getInt(10);
                active = rs.getInt(11);
                suspendEnd = rs.getDate(12);
                passwordAtt = rs.getInt(13);
                secretQAnswer = rs.getString(14);
                secretQ = rs.getString(15);
                passwordExpired = rs.getDate(16);
            }
            System.out.println("[INFO] " + new Date().toString() + " GlobalUser created and fields filled");
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. GlobalUser.setUSer()");

        }
        catch (Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " Could not connect to db. framework.GlobalUser()");
            ex.printStackTrace();
        }
    }

    /**
     * This method will clear the active user parameters.
     */
    public static void clearUser() {

        idUsers = null;
        admin = null;
        manager = null;
        accountant = null;
        active = null;
        passwordAtt = null;

        userName = null;
        fName = null;
        lName = null;
        password = null;
        address = null;
        secretQAnswer = null;
        secretQ = null;

        dob = null;
        suspendEnd = null;
        passwordExpired = null;

        System.out.println("[INFO] " + new Date().toString() + " GlobalUser cleared");
    }

    public static Integer getIdUsers() {
        return idUsers;
    }

    public static Integer getAdmin() {
        return admin;
    }

    public static Integer getManager() {
        return manager;
    }

    public static Integer getAccountant() {
        return accountant;
    }

    public static Integer getActive() {
        return active;
    }

    public static Integer getPasswordAtt() {
        return passwordAtt;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getfName() {
        return fName;
    }

    public static String getlName() {
        return lName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getAddress() {
        return address;
    }

    public static String getSecretQAnswer() {
        return secretQAnswer;
    }

    public static String getSecretQ() {
        return secretQ;
    }

    public static String getEmail() {
        return email;
    }

    public static Date getDob() {
        return dob;
    }

    public static Date getSuspendEnd() {
        return suspendEnd;
    }

    public static Date getPasswordExpired() {
        return passwordExpired;
    }

}
