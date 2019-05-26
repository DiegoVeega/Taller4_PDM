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

class FavBookAdapter internal constructor(context: Context) : RecyclerView.Adapter<FavBookAdapter.FavBookViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var books = emptyList<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavBookViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item2, parent, false)
        return FavBookViewHolder(itemView)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: FavBookViewHolder, position: Int) {
        val current = books[position]
        holder.wordItemView.text = current.BookName
        holder.wordItemViewR.text = current.Summary

    }
    internal fun setWords(words: List<Book>) {
        this.books = words
        notifyDataSetChanged()
    }

    class FavBookViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val wordItemViewR: TextView = itemView.findViewById(R.id.textViewResumen)


    }


}