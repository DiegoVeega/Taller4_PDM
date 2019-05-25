package com.example.taller4.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taller4.Database.Entitys.Author
import com.example.taller4.Database.Entitys.Book
import com.example.taller4.Database.Entitys.Tag
import com.example.taller4.Database.RoomDB.BookRoomDatabase
import com.example.taller4.Repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataBaseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    val allAuthors : LiveData<List<Author>>
    val allBooks : LiveData<List<Book>>
    val allTags : LiveData<List<Tag>>




    init {
        val bookDao = BookRoomDatabase.getDatabase(application, viewModelScope).bookDao()
        val authorDao = BookRoomDatabase.getDatabase(application, viewModelScope).authorDAO()
        val tagDao = BookRoomDatabase.getDatabase(application, viewModelScope).tagDAO()
        repository = Repository(bookDao, tagDao, authorDao)

        allAuthors = repository.allAuthors
        allBooks = repository.allBooks
        allTags = repository.allTags


    }


    fun insertBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(book)

    }
    fun insertAuthor(author: Author) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(author)

    }
    fun insertTag(tag : Tag )= viewModelScope.launch( Dispatchers.IO ){
        repository.insert(tag)

    }
}