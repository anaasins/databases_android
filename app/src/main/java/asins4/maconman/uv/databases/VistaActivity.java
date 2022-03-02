package asins4.maconman.uv.databases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;

public class VistaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista);

        TasksDataBase dbHelper = new TasksDataBase(this, 1);
        // Gets the data repository in read mode
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                TasksContract.TasksEntry.COLUMN_NAME_NAME
        };

        // Filter results WHERE
        String selection = TasksContract.TasksEntry.COLUMN_NAME_DONE + " = ?";
        String[] selectionArgs = { "0" };

        Cursor cursor = db.query(
                TasksContract.TasksEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null
        );

        // Setup cursor adapter using cursor from last step
        TaskAdapter taskAdapter = new TaskAdapter();
        taskAdapter.setCursor(cursor);
        RecyclerView recyclerView = findViewById(R.id.reciclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);
    }
}