package asins4.maconman.uv.databases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Menu button */
    public void writeToDatabase(View view) {
        String app = getResources().getString(R.string.app_name);
        TasksDataBase dbHelper = new TasksDataBase(this, 1);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        EditText editText = (EditText) findViewById(R.id.EditTextName);
        String name = editText.getText().toString();

        CheckBox finalizada = findViewById(R.id.checkBoxFinalizada);
        boolean done = finalizada.isChecked();
        int doneI;
        if(done){
            doneI=1;
        }else {
            doneI=0;
        }

        ContentValues values = new ContentValues();
        values.put(TasksContract.TasksEntry.COLUMN_NAME_NAME, name);
        values.put(TasksContract.TasksEntry.COLUMN_NAME_DONE, doneI);

        long newRowId = db.insert(TasksContract.TasksEntry.TABLE_NAME, null, values);
    }

    /** Called when the user taps the Menu button */
    public void deleteDatabase(View view) {
        TasksDataBase dbHelper = new TasksDataBase(this, 1);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onDelete(db);
    }

    /** Called when the user taps the Menu button */
    public void readDatabase(View view) {
        Intent intent = new Intent(this, VistaActivity.class);
        startActivity(intent);
    }
}