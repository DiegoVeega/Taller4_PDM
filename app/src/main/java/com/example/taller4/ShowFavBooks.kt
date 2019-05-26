package com.example.taller4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4.Adapter.BookAdapter
import com.example.taller4.Adapter.FavBookAdapter
import com.example.taller4.ViewModels.DataBaseViewModel

class ShowFavBooks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_fav_books)

        val recyclerView = findViewById<RecyclerView>(R.id.booksFavRV)
        val adapter = this.let { FavBookAdapter(this) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)




        val viewmodel = ViewModelProviders.of(this).get(DataBaseViewModel::class.java)

        viewmodel.allFavBook.observe(this, Observer { books->
            books?.let {

                adapter?.setWords(books)

            }
        })
    }
}
