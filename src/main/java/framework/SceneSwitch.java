package framework;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneSwitch {

    /**
     * Class used to switch to a new window. Use only to load a new fxml document.
     * Example usage: SceneSwitch.switchScene("../fxml/Login.fxml", getClass());
     * @param loc String of fxml file location
     * @param c Class of calling class - Use: getClass()
     */

    static String callingPage;

    public static void switchScene(String loc, Class c) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(c.getResource(loc));
            Parent logIn = (Parent) fxmlLoader.load();
            Main.getPrimaryStage().setScene(new Scene(logIn));
            Main.getPrimaryStage().show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void switchScene(String loc, Class c, String page) {
        callingPage = page;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(c.getResource(loc));
            Parent logIn = (Parent) fxmlLoader.load();
            Main.getPrimaryStage().setScene(new Scene(logIn));
            Main.getPrimaryStage().show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void switchBack(Class c) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(c.getResource(callingPage));
            Parent logIn = (Parent) fxmlLoader.load();
            Main.getPrimaryStage().setScene(new Scene(logIn));
            Main.getPrimaryStage().show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
