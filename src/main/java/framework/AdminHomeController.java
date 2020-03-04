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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

// todo - logo on all pages

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

    @FXML
    private DatePicker newJournalDateFld;

    @FXML
    private Text GJEdate;

    @FXML
    private TextField newJournalCreditsFld;

    @FXML
    private Text GJEcredits;

    @FXML
    private TextField newJournalDebitsFld;

    @FXML
    private Text GJEdebits;

    @FXML
    private TextField newJournalAccountDescFld;

    @FXML
    private Text GJEaccdesc;

    @FXML
    private TextField newJournalAccountNameFld;

    @FXML
    private Text GJEaccname;

    @FXML
    private Text GeneralJournalEntryScreen;

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
    void onLogClicked() {
        accountsPane.setVisible(false);
        accountEditPane.setVisible(false);
        singleAccountPane.setVisible(false);
        userPane.setVisible(false);
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

    @FXML
    void onJournClicked() {

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

    @FXML
    public void initialize() {
        userName.setText(GlobalUser.getUserName());
        userPane.setVisible(false);
        accountsPane.setVisible(false);
        singleAccountPane.setVisible(false);
        accountEditPane.setVisible(false);
        logPane.setVisible(false);
        formatTable();
        formatAcctTable();
        formatLogTable();
        loadUsers();
        loadAccounts();
        loadLogs();
        setShowExpPassActions();
        setAccTypeCmbBxOptions();
        setUserTableFieldsEditable();
        setCategoryCmbBx();
        setStatementCmbBx();
        setNormalSideCmbBx();
        setDoubleClickOpenAcct();

        System.out.println("[INFO] " + new Date().toString() + " Admin home page init success");
    }

    @FXML
    void onAddUser(ActionEvent event) {
        try {

            // Checking if email exists in system
            boolean emailExists = false;
            int numRecords = 0;
            // Establish the connection to the database
            String url = "jdbc:mysql://localhost:3306/app_domain";
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
                System.out.println("[INFO] " + new Date().toString() + " Begin new user id query");
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
        userPane.setVisible(false);
        accountsPane.setVisible(false);
        accountEditPane.setVisible(false);
        singleAccountPane.setVisible(false);
        System.out.println("[INFO] " + new Date().toString() + " Admin load home screen success");

    }

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
        // Load pane
        accountsPane.setVisible(false);
        singleAccountPane.setVisible(false);
        accountEditPane.setVisible(false);
        userPane.setVisible(true);
    }

    @FXML
    void onAccountsClicked(MouseEvent event) {
        loadAccounts();
        userPane.setVisible(false);
        singleAccountPane.setVisible(false);
        accountEditPane.setVisible(false);
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

    private boolean validateAccountNum() {
        String accountNum = accountNumberField.getText();
        for (int i = 0; i < accountNum.length(); i++) {
            if (!Character.isDigit(accountNum.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean validateMoneyNumber(String num) {
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

    private String formatNumber(String number) {
        Double num = Double.valueOf(number);
        DecimalFormat format = new DecimalFormat("#.00");
        format.setGroupingUsed(true);
        format.setGroupingSize(3);
        String newNum = String.valueOf(format.format(num));
        return newNum;
    }

    @FXML
    void onSubmitClicked() {
        // Take in values from fields
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
                            String url = "jdbc:mysql://localhost:3306/app_domain";
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

    @FXML
    void onCategoryHiding() {
        try {
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

    @FXML
    void onNewCategoryHiding() {
        try {
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

    private ObservableList<Map> buildData(String sqlQry) {
        try {
            String url = "jdbc:mysql://localhost:3306/app_domain";
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

    private void loadUsers() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all users to table");
        String sqlQry = "SELECT userName, fName, lName, dob, email, admin, manager, accountant, active, suspendEnd, passwordAtt, passwordExpired  FROM app_domain.users";
        usersTableView.setItems(buildData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " User load to table success");
    }

    private void loadAccounts() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all accounts to table");
        String sqlQry = "SELECT accountNum, accountName, category, statement FROM app_domain.accounts";
        accountsTableView.setItems(buildAccountData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Accounts load to table success");
    }

    private void loadLogs() {
        System.out.println("[INFO] " + new Date().toString() + " Loading all logs to table");
        String sqlQry = "SELECT * FROM app_domain.event_log";
        logTable.setItems(buildLogData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Logs load to table success");
    }

    private void formatLogTable() {
        System.out.println("[INFO] " + new Date().toString() + " Formatting log table design");
        logIDCol.setCellValueFactory(new MapValueFactory("idevent_log"));
        logUIDCol.setCellValueFactory(new MapValueFactory("userid"));
        logActCol.setCellValueFactory(new MapValueFactory("action"));
        logAccNumCol.setCellValueFactory(new MapValueFactory("account_number"));
        logMessageCol.setCellValueFactory(new MapValueFactory("message"));
    }

    private ObservableList<Map> buildLogData(String query) {
        try {
            String url = "jdbc:mysql://localhost:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. AdminHomeController.buildLogData()");
            }

            System.out.println("[INFO] " + new Date().toString() + " Building table data from sql query");
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
            System.out.println("[INFO] " + new Date().toString() + " Returning table data");
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.buildLogData()");
            return logList;
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.buildLogData()");
            ex.printStackTrace();
            return null;
        }
    }

    private ObservableList<Map> buildAccountData(String query) {
        try {
            String url = "jdbc:mysql://localhost:3306/app_domain";
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

    private void formatAcctTable() {
        System.out.println("[INFO] " + new Date().toString() + " Formatting account table design");
        accountNumberCol.setCellValueFactory(new MapValueFactory("accountNum"));
        accountNameCol.setCellValueFactory(new MapValueFactory("accountName"));
        accountTypeCol.setCellValueFactory(new MapValueFactory("category"));
        statementCol.setCellValueFactory(new MapValueFactory("statement"));
    }



    private void loadExpiredPasswords() {
        System.out.println("[INFO] " + new Date().toString() + " Loading expired passwords to table");
        String sqlQry = "SELECT userName, fName, lName, dob, email, admin, manager, accountant, active, suspendEnd, passwordAtt, passwordExpired  FROM app_domain.users where passwordExpired < CURDATE()";
        usersTableView.setItems(buildData(sqlQry));
        System.out.println("[INFO] " + new Date().toString() + " Load expired passwords to table success");
    }

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

    private void setAccTypeCmbBxOptions() {
        System.out.println("[INFO] " + new Date().toString() + " Setting account type combo box options");
        ObservableList<String> accTypes = FXCollections.observableArrayList("Admin", "Manager", "Accountant");
        accTypeCmbBx.setItems(accTypes);
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

    /**
     * Check username against other usernames in db. If match then add 'x' to end.
     * @param uName
     * @return
     */
    private static String verifyUsername(String uName) {
        try {
            String url = "jdbc:mysql://localhost:3306/app_domain";
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

    private void updateField(String colName, String updatedText, String userName) {
        try {
            System.out.println("[INFO] " + new Date().toString() + " Updating user user field " + colName);
            alertText.setText("");
            String url = "jdbc:mysql://localhost:3306/app_domain";
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

    private void setDoubleClickOpenAcct() {
        accountsTableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String accountNum = (String) accountsTableView.getSelectionModel().getSelectedItem().get("accountNum");
                loadThisAccount(accountNum);
            }
        });
    }

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
        userPane.setVisible(false);
        singleAccountPane.setVisible(true);
        }

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
        userPane.setVisible(false);
        accountsPane.setVisible(true);
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

    private boolean validateAccountNumberUnique() {
        try {
            String url = "jdbc:mysql://localhost:3306/app_domain";
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
            String url = "jdbc:mysql://localhost:3306/app_domain";
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

    @FXML
    void onEditPressed() {
        deacAlertText.setText("");
        newAccountNameFld.setText(Account.getAccountName());
        newAccountDescFld.setText(Account.getAccountDescription());

        singleAccountPane.setVisible(false);
        accountEditPane.setVisible(true);
    }


    @FXML
    void onDeactivatePressed() {
        if (Account.getBalance().equals("0")) {
            deacAlertText.setText("");
            // todo - set account active to 0.
            String message = LocalDateTime.now() + " : " + GlobalUser.getUserName() + " deactivated an account. Account Name: " + Account.getAccountName() + ". Account Number: "
                    + Account.getAccountNumber() + ".";
            EventLog.createEventLog(GlobalUser.getIdUsers(), "Deactivate", Account.getAccountNumber(), message);
        }
        else
            deacAlertText.setText("ERROR: Cannot deactivate account with balance greater than 0");
    }

    @FXML
    void onCancelPressed() {
        deacAlertText.setText("");
        accountEditPane.setVisible(false);
        loadThisAccount(Account.getAccountNumber());
        singleAccountPane.setVisible(true);
    }

    @FXML
    void onSavePressed() {
        confirmationTxt.setText("");
        try {
            String url = "jdbc:mysql://localhost:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database ForgotPasswordController.onNewPassSubmit()");
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

                sleep(3000);

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
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.onSavePressed()");
            ex.printStackTrace();
        }
    }

}
