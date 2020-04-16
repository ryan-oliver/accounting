package framework;

import framework.GlobalUser;
import framework.SceneSwitch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

    private final String UNFILLED_FROM = "From: ";
    private final String UNFILLED_SUBJ = "Subject: ";
    private final String UNFILLED_DATE = "Date: ";
    private final String UNFILLED_MSG = "";

    private final String UNFILLLED_JOURN_NAME = "Name: ";
    private final String UNFILLLED_JOURN_DATE = "Date: ";
    private final String UNFILLLED_JOURN_DESCRIP = "Description: ";
    private final String UNFILLLED_JOURN_APPROV = "Approval: ";
    private final String UNFILLLED_JOURN_STATUS = "Status: ";

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



    @FXML
    private ImageView mailIcon;

    @FXML
    private Rectangle mailBtn;

    @FXML
    private AnchorPane mailPane;

    @FXML
    private AnchorPane mailTablePane;

    @FXML
    private TableView<Map> mailTable;

    @FXML
    private TableColumn mailMessageCol;

    @FXML
    private TableColumn mailSubjectCol;

    @FXML
    private TableColumn mailFromCol;

    @FXML
    private TableColumn mailDateCol;

    @FXML
    private Button newMessageBtn;

    @FXML
    private AnchorPane openMessagePane;

    @FXML
    private Button mailReturnBtn;

    @FXML
    private Button mailReplyBtn;

    @FXML
    private Text msgFromTxt;

    @FXML
    private Text msgDateTxt;

    @FXML
    private Text msgSubjectTxt;

    @FXML
    private Text msgContentTxt;

    @FXML
    private AnchorPane newMessagePane;

    @FXML
    private Button newMessageSendBtn;

    @FXML
    private Button cancelNewMessageBtn;

    @FXML
    private TextField newMessageToFld;

    @FXML
    private TextField newMessageSubjectFld;

    @FXML
    private TextArea newMessageFld;

    @FXML
    private TextField searchTxtFld;


    @FXML
    private RadioButton accountNumberRdo;

    @FXML
    private RadioButton accountNameRdo;

    @FXML
    private RadioButton accountTypeRdo;

    @FXML
    private RadioButton statementRdo;

    @FXML
    private ToggleGroup tgSearch;

    @FXML
    private TableView<Map> logTable;

    @FXML
    private TableColumn logIDCol;

    @FXML
    private TableColumn logUIDCol;

    @FXML
    private TableColumn logActCol;

    @FXML
    private TableColumn logAccNumCol;

    @FXML
    private TableColumn logMessageCol;

    @FXML
    private AnchorPane logPane;

    @FXML
    private Rectangle logBtn;

    @FXML
    private Rectangle journBtn;

    @FXML
    private AnchorPane journalPane;

    // Add journal pane elements here

    @FXML
    private AnchorPane newJournalPane;

    // Add new journal pane elements here

    @FXML
    private TextField journNameFld;

    @FXML
    private TextField journDescFld;

    @FXML
    private TextField journEntCreditFld;

    @FXML
    private TextField journEntDebitFld;

    @FXML
    private TextField journEntMemoFld;

    @FXML
    private DatePicker journEntDate;

    @FXML
    private TableView newJournTable;

    @FXML
    private TableColumn newJournAccountCol;

    @FXML
    private TableColumn newJournCreditCol;

    @FXML
    private TableColumn newJournDebitCol;

    @FXML
    private TableColumn newJournDateCol;

    @FXML
    private TableColumn newJournMemoCol;

    @FXML
    private Text newJournErrorTxt;

    @FXML
    private ComboBox<String> accountComboBox;

    @FXML
    private TableView<Map> allJournalsTableView;

    @FXML
    private TableColumn allJournNameCol;

    @FXML
    private TableColumn allJournDateCol;

    @FXML
    private TableColumn allJournDescriptionCol;

    @FXML
    private TableColumn allJournStatusCol;

    @FXML
    private TableColumn allJournPendingCol;

    @FXML
    private TextField journSearchFld;

    @FXML
    private CheckBox approvedChkBx;

    @FXML
    private CheckBox pendingChkBx;

    @FXML
    private AnchorPane singleJournPane;

    @FXML
    private TableView<Map> singleJournTable;

    @FXML
    private TableColumn singleJournAccountCol;

    @FXML
    private TableColumn singleJournCreditCol;

    @FXML
    private TableColumn singleJournDebitCol;

    @FXML
    private TableColumn singleJournDateCol;

    @FXML
    private TableColumn singleJournMemoCol;

    @FXML
    private Text journNameTxt;

    @FXML
    private Text journDescTxt;

    @FXML
    private Text journDateTxt;

    @FXML
    private Text journApprovalTxt;

    @FXML
    private Text journStatusTxt;

    @FXML
    private TableView<Map> ledgerTableView;

    @FXML
    private TableColumn ledgerDateCol;

    @FXML
    private TableColumn ledgerDescCol;

    @FXML
    private TableColumn jIDLedgerCol;

    @FXML
    private TableColumn ledgerDebitCol;

    @FXML
    private TableColumn ledgerCreditCol;

    @FXML
    private TableColumn ledgerBalCol;


    /**
     * Called before loading pane. Loads data.
     */
    @FXML
    public void initialize() {
        userName.setText(GlobalUser.getUserName());
        System.out.println("[INFO] " + new Date().toString() + " User home page init success");
        deactivateAllPanes();

        formatAcctTable();
        formatMesgTable();
        formatLogTable();
        formatJournalTable();
        formatJournalEntryTable();
        formatSingleJournalTable();
        formatLedgerTable();



        setDoubleClickOpenMessage();
        setShowApprovedJourns();
        setShowPendingJourns();
        setDoubleClickOpenAcct();
        setDoubleClickOpenJournal();
        setDoubleClickOpenJournalFromLedger();
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
        deactivateAllPanes();
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
        deactivateAllPanes();
        loadAccounts();
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
        if (searchTxtFld.getText() != null && tgSearch.getSelectedToggle() != null) {
            if (searchTxtFld.getText().equals("")) {
                loadAccounts();
            }
            else {
                String searchString = "\"" + searchTxtFld.getText() + "\"";
                String colString = "";
                if (accountNumberRdo.isSelected())
                    colString = "accountNum";
                else if (accountNameRdo.isSelected())
                    colString = "accountName";
                else if (accountTypeRdo.isSelected())
                    colString = "category";
                else if (statementRdo.isSelected())
                    colString = "statement";

                System.out.println("[INFO] " + new Date().toString() + " Loading search to table");
                String sqlQry = "SELECT accountNum, accountName, category, statement FROM app_domain.accounts where " + colString + " = " + searchString;
                accountsTableView.setItems(buildAccountData(sqlQry));
                System.out.println("[INFO] " + new Date().toString() + " Accounts load to table success");
            }
        }
        else {
            loadAccounts();
        }
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

        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.loadThisAccount()");
            }

            System.out.println("[INFO] " + new Date().toString() + " Getting account id from sql query");
            PreparedStatement getAccounts = conn.prepareStatement("select idaccounts from accounts where accountNum = (?)");
            getAccounts.setString(1, accNum);
            ResultSet rs = getAccounts.executeQuery();
            int accId = -1;
            while (rs.next()) {
                accId = rs.getInt(1);
            }
            loadLedger(accId);
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Returning account table data");
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.buildAccountData()");
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.buildAccountData()");
            ex.printStackTrace();
        }

        deactivateAllPanes();
        singleAccountPane.setVisible(true);
    }

    private void setDoubleClickOpenJournalFromLedger() {
        System.out.println("[INFO] " + new Date().toString() + " Setting journal table mouse actions");
        ledgerTableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String journalid = (String) ledgerTableView.getSelectionModel().getSelectedItem().get("journal_id");
                loadThisJournal(journalid);
            }

        });
    }

    // This method builds the data for the account table view. It is assembling a Map to hold the values.
    private ObservableList<Map> buildLedgerData(String query) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.loadLedger()");
            }

            System.out.println("[INFO] " + new Date().toString() + " Building ledger table data from sql query");
            ObservableList<Map> ledgerList = FXCollections.observableArrayList();
            PreparedStatement getLedger = conn.prepareStatement(query);
            ResultSet rs = getLedger.executeQuery();
            while (rs.next()) {
                Map<String, String> ledger = new HashMap<>();
                ledger.put("date", String.valueOf(rs.getDate("date")));
                ledger.put("description", rs.getString("description"));
                ledger.put("debit", rs.getString("debit"));
                ledger.put("credit", rs.getString("credit"));
                ledger.put("balance", rs.getString("balance"));
                ledger.put("journal_id", rs.getString("journal_id"));
                ledgerList.add(ledger);
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Returning ledger table data");
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.loadLedger()");
            return ledgerList;
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.loadLedger()");
            ex.printStackTrace();
            return null;
        }
    }

    private void loadLedger(int accId) {
        System.out.println("[INFO] " + new Date().toString() + " Loading ledger to table");
        String sqlQry = "SELECT * FROM ledger where account_id = " + accId;
        ledgerTableView.setItems(buildLedgerData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Ledger load to table success");
    }

    void formatLedgerTable() {
        System.out.println("[INFO] " + new Date().toString() + " Formatting ledger table design");
        ledgerDateCol.setCellValueFactory(new MapValueFactory("date"));
        ledgerBalCol.setCellValueFactory(new MapValueFactory("balance"));
        ledgerDescCol.setCellValueFactory(new MapValueFactory("description"));
        jIDLedgerCol.setCellValueFactory(new MapValueFactory("journal_id"));
        ledgerDebitCol.setCellValueFactory(new MapValueFactory("debit"));
        ledgerCreditCol.setCellValueFactory(new MapValueFactory("credit"));
    }

    /**
     * Event Log methods
     */

    @FXML
    void onLogClicked() {
        deactivateAllPanes();
        loadLogs();
        System.out.println("[INFO] " + new Date().toString() + " Clearing all panes. Showing Event Log Pane");
        logPane.setVisible(true);
    }

    @FXML
    void onLogEntered() {
        logBtn.setFill(HOV_BUTTON_COLOR);
    }

    @FXML
    void onLogExited() {
        logBtn.setFill(REG_BUTTON_COLOR);
    }

    // This method calls the buildLogData() method. Used to refresh log table
    private void loadLogs() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all logs to table");
        String sqlQry = "SELECT * FROM app_domain.event_log";
        logTable.setItems(buildLogData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Logs load to table success");
    }

    // This method formats the log table
    private void formatLogTable() {
        System.out.println("[INFO] " + new Date().toString() + " Formatting log table design");
        logIDCol.setCellValueFactory(new MapValueFactory("idevent_log"));
        logUIDCol.setCellValueFactory(new MapValueFactory("userid"));
        logActCol.setCellValueFactory(new MapValueFactory("action"));
        logAccNumCol.setCellValueFactory(new MapValueFactory("account_number"));
        logMessageCol.setCellValueFactory(new MapValueFactory("message"));
    }

    // This method builds the data for the event log table view. It is assembling a Map to hold the values.
    private ObservableList<Map> buildLogData(String query) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.buildLogData()");
            }

            System.out.println("[INFO] " + new Date().toString() + " Building log table data from sql query");
            ObservableList<Map> logList = FXCollections.observableArrayList();
            PreparedStatement getLogs = conn.prepareStatement(query);
            ResultSet rs = getLogs.executeQuery();
            while (rs.next()) {
                Map<String, String> log = new HashMap<>();
                log.put("idevent_log", String.valueOf(rs.getString("idevent_log")));
                log.put("userid", String.valueOf(rs.getString("userid")));
                log.put("action", String.valueOf(rs.getString("action")));
                log.put("account_number", String.valueOf(rs.getString("account_number")));
                log.put("message", String.valueOf(rs.getString("message")));
                logList.add(log);
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Returning log table data");
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.buildLogData()");
            return logList;
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.buildLogData()");
            ex.printStackTrace();
            return null;
        }
    }

    // Set all panes to invisible
    private void deactivateAllPanes() {
        accountsPane.setVisible(false);
        singleAccountPane.setVisible(false);
        mailPane.setVisible(false);
        mailTablePane.setVisible(false);
        openMessagePane.setVisible(false);
        newMessagePane.setVisible(false);
        journalPane.setVisible(false);
        newJournalPane.setVisible(false);
        logPane.setVisible(false);
        singleJournPane.setVisible(false);
    }

    /**
     * Journal Methods
     */

    @FXML
    void onJournClicked() {
        deactivateAllPanes();
        loadJournalTable();
        journalPane.setVisible(true);
    }

    void formatJournalTable() {
        System.out.println("[INFO] " + new Date().toString() + " Formatting journal table design");
        allJournNameCol.setCellValueFactory(new MapValueFactory("name"));
        allJournDateCol.setCellValueFactory(new MapValueFactory("date"));
        allJournDescriptionCol.setCellValueFactory(new MapValueFactory("description"));
        allJournStatusCol.setCellValueFactory(new MapValueFactory("approved"));
        allJournPendingCol.setCellValueFactory(new MapValueFactory("status"));
    }

    @FXML
    void journSearchBtnClicked(MouseEvent event) {
        if (journSearchFld.getText() != null) {
            if (journSearchFld.getText().equals("")) {
                loadJournalTable();
            }
            else {
                String searchString = "\"" + journSearchFld.getText() + "\"";

                System.out.println("[INFO] " + new Date().toString() + " Loading journal search to table");
                String sqlQry = "SELECT * FROM journal where name = " + searchString;
                allJournalsTableView.setItems(buildJournalTable(sqlQry));
                System.out.println("[INFO] " + new Date().toString() + " Journal load to table success");
            }
        }
        else {
            loadJournalTable();
        }
    }

    private void loadJournalTable() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all journals to table");
        String sqlQry = "SELECT * FROM journal";
        allJournalsTableView.setItems(buildJournalTable(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Journals load to table success");
    }

    private void  loadJournalTableAppr() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all journals to table");
        String sqlQry = "SELECT * FROM journal where approved = 1";
        allJournalsTableView.setItems(buildJournalTable(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Journals load to table success");
    }

    private void loadJournalTablePend() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all journals to table");
        String sqlQry = "SELECT * FROM journal where status = 0";
        allJournalsTableView.setItems(buildJournalTable(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Journals load to table success");
    }

    private void setDoubleClickOpenJournal() {
        System.out.println("[INFO] " + new Date().toString() + " Setting journal table mouse actions");
        allJournalsTableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String journalid = (String) allJournalsTableView.getSelectionModel().getSelectedItem().get("id");
                loadThisJournal(journalid);
            }

        });
    }

    // todo - fix dates
    private void loadThisJournal(String id) {
        // sql get info and all jes using journal id
        Journal.reopenJournal(id);
        deactivateAllPanes();

        loadSingleJournEnt();

        journNameTxt.setText(UNFILLLED_JOURN_NAME + Journal.getName());
        journDateTxt.setText(UNFILLLED_JOURN_DATE + Journal.getDate());
        journApprovalTxt.setText(UNFILLLED_JOURN_APPROV + Journal.getApproval());
        journDescTxt.setText(UNFILLLED_JOURN_DESCRIP + Journal.getDescription());
        journStatusTxt.setText(UNFILLLED_JOURN_STATUS + Journal.getStatus());

        singleJournPane.setVisible(true);
    }

    @FXML
    void onJournalReturnBtnClicked(MouseEvent event) {
        deactivateAllPanes();
        journalPane.setVisible(true);
        Journal.clearSingleJournal();
        loadJournalTable();
        journNameTxt.setText(UNFILLLED_JOURN_NAME);
        journDateTxt.setText(UNFILLLED_JOURN_DATE);
        journApprovalTxt.setText(UNFILLLED_JOURN_APPROV);
        journDescTxt .setText(UNFILLLED_JOURN_DESCRIP);
        journStatusTxt.setText(UNFILLLED_JOURN_STATUS);
    }

    private ObservableList<Map> buildJournalTable(String query) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.buildJournalData()");
            }

            System.out.println("[INFO] " + new Date().toString() + " Building journal table data from sql query");
            ObservableList<Map> journalList = FXCollections.observableArrayList();
            PreparedStatement getJournals = conn.prepareStatement(query);
            ResultSet rs = getJournals.executeQuery();
            while (rs.next()) {
                Map<String, String> log = new HashMap<>();
                log.put("id", String.valueOf(rs.getInt("idjournal")));
                log.put("name", String.valueOf(rs.getString("name")));
                log.put("date", String.valueOf(rs.getString("date")));
                log.put("description", String.valueOf(rs.getString("description")));
                if (rs.getInt("approved") == 1) {
                    log.put("approved", "Approved");
                }
                else if (rs.getInt("approved") == 0) {
                    log.put("approved", "Declined");
                }
                else {
                    log.put("approved", "Pending");
                }
                if (rs.getInt("status") == 1) {
                    log.put("status", "Resolved");
                }
                else {
                    log.put("status", "Pending");
                }
                journalList.add(log);
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Returning journal table data");
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.buildJournalData()");
            return journalList;
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.buildLogData()");
            ex.printStackTrace();
            return null;
        }
    }

    @FXML
    void onJournEntered() {
        journBtn.setFill(HOV_BUTTON_COLOR);
    }

    @FXML
    void onJournExited() {
        journBtn.setFill(REG_BUTTON_COLOR);
    }

    @FXML
    void onNewJournEntry(MouseEvent event) {
        Journal.makeNewJournal();
        loadAccountNamesForJournalCmbBx();
        deactivateAllPanes();
        newJournalPane.setVisible(true);
    }

    @FXML
    void onCancelNewJournal(MouseEvent event) {
        journNameFld.setText("");
        journDescFld.setText("");
        journEntCreditFld.setText("");
        journEntDebitFld.setText("");
        journEntDate.setValue(null);
        journEntMemoFld.setText("");

        deactivateAllPanes();
        journalPane.setVisible(true);
        newJournTable.setItems(null);
        Journal.clearJournal();
    }

    void loadAccountNamesForJournalCmbBx() {
        accountComboBox.getSelectionModel().clearSelection();
        accountComboBox.setValue(null);
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
            }
            String sqlQry = "SELECT accountName FROM app_domain.accounts";
            PreparedStatement getAccounts = conn.prepareStatement(sqlQry);
            ResultSet rs = getAccounts.executeQuery();
            ArrayList<String> names = new ArrayList<>();
            while (rs.next()) {
                names.add(rs.getString(1));
            }
            conn.close();
            ObservableList<String> accTypes = FXCollections.observableArrayList(names);
            accountComboBox.setItems(accTypes);
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.loadAccountNamesForJournalCmbBx()");
            ex.printStackTrace();
        }
    }

    @FXML
    void onJournAddClicked(MouseEvent event) {
        if (!(journEntDate.getValue() == null)) {
            if (!(journEntCreditFld.getText().equals("") && journEntDebitFld.getText().equals("")) && !(!(journEntCreditFld.getText().equals("")) && !(journEntDebitFld.getText().equals("")))) {
                if (!accountComboBox.getSelectionModel().isEmpty()) {
                    newJournErrorTxt.setText("");
                    String credit = journEntCreditFld.getText();
                    String account = accountComboBox.getSelectionModel().getSelectedItem();
                    String debit = journEntDebitFld.getText();
                    String memo = journEntMemoFld.getText();
                    java.sql.Date date = java.sql.Date.valueOf(journEntDate.getValue());
                    int journalKey = Journal.getJournalId();
                    int accountKey = -1;

                    try {
                        String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
                        Connection conn = DriverManager.getConnection(url, "root", "password");
                        if (conn != null) {
                            System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.onJournAddClicked()");
                        }
                        // Checking for username in db
                        PreparedStatement acctNumFind = conn.prepareStatement("SELECT idaccounts FROM accounts WHERE accountName = (?)");
                        acctNumFind.setString(1, account);
                        System.out.println("[INFO] " + new Date().toString() + " Begin account query");
                        ResultSet rs = acctNumFind.executeQuery();
                        System.out.println("[INFO] " + new Date().toString() + " Acct query successful");
                        while (rs.next()) {
                            accountKey = rs.getInt(1);
                        }
                        conn.close();
                        System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.onJournAddClicked()");
                    } catch (Exception ex) {
                        System.out.println("Error connecting to db");
                        ex.printStackTrace();
                    }

                    JournalEntry je = new JournalEntry(credit, debit, memo, date, journalKey, accountKey, account);
                } else {
                    newJournErrorTxt.setText("ERROR: Select an account.");
                }
            }
            else {
                newJournErrorTxt.setText("ERROR: Enter only credit or debit.");
            }
        }
        else {
            newJournErrorTxt.setText("ERROR: Please select a date.");
        }
        loadJournEnt();
    }

    private void loadJournEnt() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all je's to table");
        newJournTable.setItems(loadNewJournalTable());
        System.out.println("[INFO] " + new Date().toString() + " JE's load to table success");
    }

    private void loadSingleJournEnt() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all je's to table");
        singleJournTable.setItems(loadNewJournalTableSingle());
        System.out.println("[INFO] " + new Date().toString() + " JE's load to table success");
    }

    private void formatJournalEntryTable() {
        newJournAccountCol.setCellValueFactory(new MapValueFactory("account"));
        newJournCreditCol.setCellValueFactory(new MapValueFactory("credit"));
        newJournDebitCol.setCellValueFactory(new MapValueFactory("debit"));
        newJournDateCol.setCellValueFactory(new MapValueFactory("date"));
        newJournMemoCol.setCellValueFactory(new MapValueFactory("memo"));
    }

    private void formatSingleJournalTable() {
        singleJournAccountCol.setCellValueFactory(new MapValueFactory("account"));
        singleJournCreditCol.setCellValueFactory(new MapValueFactory("credit"));
        singleJournDebitCol.setCellValueFactory(new MapValueFactory("debit"));
        singleJournDateCol.setCellValueFactory(new MapValueFactory("date"));
        singleJournMemoCol.setCellValueFactory(new MapValueFactory("memo"));
    }

    private ObservableList<Map> loadNewJournalTable() {
        // load values to the table following logic from loadAccounts method

        ObservableList<Map> journEntryList = FXCollections.observableArrayList();
        ArrayList<JournalEntry> entryList = Journal.getListOfEntries();
        for (JournalEntry je : entryList) {
            Map<String, String> journEnt = new HashMap<>();
            journEnt.put("account", je.getAccountName());
            journEnt.put("credit", je.getCredit());
            journEnt.put("debit", je.getDebit());
            journEnt.put("date", String.valueOf(je.getDate()));
            journEnt.put("memo", je.getMemo());
            journEntryList.add(journEnt);
        }
        return journEntryList;
    }

    private ObservableList<Map> loadNewJournalTableSingle() {
        // load values to the table following logic from loadAccounts method

        ObservableList<Map> journEntryList = FXCollections.observableArrayList();
        ArrayList<JournalEntry> entryList = Journal.getListOfEntriesForSingleView();
        for (JournalEntry je : entryList) {
            Map<String, String> journEnt = new HashMap<>();
            journEnt.put("account", je.getAccountName());
            journEnt.put("credit", je.getCredit());
            journEnt.put("debit", je.getDebit());
            journEnt.put("date", String.valueOf(je.getDate()));
            journEnt.put("memo", je.getMemo());
            journEntryList.add(journEnt);
        }
        return journEntryList;
    }

    @FXML
    void onJournSubmitClicked(MouseEvent event) {
        if (verifyJournFields()) {
            if (verifyDebitsCredits()) {
                if (verifyDebitsDontExceedBalance()) {
                    if (!(Journal.getListOfEntries().size() <= 0)) {
                        postJourn();
                    }
                    else {
                        newJournErrorTxt.setText("ERROR: No journal entries to submit.");
                    }
                }
                else {
                    newJournErrorTxt.setText("ERROR: Debit exceeds balance");
                }
            }
            else {
                newJournErrorTxt.setText("ERROR: Debits and credits do not match.");
            }
        }
        else {
            newJournErrorTxt.setText("ERROR: Please enter journal name and description.");
        }
    }

    static String changeToDouble(String num) {
        String str = "";
        char[] strToAry = num.toCharArray();
        for (char c : strToAry) {
            if (!(c == ',')) {
                str = str + c;
            }
        }
        return str;

    }

    private boolean verifyDebitsDontExceedBalance() {
        ArrayList<JournalEntry> je = Journal.getListOfEntries();
        double debit = 0;
        for (JournalEntry j : je) {
            if (j.getCredit().equals("")) {
                debit = Double.valueOf(j.getDebit());
                try {
                    String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
                    Connection conn = DriverManager.getConnection(url, "root", "password");
                    if (conn != null) {
                        System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.verifyDDEB()");
                    }
                    PreparedStatement ps = conn.prepareStatement("select balance from accounts where idaccounts = " + j.getAccountKey());
                    ResultSet rs = ps.executeQuery();
                    String balance = "";
                    while (rs.next()) {
                        balance = rs.getString(1);
                    }
                    double numBal = Double.valueOf(changeToDouble(balance));
                    if (debit > numBal) {
                        return false;
                    }
                } catch (Exception ex) {
                    System.out.println("Error connecting to db");
                    ex.printStackTrace();
                }
            }

        }
        return true;
    }

    private void postJourn() {
        // post
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.postJourn()");
            }
            // Checking for username in db
            PreparedStatement postJourn = conn.prepareStatement("INSERT INTO journal values (?,?,?,?,3,0)");
            postJourn.setInt(1, Journal.getJournalId());
            postJourn.setString(2, journNameFld.getText());
            Date date = new Date();
            java.sql.Date now = new java.sql.Date(date.getTime());
            postJourn.setDate(3, now);
            postJourn.setString(4, journDescFld.getText());
            postJourn.executeUpdate();

            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.postJourn()");

            ArrayList<JournalEntry> je = Journal.getListOfEntries();
            for (JournalEntry j : je) {
                conn = DriverManager.getConnection(url, "root", "password");
                if (conn != null) {
                    System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.postJourn()");
                }

                PreparedStatement postJournEnt = conn.prepareStatement("INSERT INTO journal_entry values (null,?,?,?,?,?,?)");
                if (j.getCredit().equals("")) {
                    postJournEnt.setString(1, "");
                    postJournEnt.setString(2, j.getDebit());
                } else {
                    postJournEnt.setString(1, j.getCredit());
                    postJournEnt.setString(2, "");
                }
                postJournEnt.setString(3, j.getMemo());
                postJournEnt.setString(4, j.getDate().toString());
                postJournEnt.setInt(5, Journal.getJournalId());
                postJournEnt.setInt(6, j.getAccountKey());
                postJournEnt.executeUpdate();
                conn.close();
            }

            conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.postJourn()");
            }
            PreparedStatement getManAdm = conn.prepareStatement("select userName from users where accountant = 0");
            ResultSet rs = getManAdm.executeQuery();

            while (rs.next()) {
                PreparedStatement sendMes = conn.prepareStatement("INSERT INTO message values (null,?,?,?,?,?,?,?)");
                sendMes.setString(1, GlobalUser.getUserName());
                sendMes.setString(2, rs.getString(1));
                sendMes.setDate(3, now);
                sendMes.setString(4, ("Approval Needed: Journal ID - " + Journal.getJournalId()));
                sendMes.setString(5, GlobalUser.getUserName() + " has submitted a new journal and needs approval.");
                sendMes.setInt(6, Journal.getJournalId());
                sendMes.setInt(7, 1);
                sendMes.executeUpdate();
            }
            conn.close();
        }

        catch (Exception ex) {
            System.out.println("Error connecting to db");
            ex.printStackTrace();
        }

        deactivateAllPanes();
        loadJournalTable();
        journalPane.setVisible(true);

        journNameFld.setText("");
        journDescFld.setText("");
        journEntCreditFld.setText("");
        journEntDebitFld.setText("");
        journEntDate.setValue(null);
        journEntMemoFld.setText("");

        Journal.clearJournal();

    }

    private boolean verifyDebitsCredits() {
        ArrayList<JournalEntry> je = Journal.getListOfEntries();
        double credit = 0;
        double debit = 0;
        for (JournalEntry j : je) {
            if (j.getCredit().equals("")) {
                debit += Double.valueOf(j.getDebit());
            }
            else
                credit += Double.valueOf(j.getCredit());
        }
        if (debit == credit) {
            return true;
        }
        else
            return false;
    }

    private boolean verifyJournFields() {
        if (!(journNameFld.getText().equals("")) && !(journDescFld.getText().equals(""))) {
            return true;
        }
        return false;
    }

    private void setShowApprovedJourns() {
        System.out.println("[INFO] " + new Date().toString() + " Setting approved journal checkbox listener actions");
        approvedChkBx.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    loadJournalTable();
                }
                else {
                    loadJournalTableAppr();
                }
            }
        });
    }

    private void setShowPendingJourns() {
        System.out.println("[INFO] " + new Date().toString() + " Setting pending journal checkbox listener actions");
        pendingChkBx.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    loadJournalTable();
                }
                else {
                    loadJournalTablePend();
                }
            }
        });
    }

    /**
     * Help Button
     */

    @FXML
    void onHelpBtnPressed(MouseEvent event) {
        System.out.println("[INFO] " + new Date().toString() + " Loading Help Screen" );
        SceneSwitch.switchScene("Help.fxml", getClass(), "User Home.fxml");
    }

    /**
     * Mail methods
     */

    @FXML
    void onMailBtnPressed(MouseEvent event) {
        deactivateAllPanes();
        loadMessages();
        mailPane.setVisible(true);
        mailTablePane.setVisible(true);
        newMessagePane.setVisible(false);
        openMessagePane.setVisible(false);
    }

    // This method calls the buildAccountData() method. Used to refresh table of accounts
    private void loadMessages() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all messages to table");
        String sqlQry = "SELECT `date`, `from`, `subject`, `message`, idmessage from `message` where `to` = \"" + GlobalUser.getUserName() + "\"";
        mailTable.setItems(buildMessageTableData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Messages load to table success");
    }

    private ObservableList<Map> buildMessageTableData(String query) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.buildMessageTableData()");
            }

            System.out.println("[INFO] " + new Date().toString() + " Building message table data from sql query");
            ObservableList<Map> messageList = FXCollections.observableArrayList();
            PreparedStatement getMessages = conn.prepareStatement(query);
            ResultSet rs = getMessages.executeQuery();
            while (rs.next()) {
                Map<String, String> message = new HashMap<>();
                message.put("date", String.valueOf(rs.getString("date")));
                message.put("from", String.valueOf(rs.getString("from")));
                message.put("subject", String.valueOf(rs.getString("subject")));
                message.put("message", String.valueOf(rs.getString("message")));
                message.put("id", String.valueOf(rs.getInt("idmessage")));
                messageList.add(message);
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Returning message table data");
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.buildMessageTableData()");
            return messageList;
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.buildMessageTableData()");
            ex.printStackTrace();
            return null;
        }
    }

    private void formatMesgTable() {
        System.out.println("[INFO] " + new Date().toString() + " Formatting message table design");
        mailDateCol.setCellValueFactory(new MapValueFactory("date"));
        mailFromCol.setCellValueFactory(new MapValueFactory("from"));
        mailSubjectCol.setCellValueFactory(new MapValueFactory("subject"));
        mailMessageCol.setCellValueFactory(new MapValueFactory("message"));
    }

    private void setDoubleClickOpenMessage() {
        System.out.println("[INFO] " + new Date().toString() + " Setting message table mouse actions");

        mailTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String messageid = (String) mailTable.getSelectionModel().getSelectedItem().get("id");
                loadThisMessage(messageid);
            }
        });
    }

    private void loadThisMessage(String id) {
        System.out.println("[INFO] " + new Date().toString() + " Setting global message");
        Message.setMessage(id);

        msgFromTxt.setText(UNFILLED_FROM + Message.getFrom());
        msgDateTxt.setText(UNFILLED_DATE + Message.getDate());
        msgSubjectTxt.setText(UNFILLED_SUBJ + Message.getSubject());
        msgContentTxt.setText(Message.getMessage());

        mailTablePane.setVisible(false);
        newMessagePane.setVisible(false);
        openMessagePane.setVisible(true);
    }

    @FXML
    void onMailReturnPressed(MouseEvent event) {
        System.out.println("[INFO] " + new Date().toString() + " Clearing message text fields of unique data and resetting view");
        Message.clearMessage();
        loadMessages();

        msgFromTxt.setText(UNFILLED_FROM);
        msgDateTxt.setText(UNFILLED_DATE);
        msgSubjectTxt.setText(UNFILLED_SUBJ);
        msgContentTxt.setText("");

        openMessagePane.setVisible(false);
        mailPane.setVisible(true);
        mailTablePane.setVisible(true);
    }

    @FXML
    void onNewMessagePressed() {
        openMessagePane.setVisible(false);
        mailPane.setVisible(true);
        mailTablePane.setVisible(false);
        newMessagePane.setVisible(true);
    }

    @FXML
    void onNewMessageCancel() {

        newMessageToFld.setText("");
        newMessageSubjectFld.setText("");
        newMessageFld.setText("");

        openMessagePane.setVisible(false);
        mailPane.setVisible(true);
        mailTablePane.setVisible(true);
        newMessagePane.setVisible(false);
    }

    @FXML
    void onNewMessageSend() {
        String to = newMessageToFld.getText();
        String subject = newMessageSubjectFld.getText();
        String message = newMessageFld.getText();
        LocalDate today = LocalDate.now();

        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AHC.onNewMessageSend()");
            }

            PreparedStatement sendMsg = conn.prepareStatement("INSERT INTO message values (null, \""+ GlobalUser.getUserName() + "\", \"" + to + "\", \""
                    + today + "\", \"" + subject + "\", \"" + message + "\", null)");
            sendMsg.executeUpdate();
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AHC.onNewMessageSend()");
        }
        catch (Exception ex) {
            System.out.println("Error connecting to db");
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AHC.onNewMessageSend()");
            ex.printStackTrace();
        }

        newMessageToFld.setText("");
        newMessageSubjectFld.setText("");
        newMessageFld.setText("");

        openMessagePane.setVisible(false);
        mailPane.setVisible(true);
        mailTablePane.setVisible(true);
        newMessagePane.setVisible(false);
        loadMessages();
    }

    @FXML
    void onMessageReplyPressed() {
        newMessageToFld.setText(Message.getFrom());
        newMessageSubjectFld.setText("RE: " + Message.getSubject());

        openMessagePane.setVisible(false);
        newMessagePane.setVisible(true);
        Message.clearMessage();
        loadMessages();
    }
}

