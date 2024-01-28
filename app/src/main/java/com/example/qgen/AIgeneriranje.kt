package com.example.qgen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard

@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun AIgeneriranje() {
    val state = rememberSwipeableCardState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 38.dp
            )
            .swipableCard(
                state = state,
                onSwiped = { direction ->
                    println("The card was swiped to $direction")
                },
                onSwipeCancel = {
                    println("The swiping was cancelled")
                }
            )
    ) {
        Card {
            Text("Pitanje")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black),
                value = "BlaBlaBla",
                onValueChange = { /*TODO*/ }
            )
            Text("odgovor1")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black)
                    .padding(horizontal = 32.dp),
                value = "blablabla",
                onValueChange = { /*TODO*/ }
            )
            Text("odgovor1")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black)
                    .padding(horizontal = 32.dp),
                value = "blablabla",
                onValueChange = { /*TODO*/ }
            )
            Text("odgovor1")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black)
                    .padding(horizontal = 32.dp),
                value = "blablabla",
                onValueChange = { /*TODO*/ }
            )
            Text("Pitanje")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black),
                value = "BlaBlaBla",
                onValueChange = { /*TODO*/ }
            )

        }

    }
}
