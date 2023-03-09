package com.example.noteit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.noteit.Adapter.NotesAdapter;
import com.example.noteit.Model.Notes;
import com.example.noteit.R;
import com.example.noteit.ViewModel.NotesViewModel;
import com.example.noteit.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

   ActivityMainBinding activityMainBinding;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        activityMainBinding.textViewNofilter.setBackgroundResource(R.drawable.curve_background_selected);

        activityMainBinding.floatingbuttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddNoteActivity.class));
            }
        });




        notesViewModel.getAllNotes.observe(this,notes -> {

            setAdapter(notes);
        });


        activityMainBinding.textViewNofilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData(0);
                activityMainBinding.textViewNofilter.setBackgroundResource(R.drawable.curve_background_selected);
                activityMainBinding.textViewLtH.setBackgroundResource(R.drawable.curve_background);
                activityMainBinding.textViewHtL.setBackgroundResource(R.drawable.curve_background);


            }
        });

        activityMainBinding.textViewHtL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setData(1);
                activityMainBinding.textViewNofilter.setBackgroundResource(R.drawable.curve_background);
                activityMainBinding.textViewLtH.setBackgroundResource(R.drawable.curve_background);
                activityMainBinding.textViewHtL.setBackgroundResource(R.drawable.curve_background_selected);
            }
        });

        activityMainBinding.textViewLtH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData(2);
                activityMainBinding.textViewNofilter.setBackgroundResource(R.drawable.curve_background);
                activityMainBinding.textViewLtH.setBackgroundResource(R.drawable.curve_background_selected);
                activityMainBinding.textViewHtL.setBackgroundResource(R.drawable.curve_background);
            }
        });


        activityMainBinding.filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void setData(int priority){

        if (priority == 0) {
            notesViewModel.getAllNotes.observe(this, notes -> {
                setAdapter(notes);
            });
        }
        else  if (priority == 1) {
            notesViewModel.highToLow.observe(this, notes -> {
                setAdapter(notes);
            });
        }
        else  if (priority == 2) {
            notesViewModel.lowToHigh.observe(this, notes -> {
                setAdapter(notes);
            });
        }
    }
    public void setAdapter(List<Notes> notes){

        activityMainBinding.mainRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        if (notes.size()==0){
            activityMainBinding.addNoteTV.setVisibility(View.VISIBLE);
            activityMainBinding.mainRecyclerView.setVisibility(View.GONE);
        }
        else {
            activityMainBinding.addNoteTV.setVisibility(View.GONE);
            activityMainBinding.mainRecyclerView.setVisibility(View.VISIBLE);
            NotesAdapter notesAdapter = new NotesAdapter(MainActivity.this, notes);
            activityMainBinding.mainRecyclerView.setAdapter(notesAdapter);
        }

    }
}