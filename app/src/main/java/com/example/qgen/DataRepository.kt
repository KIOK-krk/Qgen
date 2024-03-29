package com.example.qgen

import Lekcija
import com.google.firebase.firestore.SetOptions
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
            val predmeti = snapshot?.documents?.mapNotNull { document ->
                document.toObject(Predmet::class.java)?.apply {
                    idPredmeta = document.id // Postavi idPredmeta na Firestore dokument ID
                }
            }.orEmpty()
            trySend(predmeti).isSuccess

        }
        awaitClose { slusajPromjene.remove() }
    }

    fun dohvatiLekcije(): Flow<List<Lekcija>> = callbackFlow addSnapshotListener@{
        val collectionReferenca = db.collection("Lekcije")
        val slusajPromjene = collectionReferenca.addSnapshotListener { snapshot, e ->
            if (e != null) {
                close(e)
                return@addSnapshotListener
            }
            val lekcije = snapshot?.documents?.mapNotNull { document ->
                document.toObject(Lekcija::class.java)?.apply {
                    idLekcije = document.id // Postavi idLekcije na Firestore dokument ID
                }
            }.orEmpty()
            trySend(lekcije).isSuccess
        }
        awaitClose { slusajPromjene.remove() }
    }

    fun dohvatiPitanja(): Flow<List<Pitanje>> = callbackFlow addSnapshotListener@{
        val collectionReferenca = db.collection("Pitanja")
        val slusajPromjene = collectionReferenca.addSnapshotListener { snapshot, e ->
            if (e != null) {
                close(e)
                return@addSnapshotListener
            }
            val pitanja = snapshot?.documents?.mapNotNull { document ->
                document.toObject(Pitanje::class.java)?.apply {
                    idPitanja = document.id // Postavi idLekcije na Firestore dokument ID
                }
            }.orEmpty()
            trySend(pitanja).isSuccess
        }
        awaitClose { slusajPromjene.remove() }
    }

    fun obrisiPitanje(idPitanja: String) = callbackFlow {
        val documentReferenca = db.collection("Pitanja").document(idPitanja)
        documentReferenca.delete()
            .addOnSuccessListener {
                // Uspješno brisanje pitanja.
                trySend(Result.success(Unit)).isSuccess
            }
            .addOnFailureListener { e ->
                // Došlo je do greške prilikom brisanja pitanja.
                close(e)
            }
        awaitClose { }
    }

    fun dodajPitanje(pitanje: Pitanje) = callbackFlow {
        val collectionReferenca = db.collection("Pitanja")
        collectionReferenca.add(pitanje)
            .addOnSuccessListener { documentReference ->
                trySend(Result.success(documentReference.id)).isSuccess
            }
            .addOnFailureListener { e ->
                close(e)
            }
        awaitClose { }
    }

    fun azurirajPitanje(idPitanja: String, novoPitanje: Pitanje) = callbackFlow {
        val documentReferenca = db.collection("Pitanja").document(idPitanja)
        val ažuriranoPitanje = novoPitanje.copy(idPitanja = "")
        documentReferenca.set(ažuriranoPitanje, SetOptions.merge())
            .addOnSuccessListener {
                trySend(Result.success(Unit)).isSuccess
            }
            .addOnFailureListener { e ->
                close(e)
            }
        awaitClose { }
    }
    fun dohvatiKorisnike(): Flow<List<Korisnik>> = callbackFlow addSnapshotListener@{
        val collectionReferenca = db.collection("Korisnici")
        val slusajPromjene = collectionReferenca.addSnapshotListener { snapshot, e ->
            if (e != null) {
                close(e)
                return@addSnapshotListener
            }
            val korisnici = snapshot?.documents?.mapNotNull { document ->
                document.toObject(Korisnik::class.java)?.apply {
                    idKorisnika = document.id
                }
            }.orEmpty()
            trySend(korisnici).isSuccess
        }
        awaitClose { slusajPromjene.remove() }
    }

}