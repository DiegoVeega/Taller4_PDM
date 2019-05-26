package com.example.taller4.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taller4.Database.Entitys.Author
import com.example.taller4.Database.Entitys.Book
import com.example.taller4.Database.Entitys.BookXTag
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
    val allFavBook : LiveData<List<Book>>
    val allbookxtags : LiveData<List<BookXTag>>




    init {
        val bookDao = BookRoomDatabase.getDatabase(application, viewModelScope).bookDao()
        val authorDao = BookRoomDatabase.getDatabase(application, viewModelScope).authorDAO()
        val tagDao = BookRoomDatabase.getDatabase(application, viewModelScope).tagDAO()
        val bookxtagDao = BookRoomDatabase.getDatabase(application, viewModelScope).bookxtagdao()
        repository = Repository(bookDao, tagDao, authorDao, bookxtagDao)

        allAuthors = repository.allAuthors
        allBooks = repository.allBooks
        allTags = repository.allTags
        allFavBook = repository.allFavBooks
        allbookxtags = repository.allBookXTag



    }
    //Get one
    fun getOneBook(name: String) = viewModelScope.launch (Dispatchers.IO){
        repository.getOneBook(name)
    }
    //Updates
    fun updateBook(id: Int) = viewModelScope.launch ( Dispatchers.IO ){
        repository.updateBook(id)

    }

    //Inserts
        fun insertBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
            repository.insert(book)

        }

    fun insertAuthor(author: Author) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(author)

    }
    fun insertTag(tag : Tag )= viewModelScope.launch( Dispatchers.IO ){
        repository.insert(tag)

    }

    //Deletes

    fun deleteBooks() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteBooks()
    }
    fun deleteAuthors() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAuthors()
    }
    fun deleteTags() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteTags()
    }
}