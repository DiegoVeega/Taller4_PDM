package com.example.taller4.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taller4.Database.Entitys.Book

@Dao
interface BookDAO {
    @Query("SELECT * FROM Book order by BookName ASC")
    fun getAllBooks() : LiveData<List<Book>>

    @Insert
    suspend fun insert(book : Book)

}