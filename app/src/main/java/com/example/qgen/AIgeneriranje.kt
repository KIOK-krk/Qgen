package com.example.qgen

import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.aghajari.compose.lazyswipecards.LazySwipeCards
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi


@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun AIgeneriranje(
    navigiranjeEkrana: NavHostController,
    idLekcije: String?,
    upute: String?,
    oznakaLekcije: String?,
    viewModel: AIgeneriranjeViewModel = viewModel()
) {
    val pitanjaZaDodatiState = viewModel.odgovori.collectAsState(initial = emptyList())


//    Column {
//        pitanjaZaDodatiState.value.forEach { odgovor ->
//            Text(text = odgovor.toString())
//        }
//    }
    generiraneKartice(navigiranjeEkrana, upute, idLekcije, viewModel)
}

@Composable
fun generiraneKartice(
    navigiranjeEkrana: NavHostController,
    upute: String?,
    idLekcije: String?,
    viewModel: AIgeneriranjeViewModel
) {

    LaunchedEffect(key1 = true) {
        viewModel.generirajPitanja(upute.toString())
    }

    var pitanja2 = emptyArray<Pitanje>()
    pitanja2.shuffle()
    pitanja2 = pitanja2.take(5).toTypedArray()
    val list = remember { mutableStateListOf(*pitanja2) }

    GornjiBar(navigiranjeEkrana)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LazySwipeCards(
            cardModifier = Modifier,
            cardShape = RoundedCornerShape(16.dp),
            cardShadowElevation = 4.dp,
            visibleItemCount = 4,
            rotateDegree = 15f,
            translateSize = 24.dp,
            animationSpec = SpringSpec(),
            swipeThreshold = 0.5f,
            scaleFactor = ScaleFactor(
                scaleX = 0.1f, scaleY = 0.1f
            ),
            contentPadding = PaddingValues(
                vertical = 24.dp * 4, // visibleItemCount
                horizontal = 24.dp
            )
        ) {
            onSwiped { item, direction ->
                println("OnSwiped: $item to ${direction.name}")
                if (direction.name == "RIGHT") {
                    viewModel.dodajPitanje(item as Pitanje)
                }
            }
            onSwiping { dx, ratio, direction ->
                println("$dx : $ratio : ${direction.name}")
            }

            items(list) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFFFFFF))
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFFFFF)
                        )
                    ) {
                        Text(
                            "Pitanje", modifier = Modifier.padding(start = 16.dp)
                        )
                        OutlinedTextField(
                            value = it.tekstPitanja,
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
                            "Odgovor 1", modifier = Modifier.padding(start = 16.dp)
                        )
                        var oznaci = false
                        if (it.tocanOdgovor == 1) oznaci = true
                        else oznaci = false
                        PitanjeRow(it.odgovori[0], oznaci)
                        Text(
                            "Odgovor 2", modifier = Modifier.padding(start = 16.dp)
                        )
                        if (it.tocanOdgovor == 2) oznaci = true
                        else oznaci = false
                        PitanjeRow(it.odgovori[1], oznaci)
                        Text(
                            "Odgovor 3", modifier = Modifier.padding(start = 16.dp)
                        )
                        if (it.tocanOdgovor == 3) oznaci = true
                        else oznaci = false
                        PitanjeRow(it.odgovori[2], oznaci)
                        Text(
                            "Zanimljivost", modifier = Modifier.padding(start = 16.dp)
                        )

                        OutlinedTextField(
                            value = it.zanimljivost,
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
            }
        }
    }
}

@Composable
fun PitanjeRow(odgovor: String, tocno: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = odgovor,
            onValueChange = {},
            enabled = true,
            textStyle = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .defaultMinSize(minHeight = 60.dp)
                .padding(start = 28.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                .width(250.dp)
        )
        RadioButton(
            modifier = Modifier.padding(start = 4.dp, top = 4.dp),
            selected = tocno,
            onClick = {})
    }
}


@Composable
fun GornjiBar(navigiranjeEkrana: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top
    ) {
        IconButton(onClick = { navigiranjeEkrana.navigateUp() }) {
            Icon(
                Icons.Default.ArrowBack,
                "Povratak",
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
            modifier = Modifier.padding(top = 16.dp, start = 8.dp)

        )
    }
}
