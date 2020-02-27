package framework;

import framework.GlobalUser;
import framework.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * This class controls the forgot password screen.
 */

public class ForgotPasswordController {

    @FXML
    private TextField email;

    @FXML
    private TextField userName;

    @FXML
    private TextField secretAnsField;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newPasswordConf;

    @FXML
    private Text codeSuccStr;

    @FXML
    private Text errorText;

    @FXML
    private Text questionString;

    @FXML
    private Text secretQuestion;

    @FXML
    private Text enterEUString;

    @FXML
    private Button emSub;

    @FXML
    private Button emCan;

    @FXML
    private Button secAnsSub;

    @FXML
    private Button newPassSub;

    private int uID;

    @FXML
    void onCancel(ActionEvent event) {
        SceneSwitch.switchScene("Login.fxml", getClass());
        GlobalUser.clearUser();
        System.out.println("[INFO] " + new Date().toString() + " Canceling forgot password. Returning to login. Clearing user");
    }

    // Submit uname and pass. Ensures that username matches email on record
    @FXML
    void onSubmit(ActionEvent event) {
        try {

            errorText.setText("");

            // Checking if email exists in system
            boolean emailExists = false;
            int numRecordsEml = 0;
            // Establish the connection to the database
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. ForgotPasswordController.onSubmit()");
            }
            PreparedStatement emailChk = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE email like (?)");
            emailChk.setString(1, email.getText());
            System.out.println("[INFO] " + new Date().toString() + " Begin email check query");
            ResultSet rsEml = emailChk.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Email check query success");
            while (rsEml.next()) {
                numRecordsEml = rsEml.getInt(1);
            }
            if (numRecordsEml != 0)
                emailExists = true;
            // end email check ----------------

            if (emailExists) {
                System.out.println("[INFO] " + new Date().toString() + " Email check success, checking for username match");
                // Checking for username match
                int numRecordsMatch = 0;
                boolean recordsMatch = false;
                PreparedStatement matchChk = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE email like (?) and userName like (?)");
                matchChk.setString(1, email.getText());
                matchChk.setString(2, userName.getText());
                System.out.println("[INFO] " + new Date().toString() + " Begin username match query");
                ResultSet rsMatchChk = matchChk.executeQuery();
                System.out.println("[INFO] " + new Date().toString() + " Username match query success");
                while (rsMatchChk.next()) {
                    numRecordsMatch = rsMatchChk.getInt(1);
                }
                if (numRecordsMatch != 0)
                    recordsMatch = true;

                // End username match check ----------------

                if (recordsMatch) {
                    System.out.println("[INFO] " + new Date().toString() + " Username and email records match success");

                    // set old fields invisible
                    email.setVisible(false);
                    userName.setVisible(false);
                    emSub.setVisible(false);
                    enterEUString.setVisible(false);

                    // set new fields visible
                    secretAnsField.setVisible(true);
                    questionString.setVisible(true);
                    secAnsSub.setVisible(true);
                    String secretQuestionText = "";

                    PreparedStatement getSecQ = conn.prepareStatement("SELECT secretQ from users where userName = (?)");
                    getSecQ.setString(1, userName.getText());
                    System.out.println("[INFO] " + new Date().toString() + " Begin secret question query");
                    ResultSet setOfSecrets = getSecQ.executeQuery();
                    System.out.println("[INFO] " + new Date().toString() + " Secret question query success");
                    while (setOfSecrets.next()) {
                        secretQuestionText = setOfSecrets.getString(1);
                    }
                    secretQuestion.setVisible(true);
                    secretQuestion.setText(secretQuestionText);

                    System.out.println("[INFO] " + new Date().toString() + " Secret questions prepared. Generating fields");

                    conn.close();
                    System.out.println("[INFO] " + new Date().toString() + " Database connection closed ForgotPasswordController.onSubmit()");
                }
                else {
                    errorText.setText("ERROR That email is not registered to that username");
                }
            }
            else {
                errorText.setText("ERROR: That email is not registered");
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. ForgotPasswordController.onSubmit()");
        }
        catch (Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " ForgotPasswordController.onSubmit()");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    //  Submit secret question answer after account is verified to exist
    @FXML
    void onSubmitCode(ActionEvent event) {
        try {
            errorText.setText("");
            // Establish the connection to the database
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. ForgotPasswordController.onSubmitCode()");
            }
            String secretQAnswer = "";
            PreparedStatement getSecQ = conn.prepareStatement("SELECT secretQAnswer from users where userName = (?)");
            getSecQ.setString(1, userName.getText());
            System.out.println("[INFO] " + new Date().toString() + " Begin get secret question and answer query");
            ResultSet setOfSecrets = getSecQ.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Secret question and answer query success");
            while (setOfSecrets.next()) {
                secretQAnswer = setOfSecrets.getString(1);
            }
            String secretQAnswerLower = secretQAnswer.toLowerCase();
            String secretQCorrectAnsLower = secretAnsField.getText().toLowerCase();
            if (secretQAnswerLower.equals(secretQCorrectAnsLower)) {
                // Answer was correct, remove old fields and show reset password fields
                secretAnsField.setVisible(false);
                questionString.setVisible(false);
                secAnsSub.setVisible(false);
                secretQuestion.setVisible(false);

                codeSuccStr.setVisible(true);
                newPassSub.setVisible(true);
                newPassword.setVisible(true);
                newPasswordConf.setVisible(true);

                // Getting unique id for new user
                PreparedStatement getUIDStmt = conn.prepareStatement("SELECT idusers FROM users WHERE userName = (?)");
                getUIDStmt.setString(1, userName.getText());
                System.out.println("[INFO] " + new Date().toString() + " Begin user id query");
                ResultSet uIDrs = getUIDStmt.executeQuery();
                System.out.println("[INFO] " + new Date().toString() + " GlobalUser id query success");
                uID = -1;
                while (uIDrs.next()) {
                    uID = uIDrs.getInt(1);
                }

                // Setting user
                GlobalUser.setUser(uID);
                System.out.println("[INFO] " + new Date().toString() + " Global user set");

                System.out.println("Secret question answered successfully. Opening connection to reset password.");
            }
            else {
                errorText.setText("ERROR: Sorry that is not correct");
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. ForgotPasswordController.onSubmitCode()");
        }
        catch (Exception ex) {
            System.out.println("Error connecting to db");
            ex.printStackTrace();
        }
    }

    // Submit new password after secret question answered
    @FXML
    void onNewPassSubmit(ActionEvent event) {
        try {
            errorText.setText("");
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database ForgotPasswordController.onNewPassSubmit()");
            }

            if (newPassword.getText().equals(newPasswordConf.getText())) {
                if (passwordValid(newPassword.getText())) {
                    if (!passwordHasBeenUsed(newPassword.getText(), GlobalUser.getIdUsers())) {

                        // Setting password expire date
                        LocalDate today = LocalDate.now();
                        LocalDate passExpire = today.plusMonths(3);
                        java.sql.Date sqlPassExpire = java.sql.Date.valueOf(passExpire);

                        PreparedStatement updatePassStmt = conn.prepareStatement("UPDATE users set " +
                                "password = (?), passwordExpired = (?) where userName = (?)");
                        updatePassStmt.setString(1, newPassword.getText());
                        updatePassStmt.setDate(2, sqlPassExpire);
                        updatePassStmt.setString(3, userName.getText());
                        System.out.println("[INFO] " + new Date().toString() + " Begin update user password field query");
                        updatePassStmt.executeUpdate();
                        System.out.println("[INFO] " + new Date().toString() + " Update user password field query success");

                        // Inserting user password into passwords db
                        PreparedStatement passwordStmt = conn.prepareStatement("INSERT INTO passwords values (?,?,?)");
                        passwordStmt.setString(1, null);
                        passwordStmt.setInt(2, uID);
                        passwordStmt.setString(3, newPassword.getText());
                        System.out.println("[INFO] " + new Date().toString() + " Begin update password query");
                        passwordStmt.executeUpdate();
                        System.out.println("[INFO] " + new Date().toString() + " Update password query success");

                        System.out.println("[INFO] " + new Date().toString() + " Update password successful. Returning to login. Clearing user");
                        SceneSwitch.switchScene("Create User Success.fxml", getClass());
                    }
                    else {
                        errorText.setText("ERROR: That password has been used before.\n" +
                                "Please choose a different password.");
                    }
                }
                else {
                    errorText.setText("ERROR: Password is invalid.\n" +
                            "REQUIREMENTS: \n" +
                            "Must begin with a letter\n" +
                            "Must be 8 characters in length\n" +
                            "Must contain a letter, a number, and a special character");
                }
            }
            else {
                errorText.setText("ERROR: Passwords do not match");
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. ForgotPasswordController.onNewPassSubmit()");
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " ForgotPasswordController.onNewPassSubmit()");
            ex.printStackTrace();
        }

    }

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

    private static boolean passwordHasBeenUsed(String pwd, int iduser) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("Connected to the database");
            }
            PreparedStatement matchChk = conn.prepareStatement("SELECT COUNT(*) FROM passwords WHERE iduser like (?) and password like (?)");
            matchChk.setInt(1, iduser);
            matchChk.setString(2, pwd);
            ResultSet rsMatchChk = matchChk.executeQuery();
            int numRecordsMatch = -1;
            while (rsMatchChk.next()) {
                numRecordsMatch = rsMatchChk.getInt(1);
            }
            if (numRecordsMatch != 0)
                return true;
            return false;
        }
        catch (Exception ex) {
            System.out.println("[ERROR] Password has been used before. Set failed.");
        }
        return false;
    }

}
