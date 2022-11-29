package com.example.notesapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Notes")
@kotlinx.parcelize.Parcelize
data class Note(

    @ColumnInfo(name = "Title")
    var noteTitle: String?,
    @ColumnInfo(name = "Description")
    var noteDesc: String?,
    @ColumnInfo(name = "Time")
    var noteTime: String,
    @PrimaryKey(autoGenerate = true) var noteId: Int = 0

):Parcelable {

}