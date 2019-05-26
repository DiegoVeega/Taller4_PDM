package com.example.taller4.Adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4.Database.DAO.BookDAO
import com.example.taller4.Database.Entitys.Book
import com.example.taller4.R
import com.example.taller4.ViewModels.DataBaseViewModel

class BookAdapter internal constructor(context: Context) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() , View.OnClickListener {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var books = emptyList<Book>() //
    private var listener: View.OnClickListener ?= null
    private var listenerBoton: View.OnClickListener ?= null

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val wordItemViewR: TextView = itemView.findViewById(R.id.textViewResumen)
        val wordItemViewFav : Button = itemView.findViewById(R.id.btn_favorito)


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        itemView.setOnClickListener(listener)

        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {


        val current = books[position]
        holder.wordItemView.text = current.BookName
        holder.wordItemViewR.text = current.Summary
        holder.wordItemViewFav.setOnClickListener(listenerBoton)
        if(current.Fav.equals(false)){
            holder.wordItemViewFav.text = "Agregar"

        }else{
            holder.wordItemViewFav.text = "Eliminar"
        }
       holder.wordItemViewFav.tag = current.id_Book




    }

    internal fun setWords(words: List<Book>) {
        this.books = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = books.size

    fun setOnClickListenerBoton(listener : View.OnClickListener){
        this.listenerBoton = listener

    }

    fun setOnClickListener(listener: View.OnClickListener){
        this.listener = listener

    }


    override fun onClick(v: View?) {
        if(listener !=null){
            listener?.onClick(v)
        }
        if(listenerBoton != null){
            listenerBoton?.onClick(v)
        }
    }

}