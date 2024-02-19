package com.example.qgen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun PredmetiEkran(
    navigiranjeEkrana: NavHostController,
    viewModel: PredmetiViewModel = viewModel()
) {
    viewModel.dohvatiSveLekcije()
    val predmeti = viewModel.sviPredmet.collectAsState().value

    Column {
        Row {
            Text(text = "Predmeti",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(all = 10.dp)
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
        LazyColumn() {
            items(predmeti) { predmet ->
                PredmetKartica(predmet,navigiranjeEkrana,viewModel)
            }
        }
        }
    }


@Composable
fun PredmetKartica(predmet: Predmet,navigiranjeEkrana:NavHostController, viewModel: PredmetiViewModel) {
    // Pretpostavimo da viewModel već prati prosireno stanje za svaki predmet
    val lekcije = viewModel.sveLekcije.collectAsState().value
        .filter { it.PredmetID == predmet.idPredmeta && it.Razred == "6" }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                viewModel.togglePredmetProsiren(predmet.idPredmeta)
            }) {
            Text(
                text = predmet.nazivPredmeta,
                modifier = Modifier.padding(16.dp)
            )
            if (predmet.prosireno) {
                lekcije.forEach { lekcija ->
                    karticaLekcija(nazivLekcije = lekcija.Naziv, navigiranjeEkrana = navigiranjeEkrana)
                }
            }
        }
    }
}

@Composable
fun karticaLekcija(
    nazivLekcije: String,
    navigiranjeEkrana: NavHostController
) {
    Text(
        text = nazivLekcije,
        modifier = Modifier
            .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
            .clickable {
                navigiranjeEkrana.navigate("ListaPitanja")
            }
    )
}