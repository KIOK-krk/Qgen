package com.example.qgen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PredmetiViewModel : ViewModel(){
    private val predmeti = MutableStateFlow<List<Predmet>>(emptyList())
    val sviPredmet: StateFlow<List<Predmet>> = predmeti

    init{
        viewModelScope.launch {
            DataRepository.dohvatiPredmete().collect { predmetiList ->
                predmeti.value = predmetiList
            }
        }
    }
}