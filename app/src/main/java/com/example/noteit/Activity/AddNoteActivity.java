package com.example.noteit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderGetKt;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.example.noteit.Model.Notes;
import com.example.noteit.R;
import com.example.noteit.ViewModel.NotesViewModel;
import com.example.noteit.databinding.ActivityAddNoteBinding;

import java.text.DateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding activityAddNoteBinding;
    NotesViewModel notesViewModel;
    String title,subTitle,notes;
    String priority = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



         activityAddNoteBinding = ActivityAddNoteBinding.inflate(getLayoutInflater());
         setContentView(activityAddNoteBinding.getRoot());


        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        activityAddNoteBinding.floatingbuttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = activityAddNoteBinding.titleEv.getText().toString();
                subTitle = activityAddNoteBinding.subTitleEv.getText().toString();
                notes = activityAddNoteBinding.noteEv.getText().toString();

                if (title.isEmpty()){
                    activityAddNoteBinding.titleEv.setError("Title Required");
                }
                else if (subTitle.isEmpty()){
                    activityAddNoteBinding.subTitleEv.setError("SubTitle Required");
                }
                else if (notes.isEmpty()){
                    activityAddNoteBinding.noteEv.setError("Note Required");
                }
                else {
                    createNote(title,subTitle,notes);
                }




            }
        });

        activityAddNoteBinding.imageViewGreenAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                priority = "1";
                activityAddNoteBinding.imageViewGreenAdd.setImageResource(R.drawable.baseline_done_24);
                activityAddNoteBinding.imageViewYellowAdd.setImageResource(0);
                activityAddNoteBinding.imageViewRedAdd.setImageResource(0);
            }
        });

        activityAddNoteBinding.imageViewYellowAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                priority = "2";
                activityAddNoteBinding.imageViewGreenAdd.setImageResource(0);
                activityAddNoteBinding.imageViewYellowAdd.setImageResource(R.drawable.baseline_done_24);
                activityAddNoteBinding.imageViewRedAdd.setImageResource(0);
            }
        });

        activityAddNoteBinding.imageViewRedAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                priority = "3";
                activityAddNoteBinding.imageViewGreenAdd.setImageResource(0);
                activityAddNoteBinding.imageViewYellowAdd.setImageResource(0);
                activityAddNoteBinding.imageViewRedAdd.setImageResource(R.drawable.baseline_done_24);
            }
        });




    }

    private void createNote(String title, String subTitle, String notes) {
        String date = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             date = String.valueOf(java.time.LocalDate.now());
        }
        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubTitle = subTitle;
        notes1.notesDate = date;
        notes1.notesPriority = priority;
        notes1.notes = notes;

        notesViewModel.insertNotes(notes1);

        Toast.makeText(this, "Note Created Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}