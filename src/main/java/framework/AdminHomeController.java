package framework;

import framework.GlobalUser;
import framework.SceneSwitch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

public class AdminHomeController {

    private final Color REG_BUTTON_COLOR = Color.web("#a4a6a8");
    private final Color HOV_BUTTON_COLOR = Color.web("#949698");

    private final Color REG_SEARCH_COLOR = Color.web("#424242");
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
    private ImageView profilePicture;

    @FXML
    private Text userName;

    @FXML
    private Rectangle homeBtn;

    @FXML
    private Rectangle userBtn;

    @FXML
    private Rectangle accountsBtn;

    @FXML
    private Rectangle logoutBtn;

    @FXML
    private AnchorPane userPane;

    @FXML
    private TableView<Map> usersTableView;

    @FXML
    private TableColumn userNameCol;

    @FXML
    private TableColumn fNameCol;

    @FXML
    private TableColumn lNameCol;

    @FXML
    private TableColumn dOBCol;

    @FXML
    private TableColumn emailCol;

    @FXML
    private TableColumn adminCol;

    @FXML
    private TableColumn managerCol;

    @FXML
    private TableColumn accountantCol;

    @FXML
    private TableColumn activeCol;

    @FXML
    private TableColumn suspendEndCol;

    @FXML
    private TableColumn passAttCol;

    @FXML
    private TableColumn passExpCol;

    @FXML
    private TextField fNameFld;

    @FXML
    private TextField lNameFld;

    @FXML
    private TextField emailFld;

    @FXML
    private TextField addressFld;

    @FXML
    private DatePicker dobFld;

    @FXML
    private TextField passwordFld;

    @FXML
    private TextField secQFld;

    @FXML
    private TextField secAFld;

    @FXML
    private ComboBox<String> accTypeCmbBx;

    @FXML
    private CheckBox expPassChkBx;

    @FXML
    private Text alertText;

    @FXML
    private Text deacAlertText;

    @FXML
    private AnchorPane accountsPane;

    @FXML
    private TableView<Map> accountsTableView;

    @FXML
    private TableColumn accountNumberCol;

    @FXML
    private TableColumn accountNameCol;

    @FXML
    private TableColumn accountTypeCol;

    @FXML
    private TableColumn statementCol;

    @FXML
    private Text alertTextAccountList;

    @FXML
    private Rectangle searchBtn;

    @FXML
    private TextField accountNameField;

    @FXML
    private TextField accountNumberField;

    @FXML
    private TextField accountDescriptionField;

    @FXML
    private ComboBox<String> categoryCmbBx;

    @FXML
    private ComboBox<String> subcategoryCmbBx;

    @FXML
    private TextField initialBalanceField;

    @FXML
    private ComboBox<String> statementCmbBx;

    @FXML
    private ComboBox<String> normalSideCmbBx;

    @FXML
    private Rectangle submitBtn;

    @FXML
    private Text accountAlertText;

    @FXML
    private Rectangle journBtn;

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
    private Text confirmationTxt;

    @FXML
    private TextField newAccountNameFld;

    @FXML
    private TextField newAccountDescFld;

    @FXML
    private TextField searchTxtFld;

    @FXML
    private ComboBox<String> newNormalSideCombBx;

    @FXML
    private ComboBox<String> newStatementCmbBx;

    @FXML
    private ComboBox<String> newCatCmbBx;

    @FXML
    private ComboBox<String> newSubCatCmbBx;

    @FXML
    private AnchorPane accountEditPane;

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
    private Button declineBtn;

    @FXML
    private Button approveBtn;

    @FXML
    private AnchorPane declineJournPane;

    @FXML
    private TextArea reasonFld;

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

    @FXML
    private Button addFileBtn;

    @FXML
    private Label addFileLbl;

    @FXML
    private AnchorPane RatioPane;

    /**
     * Initialize method is called before primary screen is loaded. Place setup methods here.
     */
    @FXML
    public void initialize() {

        System.out.println("[INFO] " + new Date().toString() + " Admin home page init begin");

        userName.setText(GlobalUser.getUserName());

        deactivateAllPanes();

        // Format tables and set actions on each pane
        formatTable();
        formatAcctTable();
        formatLogTable();
        formatMesgTable();
        formatJournalTable();
        formatJournalEntryTable();
        formatSingleJournalTable();
        formatLedgerTable();

        setShowExpPassActions();
        setShowApprovedJourns();
        setShowPendingJourns();
        setAccTypeCmbBxOptions();
        setUserTableFieldsEditable();
        setCategoryCmbBx();
        setStatementCmbBx();
        setNormalSideCmbBx();
        setDoubleClickOpenAcct();
        setDoubleClickOpenMessage();
        setDoubleClickOpenJournal();
        setDoubleClickOpenJournalFromLedger();

        System.out.println("[INFO] " + new Date().toString() + " Admin home page init success");
    }






    /**
     * Accounts page methods
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

    // This method calls the buildAccountData() method. Used to refresh table of accounts
    private void loadAccounts() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all accounts to table");
        String sqlQry = "SELECT accountNum, accountName, category, statement FROM app_domain.accounts";
        accountsTableView.setItems(buildAccountData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Accounts load to table success");
    }



    // Method to ensure valid account number
    private boolean validateAccountNum() {
        System.out.println("[INFO] " + new Date().toString() + " Validating account number");
        String accountNum = accountNumberField.getText();
        for (int i = 0; i < accountNum.length(); i++) {
            if (!Character.isDigit(accountNum.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Method to validate number is in money format
    private boolean validateMoneyNumber(String num) {
        System.out.println("[INFO] " + new Date().toString() + " Validating money format");
        String balance = num;
        boolean ignore = false;
        if (balance.charAt(balance.length() - 3) == '.' ) {
            ignore = true;
        }
        for (int i = 0; i < balance.length(); i++) {
            if (!Character.isDigit(balance.charAt(i))) {
                if (i == balance.length() - 3 && ignore) {
                    // do nothing
                }
                else
                    return false;
            }
        }
        return true;
    }

    // Method to format number as money
    private String formatNumber(String number) {
        System.out.println("[INFO] " + new Date().toString() + " Formatting number as money");
        Double num = Double.valueOf(number);
        DecimalFormat format = new DecimalFormat("#.00");
        format.setGroupingUsed(true);
        format.setGroupingSize(3);
        String newNum = String.valueOf(format.format(num));
        return newNum;
    }

    // This method builds the data for the account table view. It is assembling a Map to hold the values.
    private ObservableList<Map> buildAccountData(String query) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.buildAccountData()");
            }

            System.out.println("[INFO] " + new Date().toString() + " Building account table data from sql query");
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
            System.out.println("[INFO] " + new Date().toString() + " Returning account table data");
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.buildAccountData()");
            return accountList;
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.buildAccountData()");
            ex.printStackTrace();
            return null;
        }
    }

    // This method formats the account table
    private void formatAcctTable() {
        System.out.println("[INFO] " + new Date().toString() + " Formatting account table design");
        accountNumberCol.setCellValueFactory(new MapValueFactory("accountNum"));
        accountNameCol.setCellValueFactory(new MapValueFactory("accountName"));
        accountTypeCol.setCellValueFactory(new MapValueFactory("category"));
        statementCol.setCellValueFactory(new MapValueFactory("statement"));
    }

    // This method sets options for the account type combo box
    private void setAccTypeCmbBxOptions() {
        System.out.println("[INFO] " + new Date().toString() + " Setting account type combo box options");
        ObservableList<String> accTypes = FXCollections.observableArrayList("Admin", "Manager", "Accountant");
        accTypeCmbBx.setItems(accTypes);
    }

    // Method to set table actions for account table.
    private void setDoubleClickOpenAcct() {
        System.out.println("[INFO] " + new Date().toString() + " Setting account table mouse actions");

        accountsTableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String accountNum = (String) accountsTableView.getSelectionModel().getSelectedItem().get("accountNum");
                loadThisAccount(accountNum);
            }
        });
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

    // Method to set global account
    private void loadThisAccount(String accNum) {

        System.out.println("[INFO] " + new Date().toString() + " Setting global account");

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

    private void loadLedger(int accId) {
        System.out.println("[INFO] " + new Date().toString() + " Loading ledger to table");
        String sqlQry = "SELECT * FROM ledger where account_id = " + accId;
        ledgerTableView.setItems(buildLedgerData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Ledger load to table success");
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


    void formatLedgerTable() {
        System.out.println("[INFO] " + new Date().toString() + " Formatting ledger table design");
        ledgerDateCol.setCellValueFactory(new MapValueFactory("date"));
        ledgerBalCol.setCellValueFactory(new MapValueFactory("balance"));
        ledgerDescCol.setCellValueFactory(new MapValueFactory("description"));
        jIDLedgerCol.setCellValueFactory(new MapValueFactory("journal_id"));
        ledgerDebitCol.setCellValueFactory(new MapValueFactory("debit"));
        ledgerCreditCol.setCellValueFactory(new MapValueFactory("credit"));
    }

    // Method to return to all accounts page
    @FXML
    void onReturnPressed(ActionEvent event) {
        System.out.println("[INFO] " + new Date().toString() + " Clearing account text fields of unique data and resetting view");

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
        userPane.setVisible(false);
        accountsPane.setVisible(true);
    }

    private boolean validateAccountNumberUnique() {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.validateAccountNumber()");
            }
            PreparedStatement emailChk = conn.prepareStatement("SELECT COUNT(*) FROM accounts WHERE accountNum like (?)");
            emailChk.setString(1, accountNumberField.getText());
            System.out.println("[INFO] " + new Date().toString() + " Begin account number check query");
            ResultSet rsAccNum = emailChk.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Account number check query success");
            int numAccts = -1;
            while (rsAccNum.next()) {
                numAccts = rsAccNum.getInt(1);
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.validateAccountNumber()");
            if (numAccts != 0) {
                return false;
            }
            else
                return true;
        }
        catch (Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.validateAccountNumber()");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    private boolean validateAccountNameUnique() {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.validateAccountName()");
            }
            PreparedStatement emailChk = conn.prepareStatement("SELECT COUNT(*) FROM accounts WHERE accountName like (?)");
            emailChk.setString(1, accountNameField.getText());
            System.out.println("[INFO] " + new Date().toString() + " Begin account name check query");
            ResultSet rsAccNme = emailChk.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Account name check query success");
            int numAccts = -1;
            while (rsAccNme.next()) {
                numAccts = rsAccNme.getInt(1);
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.validateAccountName()");
            if (numAccts != 0) {
                return false;
            }
            else
                return true;

        }
        catch (Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.validateAccountName()");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    // Setting combo box options
    void setCategoryCmbBx() {
        System.out.println("[INFO] " + new Date().toString() + " Setting category type combo box options");
        ObservableList<String> catTypes = FXCollections.observableArrayList("Asset", "Liability", "Equity");
        categoryCmbBx.setItems(catTypes);
        newCatCmbBx.setItems(catTypes);
    }


    void setStatementCmbBx() {
        System.out.println("[INFO] " + new Date().toString() + " Setting statement type combo box options");
        ObservableList<String> statementTypes = FXCollections.observableArrayList("IS", "BS", "RE");
        statementCmbBx.setItems(statementTypes);
        newStatementCmbBx.setItems(statementTypes);
    }


    void setNormalSideCmbBx() {
        System.out.println("[INFO] " + new Date().toString() + " Setting normal side type combo box options");
        ObservableList<String> normalSideTypes = FXCollections.observableArrayList("Credit", "Debit");
        normalSideCmbBx.setItems(normalSideTypes);
        newNormalSideCombBx.setItems(normalSideTypes);
    }

    // Method to transition to edit account view
    @FXML
    void onEditPressed() {

        System.out.println("[INFO] " + new Date().toString() + " Begin editing account: " + Account.getAccountName());

        deacAlertText.setText("");
        newAccountNameFld.setText(Account.getAccountName());
        newAccountDescFld.setText(Account.getAccountDescription());

        singleAccountPane.setVisible(false);
        accountEditPane.setVisible(true);
    }

    // Deactivate account
    @FXML
    void onDeactivatePressed() {

        System.out.println("[INFO] " + new Date().toString() + " Attempting deactivate of account:  " + Account.getAccountName());


        if (Account.getBalance().equals("0")) {
            deacAlertText.setText("");
            // todo (R1) - set account active to 0.

            // Adding event to event log
            String message = LocalDateTime.now() + " : " + GlobalUser.getUserName() + " deactivated an account. Account Name: " + Account.getAccountName() + ". Account Number: "
                    + Account.getAccountNumber() + ".";
            EventLog.createEventLog(GlobalUser.getIdUsers(), "Deactivate", Account.getAccountNumber(), message);
        }
        else
            deacAlertText.setText("ERROR: Cannot deactivate account with balance greater than 0");
    }

    // Cancel account edit
    @FXML
    void onCancelPressed() {
        System.out.println("[INFO] " + new Date().toString() + " Cancel editing account " + Account.getAccountName());
        deacAlertText.setText("");
        accountEditPane.setVisible(false);
        loadThisAccount(Account.getAccountNumber());
        singleAccountPane.setVisible(true);
    }

    // Save account edit
    @FXML
    void onSavePressed() {
        confirmationTxt.setText("");
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database AdminHomeController.onSavePressed()");
            }

            if (!(newAccountNameFld.getText() == null) && !(newAccountDescFld.getText() == null) && !(newNormalSideCombBx.getValue() == null) && !(newStatementCmbBx.getValue() == null) && !(newCatCmbBx.getValue() == null) && !(newSubCatCmbBx.getValue() == null)) {
                PreparedStatement updateAcct = conn.prepareStatement("update accounts set accountName = (?)," +
                        " accountDesc = (?), normalSide = (?), statement = (?)," +
                        " category = (?), subcategory = (?) where idaccounts = (?)");
                updateAcct.setString(1, newAccountNameFld.getText());
                updateAcct.setString(2, newAccountDescFld.getText());
                updateAcct.setString(3, newNormalSideCombBx.getValue());
                updateAcct.setString(4, newStatementCmbBx.getValue());
                updateAcct.setString(5, newCatCmbBx.getValue());
                updateAcct.setString(6, newSubCatCmbBx.getValue());
                updateAcct.setInt(7, Account.getAccountId());
                updateAcct.executeUpdate();

                confirmationTxt.setText("Update successful! Returning to account page");


                // Adding event log

                String message = LocalDateTime.now() + " : " + GlobalUser.getUserName() + " updated an account. Account Number: "
                        + Account.getAccountNumber() + ". New Values: Account Name: " + newAccountNameFld.getText() + ". Account Description: "+ newAccountDescFld.getText() +
                        ". Normal Side: " + newNormalSideCombBx.getValue() + ". Statement: " + newStatementCmbBx.getValue() + ". Category: " + newCatCmbBx.getValue() + ". Subcategory: " + newSubCatCmbBx.getValue() + ".";

                EventLog.createEventLog(GlobalUser.getIdUsers(), "Update", Account.getAccountNumber(), message);

                accountEditPane.setVisible(false);
                loadThisAccount(Account.getAccountNumber());
                singleAccountPane.setVisible(true);
            }
            else {
                confirmationTxt.setText("ERROR: Please fill all fields");
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.onSavePressed()");

        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.onSavePressed()");
            ex.printStackTrace();
        }
    }

    // Method to submit new account to database. Draws values from fields on accounts page.
    /**
     *  Submit Button methods. This is part of accounts page
     */
    @FXML
    void onSubmitClicked() {
        // Take in values from fields
        System.out.println("[INFO] " + new Date().toString() + " Setting account field values for DB insert");
        String accountName = accountNameField.getText();
        String accountNumber = accountNumberField.getText();
        if (validateAccountNameUnique()) {
            if (validateAccountNumberUnique()) {
                if (validateAccountNum()) {
                    String accountDescription = accountDescriptionField.getText();
                    String category = categoryCmbBx.getValue();
                    String subcategory = subcategoryCmbBx.getValue();
                    String statement = statementCmbBx.getValue();
                    if (validateMoneyNumber(initialBalanceField.getText())) {
                        String initialBalance = formatNumber(initialBalanceField.getText());
                        String normalSide = normalSideCmbBx.getValue();
                        String balance = initialBalance;
                        Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
                        int userID = GlobalUser.getIdUsers();

                        try {
                            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
                            Connection conn = DriverManager.getConnection(url, "root", "password");
                            if (conn != null) {
                                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.onSubmitClicked()");
                            }

                            System.out.println("[INFO] " + new Date().toString() + " Begin account insert query");

                            PreparedStatement accountInsert = conn.prepareStatement("insert into accounts values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            accountInsert.setString(1, null);
                            accountInsert.setString(2, accountNumber);
                            accountInsert.setString(3, accountName);
                            accountInsert.setString(4, accountDescription);
                            accountInsert.setString(5, normalSide);
                            accountInsert.setString(6, category);
                            accountInsert.setString(7, subcategory);
                            accountInsert.setString(8, initialBalance);
                            accountInsert.setString(9, balance);
                            accountInsert.setTimestamp(10, dateCreated);
                            accountInsert.setInt(11, userID);
                            accountInsert.setInt(12, 1);
                            accountInsert.setString(13, statement);
                            accountInsert.executeUpdate();
                            loadAccounts();
                            System.out.println("[INFO] " + new Date().toString() + " Account insert query successful");

                            System.out.println("[INFO] " + new Date().toString() + " Adding account creation to Event Log");

                            // Adding account to event log
                            String message = LocalDateTime.now() + " : " + GlobalUser.getUserName() + " created an account. Account Name: " + accountNameField.getText() + ". Account Number: "
                                    + accountNumberField.getText() + ".";
                            EventLog.createEventLog(GlobalUser.getIdUsers(), "Create", accountNumberField.getText(), message);

                            conn.close();
                            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.onSubmitClicked()");
                        } catch (Exception ex) {
                            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.onSubmitClicked()");
                            System.err.println(ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("[ERROR] " + new Date().toString() + " Invalid initial balance.");
                        accountAlertText.setText("ERROR: Initial balance must be in ##.## format");
                    }
                }
                else {
                    System.out.println("[ERROR] " + new Date().toString() + " Invalid account number.");
                    accountAlertText.setText("ERROR: Account number must be only digits.");
                }

            }
            else {
                System.out.println("[ERROR] " + new Date().toString() + " Invalid account number.");
                accountAlertText.setText("ERROR: Account number must be unique.");
            }
        }
        else {
            System.out.println("[ERROR] " + new Date().toString() + " Invalid account name.");
            accountAlertText.setText("ERROR: Account name must be unique.");
        }
    }

    @FXML
    void onSubmitEntered() {
        submitBtn.setFill(HOV_SEARCH_COLOR);
    }

    @FXML
    void onSubmitExited() {
        submitBtn.setFill(REG_SEARCH_COLOR);
    }


    /**
     * Various functions. Comments will explain each method
     */

    // Set all panes to invisible
    private void deactivateAllPanes() {
        accountsPane.setVisible(false);
        accountEditPane.setVisible(false);
        singleAccountPane.setVisible(false);

        userPane.setVisible(false);

        logPane.setVisible(false);

        mailPane.setVisible(false);
        mailTablePane.setVisible(false);
        openMessagePane.setVisible(false);
        newMessagePane.setVisible(false);

        journalPane.setVisible(false);
        newJournalPane.setVisible(false);

        declineJournPane.setVisible(false);

        singleJournPane.setVisible(false);
    }

    // This method updates subcategory options for the subcategory combo box on the NEW accounts page. It is based on
    // the selection in the category combo box
    @FXML
    void onCategoryHiding() {
        try {
            System.out.println("[INFO] " + new Date().toString() + " Updating subcat options");
            String category = categoryCmbBx.getValue();
            ObservableList<String> subcatTypes;
            if (category.equals("Asset")) {
                subcatTypes = FXCollections.observableArrayList("Cash", "Inventory", "Accounts Receivable", "Supplies");
                subcategoryCmbBx.setItems(subcatTypes);
            } else if (category.equals("Liability")) {
                subcatTypes = FXCollections.observableArrayList("Accounts Payable", "Notes Payable", "Accrued Liabilities", "Mortgage Payable");
                subcategoryCmbBx.setItems(subcatTypes);
            } else if (category.equals("Equity")) {
                subcatTypes = FXCollections.observableArrayList("Revenues", "Expenses", "Owners Drawing", "Common Stock", "Dividends");
                subcategoryCmbBx.setItems(subcatTypes);
            } else {
                subcategoryCmbBx.setItems(null);
            }
        }
        catch(NullPointerException ex) {
            subcategoryCmbBx.setItems(null);
        }
    }

    // This method updates subcategory options for the subcategory combo box on the accounts EDIT page. It is based on
    // the selection in the category combo box
    @FXML
    void onNewCategoryHiding() {
        try {
            System.out.println("[INFO] " + new Date().toString() + " Updating subcat options");
            String category = newCatCmbBx.getValue();
            ObservableList<String> subcatTypes;
            if (category.equals("Asset")) {
                subcatTypes = FXCollections.observableArrayList("Cash", "Inventory", "Accounts Receivable", "Supplies");
                newSubCatCmbBx.setItems(subcatTypes);
            } else if (category.equals("Liability")) {
                subcatTypes = FXCollections.observableArrayList("Accounts Payable", "Notes Payable", "Accrued Liabilities", "Mortgage Payable");
                newSubCatCmbBx.setItems(subcatTypes);
            } else if (category.equals("Equity")) {
                subcatTypes = FXCollections.observableArrayList("Revenues", "Expenses", "Owners Drawing", "Common Stock", "Dividends");
                newSubCatCmbBx.setItems(subcatTypes);
            } else {
                newSubCatCmbBx.setItems(null);
            }
        }
        catch(NullPointerException ex) {
            newSubCatCmbBx.setItems(null);
        }
    }

    // Validates passwords
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

    // Used to validate passwords
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

    // Used to validate Passwords
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

    // Method to check username against other usernames in db. If match then add 'x' to end of username.
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
        deactivateAllPanes();
        newJournalPane.setVisible(true);
        Journal.makeNewJournal();
        loadAccountNamesForJournalCmbBx();
    }

    @FXML
    void onCancelNewJournal(MouseEvent event) {
        journNameFld.setText("");
        journDescFld.setText("");
        journEntCreditFld.setText("");
        journEntDebitFld.setText("");
        journEntDate.setValue(null);
        journEntMemoFld.setText("");
        addFileLbl.setText("");

        deactivateAllPanes();
        journalPane.setVisible(true);
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
                        addFileLbl.setText("");
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

    @FXML
    void onAddFileClicked(MouseEvent event) {
        final JFileChooser fc = new JFileChooser();
        int response = fc.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION){
            addFileLbl.setText("File added to journal: " + fc.getSelectedFile().toString());
            //System.out.println("[INFO] " + fc.getSelectedFile().toString());
        } else{

        }
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
            PreparedStatement getManAdm = conn.prepareStatement("select userName from users where accountant = 0 and userName not like  \"" + GlobalUser.getUserName() + "\"");
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


        loadJournalTable();
        deactivateAllPanes();
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



    // End journal methods

    /**
     * Account search methods
     */

    @FXML
    void onSearchClicked() {
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
    void onSearchEntered() {
        searchBtn.setFill(HOV_SEARCH_COLOR);
    }

    @FXML
    void onSearchExited() {
        searchBtn.setFill(REG_SEARCH_COLOR);
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
        System.out.println("[INFO] " + new Date().toString() + " Loading admin home screen");
        deactivateAllPanes();
        System.out.println("[INFO] " + new Date().toString() + " Admin load home screen success");
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
        System.out.println("[INFO] " + new Date().toString() + " Logging out admin. Global user object cleared. Returning to login screen" );
        GlobalUser.clearUser();
        SceneSwitch.switchScene("Login.fxml", getClass());
    }

    /**
     * User button methods
     * @param event
     */
    @FXML
    void onUsersButtonEntered(MouseEvent event) {
        userBtn.setFill(HOV_BUTTON_COLOR);
    }

    @FXML
    void onUsersButtonExited(MouseEvent event) {
        userBtn.setFill(REG_BUTTON_COLOR);
    }

    @FXML
    void onUsersClicked(MouseEvent event) {
        deactivateAllPanes();
        // Load data
        loadUsers();
        // Load pane
        userPane.setVisible(true);
    }

    /**
     * Method for adding user. Called when admin is adding user directly.
     * @param event
     */
    @FXML
    void onAddUser(ActionEvent event) {
        try {

            // Checking if email exists in system
            boolean emailExists = false;
            int numRecords = 0;
            // Establish the connection to the database
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.onAddUser()");
            }

            PreparedStatement emailChk = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE email like (?)");
            emailChk.setString(1, emailFld.getText());
            System.out.println("[INFO] " + new Date().toString() + " Begin email check query");
            ResultSet rs = emailChk.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Email check query successful");
            while (rs.next()) {
                numRecords = rs.getInt(1);
            }
            if (numRecords != 0)
                emailExists = true;

            if (fNameFld.getText() == null || lNameFld.getText() == null || emailFld.getText() == null || addressFld.getText() == null ||
                    dobFld.getValue() == null || passwordFld.getText() == null ||
                    secQFld.getText() == null || secAFld.getText() == null || accTypeCmbBx.getValue() == null) {
                alertText.setText("Please fill all fields!");
            } else if (!passwordValid(passwordFld.getText())) {
                alertText.setText("ERROR: Password is invalid.");
            } else if (emailExists) {
                alertText.setText("ERROR: Email already exists in system.");
                System.out.println("[WARN] " + new Date().toString() + " Email check failed, email already exists in system");
            } else {

                System.out.println("[INFO] " + new Date().toString() + " Email check passed, All fields filled,");

                alertText.setText("");

                System.out.println("[INFO] " + new Date().toString() + " Preparing user fields for DB entry");

                // Prepare sql statement
                String firstName = fNameFld.getText();
                String lastName = lNameFld.getText();

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

                String password = passwordFld.getText();

                java.sql.Date dateOfBirth = java.sql.Date.valueOf(dobFld.getValue());

                String addr = addressFld.getText();

                String emailAdd = emailFld.getText();

                // Setting password expire date
                LocalDate today = LocalDate.now();
                LocalDate passExpire = today.plusMonths(3);
                java.sql.Date sqlPassExpire = java.sql.Date.valueOf(passExpire);

                int accountant = 0;
                int manager = 0;
                int admin = 0;
                if (accTypeCmbBx.getValue().equals("Admin"))
                    admin = 1;
                else if (accTypeCmbBx.getValue().equals("Manager"))
                    manager = 1;
                else
                    accountant = 1;


                String secretQuestion = secQFld.getText();
                String secretAnswer = secAFld.getText();

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
                pstmt.setInt(9, admin);
                pstmt.setInt(10, manager);
                pstmt.setInt(11, accountant);
                pstmt.setInt(12, 1);
                pstmt.setDate(13, null);
                pstmt.setInt(14, 0);
                pstmt.setString(15, secretAnswer);
                pstmt.setString(16, secretQuestion);
                pstmt.setDate(17, sqlPassExpire);

                pstmt.executeUpdate();

                System.out.println("[INFO] " + new Date().toString() + " New user insert query successful");

                // Getting unique id for new user
                PreparedStatement getUIDStmt = conn.prepareStatement("SELECT idusers FROM users WHERE userName = (?)");
                getUIDStmt.setString(1, userName);
                System.out.println("[INFO] " + new Date().toString() + " Begin new user id query for password DB insert");
                ResultSet uIDrs = getUIDStmt.executeQuery();
                System.out.println("[INFO] " + new Date().toString() + " New user id query successful");
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

                System.out.println("[INFO] " + new Date().toString() + " New user insert complete.");
                // Close connection
                conn.close();
                loadUsers();
                System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.onAddUser()");
            }
        }

        catch (Exception e) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.onAddUser()" );
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // This method builds the data for the users table view. It is assembling a Map to hold the values.
    private ObservableList<Map> buildData(String sqlQry) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.buildData()");
            }

            System.out.println("[INFO] " + new Date().toString() + " Building table data from sql query");
            ObservableList<Map> userList = FXCollections.observableArrayList();
            PreparedStatement getUsers = conn.prepareStatement(sqlQry);
            ResultSet rs = getUsers.executeQuery();
            while (rs.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("userName", String.valueOf(rs.getString("userName")));
                user.put("fName", String.valueOf(rs.getString("fName")));
                user.put("lName", String.valueOf(rs.getString("lName")));
                user.put("dob", String.valueOf(rs.getDate("dob")));
                user.put("email", String.valueOf(rs.getString("email")));
                user.put("admin", String.valueOf(rs.getInt("admin")));
                user.put("manager", String.valueOf(rs.getInt("manager")));
                user.put("accountant", String.valueOf(rs.getInt("accountant")));
                user.put("active", String.valueOf(rs.getInt("active")));
                user.put("suspendEnd", String.valueOf(rs.getDate("suspendEnd")));
                user.put("passwordAtt", String.valueOf(rs.getInt("passwordAtt")));
                user.put("passwordExpired", String.valueOf(rs.getDate("passwordExpired")));
                userList.add(user);
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Returning table data");
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.buildData()");
            return userList;
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.buildData()");
            ex.printStackTrace();
            return null;
        }
    }

    // This method calls the buildData() method. Used to refresh users table
    private void loadUsers() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all users to table");
        String sqlQry = "SELECT userName, fName, lName, dob, email, admin, manager, accountant, active, suspendEnd, passwordAtt, passwordExpired  FROM app_domain.users";
        usersTableView.setItems(buildData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " User load to table success");
    }

    // This method formats the user table
    private void formatTable() {
        System.out.println("[INFO] " + new Date().toString() + " Formatting user table design");
        userNameCol.setCellValueFactory(new MapValueFactory("userName"));
        fNameCol.setCellValueFactory(new MapValueFactory("fName"));
        lNameCol.setCellValueFactory(new MapValueFactory("lName"));
        dOBCol.setCellValueFactory(new MapValueFactory("dob"));
        dOBCol.setStyle( "-fx-alignment: CENTER;");
        emailCol.setCellValueFactory(new MapValueFactory("email"));
        adminCol.setCellValueFactory(new MapValueFactory("admin"));
        adminCol.setStyle( "-fx-alignment: CENTER;");
        managerCol.setCellValueFactory(new MapValueFactory("manager"));
        managerCol.setStyle( "-fx-alignment: CENTER;");
        accountantCol.setCellValueFactory(new MapValueFactory("accountant"));
        accountantCol.setStyle( "-fx-alignment: CENTER;");
        activeCol.setCellValueFactory(new MapValueFactory("active"));
        activeCol.setStyle( "-fx-alignment: CENTER;");
        suspendEndCol.setCellValueFactory(new MapValueFactory("suspendEnd"));
        suspendEndCol.setStyle( "-fx-alignment: CENTER;");
        passAttCol.setCellValueFactory(new MapValueFactory("passwordAtt"));
        passAttCol.setStyle( "-fx-alignment: CENTER;");
        passExpCol.setCellValueFactory(new MapValueFactory("passwordExpired"));
        passExpCol.setStyle( "-fx-alignment: CENTER;");
    }

    // This method assigns a listener to the expired password checkbox. Will be called when checkbox is pressed or unpressed
    private void setShowExpPassActions() {
        System.out.println("[INFO] " + new Date().toString() + " Setting expired password checkbox listener actions");
        expPassChkBx.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    loadUsers();
                }
                else {
                    loadExpiredPasswords();
                }
            }
        });
    }

    // This method loads expired password users to the users table. Reuses the buildData() method
    private void loadExpiredPasswords() {
        System.out.println("[INFO] " + new Date().toString() + " Loading expired passwords to table");
        String sqlQry = "SELECT userName, fName, lName, dob, email, admin, manager, accountant, active, suspendEnd, passwordAtt, passwordExpired  FROM app_domain.users where passwordExpired < CURDATE()";
        usersTableView.setItems(buildData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Load expired passwords to table success");
    }

    // Method used to update a field in the users table if it was altered
    private void updateField(String colName, String updatedText, String userName) {
        try {
            System.out.println("[INFO] " + new Date().toString() + " Updating user user field " + colName);
            alertText.setText("");
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database AdminHomeController.updateField()");
            }
            String sqlQry = "UPDATE users set " + colName + " = (?) where userName = (?)";
            PreparedStatement updateUserField = conn.prepareStatement(sqlQry);
            updateUserField.setString(1, updatedText);
            updateUserField.setString(2, userName);
            System.out.println("[INFO] " + new Date().toString() + " Update user field begin");
            updateUserField.executeUpdate();
            System.out.println("[INFO] " + new Date().toString() + " Update user field success");
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.updateField()");
            ex.printStackTrace();
        }
    }

    private void setUserTableFieldsEditable() {

        userNameCol.setEditable(true);
        userNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        userNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                        @Override
                                        public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                            String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                            String colName = "userName";
                                            String updatedText = t.getNewValue();
                                            updateField(colName, updatedText, username);
                                        }
                                    }
        );

        fNameCol.setEditable(true);
        fNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        fNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                     @Override
                                     public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                         String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                         String colName = "fName";
                                         String updatedText = t.getNewValue();
                                         updateField(colName, updatedText, username);
                                     }
                                 }
        );

        lNameCol.setEditable(true);
        lNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                     @Override
                                     public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                         String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                         String colName = "lName";
                                         String updatedText = t.getNewValue();
                                         updateField(colName, updatedText, username);
                                     }
                                 }
        );

        dOBCol.setEditable(true);
        dOBCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dOBCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                   @Override
                                   public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                       String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                       String colName = "dob";
                                       String updatedText = t.getNewValue();
                                       updateField(colName, updatedText, username);
                                   }
                               }
        );

        emailCol.setEditable(true);
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                     @Override
                                     public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                         String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                         String colName = "email";
                                         String updatedText = t.getNewValue();
                                         updateField(colName, updatedText, username);
                                     }
                                 }
        );

        adminCol.setEditable(true);
        adminCol.setCellFactory(TextFieldTableCell.forTableColumn());
        adminCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                     @Override
                                     public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                         String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                         String colName = "admin";
                                         String updatedText = t.getNewValue();
                                         updateField(colName, updatedText, username);
                                     }
                                 }
        );

        managerCol.setEditable(true);
        managerCol.setCellFactory(TextFieldTableCell.forTableColumn());
        managerCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                       @Override
                                       public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                           String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                           String colName = "manager";
                                           String updatedText = t.getNewValue();
                                           updateField(colName, updatedText, username);
                                       }
                                   }
        );

        accountantCol.setEditable(true);
        accountantCol.setCellFactory(TextFieldTableCell.forTableColumn());
        accountantCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                          @Override
                                          public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                              String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                              String colName = "accountant";
                                              String updatedText = t.getNewValue();
                                              updateField(colName, updatedText, username);
                                          }
                                      }
        );

        activeCol.setEditable(true);
        activeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        activeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                      @Override
                                      public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                          String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                          String colName = "active";
                                          String updatedText = t.getNewValue();
                                          updateField(colName, updatedText, username);
                                      }
                                  }
        );

        suspendEndCol.setEditable(true);
        suspendEndCol.setCellFactory(TextFieldTableCell.forTableColumn());
        suspendEndCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                          @Override
                                          public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                              String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                              String colName = "suspendEnd";
                                              String updatedText = t.getNewValue();
                                              updateField(colName, updatedText, username);
                                          }
                                      }
        );

        passAttCol.setEditable(true);
        passAttCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passAttCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                       @Override
                                       public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                           String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                           String colName = "passwordAtt";
                                           String updatedText = t.getNewValue();
                                           updateField(colName, updatedText, username);
                                       }
                                   }
        );

        passExpCol.setEditable(true);
        passExpCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passExpCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<HashMap, String>> () {
                                       @Override
                                       public void handle(TableColumn.CellEditEvent<HashMap, String> t) {
                                           String username = (String) usersTableView.getSelectionModel().getSelectedItem().get("userName");
                                           String colName = "passwordExpired";
                                           String updatedText = t.getNewValue();
                                           updateField(colName, updatedText, username);
                                       }
                                   }
        );
    }

    /**
     * Help Button
     */

    @FXML
    void onHelpBtnPressed(MouseEvent event) {
        System.out.println("[INFO] " + new Date().toString() + " Loading Help Screen" );
        SceneSwitch.switchScene("Help.fxml", getClass(), "Admin Home.fxml");
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

        if ((Message.getIsJournal() == 1)) {
            approveBtn.setVisible(true);
            declineBtn.setVisible(true);
        }
        else {
            approveBtn.setVisible(false);
            declineBtn.setVisible(false);
        }
        msgFromTxt.setText(UNFILLED_FROM + Message.getFrom());
        msgDateTxt.setText(UNFILLED_DATE + Message.getDate());
        msgSubjectTxt.setText(UNFILLED_SUBJ + Message.getSubject());
        msgContentTxt.setText(Message.getMessage());

        mailTablePane.setVisible(false);
        newMessagePane.setVisible(false);
        openMessagePane.setVisible(true);
    }

    @FXML
    void onApproveClicked(MouseEvent event) {
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.onApproveCLicked()");
            }
            int journId = Message.getJournalId();

            PreparedStatement setJournApproved = conn.prepareStatement("update journal set approved = 1, status = 1 where idjournal = (?)");
            setJournApproved.setInt(1,journId);
            setJournApproved.executeUpdate();

            PreparedStatement journalEntries = conn.prepareStatement("select * from journal_entry where journal_key = (?)");
            journalEntries.setInt(1, journId);
            ResultSet rs = journalEntries.executeQuery();
            String creditAsString = "";
            String debitAsString = "";
            String memo = "";
            java.sql.Date date = null;
            String balAsString = "";
            int accountKey = -1;

            while (rs.next()) {
                creditAsString = rs.getString(2);
                debitAsString = rs.getString(3);
                memo = rs.getString(4);
                date = rs.getDate(5);
                accountKey = rs.getInt(7);

                // Post to account
                PreparedStatement getBalanceFromAcct = conn.prepareStatement("select balance from accounts where idaccounts = (?)");
                getBalanceFromAcct.setInt(1, accountKey);
                ResultSet rs2 = getBalanceFromAcct.executeQuery();

                while (rs2.next()) {
                    balAsString = rs2.getString(1);
                }
                double balance = Double.valueOf(changeToDouble(balAsString));
                double credit = 0;
                double debit = 0;
                if (creditAsString.equals("")) {
                    debit = Double.valueOf(changeToDouble(debitAsString));
                    balance -= debit;
                }
                else {
                    credit = Double.valueOf(changeToDouble(creditAsString));
                    balance += credit;
                }

                PreparedStatement updateAct = conn.prepareStatement("update accounts set balance = (?) where idaccounts = (?)");
                updateAct.setString(1, formatNumber(String.valueOf(balance)));
                updateAct.setInt(2, accountKey);
                updateAct.executeUpdate();

                // Post to ledger

                PreparedStatement addLedger = conn.prepareStatement("insert into ledger values (null,?,?,?,?,?,?,?)");
                addLedger.setDate(1, date);
                addLedger.setString(2, memo);
                addLedger.setString(3, debitAsString);
                addLedger.setString(4, creditAsString);
                addLedger.setString(5, String.valueOf(balance));
                addLedger.setInt(6, accountKey);
                addLedger.setInt(7, journId);
                addLedger.executeUpdate();
            }
            // Send message to sender

            PreparedStatement sendMsg = conn.prepareStatement("insert into message values (null,?,?,?,?,?,null, 0)");
            sendMsg.setString(1, GlobalUser.getUserName());
            sendMsg.setString(2, Message.getFrom());
            Date dateString = new Date();
            java.sql.Date now = new java.sql.Date(dateString.getTime());
            sendMsg.setString(3, now.toString());
            sendMsg.setString(4, "RE: " + Message.getSubject());
            sendMsg.setString(5, "Your journal entry was approved.");
            sendMsg.executeUpdate();

            // Remove message
            PreparedStatement removeMessage = conn.prepareStatement("delete from message where idmessage = (?)");
            removeMessage.setInt(1, Integer.valueOf(Message.getId()));
            removeMessage.executeUpdate();
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.onApproveCLicked()");
            ex.printStackTrace();
        }
        Message.clearMessage();
        loadMessages();

        msgFromTxt.setText(UNFILLED_FROM);
        msgDateTxt.setText(UNFILLED_DATE);
        msgSubjectTxt.setText(UNFILLED_SUBJ);
        msgContentTxt.setText("");

        deactivateAllPanes();
        mailPane.setVisible(true);
        mailTablePane.setVisible(true);
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

    @FXML
    void onDeclineClicked(MouseEvent event) {
        declineJournPane.setVisible(true);
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
                    + today + "\", \"" + subject + "\", \"" + message +
                    "\", null, 0)");
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

    @FXML
    void onCancelDeclineJourn(MouseEvent event) {
        declineJournPane.setVisible(false);
    }

    @FXML
    void onDecline2Clicked(MouseEvent event) {
        // send message
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AHC.onDecline2Clicked()");
            }

            int journId = Message.getJournalId();

            PreparedStatement setJournApproved = conn.prepareStatement("update journal set approved = 0, status = 1 where idjournal = (?)");
            setJournApproved.setInt(1,journId);
            setJournApproved.executeUpdate();

            // Send message to sender

            PreparedStatement sendMsg = conn.prepareStatement("insert into message values (null,?,?,?,?,?,null, 0)");
            sendMsg.setString(1, GlobalUser.getUserName());
            sendMsg.setString(2, Message.getFrom());
            Date dateString = new Date();
            java.sql.Date now = new java.sql.Date(dateString.getTime());
            sendMsg.setString(3, now.toString());
            sendMsg.setString(4, "RE: " + Message.getSubject());
            sendMsg.setString(5, "Your journal entry was declined.\n" + reasonFld.getText());
            sendMsg.executeUpdate();

            // Remove message
            PreparedStatement removeMessage = conn.prepareStatement("delete from message where idmessage = (?)");
            removeMessage.setInt(1, Integer.valueOf(Message.getId()));
            removeMessage.executeUpdate();
        }
        catch (Exception ex) {
            System.out.println("Error connecting to db");
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AHC.onDecline2Clicked()");
            ex.printStackTrace();
        }
        reasonFld.setText("");
        deactivateAllPanes();
        loadMessages();
        mailPane.setVisible(true);
        mailTablePane.setVisible(true);
    }



}
