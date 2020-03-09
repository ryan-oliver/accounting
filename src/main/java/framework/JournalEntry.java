package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * This class creates a journal entry. It is used when extracting values for new journal table and when
 * adding the entries to the database.
 */

public class JournalEntry {

    String credit;
    String debit;
    String memo;
    String accountName;
    Date date;
    Integer journalKey;
    Integer accountKey;

    public JournalEntry(String credit, String debit, String memo, Date date, Integer journalKey, Integer accountKey, String accountName) {
        this.credit = credit;
        this.debit = debit;
        this.memo = memo;
        this.date = date;
        this.journalKey = journalKey;
        this.accountKey = accountKey;
        this.accountName = accountName;
        Journal.addJournalEntry(this);
    }

    public JournalEntry(String credit, String debit, String memo, Date date, Integer accountKey) {
        this.credit = credit;
        this.debit = debit;
        this.memo = memo;
        this.date = date;

        try {
            String url = "jdbc:mysql://35.245.123.161:3306/app_domain";
            Connection conn = DriverManager.getConnection(url, "root", "password");
            if (conn != null) {
                System.out.println("[INFO] " + new Date().toString() + " Connected to the database. JournalEntry()");
            }
            // Checking for username in db
            PreparedStatement acctNamFind = conn.prepareStatement("SELECT accountName FROM accounts WHERE idaccounts = (?)");
            acctNamFind.setInt(1, accountKey);
            System.out.println("[INFO] " + new Date().toString() + " Begin account query");
            ResultSet rs = acctNamFind.executeQuery();
            System.out.println("[INFO] " + new Date().toString() + " Acct query successful");
            while (rs.next()) {
                this.accountName = rs.getString(1);
            }
            conn.close();
            System.out.println("[INFO] " + new Date().toString() + " Database connection closed. JournalEntry()");
        }

        catch (Exception ex) {
            System.out.println("Error connecting to db");
            ex.printStackTrace();
        }

        Journal.addJournalEntryToSingleView(this);
    }

    public String getAccountName() {
        return accountName;
    }

    public String getCredit() {
        return credit;
    }

    public String getDebit() {
        return debit;
    }

    public String getMemo() {
        return memo;
    }

    public Date getDate() {
        return date;
    }

    public Integer getJournalKey() {
        return journalKey;
    }

    public Integer getAccountKey() {
        return accountKey;
    }
}
