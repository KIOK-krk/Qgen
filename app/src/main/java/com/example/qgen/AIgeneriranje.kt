package com.example.qgen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard

@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun AIgeneriranje(navigiranjeEkrana: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                    Text("Pitanje")
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = "BlaBlaBla",
                        onValueChange = { /*TODO*/ }
                    )
                    Text("Odgovor 1")
                    PitanjeRow()
                    Text("Odgovor 2")
                    PitanjeRow()
                    Text("Odgovor 3")
                    PitanjeRow()
                    Text("Pitanje")
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = "BlaBlaBla",
                        onValueChange = { /*TODO*/ }
                    )

                }

            }
        }
    }
    @Composable
    fun PitanjeRow() {
        Row {
            OutlinedTextField(
            modifier = Modifier
                .padding(start = 24.dp),
            value = "blablabla",
            onValueChange = { /*TODO*/ }
        )
        RadioButton(
            modifier = Modifier
                .padding(top = 4.dp),
            selected = false,
            onClick = {}
        )
    }
}
