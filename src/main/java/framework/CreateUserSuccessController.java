package framework;


import framework.GlobalUser;
import framework.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


import java.util.Date;


public class CreateUserSuccessController {

    @FXML
    private Text usernameTxt;

    @FXML
    private Text passwordTxt;

    @FXML
    private Text accountTypeTxt;

    @FXML
    public void initialize() {
        GlobalUser.setUser(GlobalUser.getIdUsers());
        String acctType;
        if (GlobalUser.getAccountant() == 1)
            acctType = "Accountant";
        else if (GlobalUser.getManager() == 1)
            acctType = "Manager";
        else if (GlobalUser.getAdmin() == 1)
            acctType = "Admin";
        else {
            acctType = "Error contact admin";
            System.out.println("[FATAL ERROR] " + new Date().toString() + "GlobalUser account type not set");
        }

        usernameTxt.setText("Username: " + GlobalUser.getUserName());
        accountTypeTxt.setText("Account Type: " + acctType);
        System.out.println("[INFO] " + new Date().toString() + " GlobalUser fields filled for create user success screen");
    }

    @FXML
    void onReturnToLogin(ActionEvent event) {
        SceneSwitch.switchScene("Login.fxml", getClass());
        System.out.println("[INFO] " + new Date().toString() + " Clearing create user success screen. Returning to login screen. Clearing global user");
        GlobalUser.clearUser();
    }

    @FXML
    void onShowPassPressed(MouseEvent event) {
        passwordTxt.setText("Password: " + GlobalUser.getPassword());
        System.out.println("[INFO] " + new Date().toString() + " Password shown to user");
    }

    @FXML
    void onShowPassReleased(MouseEvent event) {
        passwordTxt.setText("Password: **********");
        System.out.println("[INFO] " + new Date().toString() + " Password removed from screen");
    }

}
