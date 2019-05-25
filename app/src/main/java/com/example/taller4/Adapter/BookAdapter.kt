package com.example.taller4.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4.Database.Entitys.Book
import com.example.taller4.R

class BookAdapter internal constructor(context: Context) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() , View.OnClickListener {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var books = emptyList<Book>() // Cached copy of words
    private var listener: View.OnClickListener ?= null

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val wordItemViewR: TextView = itemView.findViewById(R.id.textViewResumen)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        itemView.setOnClickListener(this)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val current = books[position]
        holder.wordItemView.text = current.BookName
        holder.wordItemViewR.text = current.Summary
    }

    internal fun setWords(words: List<Book>) {
        this.books = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = books.size

    fun setOnClickListener(listener: View.OnClickListener){
        this.listener = listener

    }

    override fun onClick(v: View?) {
        if(listener !=null){
            listener?.onClick(v)
        }
    }
}