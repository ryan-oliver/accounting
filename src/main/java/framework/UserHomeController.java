package framework;

import framework.GlobalUser;
import framework.SceneSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// todo - add search from admin

/**
 * This class controls the user page after login. Currently used for manager and for accountant.
 */

public class UserHomeController {

    private final Color REG_BUTTON_COLOR = Color.web("#a4a6a8");
    private final Color HOV_BUTTON_COLOR = Color.web("#949698");

    private final Color REG_SEARCH_COLOR = Color.web("424242");
    private final Color HOV_SEARCH_COLOR = Color.web("#4d4b4b");

    private final String UNFILLED_ACCT_NAME = "Account Name: ";
    private final String UNFILLED_ACCT_NUM = "Account Number: ";
    private final String UNFILLED_ACCT_DESC = "Account Description: ";
    private final String UNFILLED_CREATED = "Created: ";
    private final String UNFILLED_NORM_SIDE = "Normal Side: ";
    private final String UNFILLED_INIT_BAL = "Initial Balance: ";
    private final String UNFILLED_BAL = "Balance: ";
    private final String UNFILLED_STATEMENT = "Statement: ";
    private final String UNFILLED_ACTIVE = "Active: ";
    private final String UNFILLED_CAT = "Category: ";
    private final String UNFILLED_SUBCAT = "Subcategory: ";

    @FXML
    private Rectangle homeBtn;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Rectangle logoutBtn;

    @FXML
    private Text userName;

    @FXML
    private Rectangle accountsBtn;

    @FXML
    private AnchorPane accountsPane;

    @FXML
    private TableView<Map> accountsTableView;

    @FXML
    private TableColumn<?, ?> accountNumberCol;

    @FXML
    private TableColumn<?, ?> accountNameCol;

    @FXML
    private TableColumn<?, ?> accountTypeCol;

    @FXML
    private TableColumn<?, ?> statementCol;

    @FXML
    private Text alertTextAccountList;

    @FXML
    private Rectangle searchBtn;

    @FXML
    private Text accountAlertText;

    @FXML
    private AnchorPane singleAccountPane;

    @FXML
    private Text accNameTxt;

    @FXML
    private Text accNumTxt;

    @FXML
    private Text accDescTxt;

    @FXML
    private Text normalSideTxt;

    @FXML
    private Text categoryTxt;

    @FXML
    private Text subcatTxt;

    @FXML
    private Text initBalTxt;

    @FXML
    private Text balTxt;

    @FXML
    private Text createdTxt;

    @FXML
    private Text activeTxt;

    @FXML
    private Text statementTxt;

    @FXML
    private Button returnToAllAccountsBtn;

    /**
     * Called before loading pane. Loads data.
     */
    @FXML
    public void initialize() {
        userName.setText(GlobalUser.getUserName());
        System.out.println("[INFO] " + new Date().toString() + " User home page init success");
        accountsPane.setVisible(false);
        singleAccountPane.setVisible(false);
        formatAcctTable();
        loadAccounts();
        setDoubleClickOpenAcct();
    }

    /**
     * Home button methods
     * @param event
     */

    @FXML
    void onHomeBtnEntered(MouseEvent event) {
        homeBtn.setFill(HOV_BUTTON_COLOR);
    }

    @FXML
    void onHomeBtnExited(MouseEvent event) {
        homeBtn.setFill(REG_BUTTON_COLOR);
    }

    @FXML
    void onHomeClicked(MouseEvent event) {
        accountsPane.setVisible(false);
        singleAccountPane.setVisible(false);
    }

    /**
     * Logout button methods
     * @param event
     */

    @FXML
    void onLogoutBtnEntered(MouseEvent event) {
        logoutBtn.setFill(HOV_BUTTON_COLOR);
    }

    @FXML
    void onLogoutBtnExited(MouseEvent event) {
        logoutBtn.setFill(REG_BUTTON_COLOR);
    }

    @FXML
    void onLogoutClicked(MouseEvent event) {
        System.out.println("[INFO] " + new Date().toString() + " Logging out user. Global user object cleared. Returning to login screen" );
        GlobalUser.clearUser();
        SceneSwitch.switchScene("Login.fxml", getClass());
    }

    /**\
     * Accounts view methods
     * @param event
     */

    @FXML
    void onAccountsClicked(MouseEvent event) {
        singleAccountPane.setVisible(false);
        accountsPane.setVisible(true);
    }

    @FXML
    void onAccountsEntered(MouseEvent event) {
        accountsBtn.setFill(HOV_BUTTON_COLOR);
    }

    @FXML
    void onAccountsExited(MouseEvent event) {
        accountsBtn.setFill(REG_BUTTON_COLOR);
    }

    // Returns single account view text to default
    @FXML
    void onReturnPressed(ActionEvent event) {
        accNameTxt.setText(UNFILLED_ACCT_NAME);
        accNumTxt.setText(UNFILLED_ACCT_NUM);
        accDescTxt.setText(UNFILLED_ACCT_DESC);
        createdTxt.setText(UNFILLED_CREATED);
        normalSideTxt.setText(UNFILLED_NORM_SIDE);
        initBalTxt.setText(UNFILLED_INIT_BAL);
        balTxt.setText(UNFILLED_BAL);
        statementTxt.setText(UNFILLED_STATEMENT);
        activeTxt.setText(UNFILLED_ACTIVE);
        categoryTxt.setText(UNFILLED_CAT);
        subcatTxt.setText(UNFILLED_SUBCAT);

        Account.clearAccount();

        singleAccountPane.setVisible(false);
        accountsPane.setVisible(true);
    }

    @FXML
    void onSearchClicked(MouseEvent event) {

    }

    @FXML
    void onSearchEntered(MouseEvent event) {
        searchBtn.setFill(HOV_SEARCH_COLOR);
    }

    @FXML
    void onSearchExited(MouseEvent event) {
        searchBtn.setFill(REG_SEARCH_COLOR);
    }

    // Used to set up account table mapping
    private void formatAcctTable() {
        System.out.println("[INFO] " + new Date().toString() + " Formatting account table design");
        accountNumberCol.setCellValueFactory(new MapValueFactory("accountNum"));
        accountNameCol.setCellValueFactory(new MapValueFactory("accountName"));
        accountTypeCol.setCellValueFactory(new MapValueFactory("category"));
        statementCol.setCellValueFactory(new MapValueFactory("statement"));
    }

    // Used to load accounts to table
    private void loadAccounts() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all accounts to table");
        String sqlQry = "SELECT accountNum, accountName, category, statement FROM app_domain.accounts";
        accountsTableView.setItems(buildAccountData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Accounts load to table success");
    }

    // Used to build data for accounts table
    private ObservableList<Map> buildAccountData(String query) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.buildAccountData()");
            }

            System.out.println("[INFO] " + new Date().toString() + " Building table data from sql query");
            ObservableList<Map> accountList = FXCollections.observableArrayList();
            PreparedStatement getAccounts = conn.prepareStatement(query);
            ResultSet rs = getAccounts.executeQuery();
            while (rs.next()) {
                Map<String, String> account = new HashMap<>();
                account.put("accountNum", String.valueOf(rs.getString("accountNum")));
                account.put("accountName", String.valueOf(rs.getString("accountName")));
                account.put("category", String.valueOf(rs.getString("category")));
                account.put("statement", String.valueOf(rs.getString("statement")));
                accountList.add(account);
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Returning table data");
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.buildAccountData()");
            return accountList;
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.buildAccountData()");
            ex.printStackTrace();
            return null;
        }
    }

    // Sets account table mouse action
    private void setDoubleClickOpenAcct() {
        accountsTableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String accountNum = (String) accountsTableView.getSelectionModel().getSelectedItem().get("accountNum");
                loadThisAccount(accountNum);
            }
        });
    }

    // Helper for table mouse action. Sets single account view text information.
    private void loadThisAccount(String accNum) {

        Account.setAccount(accNum);

        accNameTxt.setText(UNFILLED_ACCT_NAME + Account.getAccountName());
        accNumTxt.setText(UNFILLED_ACCT_NUM + Account.getAccountNumber());
        accDescTxt.setText(UNFILLED_ACCT_DESC + Account.getAccountDescription());
        createdTxt.setText(UNFILLED_CREATED + Account.getCreated());
        normalSideTxt.setText(UNFILLED_NORM_SIDE + Account.getNormalSide());
        initBalTxt.setText(UNFILLED_INIT_BAL + Account.getInitialBalance());
        balTxt.setText(UNFILLED_BAL + Account.getBalance());
        statementTxt.setText(UNFILLED_STATEMENT + Account.getStatement());
        activeTxt.setText(UNFILLED_ACTIVE + Account.getActive());
        categoryTxt.setText(UNFILLED_CAT + Account.getCategory());
        subcatTxt.setText(UNFILLED_SUBCAT + Account.getSubcategory());

        accountsPane.setVisible(false);
        singleAccountPane.setVisible(true);
    }

}

