package com.example.taller4.Database.Entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tag")
class Tag (
    @PrimaryKey
    @ColumnInfo(name="Id_Tag")
    var Id_Tag : Int = 0,
    @ColumnInfo(name = "Tag_name")
    val TagName : String
)