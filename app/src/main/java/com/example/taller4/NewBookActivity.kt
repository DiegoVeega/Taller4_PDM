package com.example.taller4

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.AndroidRuntimeException
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.taller4.Database.Entitys.Book
import com.example.taller4.ViewModels.DataBaseViewModel
import java.lang.Exception

class NewBookActivity : AppCompatActivity() {
    private lateinit var editIdBook : EditText
    private lateinit var editBookName : EditText
    private lateinit var editIdAuthor : EditText
    private lateinit var editISBN: EditText
    private lateinit var editCover : EditText
    private lateinit var editSummary : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        val viewmodel = ViewModelProviders.of(this).get(DataBaseViewModel::class.java)
        editIdBook = findViewById(R.id.et_idBook)
        editBookName= findViewById(R.id.et_Bookname)
        editIdAuthor = findViewById(R.id.et_idAuthorBook)
        editISBN = findViewById(R.id.et_ISBN)
        editCover = findViewById(R.id.et_Cover)
        editSummary = findViewById(R.id.et_Summary)

        val button = findViewById<Button>(R.id.btn_agregar)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editIdBook.text)||TextUtils.isEmpty(editBookName.text) || TextUtils.isEmpty(editIdAuthor.text) || TextUtils.isEmpty(editISBN.text) || TextUtils.isEmpty(editCover.text)  || TextUtils.isEmpty(editSummary.text)   ) {
                //setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {



                val idBook = editIdBook.text.toString()
                val name = editBookName.text.toString()
                val id = editIdAuthor.text.toString()
                val ISBN = editISBN.text.toString()
                val cover = editCover.text.toString()
                val summary = editSummary.text.toString()

                try {
                viewmodel.insertBook(Book(idBook.toInt(),name,id.toInt(),ISBN,cover.toInt(),summary, false))

                Toast.makeText(this, "Se Agrego Libro Correctamente", Toast.LENGTH_SHORT).show()
                }catch (e : android.database.sqlite.SQLiteConstraintException){
                    Toast.makeText(this, "Asegurese que el ID no se este repetido", Toast.LENGTH_SHORT).show()

                }

                /*replyIntent.putExtra(EXTRA_REPLY, name)
                setResult(Activity.RESULT_OK, replyIntent)*/
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
