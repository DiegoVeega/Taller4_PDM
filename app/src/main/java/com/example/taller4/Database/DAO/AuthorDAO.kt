package com.example.taller4.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taller4.Database.Entitys.Author

@Dao
interface AuthorDAO {
    @Query("SELECT * FROM Author order by Author_Name ASC")
    fun getAllAuthor (): LiveData<List<Author>>

    @Insert
    suspend fun insert(author : Author)

    @Query("DELETE FROM Author")
    suspend fun deleteAllAuthor()
}