package com.example.noteit.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.noteit.Model.Notes;
import com.example.noteit.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        getAllNotes = notesRepository.getAllNotes;
        highToLow = notesRepository.highToLow;
        lowToHigh = notesRepository.lowToHigh;
    }

    public void insertNotes(Notes notes){
        notesRepository.insertNotes(notes);
    }

    public void updateNotes(Notes notes){
        notesRepository.updateNotes(notes);
    }

   public void deleteNotes(int id){
        notesRepository.deleteNotes(id);
    }
}
