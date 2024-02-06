package com.example.qgen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PredmetiViewModel : ViewModel(){
    private val predmeti = MutableStateFlow<List<Predmet>>(emptyList())
    val sviPredmet: StateFlow<List<Predmet>> = predmeti

    private val lekcije = MutableStateFlow<List<Lekcija>>(emptyList())
    val sveLekcije: MutableStateFlow<List<Lekcija>> = lekcije
    init{
        viewModelScope.launch {
            DataRepository.dohvatiPredmete().collect { predmetiList ->
                predmeti.value = predmetiList
            }
        }
    }
    fun dohvatiLekcijeZaPredmet(predmetID : String, razred: String){
        viewModelScope.launch {
            DataRepository.dohvatiLekcije().collect { lekcijeList ->
                lekcije.value = lekcijeList.filter { it.predmetID == predmetID && it.razred == razred }
            }
        }
    }
    fun togglePredmetProsiren(predmetID: String) {
        val noviPredmeti = predmeti.value.map { predmet ->
            if (predmet.idPredmeta == predmetID) {
                predmet.copy(prosireno = !predmet.prosireno)
            } else predmet
        }
        predmeti.value = noviPredmeti
    }
}