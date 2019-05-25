package com.example.taller4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4.Adapter.BookAdapter
import com.example.taller4.Interface.IComunicaFragments
import com.example.taller4.ViewModels.DataBaseViewModel

class MainActivity : AppCompatActivity(), ListBookFragment.OnFragmentInteractionListener, DetailBookFragment.OnFragmentInteractionListener, IComunicaFragments {

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var listaFragment = ListBookFragment()
    var detalleFragment = DetailBookFragment()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(findViewById<FrameLayout>(R.id.ContenedorFragments) != null){
            if(savedInstanceState != null){


            }
            supportFragmentManager.beginTransaction().replace(R.id.ContenedorFragments, listaFragment).commit()

        }else{

            supportFragmentManager.beginTransaction().replace(R.id.fl_lista, listaFragment).commit();

        }



    }
    override fun enviarLibro(nombre: String, id_Autor: Int, ISBN: String, Caratula: Int, Resumen: String
    ) {
        val bundle  = Bundle()

        bundle.putString("Nombre",nombre)
        bundle.putInt("Id",id_Autor)
        bundle.putString("ISBN",  ISBN)
        bundle.putInt("Caratula", Caratula)
        bundle.putString("Resumen", Resumen)

        detalleFragment.arguments = bundle



        val fragmento : Fragment? = supportFragmentManager.findFragmentById(R.id.fl_detalle);
        if(findViewById<FrameLayout>(R.id.ContenedorFragments) == null){

            if(fragmento is DetailBookFragment) {
                val fragmento2: Fragment? = supportFragmentManager.findFragmentByTag("fragdetalle")
                supportFragmentManager.beginTransaction().remove(fragmento2!!).commit()

            }
            supportFragmentManager.beginTransaction().replace(R.id.fl_detalle, detalleFragment, "fragdetalle").addToBackStack(null).commitAllowingStateLoss();

            Log.i("Aja:","Aja")

        }else{
            val bundle  = Bundle()

            bundle.putString("Nombre",nombre)
            bundle.putInt("Id",id_Autor)
            bundle.putString("ISBN",  ISBN)
            bundle.putInt("Caratula", Caratula)
            bundle.putString("Resumen", Resumen)

            detalleFragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.ContenedorFragments, detalleFragment).addToBackStack(null).commit()

        }

    }



}
