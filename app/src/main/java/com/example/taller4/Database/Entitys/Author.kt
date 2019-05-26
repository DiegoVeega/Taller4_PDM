package com.example.taller4.Database.Entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Author")
class Author(
    @PrimaryKey
    @ColumnInfo(name="Id_Author")
    var Id_Author : Int = 0,

    @ColumnInfo(name = "Author_Name")
    val AuthorName: String,
    @ColumnInfo(name = "Country")
    val country: String
)
