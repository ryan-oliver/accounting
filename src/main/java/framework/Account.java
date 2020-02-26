package framework;

import java.sql.*;
import java.util.Date;

public class Account {

    public static String accountName;
    public static String accountNumber;
    public static String accountDescription;
    public static String normalSide;
    public static String initialBalance;
    public static String balance;
    public static String statement;
    public static String active;
    public static String category;
    public static String subcategory;
    public static String created;
    public static String userCreated;

    public static Integer accountId;

    public static void setAccount(String accountNum) {
        try {

            String url = "jdbc:mysql://localhost:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. Account.setAccount()");
            }
            PreparedStatement loadAcct = conn.prepareStatement("select * from accounts where accountNum = (?)");
            loadAcct.setString(1, accountNum);
            ResultSet rs = loadAcct.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Setting global account parameters");
            while (rs.next()) {
                accountId = rs.getInt(1);
                accountNumber = rs.getString(2);
                accountName = rs.getString(3);
                accountDescription = rs.getString(4);
                normalSide = rs.getString(5);
                category = rs.getString(6);
                subcategory = rs.getString(7);
                initialBalance = rs.getString(8);
                balance = rs.getString(9);
                created = String.valueOf(rs.getTimestamp(10));
                userCreated = String.valueOf(rs.getInt(11));
                if (rs.getInt(12) == 1)
                    active = "Y";
                else
                    active = "N";
                statement = rs.getString(13);
            }

            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " CDatabase connection closed. Account.setAccount()");

        }
        catch (Exception ex) {
            System.out.println("Error connecting to db");
            System.out.println("[FATAL ERROR] " + new Date().toString() + " Account.setAccount()");
            ex.printStackTrace();
        }

    }

    public static void clearAccount() {
        accountId = null;
        accountNumber = null;
        accountName = null;
        accountDescription = null;
        normalSide = null;
        category = null;
        subcategory = null;
        initialBalance = null;
        balance = null;
        created = null;
        userCreated = null;
        active = null;
        statement = null;
    }

    public static String getAccountName() {
        return accountName;
    }

    public static Integer getAccountId() {
        return accountId;
    }

    public static String getAccountNumber() {
        return accountNumber;
    }

    public static String getAccountDescription() {
        return accountDescription;
    }

    public static String getNormalSide() {
        return normalSide;
    }

    public static String getInitialBalance() {
        return initialBalance;
    }

    public static String getBalance() {
        return balance;
    }

    public static String getStatement() {
        return statement;
    }

    public static String getActive() {
        return active;
    }

    public static String getCategory() {
        return category;
    }

    public static String getSubcategory() {
        return subcategory;
    }

    public static String getCreated() {
        return created;
    }
}
