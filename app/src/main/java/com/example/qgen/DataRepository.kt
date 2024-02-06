package com.example.qgen

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object DataRepository {
    private val db = Firebase.firestore
    fun dohvatiPredmete(): Flow<List<Predmet>> = callbackFlow addSnapshotListener@{
        val collectionReferenca = db.collection("Predmeti")
        val slusajPromjene = collectionReferenca.addSnapshotListener { snapshot, e ->
            if (e != null) {
                close(e)
                return@addSnapshotListener
            }
            val predmeti = snapshot?.documents?.mapNotNull { it.toObject((Predmet::class.java)) }.orEmpty()
            trySend(predmeti).isSuccess
        }
        awaitClose { slusajPromjene.remove() }
    }

}