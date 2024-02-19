package com.example.qgen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaPitanjaViewModel : ViewModel(){
    private val pitanja = MutableStateFlow<List<Pitanje>>(emptyList())
    val svaPitanja: StateFlow<List<Pitanje>> = pitanja

    init{
        viewModelScope.launch {
            DataRepository.dohvatiPitanja().collect { pitanjaList ->
                pitanja.value = pitanjaList
            }
        }
    }
//    fun dohvatiSvaPitanja(){
//        viewModelScope.launch {
//            DataRepository.dohvatiPitanja().collect { pitanjaList ->
//                pitanja.value = pitanjaList //.filter { it.pitanjeID == pitanjeID && it.razred == razred }
//            }
//            Log.d("pitanja", pitanja.value.toString())
//        }
//    }

}
