package com.example.qgen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
@Preview
@Composable
fun AIgeneriranjePreview(){
    AIgeneriranje()
}


@Composable
fun ListaPitanja() {
    Column{
        Row {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,
                tint = Color(0xFF1c81b8))
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.Create, contentDescription = null,
                tint = Color(0xFF1c81b8))
        }
        LazyColumn {
            items(15) {
                Card(
                    shape = RoundedCornerShape(
                        7.dp
                    ),
                    elevation = CardDefaults.cardElevation(
                        5.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(vertical = 3.5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text("Pitanje $it",
                            modifier = Modifier
                                .padding(top = 4.dp, start = 25.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Default.Add, contentDescription = null,
                            tint = Color(0xFF1c81b8),
                            modifier = Modifier
                                .padding(top = 4.dp, end = 15.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ListaPitanjaPreview(){
    ListaPitanja()
}

@Composable
fun ListaLekcija(){
    Column {
        Row{
            Text(text = "Predmeti",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(all =10.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "5. Razred",
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(end =10.dp,
                        start =10.dp,
                        bottom =10.dp,
                top =15.dp,)
            )
        }
        Card(
            shape = RoundedCornerShape(
                7.dp
            ),
            elevation = CardDefaults.cardElevation(
                5.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(vertical = 3.5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Geometrija",
                    modifier = Modifier
                        .padding(top = 4.dp, start = 25.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null,
                    tint = Color(0xFF1c81b8),
                    modifier = Modifier
                        .padding(top = 4.dp, end = 15.dp)
                )
            }
        }
        Card(
            shape = RoundedCornerShape(
                7.dp
            ),
            elevation = CardDefaults.cardElevation(
                5.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(vertical = 3.5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Razlomci",
                    modifier = Modifier
                        .padding(top = 4.dp, start = 25.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null,
                    tint = Color(0xFF1c81b8),
                    modifier = Modifier
                        .padding(top = 4.dp, end = 15.dp)
                )
            }
        }
        Card(
            shape = RoundedCornerShape(
                7.dp
            ),
            elevation = CardDefaults.cardElevation(
                5.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(vertical = 3.5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("neka lekcija ne znam",
                    modifier = Modifier
                        .padding(top = 4.dp, start = 25.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null,
                    tint = Color(0xFF1c81b8),
                    modifier = Modifier
                        .padding(top = 4.dp, end = 15.dp)
                )
            }
        }
        Card(
            shape = RoundedCornerShape(
                7.dp
            ),
            elevation = CardDefaults.cardElevation(
                5.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(vertical = 3.5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Ponavljanje",
                    modifier = Modifier
                        .padding(top = 4.dp, start = 25.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null,
                    tint = Color(0xFF1c81b8),
                    modifier = Modifier
                        .padding(top = 4.dp, end = 15.dp)
                )
            }
        }
    }
}
@Preview
@Composable
fun ListaLekcijaPreview(){
    ListaLekcija()
}
@Preview
@Composable
fun ListaPredmetaPreview(){
    ListaPredmeta()
}
@Composable
fun ListaPredmeta(){
    Column {
        Row{
            Text(text = "Predmeti",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(all =10.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "5. Razred",
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(end =10.dp,
                        start =10.dp,
                        bottom =10.dp,
                        top =15.dp,)
            )
        }
        Card(
            shape = RoundedCornerShape(
                7.dp
            ),
            elevation = CardDefaults.cardElevation(
                5.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(vertical = 3.5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Matematika",
                    modifier = Modifier
                        .padding(top = 4.dp, start = 25.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null,
                    tint = Color(0xFF1c81b8),
                    modifier = Modifier
                        .padding(top = 4.dp, end = 15.dp)
                )
            }
        }
        Card(
            shape = RoundedCornerShape(
                7.dp
            ),
            elevation = CardDefaults.cardElevation(
                5.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(vertical = 3.5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Hrvatski Jezik",
                    modifier = Modifier
                        .padding(top = 4.dp, start = 25.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null,
                    tint = Color(0xFF1c81b8),
                    modifier = Modifier
                        .padding(top = 4.dp, end = 15.dp)
                )
            }
        }
        Card(
            shape = RoundedCornerShape(
                7.dp
            ),
            elevation = CardDefaults.cardElevation(
                5.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(vertical = 3.5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Povijest",
                    modifier = Modifier
                        .padding(top = 4.dp, start = 25.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null,
                    tint = Color(0xFF1c81b8),
                    modifier = Modifier
                        .padding(top = 4.dp, end = 15.dp)
                )
            }
        }
        Card(
            shape = RoundedCornerShape(
                7.dp
            ),
            elevation = CardDefaults.cardElevation(
                5.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(vertical = 3.5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Geografija",
                    modifier = Modifier
                        .padding(top = 4.dp, start = 25.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null,
                    tint = Color(0xFF1c81b8),
                    modifier = Modifier
                        .padding(top = 4.dp, end = 15.dp)
                )
            }
        }
    }
}

