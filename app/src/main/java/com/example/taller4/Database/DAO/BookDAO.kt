package com.example.taller4.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taller4.Database.Entitys.Book

@Dao
interface BookDAO {
    @Query("SELECT * FROM Book where BookName =:name")
    suspend fun getOneBook(name: String) : Book

    @Query("SELECT * FROM Book order by BookName ASC")
    fun getAllBooks() : LiveData<List<Book>>

    @Insert
    suspend fun insert(book : Book)

    @Query("UPDATE Book SET Fav = NOT Fav where id_Book = :id")
    suspend fun updateBook(id : Int)

    @Query("SELECT * FROM Book where Fav = 1")
    fun getAllFavBooks() : LiveData<List<Book>>
}