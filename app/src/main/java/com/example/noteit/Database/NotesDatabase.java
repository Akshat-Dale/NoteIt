package com.example.noteit.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.noteit.Model.Notes;
import com.example.noteit.Dao.NotesDao;

@Database(entities = {Notes.class},version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    public static  NotesDatabase getDatabaseInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,"NoteIt_DataBase").allowMainThreadQueries().build();
        }
            return INSTANCE;
    }
}
