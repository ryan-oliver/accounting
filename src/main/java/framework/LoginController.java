package framework;

import framework.GlobalUser;
import framework.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;

public class LoginController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private Text alertText;


    // Open sign up window
    @FXML
    void signUpWin(ActionEvent event) {
        SceneSwitch.switchScene("Sign Up.fxml", getClass());
        System.out.println("[INFO] " + new Date().toString() + " Transferring to sign up screen from login screen");
    }

    // Open forgot password window
    @FXML
    void onForgotPassword(ActionEvent event) {
        SceneSwitch.switchScene("Forgot Password.fxml", getClass());
        System.out.println("[INFO] " + new Date().toString() + " Transferring to forgot password screen from login screen");
    }

    // Process a sign in attempt
    @FXML
    void onSignIn(ActionEvent event) {

        try {
            // Establishing connection to db
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. LoginController.onSignIn()");
            }

            // Checking if username exists
            PreparedStatement signInChk = conn.prepareStatement("SELECT COUNT(*) from users where userName = (?)");
            signInChk.setString(1, userName.getText());
            System.out.println("[INFO] " + new Date().toString() + " Begin username check query");
            ResultSet uNameCt = signInChk.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Username check query success");
            int numOfAccts = 0;
            while (uNameCt.next()) {
                numOfAccts = uNameCt.getInt(1);
            }

            if (numOfAccts == 1) {
                // Get user id of username and generate user object
                PreparedStatement getUID = conn.prepareStatement("SELECT idusers from users where userName = (?)");
                getUID.setString(1, userName.getText());
                System.out.println("[INFO] " + new Date().toString() + " Begin user id get query");
                ResultSet UIDrs = getUID.executeQuery();
                System.out.println("[INFO] " + new Date().toString() + " GlobalUser id get query success");
                int uid = -1;
                while (UIDrs.next()) {
                    uid = UIDrs.getInt(1);
                }
                System.out.println("[INFO] " + new Date().toString() + " Creating global user object");
                GlobalUser.setUser(uid);

                if (GlobalUser.getAdmin() == 1) {
                    // Log in admin
                    logInAdmin();
                    System.out.println("[INFO] " + new Date().toString() + " GlobalUser identified as admin. Logging in...");
                }
                else if (GlobalUser.getAccountant() == 1) {
                    // Log in user
                    logInUser();
                    System.out.println("[INFO] " + new Date().toString() + " GlobalUser identified as accountant. Logging in...");

                }
                else if (GlobalUser.getManager() == 1) {
                    logInManager();
                    System.out.println("[INFO] " + new Date().toString() + " GlobalUser identified as manager. Logging in...");
                }
                else {
                    System.out.println("[FATAL ERROR] " + new Date().toString() + " Account type not set for user: " + userName.getText());
                    alertText.setText("ERROR: Account type not set. Contact admin");
                    GlobalUser.clearUser();
                }
            }
            else {
                // Print error
                System.out.println("[ERROR] " + new Date().toString() + " Username not registered: " + userName.getText());
                alertText.setText("ERROR: Username or password incorrect");
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. LoginController.onSignIn()");
        }
        catch (Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " LogInController.onSignIn()" );
            ex.printStackTrace();

        }

    }

    // Log in a non-admin account
    private void logInManager() {
        // Check password
        if (GlobalUser.getPassword().equals(password.getText())) {
            LocalDate ld = LocalDate.now();
            Date today = java.sql.Date.valueOf(ld);
            if (GlobalUser.getPasswordExpired().compareTo(today) >= 0) {
                if (GlobalUser.getSuspendEnd() == null || GlobalUser.getSuspendEnd().compareTo(today) >= 0) {
                    if (GlobalUser.getPasswordAtt() < 4) {
                        try {

                            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
                            Connection conn = DriverManager.getConnection(url, "root", "password");
                            if (conn != null) {
                                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. LoginController.logInManager()");
                            }

                            PreparedStatement setAtt = conn.prepareStatement("update users set passwordAtt = (?) where idusers = (?)");
                            setAtt.setInt(1, 0);
                            setAtt.setInt(2, GlobalUser.getIdUsers());
                            System.out.println("[INFO] " + new Date().toString() + " Begin password attempt reset to 0");
                            setAtt.executeUpdate();
                            System.out.println("[INFO] " + new Date().toString() + " Password attempt reset complete");


                            // Log in
                            SceneSwitch.switchScene("Manager Home.fxml", getClass());
                            System.out.println("[INFO] " + new Date().toString() + " Username / password matched. Transferring scene to manager home page");
                        } catch (Exception ex) {
                            System.out.println("[FATAL ERROR] " + new Date().toString() + " LogInController.logInManager(1)");
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("[ERROR] " + new Date().toString() + " Exceed wrong password attempts for user: " + GlobalUser.getUserName());
                        alertText.setText("ERROR: Exceeded allowed login attempts. Contact the admin.");

                    }
                }
                else {
                    System.out.println("[ERROR] " + new Date().toString() + " Account suspended for user: " + GlobalUser.getUserName());
                    alertText.setText("ERROR: Account is suspended. Contact the admin.");
                }
            } else {
                System.out.println("[ERROR] " + new Date().toString() + " Password expired for user: " + GlobalUser.getUserName());
                alertText.setText("ERROR: Password is expired. Contact the admin or reset through the forgot password tool");
            }
        }
        else {
            int pasAtt = GlobalUser.getPasswordAtt() + 1;
            System.out.println("[ERROR] " + new Date().toString() + " Username / password not matched. Incrementing password attempts in db");
            alertText.setText("ERROR: Username or password incorrect");
            try {
                String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
                Connection conn = DriverManager.getConnection(url, "root", "password");
                if (conn != null) {
                    System.out.println("[INFO] " + new Date().toString() + " Connected to the database. LoginController.logInManager()");
                }

                PreparedStatement setAtt = conn.prepareStatement("update users set passwordAtt = (?) where idusers = (?)");
                setAtt.setInt(1, pasAtt);
                setAtt.setInt(2, GlobalUser.getIdUsers());
                System.out.println("[INFO] " + new Date().toString() + " Begin password attempt update");
                setAtt.executeUpdate();
                System.out.println("[INFO] " + new Date().toString() + " Password attempt update complete");
                conn.close();
                System.out.println("[INFO] " + new Date().toString() + " Database connection closed. LoginController.logInManager()");
            }
            catch (Exception ex) {
                System.out.println("[FATAL ERROR] " + new Date().toString() + " LogInController.logInManager(2)");
                ex.printStackTrace();
            }
            GlobalUser.clearUser();
        }
    }

    // Log in a non-admin account
    private void logInUser() {
        // Check password
        if (GlobalUser.getPassword().equals(password.getText())) {
            LocalDate ld = LocalDate.now();
            Date today = java.sql.Date.valueOf(ld);
            if (GlobalUser.getPasswordExpired().compareTo(today) >= 0) {
                if (GlobalUser.getSuspendEnd() == null || GlobalUser.getSuspendEnd().compareTo(today) >= 0) {
                    if (GlobalUser.getPasswordAtt() < 4) {
                        try {

                            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
                            Connection conn = DriverManager.getConnection(url, "root", "password");
                            if (conn != null) {
                                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. LoginController.logInUser()");
                            }

                            PreparedStatement setAtt = conn.prepareStatement("update users set passwordAtt = (?) where idusers = (?)");
                            setAtt.setInt(1, 0);
                            setAtt.setInt(2, GlobalUser.getIdUsers());
                            System.out.println("[INFO] " + new Date().toString() + " Begin password attempt reset to 0");
                            setAtt.executeUpdate();
                            System.out.println("[INFO] " + new Date().toString() + " Password attempt reset complete");


                            // Log in
                            SceneSwitch.switchScene("User Home.fxml", getClass());
                            System.out.println("[INFO] " + new Date().toString() + " Username / password matched. Transferring scene to user home page");
                        } catch (Exception ex) {
                            System.out.println("[FATAL ERROR] " + new Date().toString() + " LogInController.logInUser(1)");
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("[ERROR] " + new Date().toString() + " Exceed wrong password attempts for user: " + GlobalUser.getUserName());
                        alertText.setText("ERROR: Exceeded allowed login attempts. Contact the admin.");

                    }
                }
                else {
                    System.out.println("[ERROR] " + new Date().toString() + " Account suspended for user: " + GlobalUser.getUserName());
                    alertText.setText("ERROR: Account is suspended. Contact the admin.");
                }
            } else {
                System.out.println("[ERROR] " + new Date().toString() + " Password expired for user: " + GlobalUser.getUserName());
                alertText.setText("ERROR: Password is expired. Contact the admin or reset through the forgot password tool");
            }
        }
        else {
            int pasAtt = GlobalUser.getPasswordAtt() + 1;
            System.out.println("[ERROR] " + new Date().toString() + " Username / password not matched. Incrementing password attempts in db");
            alertText.setText("ERROR: Username or password incorrect");
            try {
                String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
                Connection conn = DriverManager.getConnection(url, "root", "password");
                if (conn != null) {
                    System.out.println("[INFO] " + new Date().toString() + " Connected to the database. LoginController.logInUser()");
                }

                PreparedStatement setAtt = conn.prepareStatement("update users set passwordAtt = (?) where idusers = (?)");
                setAtt.setInt(1, pasAtt);
                setAtt.setInt(2, GlobalUser.getIdUsers());
                System.out.println("[INFO] " + new Date().toString() + " Begin password attempt update");
                setAtt.executeUpdate();
                System.out.println("[INFO] " + new Date().toString() + " Password attempt update complete");
                conn.close();
                System.out.println("[INFO] " + new Date().toString() + " Database connection closed. LoginController.logInUser()");
            }
            catch (Exception ex) {
                System.out.println("[FATAL ERROR] " + new Date().toString() + " LogInController.logInUser(2)");
                ex.printStackTrace();
            }
            GlobalUser.clearUser();
        }
    }

    // log in admin account
    void logInAdmin() {
        // Check password
        if (GlobalUser.getPassword().equals(password.getText())) {
                // Log in
            System.out.println("[INFO] " + new Date().toString() + " Username / password matched. Transferring scene to admin home page");
            SceneSwitch.switchScene("Admin Home.fxml", getClass());
        }
        else {
            System.out.println("[ERROR] " + new Date().toString() + " Username / password not matched. Admin login failed");
            alertText.setText("ERROR: Username or password incorrect");
            GlobalUser.clearUser();
        }
    }

    /**
     * Help Button
     */

    @FXML
    void onHelpBtnPressed(MouseEvent event) {
        System.out.println("[INFO] " + new Date().toString() + " Loading Help Screen" );
        SceneSwitch.switchScene("Help.fxml", getClass(), "Login.fxml");
    }
}
