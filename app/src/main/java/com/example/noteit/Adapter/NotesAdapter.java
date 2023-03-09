package com.example.noteit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.telephony.UiccPortInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteit.Activity.MainActivity;
import com.example.noteit.Activity.UpdateNoteActivity;
import com.example.noteit.Model.Notes;
import com.example.noteit.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    Context context;
    List<Notes> notesList;

    public NotesAdapter(Context context, List<Notes> notesList) {

        this.context = context;
        this.notesList = notesList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Notes notes = notesList.get(position);

        if (notes.notesPriority.equals("1")){
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        }
        else if (notes.notesPriority.equals("2")){
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        }
        else {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.title.setText(notes.notesTitle);
        holder.subtitle.setText(notes.notesSubTitle);
        holder.notes.setText(notes.notesDate);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdateNoteActivity.class);
                intent.putExtra("id",notes.id);
                Log.i("akshat",String.valueOf(notes.id)+" id in adapter while click");
                intent.putExtra("title",notes.notesTitle);
                intent.putExtra("subTitle",notes.notesSubTitle);
                intent.putExtra("priority",notes.notesPriority);
                intent.putExtra("note",notes.notes);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,subtitle,notes;
        View notesPriority;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTV);
            subtitle = itemView.findViewById(R.id.subTitleTv);
            notes = itemView.findViewById(R.id.dateTV);
            notesPriority = itemView.findViewById(R.id.notePriority);
        }
    }
}
