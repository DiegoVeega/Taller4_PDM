package com.example.taller4.Interface

import com.example.taller4.Database.Entitys.Book

interface IComunicaFragments {
    fun enviarLibro(nombre : String, id_Autor : Int, ISBN : String, Caratula: Int, Resumen : String)
}