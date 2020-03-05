package framework;

import java.util.ArrayList;

public class Journal {

    private static ArrayList<JournalEntry> listOfEntries;

    private static Integer journalId;

    public Journal() {
        listOfEntries = new ArrayList<>();
        // todo (R2) - add SQL query to find number of journal entries,
        //  then add one to that number
        // journalId = ^^ this number
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
