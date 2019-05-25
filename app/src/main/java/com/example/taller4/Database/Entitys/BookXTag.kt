package com.example.taller4.Database.Entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "BookXTag", foreignKeys =
arrayOf(
    ForeignKey(
        entity = Book::class,
        parentColumns = arrayOf("Id_Libro"),
        childColumns = arrayOf("IDXLibro"),
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Tag::class,
        parentColumns = arrayOf("Id_Tag "),
        childColumns = arrayOf("IDXTag"),
        onDelete = ForeignKey.CASCADE
    )
)

    )
class BookXTag (
    @ColumnInfo(name =" IDXTag")
    val IDXTag : Int,
            @ColumnInfo(name = "IDXLibro")
            val IDXLibro:  Int
){
    @PrimaryKey(autoGenerate = true)
    var IdLibroxTag : Int  = 0
}