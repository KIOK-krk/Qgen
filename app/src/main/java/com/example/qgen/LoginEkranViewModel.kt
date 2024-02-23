package com.example.qgen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(email : String) : ViewModel(){

    private val korisnici = MutableStateFlow<List<Korisnik>>(emptyList())
    val sviKorisnici: StateFlow<List<Korisnik>> = korisnici

    init {
        viewModelScope.launch {
            DataRepository.dohvatiKorisnike().collect { korisniciList ->
                korisnici.value = korisniciList
            }
        }
    }
}

