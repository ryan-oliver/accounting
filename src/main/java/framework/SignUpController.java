package framework;

import framework.SceneSwitch;
import framework.GlobalUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;


import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for controlling the sign up page
 */

public class SignUpController {

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private DatePicker dob;

    @FXML
    private PasswordField pwd;

    @FXML
    private PasswordField pwdConf;

    @FXML
    private RadioButton AccRadio;

    @FXML
    private RadioButton ManRadio;

    @FXML
    private Text alertText;

    @FXML
    private TextField secQ;

    @FXML
    private TextField secAns;

    @FXML
    private ToggleGroup tg;

    // If log in button is pressed. Returns to main landing page
    @FXML
    void onLogIn(ActionEvent event) {
        SceneSwitch.switchScene("Login.fxml", getClass());
        System.out.println("[INFO] " + new Date().toString() + " Transferring to login screen from sign in screen");
    }

    // Attempts to add user to db. This transfers to success screen if inert is successful
    @FXML
    void onSignUp(ActionEvent event) {

        try {

            // Checking if email exists in system
            boolean emailExists = false;
            int numRecords = 0;
            // Establish the connection to the database
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. SignUpController.onSignUp()");
            }

            PreparedStatement emailChk = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE email like (?)");
            emailChk.setString(1, email.getText());
            System.out.println("[INFO] " + new Date().toString() + " Begin email check query");
            ResultSet rs = emailChk.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Email check query successful");
            while (rs.next()) {
                numRecords = rs.getInt(1);
            }
            if (numRecords != 0)
                emailExists = true;

            if (fName.getText() == null || lName.getText() == null || email.getText() == null || address.getText() == null ||
                    dob.getValue() == null || pwd.getText() == null || pwdConf.getText() == null || (!AccRadio.isSelected() && !ManRadio.isSelected()) ||
                    secQ.getText() == null || secAns.getText() == null) {
                alertText.setText("Please fill all fields!");
            } else if (!pwd.getText().equals(pwdConf.getText())) {
                alertText.setText("Passwords do not match!");
            } else if (pwd.getText().equals(pwdConf.getText()) && !passwordValid(pwd.getText())) {
                alertText.setText("ERROR: Password is invalid.\n" +
                        "REQUIREMENTS: \n" +
                        "Must begin with a letter.\n" +
                        "Must be 8 characters in length.\n" +
                        "Must contain a letter, a number, and a special character.");
            } else if (emailExists) {
                alertText.setText("ERROR: Email exists in system. Please log in or request a new password.");
            } else {

                System.out.println("[INFO] " + new Date().toString() + " Email check passed, All fields filled, Passwords Match");

                alertText.setText("");

                // Prepare sql statement
                String firstName = fName.getText();
                String lastName = lName.getText();

                // Prepare username
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                String month;

                int monthInt = cal.get(Calendar.MONTH) + 1;
                if (monthInt < 10)
                    month = "0" + String.valueOf(monthInt);
                else
                    month = String.valueOf(monthInt);
                String year = String.valueOf(cal.get(Calendar.YEAR) % 100);
                String userName = firstName.toLowerCase().charAt(0) + lastName.toLowerCase()
                        + month + year;

                userName = verifyUsername(userName);

                String password = pwd.getText();

                java.sql.Date dateOfBirth = java.sql.Date.valueOf(dob.getValue());

                String addr = address.getText();

                String emailAdd = email.getText();

                // Setting password expire date
                LocalDate today = LocalDate.now();
                LocalDate passExpire = today.plusMonths(3);
                java.sql.Date sqlPassExpire = java.sql.Date.valueOf(passExpire);

                int accountant = 0;
                int manager = 0;
                if (AccRadio.isSelected())
                    accountant = 1;
                else
                    manager = 1;

                String secretQuestion = secQ.getText();
                String secretAnswer = secAns.getText();

                System.out.println("[INFO] " + new Date().toString() + " Begin user insert query");

                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pstmt.setString(1, null);
                pstmt.setString(2, userName);
                pstmt.setString(3, firstName);
                pstmt.setString(4, lastName);
                pstmt.setString(5, password);
                pstmt.setDate(6, dateOfBirth);
                pstmt.setString(7, addr);
                pstmt.setString(8, emailAdd);
                pstmt.setInt(9, 0);
                pstmt.setInt(10, manager);
                pstmt.setInt(11, accountant);
                pstmt.setInt(12, 1);
                pstmt.setDate(13, null);
                pstmt.setInt(14, 0);
                pstmt.setString(15, secretAnswer);
                pstmt.setString(16, secretQuestion);
                pstmt.setDate(17, sqlPassExpire);

                pstmt.executeUpdate();

                System.out.println("[INFO] " + new Date().toString() + " User insert query successful");

                // Getting unique id for new user
                PreparedStatement getUIDStmt = conn.prepareStatement("SELECT idusers FROM users WHERE userName = (?)");
                getUIDStmt.setString(1, userName);
                System.out.println("[INFO] " + new Date().toString() + " Begin user id query");
                ResultSet uIDrs = getUIDStmt.executeQuery();
                System.out.println("[INFO] " + new Date().toString() + " GlobalUser id query successful");
                int uID = -1;
                while (uIDrs.next()) {
                    uID = uIDrs.getInt(1);
                }

                // Inserting user password into passwords db
                PreparedStatement passwordStmt = conn.prepareStatement("INSERT INTO passwords values (?,?,?)");
                passwordStmt.setString(1, null);
                passwordStmt.setInt(2, uID);
                passwordStmt.setString(3, password);
                System.out.println("[INFO] " + new Date().toString() + " Begin password inert query");
                passwordStmt.executeUpdate();
                System.out.println("[INFO] " + new Date().toString() + " Password insert query successful");

                GlobalUser.setUser(uID);
                System.out.println("[INFO] " + new Date().toString() + " GlobalUser insert complete. Global user set. Closing connection and transferring to success screen.");
                // Close connection
                conn.close();
                System.out.println("[INFO] " + new Date().toString() + " Database connection closed. SignUpController.onSignUp()");
                SceneSwitch.switchScene("Create User Success.fxml", getClass());
            }
        }

        catch (Exception e) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " SignUpController.onSIgnUp()" );
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Below methods verify password requirements
     * @param pwd = user supplied password
     * @return
     */

    private static boolean passwordValid(String pwd) {
        if (pwd.length() < 8) {
            System.out.println("Password length fail");
            return false;
        }
        else if (!firstLetterIsChar(pwd)) {
            return false;
        }
        else if (!containsIntAndSpecial(pwd)) {
            return false;
        }
        else {
            return true;
        }
    }

    private static boolean firstLetterIsChar(String pwd) {
        char fLetter = pwd.charAt(0);
        int letterUnicode = fLetter;

        if ((letterUnicode > 64 && letterUnicode < 91) ||
                (letterUnicode > 97 && letterUnicode < 123)) {
            return true;
        }

        System.out.println("Password first char fail");
        return false;
    }

    private static boolean containsIntAndSpecial(String pwd) {
        boolean special = false;
        boolean number = false;
        char currentLetter;
        for (int i = 0; i < pwd.length(); i++) {
            currentLetter = pwd.charAt(i);

            if (Character.isDigit(currentLetter))
                number = true;
            if ((currentLetter > 32 && currentLetter < 49) ||
                    (currentLetter > 57 && currentLetter < 65) ||
                    (currentLetter > 90 && currentLetter < 97) ||
                    (currentLetter > 122 && currentLetter < 127))
                special = true;

            if (number && special)
                return true;
        }
        System.out.println("Number or Special error");
        return false;
    }

    /**
     * End password verify methods
     */


    /**
     * Check username against other usernames in db. If match then add 'x' to end.
     * @param uName
     * @return
     */
    private static String verifyUsername(String uName) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. SignUpController.verifyUsername()");
            }
            // Checking for username in db
            PreparedStatement unameChk = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE userName like CONCAT('%', (?), '%')");
            unameChk.setString(1, uName);
            System.out.println("[INFO] " + new Date().toString() + " Begin username query");
            ResultSet rs = unameChk.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Username query successful");
            int numRecords = 0;
            while (rs.next()) {
                numRecords = rs.getInt(1);
            }
            if (numRecords == 0) {
                System.out.println("[INFO] " + new Date().toString() + " Database connection closed. SignUpController.verifyUsername()");
                return uName;
            }
            String uNameNew = uName + String.valueOf(numRecords);
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. SignUpController.verifyUsername()");
            return uNameNew;
        }

        catch (Exception ex) {
            System.out.println("Error connecting to db");
            ex.printStackTrace();
        }
        System.out.println("[Fatal Error] " + new Date().toString() + " Username creation error");
        return "Error Contact Admin";
    }

}
