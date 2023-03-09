package com.example.noteit.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NoteIt_DataBase")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_title")
    public String notesTitle;

    @ColumnInfo(name = "notes_subTitle")
    public String notesSubTitle;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name ="notes_priority")
    public String notesPriority;

    @ColumnInfo(name ="notes_date")
    public String notesDate;


}
