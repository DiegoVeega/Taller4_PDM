package com.example.taller4.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4.Database.DAO.AuthorDAO
import com.example.taller4.Database.DAO.BookDAO
import com.example.taller4.Database.DAO.TagDAO
import com.example.taller4.Database.Entitys.Author
import com.example.taller4.Database.Entitys.Book
import com.example.taller4.Database.Entitys.Tag

class Repository(private val BookDao : BookDAO, private val TagDao : TagDAO, private val AuthorDao : AuthorDAO) {

    val allBooks: LiveData<List<Book>> = BookDao.getAllBooks()
    val allTags : LiveData<List<Tag>> = TagDao.getAllTags()
    val allAuthors : LiveData<List<Author>> = AuthorDao.getAllAuthor()

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
}