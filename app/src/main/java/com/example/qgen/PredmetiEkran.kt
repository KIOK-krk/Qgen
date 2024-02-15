package com.example.qgen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun PredmetiEkran(
    navigiranjeEkrana: NavHostController,
    viewModel: PredmetiViewModel = viewModel()
) {
    val predmeti = viewModel.sviPredmet.collectAsState().value
    val lekcije = viewModel.sveLekcije.collectAsState().value

    Column {
        Row {
            Text(text = "Predmeti",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .clickable { navigiranjeEkrana.navigate("ListaPitanja") }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "5. Razred",
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(
                        end = 10.dp,
                        start = 10.dp,
                        bottom = 10.dp,
                        top = 15.dp,
                    )
            )
        }
        LazyColumn {
            items(predmeti) { predmet ->
                PredmetKartica(predmet, viewModel)
            }
        }
    }
}

@Composable
fun PredmetKartica(predmet: Predmet, viewModel: PredmetiViewModel) {
    var visina = 70
    Card(
        shape = RoundedCornerShape(
            8.dp
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(visina.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.togglePredmetProsiren(predmet.idPredmeta)
            }
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    predmet.nazivPredmeta,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 4.dp, start = 24.dp)
                        .weight(1f)
                )
                Icon(
                    imageVector = if (predmet.prosireno) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xFF1c81b8),
                    modifier = Modifier
                        .padding(top = 8.dp, end = 15.dp)
                )
            }
            if (predmet.prosireno) {
                val lekcije = viewModel.sveLekcije.collectAsState().value
                    .filter { it.predmetID == predmet.idPredmeta }
                LazyColumn {
                    items(lekcije) { lekcija ->
                        Text(
                            text = lekcija.naziv,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}