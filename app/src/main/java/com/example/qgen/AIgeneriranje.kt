package com.example.qgen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard

@Composable
fun GornjiBar(navigiranjeEkrana: NavHostController) {
    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ){
        IconButton(onClick = { navigiranjeEkrana.navigateUp() }) {
            Icon(
                Icons.Default.ArrowBack, "Povratak",
                tint = Color(0xFF1c81b8),
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(55.dp)
            )
        }
        Text(
            text = "Nova AI Pitanja",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp, start = 8.dp)

        )
    }
}

@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun AIgeneriranje(navigiranjeEkrana: NavHostController) {
    GornjiBar(navigiranjeEkrana)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = rememberSwipeableCardState()
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp
                )
                .swipableCard(state = state, onSwiped = { direction ->
                    println("The card was swiped to $direction")
                }, onSwipeCancel = {
                    println("The swiping was cancelled")
                })
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor =Color(0xFF1c81b8)
                )
            ) {
                Text(
                    "Pitanje",
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                OutlinedTextField(
                    value = "Koji je najbitniji dio kompjutera?",
                    onValueChange = {},
                    enabled = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 60.dp)
                        .padding(start = 16.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                        .background(Color.White)
                )
                Text(
                    "Odgovor 1",
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                PitanjeRow()
                Text(
                    "Odgovor 2",
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                PitanjeRow()
                Text(
                    "Odgovor 3",
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                PitanjeRow()
                Text(
                    "Zanimljivost",
                    modifier = Modifier
                        .padding(start = 16.dp)
                )

                OutlinedTextField(
                    value = "Koji je najbitniji dio kompjutera?Koji je najbitniji dio kompjutera?",
                    onValueChange = {},
                    enabled = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 150.dp)
                        .padding(start = 16.dp, end = 8.dp, top = 4.dp, bottom = 8.dp)
                        .background(Color.White)
                )

            }

        }
        Row{
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(0xFF1c81b8)),
                modifier = Modifier
                    .padding(end = 32.dp)) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                )
            }
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(0xFF1c81b8)),
                modifier = Modifier
                    .padding(start = 32.dp)) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                )
            }
        }
    }
}
@Composable
fun PitanjeRow() {
Row(
    modifier = Modifier
        .fillMaxWidth()
) {
    OutlinedTextField(
        value = "Matična ploča",
        onValueChange = {},
        enabled = true,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        ),
        modifier = Modifier
            .defaultMinSize(minHeight = 60.dp)
            .padding(start = 28.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
            .width(250.dp)
            .background(Color.White)
       )
    RadioButton(
        modifier = Modifier
            .padding(start = 4.dp, top = 4.dp),
        selected = false,
        onClick = {}
    )
}
}
