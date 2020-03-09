package framework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Journal {

    private static ArrayList<JournalEntry> listOfEntries;
    private static ArrayList<JournalEntry> listOfEntriesForSingleView;
    private static Integer journalId;

    private static String name;
    private static String description;
    private static String date;
    private static String approval;
    private static String status;
    private static int id;

    public static void reopenJournal(String idjournal) {
        id = Integer.valueOf(idjournal);
        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. Journal()");
            }
            PreparedStatement loadJournal = conn.prepareStatement("select * from journal where idjournal = (?)");
            loadJournal.setInt(1, id);
            ResultSet rs = loadJournal.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Setting global journal parameters");
            while (rs.next()) {
                name = rs.getString(2);
                java.sql.Date dateSql = rs.getDate(3);
                Date stringDate = new Date(dateSql.getTime());
                date = stringDate.toString();
                description = rs.getString(4);
                if (rs.getInt(5) == 0)
                    approval = "Declined";
                else if (rs.getInt(5) == 1)
                    approval = "Approved";
                else
                    approval = "Pending";
                if (rs.getInt(6) == 0)
                    status = "Pending";
                else
                    status = "Reviewed";
            }

            listOfEntriesForSingleView = new ArrayList<>();
            PreparedStatement getJEs = conn.prepareStatement("select * from journal_entry where journal_key = (?)");
            getJEs.setInt(1, id);
            ResultSet rsJEs = getJEs.executeQuery();
            // params
            String credit = "";
            String debit = "";
            String memo = "";
            java.sql.Date date;
            int acctNum = -1;

            while (rsJEs.next()) {
                // set params
                credit = rsJEs.getString(2);
                debit = rsJEs.getString(3);
                memo = rsJEs.getString(4);
                date = rsJEs.getDate(5);
                acctNum = rsJEs.getInt(7);
                // construct
                Date stringDate = new Date(date.getTime());
                JournalEntry je = new JournalEntry(credit, debit, memo, stringDate, acctNum);
            }

            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. Journal()");

        }
        catch (Exception ex) {
            System.out.println("Error connecting to db");
            System.out.println("[FATAL ERROR] " + new Date().toString() + " Journal()");
            ex.printStackTrace();
        }
    }

    public static void clearSingleJournal() {
        name = "";
        description = "";
        date = "";
        approval = "";
        status = "";
        id = -1;

        listOfEntriesForSingleView.clear();
    }

    public static void makeNewJournal() {
        listOfEntries = new ArrayList<>();

        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. Journal()");
            }

            PreparedStatement getJournCt = conn.prepareStatement("SELECT COUNT(*) from journal");
            ResultSet rs = getJournCt.executeQuery();
            while (rs.next()) {
                journalId = rs.getInt(1) + 1;
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. Journal()");
        }
        catch(Exception ex) {
            System.out.println("[FATAL ERROR] " + new Date().toString() + " Journal()");
            ex.printStackTrace();
        }
    }

    public static void addJournalEntry(JournalEntry je) {
        listOfEntries.add(je);
    }

    public static void addJournalEntryToSingleView(JournalEntry je) {
        listOfEntriesForSingleView.add(je);
    }

    public static void clearJournal() {
        journalId = null;
        listOfEntries.clear();
    }

    public static Integer getListLength() {
        return listOfEntries.size();
    }

    public static Integer getJournalId() {
        return journalId;
    }

    public static ArrayList<JournalEntry> getListOfEntries() {
        return listOfEntries;
    }

    public static ArrayList<JournalEntry> getListOfEntriesForSingleView() {
        return listOfEntriesForSingleView;
    }

    public static String getName() {
        return name;
    }

    public static String getDescription() {
        return description;
    }

    public static String getDate() {
        return date;
    }

    public static String getApproval() {
        return approval;
    }

    public static String getStatus() {
        return status;
    }

    public static int getId() {
        return id;
    }
}
