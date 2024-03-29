package com.example.qgen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel(){

    private val korisnici = MutableStateFlow<List<Korisnik>>(emptyList())
    val sviKorisnici: StateFlow<List<Korisnik>> = korisnici

    private val _greska = MutableStateFlow<Boolean>(false)
    val greska: StateFlow<Boolean> = _greska

    init {
        viewModelScope.launch {
            DataRepository.dohvatiKorisnike().collect { korisniciList ->
                korisnici.value = korisniciList
            }
        }
    }

    fun provjeriLogin(email: String, lozinka:String):Boolean{
        for(korisnik in korisnici.value){
            if(korisnik.email == email && korisnik.lozinka == lozinka) {
                LogiraniKorisnik.oznakaLogiranogKorsinika = korisnik.oznakaKorisnika
                _greska.value = false
                return true
            }
        }
        _greska.value = true
        return false
    }
}


object LogiraniKorisnik {
    var oznakaLogiranogKorsinika = ""
        get() = field
        set(value) {
            field = value
        }
}
