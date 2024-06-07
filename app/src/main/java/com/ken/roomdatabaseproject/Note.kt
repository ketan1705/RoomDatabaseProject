package com.ken.roomdatabaseproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,

    // column info is not compulsory
    // but it's a good practice
    // mostly it is used for given different column name in database
    @ColumnInfo(name = "content")
    val content: String

)
