package asins4.maconman.uv.databases;

import android.provider.BaseColumns;

public class TasksContract {

    private TasksContract() {}

    public static class TasksEntry implements BaseColumns {
        // Name of the table in the database
        public static final String TABLE_NAME="ToDo";
        // Name of the columns in the table
        public static final String COLUMN_NAME_NAME="name";
        public static final String COLUMN_NAME_DONE="done";
    }
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TasksEntry.TABLE_NAME + " (" +
                    TasksEntry._ID + " INTEGER PRIMARY KEY," +
                    TasksEntry.COLUMN_NAME_NAME + " TEXT," +
                    TasksEntry.COLUMN_NAME_DONE + " REAL)";
    public static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TasksEntry.TABLE_NAME;
}
