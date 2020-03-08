package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class Journal {

    private static ArrayList<JournalEntry> listOfEntries;

    private static Integer journalId;

    public Journal() {
        listOfEntries = new ArrayList<>();
        // todo (R2) - add SQL query to find number of journal entries,
        //  then add one to that number
        // journalId = ^^ this number
//        try {
//            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
//            Connection conn = DriverManager.getConnection(url, "root", "password");
//            if (conn != null) {
//                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. Journal.Journal()");
//            }
//            PreparedStatement emailChk = conn.prepareStatement("SELECT COUNT(*) FROM journal WHERE idjournal like (?)");
//            emailChk.setString(1, idjournal.getText());
//            while (emailChk.next()){
//                count = res.getInt(1);
//            }
//            System.out.println("Number of row:"+count);
//            System.out.println("[INFO] " + new Date().toString() + " Loading all Journals to table");
//            String sqlQry = "SELECT idjournal, name, date, description, statement FROM app_domain.accounts";
//            accountsTableView.setItems(buildAccountData(sqlQry));
//            System.out.println("[INFO] " + new Date().toString() + " Accounts load to table success");
//            int numAccts = -1;
//            while (rsAccNme.next()) {
//                numAccts = rsAccNme.getInt(1);
//            }
//            conn.close();
//            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. AdminHomeController.validateAccountName()");
//            if (numAccts != 0) {
//                return false;
//            }
//            else
//                return true;
//
//        }
//        catch (Exception ex) {
//            System.out.println("[FATAL ERROR] " + new Date().toString() + " AdminHomeController.validateAccountName()");
//            System.err.println(ex.getMessage());
//            ex.printStackTrace();
//            return false;
//        }
    }

    public static void addJournalEntry(JournalEntry je) {
        listOfEntries.add(je);
    }

    public void clearJournal() {
        journalId = null;
        listOfEntries.clear();

        // todo (R2) - remember to clear the static values when the user either cancels or completes the journal entry.
        //  The values should only hold value when the user is adding a new journal.
    }

    public static Integer getListLength() {
        return listOfEntries.size();
    }
}
