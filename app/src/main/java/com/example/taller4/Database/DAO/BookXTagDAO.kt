package com.example.taller4.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taller4.Database.Entitys.BookXTag

@Dao
interface BookXTagDAO {
    @Query("SELECT * FROM  bookxtag")
    fun getAllBookXTag() : LiveData<List<BookXTag>>
    @Insert
    suspend fun insert(bookxtag : BookXTag)

    @Query(" DELETE FROM Book")
    suspend fun deleteBookxTag()
}