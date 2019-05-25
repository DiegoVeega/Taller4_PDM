package com.example.taller4

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4.Adapter.BookAdapter
import com.example.taller4.Database.Entitys.Author
import com.example.taller4.Database.Entitys.Book
import com.example.taller4.Interface.IComunicaFragments
import com.example.taller4.ViewModels.DataBaseViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListBookFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListBookFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListBookFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    var actividad  = Activity()
    var iCOmunica = object : IComunicaFragments {
        override fun enviarLibro(
            nombre: String,
            id_Autor: Int,
            ISBN: String,
            Caratula: Int,
            Resumen: String
        ) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_list_book, container, false)

        val recyclerView = vista.findViewById<RecyclerView>(R.id.booksRV)
        val adapter = context?.let { BookAdapter(it) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)




        val viewmodel = ViewModelProviders.of(this).get(DataBaseViewModel::class.java)

        viewmodel.insertAuthor(Author("Mario","Espania"))
        viewmodel.insertAuthor(Author("Ricardo","El salvador"))
        viewmodel.insertAuthor(Author("Victor","Costa Rica"))

        viewmodel.allBooks.observe(this, Observer { books->
            books?.let { adapter?.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View) {
                    Toast.makeText(context, "Selecciona: "+ books.get(recyclerView.getChildAdapterPosition(v)).id_Book,Toast.LENGTH_SHORT).show()
                    iCOmunica.enviarLibro(books.get(recyclerView.getChildAdapterPosition(v)).BookName,
                        books.get(recyclerView.getChildAdapterPosition(v)).id_Book,
                        books.get(recyclerView.getChildAdapterPosition(v)).Isbn_libro,
                        books.get(recyclerView.getChildAdapterPosition(v)).Cover,
                        books.get(recyclerView.getChildAdapterPosition(v)).Summary)

                }})}
        })

        viewmodel.allAuthors.observe(this, Observer { authors ->
            Log.d("autors", "- - - - - - - - - - - - - -")
            for(repo in authors){
                Log.d("Lista de repos", repo.AuthorName)

            }
        })

        viewmodel.allBooks.observe(this, Observer { books->
            books?.let { adapter?.setWords(books) }
        })

        val fab = vista.findViewById<Button>(R.id.btn_agregar)
        fab.setOnClickListener {
            val intent = Intent(context, NewBookActivity::class.java)
            startActivity(intent)
        }
        return vista
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Interface
        if(context is Activity){
            this.actividad = context as Activity
            iCOmunica = this.actividad as IComunicaFragments
        }

        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListBookFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListBookFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
