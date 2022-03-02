package asins4.maconman.uv.databases;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
    private Cursor items;
    View.OnClickListener mOnItemClickListener;

    public Cursor getCursor(){
            return items;
            }

    public void setCursor(Cursor newCursor){
            items = newCursor;
            notifyDataSetChanged();
    }


    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        v.setOnClickListener(mOnItemClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        items.moveToPosition(position);
        int id_name = items.getColumnIndex(TasksContract.TasksEntry.COLUMN_NAME_NAME);
        String name = items.getString(id_name);
        holder.textViewName.setText(name);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.getCount() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Referencias UI
        public TextView textViewName;

        public ViewHolder(View v) {
            super(v);
            v.setTag(this);
            textViewName = (TextView) v.findViewById(R.id.textViewName);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
