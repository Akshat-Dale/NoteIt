package com.example.noteit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.noteit.Model.Notes;
import com.example.noteit.R;
import com.example.noteit.ViewModel.NotesViewModel;
import com.example.noteit.databinding.ActivityUpdateNoteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class UpdateNoteActivity extends AppCompatActivity {

    ActivityUpdateNoteBinding activityUpdateNoteBinding;
    String priority = "1";
    String title,subTitle,notes;
    NotesViewModel notesViewModel;
    int oldId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        activityUpdateNoteBinding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(activityUpdateNoteBinding.getRoot());

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        subTitle = intent.getStringExtra("subTitle");
        oldId = intent.getIntExtra("id",0);
        priority = intent.getStringExtra("priority");
        notes = intent.getStringExtra("note");

        Log.i("akshat oldid",String.valueOf(oldId)+" id in updateActivity while click");

        activityUpdateNoteBinding.titleEvUpdate.setText(title);
        activityUpdateNoteBinding.subTitleEvUpdate.setText(subTitle);
        activityUpdateNoteBinding.noteEvUpdate.setText(notes);

        if (priority.equals("1")){
            activityUpdateNoteBinding.imageViewGreenUpdate.setImageResource(R.drawable.baseline_done_24);
            activityUpdateNoteBinding.imageViewYellowUpdate.setImageResource(0);
            activityUpdateNoteBinding.imageViewRedUpdate.setImageResource(0);

        } else if (priority.equals("2")) {
            activityUpdateNoteBinding.imageViewGreenUpdate.setImageResource(0);
            activityUpdateNoteBinding.imageViewYellowUpdate.setImageResource(R.drawable.baseline_done_24);
            activityUpdateNoteBinding.imageViewRedUpdate.setImageResource(0);
        }
        else if (priority.equals("3")){
            activityUpdateNoteBinding.imageViewGreenUpdate.setImageResource(0);
            activityUpdateNoteBinding.imageViewYellowUpdate.setImageResource(0);
            activityUpdateNoteBinding.imageViewRedUpdate.setImageResource(R.drawable.baseline_done_24);
        }


        activityUpdateNoteBinding.floatingbuttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = activityUpdateNoteBinding.titleEvUpdate.getText().toString();
                String subTitle = activityUpdateNoteBinding.subTitleEvUpdate.getText().toString();
                String notes = activityUpdateNoteBinding.noteEvUpdate.getText().toString();

                if (title.isEmpty()){
                    activityUpdateNoteBinding.titleEvUpdate.setError("Title Required");
                }
                else if (subTitle.isEmpty()){
                    activityUpdateNoteBinding.subTitleEvUpdate.setError("SubTitle Required");
                }
                else if (notes.isEmpty()){
                    activityUpdateNoteBinding.noteEvUpdate.setError("Note Required");
                }
                else {
                    updateNote(title,subTitle,notes);
                }



            }
        });



        activityUpdateNoteBinding.imageViewGreenUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                priority = "1";
                activityUpdateNoteBinding.imageViewGreenUpdate.setImageResource(R.drawable.baseline_done_24);
                activityUpdateNoteBinding.imageViewYellowUpdate.setImageResource(0);
                activityUpdateNoteBinding.imageViewRedUpdate.setImageResource(0);
            }
        });

        activityUpdateNoteBinding.imageViewYellowUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                priority = "2";
                activityUpdateNoteBinding.imageViewGreenUpdate.setImageResource(0);
                activityUpdateNoteBinding.imageViewYellowUpdate.setImageResource(R.drawable.baseline_done_24);
                activityUpdateNoteBinding.imageViewRedUpdate.setImageResource(0);
            }
        });

        activityUpdateNoteBinding.imageViewRedUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                priority = "3";
                activityUpdateNoteBinding.imageViewGreenUpdate.setImageResource(0);
                activityUpdateNoteBinding.imageViewYellowUpdate.setImageResource(0);
                activityUpdateNoteBinding.imageViewRedUpdate.setImageResource(R.drawable.baseline_done_24);
            }
        });

    }

    private void updateNote(String title, String subTitle, String notes) {

        String date = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = String.valueOf(java.time.LocalDate.now());
        }

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.id = oldId;
        notes1.notesSubTitle = subTitle;
        notes1.notesDate = date;
        notes1.notesPriority = priority;
        notes1.notes = notes;

        notesViewModel.updateNotes(notes1);

        Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void deleteNote(int id){

        notesViewModel.deleteNotes(id);
        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.deleteNote:

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNoteActivity.this);
                View view = LayoutInflater.from(UpdateNoteActivity.this)
                        .inflate(R.layout.delete_confirmation_sheet,(LinearLayout) findViewById(R.id.lineardelete));

                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

                Button noButton = view.findViewById(R.id.buttonNo);
                Button yesButton = view.findViewById(R.id.buttonYes);

                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                    }
                });

                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        deleteNote(oldId);
                    }
                });


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}