package com.example.taller4.Database.Entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Book",
    foreignKeys =
    arrayOf(
        ForeignKey(
            entity = Author::class,
            parentColumns = arrayOf("Id_Author"),
            childColumns = arrayOf("Id_AutorLibro"),
            onDelete = ForeignKey.CASCADE
        )
         )
)

class Book (
    @PrimaryKey
    @ColumnInfo(name="id_Book")
    var id_Book: Int,
    @ColumnInfo(name= "BookName")
    val BookName: String,
    @ColumnInfo(name = "Id_AutorLibro")
    val Id_AutorLibro: Int,
    @ColumnInfo(name = "ISBN")
    val Isbn_libro: String,
    @ColumnInfo(name = "Cover")
    val Cover: Int,
    @ColumnInfo(name = "Summary")
    val Summary: String,
    @ColumnInfo(name = "Fav")
    val Fav: Boolean)
