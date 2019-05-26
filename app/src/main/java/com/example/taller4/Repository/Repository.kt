package com.example.taller4.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4.Database.DAO.AuthorDAO
import com.example.taller4.Database.DAO.BookDAO
import com.example.taller4.Database.DAO.BookXTagDAO
import com.example.taller4.Database.DAO.TagDAO
import com.example.taller4.Database.Entitys.Author
import com.example.taller4.Database.Entitys.Book
import com.example.taller4.Database.Entitys.BookXTag
import com.example.taller4.Database.Entitys.Tag

class Repository(private val BookDao : BookDAO, private val TagDao : TagDAO, private val AuthorDao : AuthorDAO, private val BookxtagDao: BookXTagDAO) {

    val allBooks: LiveData<List<Book>> = BookDao.getAllBooks()
    val allTags : LiveData<List<Tag>> = TagDao.getAllTags()
    val allAuthors : LiveData<List<Author>> = AuthorDao.getAllAuthor()
    val allFavBooks : LiveData<List<Book>> = BookDao.getAllFavBooks()
    val allBookXTag : LiveData<List<BookXTag>> = BookxtagDao.getAllBookXTag()


    //getOne
    @WorkerThread
    suspend fun getOneBook(name : String){
        BookDao.getOneBook(name)
    }
    //Updates
    @WorkerThread
    suspend fun updateBook(id : Int){
        BookDao.updateBook(id)
    }
    //Inserts
    @WorkerThread
    suspend fun insert(bookXTag: BookXTag){
        BookxtagDao.insert(bookXTag)
    }
    @WorkerThread
    suspend fun insert(book: Book) {
        BookDao.insert(book)
    }

    @WorkerThread
    suspend fun insert(tag: Tag){
        TagDao.insert(tag)
    }

    @WorkerThread
    suspend fun insert(author: Author){
        AuthorDao.insert(author)

    }
    //Deletes

    @WorkerThread
    suspend fun deletebookxtag(){
        BookxtagDao.deleteBookxTag()

    }
    @WorkerThread
    suspend fun deleteTags(){
        TagDao.deleteTags()
    }
    @WorkerThread
    suspend fun deleteAuthors(){
        AuthorDao.deleteAllAuthor()
    }
    @WorkerThread
    suspend fun deleteBooks(){
        BookDao.DeleteBooks()
    }
}