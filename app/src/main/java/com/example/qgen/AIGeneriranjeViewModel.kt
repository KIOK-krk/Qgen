package com.example.qgen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.chat.ChatCompletionChunk
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class AIgeneriranjeViewModel() : ViewModel() {
    private val _pitanja = MutableStateFlow<List<Pitanje>>(emptyList())
    val pitanja: StateFlow<List<Pitanje>> = _pitanja

    private val _pitanjaZaDodati = MutableStateFlow<List<Pitanje>>(emptyList())
    val pitanjaZaDodati: StateFlow<List<Pitanje>> = _pitanjaZaDodati

    private val _odgovori = MutableStateFlow<List<String>>(emptyList())
    val odgovori: StateFlow<List<String>> = _odgovori

    fun generirajPitanja2(AIupute: String) {
        val openai = OpenAI(
            token = "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",
            timeout = Timeout(socket = 60.seconds),

            )
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "Ti si asistent za učitelje, kojima treba pomoći u generiranju pitanja i odgovora Za učenike od 6 do 14 godina. Na hrvatskom jeziku."
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = AIupute
                )
            )
        )
        val completions: Flow<ChatCompletionChunk> = openai.chatCompletions(chatCompletionRequest)
    }

    fun generirajPitanja(AIupute: String) {
        viewModelScope.launch {
            val openai = OpenAI(
                token = "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",
                timeout = Timeout(socket = 60.seconds)
            )
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.System,
                        content = "Ti si asistent za učitelje, kojima treba pomoći u generiranju pitanja i odgovora Za učenike od 6 do 14 godina. Na hrvatskom jeziku."
                    ),
                    ChatMessage(role = ChatRole.User, content = AIupute)
                )
            )
            openai.chatCompletions(chatCompletionRequest).collect { completionChunk ->
                // Obrada svakog komada odgovora
                val currentOdgovori = _odgovori.value.toMutableList()
                currentOdgovori.add(completionChunk.choices.first().delta.content.orEmpty())
                _odgovori.value = currentOdgovori
            }
        }
    }

    fun dodajPitanje(pitanje: Pitanje) {
        //val trenutnaLista = _pitanjaZaDodati.value.toMutableList()
        //trenutnaLista.add(pitanje)
        //_pitanjaZaDodati.value = trenutnaLista
        viewModelScope.launch {
            DataRepository.dodajPitanje(pitanje).collect { rezultat ->
                if (rezultat.isSuccess) {
                    println("Pitanje dodano s ID: ${rezultat.getOrNull()}")
                } else {
                    rezultat.exceptionOrNull()?.message?.let { println(it) }
                }
            }
        }
    }



}