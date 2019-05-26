package com.example.taller4.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taller4.Database.Entitys.Tag

@Dao
interface TagDAO {
    @Query("SELECT * FROM Tag ORDER BY Tag_name ASC")
    fun getAllTags() : LiveData<List<Tag>>

    @Insert
    suspend fun insert(tag : Tag)

    @Query("DELETE FROM Tag")
    suspend fun deleteTags()
}