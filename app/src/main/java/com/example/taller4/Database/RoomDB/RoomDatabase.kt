package com.example.taller4.Database.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taller4.Database.DAO.AuthorDAO
import com.example.taller4.Database.DAO.BookDAO
import com.example.taller4.Database.DAO.BookXTagDAO
import com.example.taller4.Database.DAO.TagDAO
import com.example.taller4.Database.Entitys.Author
import com.example.taller4.Database.Entitys.Book
import com.example.taller4.Database.Entitys.BookXTag
import com.example.taller4.Database.Entitys.Tag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Tag::class,Book::class, Author::class, BookXTag::class), version = 9)
public abstract class BookRoomDatabase : RoomDatabase() {

    abstract fun bookDao() : BookDAO
    abstract fun tagDAO() : TagDAO
    abstract fun authorDAO() : AuthorDAO
    abstract  fun bookxtagdao() : BookXTagDAO

    companion object {
        @Volatile
        private var INSTANCE: BookRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BookRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "Book_database"
                ).fallbackToDestructiveMigration().
                        addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private class WordDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.authorDAO(), database.bookDao(),database.tagDAO(), database.bookxtagdao())
                    }
                }
            }
        }


        suspend fun populateDatabase(authorDAO: AuthorDAO, bookDao : BookDAO, tagDAO: TagDAO, bookXTagDAO: BookXTagDAO) {
           bookDao.DeleteBooks()
            authorDAO.deleteAllAuthor()
            tagDAO.deleteTags()
            bookXTagDAO.deleteBookxTag()

            //Insertar autores

            authorDAO.insert(Author(1,"Mario","Espania"))
            authorDAO.insert(Author(2,"Ricardo","El Salvador"))
            authorDAO.insert(Author(3,"Victor","Costa Rica"))

            //Insertar Tags


            tagDAO.insert(Tag(1,"Aventura"))
            tagDAO.insert(Tag(2,"Accion"))
            tagDAO.insert(Tag(3,"Terror"))
            tagDAO.insert(Tag(4,"Ciencia Ficcion"))
            tagDAO.insert(Tag(5,"Romance"))

            //Insertar Libros

            bookDao.insert(Book(1,"Harry Potter",1,"123456",1,"es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.",false))
            bookDao.insert(Book(2,"Don Quijote de la mancha",2,"298374",1,"es una novela escrita por el español Miguel de Cervantes Saavedra. Publicada su primera parte con el título de El ingenioso hidalgo don Quijote de la Mancha a comienzos de 1605,",false))
            bookDao.insert(Book(3,"El señor de los anillos",3,"234234",1,"es una novela de fantasía épica escrita por el filólogo y escritor británico J. R. R. Tolkien. ",false))
            bookDao.insert(Book(4,"El Principito",1,"356345",1," es una novela corta y la obra más famosa del escritor y aviador francés Antoine de Saint-Exupéry ",false))
            bookDao.insert(Book(5,"El Codigo Da Vinci",2,"657567",1," es una novela de misterio escrita por Dan Brown y publicada por primera vez por Random House en 2003.  ",false))
            bookDao.insert(Book(6,"El Alquimista",3,"678678",1,"es una novela escrita por el brasileño Paulo Coelho que ha sido traducida a más de 63 lenguas y publicada en 150 países, ",false))
            bookDao.insert(Book(7,"El Perfume",1,"3235234",1,"Con su increíble talento por discernir las esencias, Jean-Baptiste Grenouille se convierte en el aprendiz de un perfumista en la Francia del siglo XVIII. ",false))
            bookDao.insert(Book(8,"Tierra de Hombres",2,"2798078",1,"Los constantes abusos por parte de sus colegas obligan a una mujer que trabaja en las minas a entablar una demanda de acoso sexual en contra de la compañía. ",false))
            bookDao.insert(Book(9,"El Viejo y el mar",3,"96775",1,"El viejo y el mar es una novela escrita por Ernest Hemingway en 1951 en Cuba y publicada en 1952.",false))
            bookDao.insert(Book(10,"El buscon",1,"45674576",1,"La vida del Buscón \u200B es una novela picaresca en castellano, escrita por Francisco de Quevedo.",false))

            //Insertar BookxTag
            bookXTagDAO.insert(BookXTag(1,1,1))
            bookXTagDAO.insert(BookXTag(2,2,2))
            bookXTagDAO.insert(BookXTag(3,3,3))
            bookXTagDAO.insert(BookXTag(4,4,1))


        }




    }


}
