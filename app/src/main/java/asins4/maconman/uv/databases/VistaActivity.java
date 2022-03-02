package asins4.maconman.uv.databases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;

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

        View.OnClickListener onItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This viewHolder will have all required values.
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                int posicion = viewHolder.getAdapterPosition();
                //muevo el cursor a la tarea que quiero marcar como hecha
                //este cursor sale de arriba de cuando cojo los datos a mostrar
                cursor.moveToPosition(posicion);
                //recojo el indice de dicha tarea
                int index = cursor.getColumnIndex(TasksContract.TasksEntry._ID);
                int id = cursor.getInt(index);

                //update db
                ContentValues values = new ContentValues();
                //lo que voy a cambiar
                values.put(TasksContract.TasksEntry.COLUMN_NAME_DONE, 1);
                // la clausula where
                String selection = TasksContract.TasksEntry._ID + " = ?";
                String[] selectionArgs = { String.valueOf(id) };
                db.update(TasksContract.TasksEntry.TABLE_NAME, values, selection, selectionArgs);
                // Implement the listener!
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        };

        taskAdapter.setOnItemClickListener(onItemClickListener);
    }
}