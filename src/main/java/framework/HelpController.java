package framework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Date;

public class HelpController {


    @FXML
    private Text titleTxt;

    @FXML
    private Text contentText;

    @FXML
    public void initialize() {
        System.out.println("[INFO] " + new Date().toString() + " Help page loaded.");

        titleTxt.setText("Help");
        contentText.setText("We need to add this content\n");
    }

    @FXML
    void onAccountsClicked(MouseEvent event) {
        titleTxt.setText("Accounts");
        contentText.setText("We need to add this content\n");
    }

    @FXML
    void onEventLogClicked(MouseEvent event) {
        titleTxt.setText("Event Log");
        contentText.setText("We need to add this content\n");
    }

    @FXML
    void onJournalsClicked(MouseEvent event) {
        titleTxt.setText("Journals");
        contentText.setText("We need to add this content\n");
    }

    @FXML
    void onMessagingClicked(MouseEvent event) {
        titleTxt.setText("Messaging");
        contentText.setText("We need to add this content\n");
    }

    @FXML
    void onUserClicked(MouseEvent event) {
        titleTxt.setText("Users");
        contentText.setText("We need to add this content\n");
    }

    @FXML
    void onReturnPressed(MouseEvent event) {
        SceneSwitch.switchBack(getClass());
        System.out.println("[INFO] " + new Date().toString() + " Help page exited. Returning to: " + SceneSwitch.callingPage);
    }



}