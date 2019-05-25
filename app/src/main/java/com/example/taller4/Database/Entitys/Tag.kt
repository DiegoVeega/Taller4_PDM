package com.example.taller4.Database.Entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tag")
class Tag (
    @ColumnInfo(name = "Tag_name")
    val TagName : String
){
 @PrimaryKey(autoGenerate = true)
 var Id_Tag : Int = 0

}