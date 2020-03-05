package framework;

import java.util.Date;

/**
 * This class creates a journal entry. It is used when extracting values for new journal table and when
 * adding the entries to the database.
 */

public class JournalEntry {

    String credit;
    String debit;
    String memo;
    Date date;
    Integer journalKey;
    Integer accountKey;

    public JournalEntry(String credit, String debit, String memo, Date date, Integer journalKey, Integer accountKey) {
        this.credit = credit;
        this.debit = debit;
        this.memo = memo;
        this.date = date;
        this.journalKey = journalKey;
        this.accountKey = accountKey;
        Journal.addJournalEntry(this);
    }

    public static void main(String[] args) {
        // Testing method

        Journal j = new Journal();
        JournalEntry je = new JournalEntry("100", "0", "TEst", new Date(), 5, 3);
        System.out.println(Journal.getListLength());
    }
}
