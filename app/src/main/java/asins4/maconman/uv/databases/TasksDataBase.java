package asins4.maconman.uv.databases;

import static asins4.maconman.uv.databases.TasksContract.CREATE_TABLE;
import static asins4.maconman.uv.databases.TasksContract.DELETE_ENTRIES;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TasksDataBase extends SQLiteOpenHelper {
    // Database name
    private static final String DATABASE_NAME="Tasks";

    public TasksDataBase(Context context, int version){
        super(context, DATABASE_NAME, null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDelete(SQLiteDatabase db) {
        db.execSQL(DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
