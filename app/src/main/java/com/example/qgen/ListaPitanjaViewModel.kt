package com.example.qgen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaPitanjaViewModel : ViewModel(){
    private val pitanja = MutableStateFlow<List<Pitanje>>(emptyList())
    val svaPitanja: StateFlow<List<Pitanje>> = pitanja

    init {
        viewModelScope.launch {
            DataRepository.dohvatiPitanja().collect { pitanjaList ->
                pitanja.value = pitanjaList
            }
        }
    }

    fun azurirajPitanje(idPitanja: String, azuriranoPitanje: Pitanje) {
        viewModelScope.launch {
            DataRepository.azurirajPitanje(idPitanja, azuriranoPitanje).collect { rezultat ->
                if (rezultat.isSuccess) {
                    println("Pitanje ažurirano.")
                } else {
                    rezultat.exceptionOrNull()?.message?.let { println(it) }
                }
            }
        }
    }

    fun obrisiPitanje(idPitanja: String) {
        viewModelScope.launch {
            DataRepository.obrisiPitanje(idPitanja).collect { rezultat ->
                if (rezultat.isSuccess) {
                    println("Pitanje je uspješno obrisano.")
                } else {
                    rezultat.exceptionOrNull()?.message?.let { println(it) }
                }
            }
        }
    }
}
