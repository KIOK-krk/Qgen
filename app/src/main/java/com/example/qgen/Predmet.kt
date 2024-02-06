package com.example.qgen

data class Predmet(
    val idPredmeta: String = "",
    val nazivPredmeta: String = "",
    val prosireno: Boolean = false,
    val razred: List<String> = emptyList<String>()
)


