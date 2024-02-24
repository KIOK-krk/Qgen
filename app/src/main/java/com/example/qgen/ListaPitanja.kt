package com.example.qgen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun ListaPitanja(
    navigiranjeEkrana: NavHostController,
    naslov: String?,
    idLekcija: String?,
    oznakaLekcije : String?,
    viewModel: ListaPitanjaViewModel = viewModel()
) {
    val pitanja = viewModel.svaPitanja.collectAsState().value.filter { it.idLekcije == idLekcija }
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ){
        IconButton(onClick = { navigiranjeEkrana.navigateUp() }) {
            Icon(
                Icons.Default.ArrowBack, null,
                tint = Color(0xFF1c81b8),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(55.dp)
            )
        }
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                if (naslov != null) {
                    Text(
                        text = naslov,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 32.dp, top = 16.dp,bottom = 4.dp)
                    )
                }
                if (oznakaLekcije != null) {
                    Text(
                        text = oznakaLekcije + "-" + LogiraniKorisnik.oznakaLogiranogKorsinika,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 32.dp, bottom = 8.dp)
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.Create, contentDescription = "Create",
                tint = Color(0xFF1c81b8),
                modifier = Modifier
                    .background(Color.White, shape = CircleShape)
                    .size(35.dp)
                    .clickable {
                        navigiranjeEkrana.navigate("NovoPitanje/${idLekcija}")
                    }
            )

        }
        LazyColumn(verticalArrangement = Arrangement.Center) {
            items(pitanja) { pitanje ->
                if (idLekcija != null) {
                    NaslovPitanjeKartica(
                        navigiranjeEkrana = navigiranjeEkrana,
                        pitanje,
                        idLekcija,
                        viewModel
                    )
                }
            }
        }
    }
    Column (verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        ExtendedFloatingActionButton(
            containerColor = Color(0xFF1c81b8) ,
            onClick = { navigiranjeEkrana.navigate("AIgeneriranje") },
            icon = {
                Icon(
                    Icons.Filled.Add, null,
                    tint = Color.White
                )
            },
            text = {
                Text(
                    text = "Kreiraj nova AI pitanja",
                    color = Color.White
                )
            },
            modifier = Modifier
                .padding(bottom = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun NaslovPitanjeKartica(
    navigiranjeEkrana: NavHostController,
    pitanje: Pitanje,
    idLekcija: String,
    viewModel: ListaPitanjaViewModel
) {
    var popupProsiren by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(vertical = 3.5.dp)
            .clickable { popupProsiren = !popupProsiren }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                pitanje.tekstPitanja,
                modifier = Modifier
                    .padding(vertical = 9.dp, horizontal = 8.dp)
                    .weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color(0xFF1c81b8),
                modifier = Modifier
                    .clickable { popupProsiren = !popupProsiren }
                    .padding(end = 10.dp)
            )
        }
    }
    if (popupProsiren == true) {
        pitanjeKartica(pitanje, idLekcija, viewModel)
    }
}

@Composable
fun pitanjeKartica(pitanje: Pitanje, idLekcija: String, viewModel: ListaPitanjaViewModel) {
    var pitanjeText by remember { mutableStateOf(pitanje.tekstPitanja) }
    var odgovor1Text by remember { mutableStateOf(pitanje.odgovori[0]) }
    var odgovor2Text by remember { mutableStateOf(pitanje.odgovori[1]) }
    var odgovor3Text by remember { mutableStateOf(pitanje.odgovori[2]) }
    var zanimljivostText by remember { mutableStateOf(pitanje.zanimljivost) }
    var odabranTocanOdgovor by remember { mutableStateOf(pitanje.tocanOdgovor - 1) } // 0 znači da nijedan nije selektovan

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column {
                    Text("Pitanje")
                    OutlinedTextField(
                        value = pitanjeText,
                        onValueChange = { pitanjeText = it },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 60.dp)
                            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                    )
                    Text("Odgovor 1")
                    ListaPitanjaPitanjeRow(
                        odgovor1Text,
                        { odgovor1Text = it },
                        odabranTocanOdgovor == 1,
                        { odabranTocanOdgovor = 1 })
                    Text("Odgovor 2")
                    ListaPitanjaPitanjeRow(
                        odgovor2Text,
                        { odgovor2Text = it },
                        odabranTocanOdgovor == 2,
                        { odabranTocanOdgovor = 2 })
                    Text("Odgovor 3")
                    ListaPitanjaPitanjeRow(
                        odgovor3Text,
                        { odgovor3Text = it },
                        odabranTocanOdgovor == 3,
                        { odabranTocanOdgovor = 3 })
                    Text("Zanimljivost")
                    OutlinedTextField(
                        value = zanimljivostText,
                        onValueChange = { zanimljivostText = it },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 150.dp)
                            .background(Color.White, RoundedCornerShape(40.dp))
                            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                    )
                }
            }
            Row {
                Button(
                    onClick = {
                        val azuriranoPitanje = Pitanje(
                            tekstPitanja = pitanjeText,
                            odgovori = listOf(odgovor1Text, odgovor2Text, odgovor3Text),
                            tocanOdgovor = odabranTocanOdgovor + 1,
                            zanimljivost = zanimljivostText,
                            //autor = "Ažurirani Autor", // Pretpostavka da autor ostaje isti ili dodajte logiku za ažuriranje
                            idLekcije = idLekcija // Pretpostavka da ID lekcije ostaje isti ili dodajte logiku za ažuriranje
                        )

                        viewModel.azurirajPitanje(pitanje.idPitanja, azuriranoPitanje)
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF1c81b8))
                ) {
                    Text(text = "Snimi")
                }
                Spacer(modifier = Modifier.padding(horizontal = 32.dp))
                Button(
                    onClick = {
                        viewModel.obrisiPitanje(pitanje.idPitanja)
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF1c81b8))
                ) {
                    Text(text = "Obriši")
                }
            }
        }
    }
}

@Composable
fun ListaPitanjaPitanjeRow(
    odgovorText: String,
    onOdgovorChange: (String) -> Unit,
    isSelected: Boolean,
    onSelectChange: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = odgovorText,
            onValueChange = onOdgovorChange,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .defaultMinSize(minHeight = 60.dp)
                .background(Color.White, RoundedCornerShape(40.dp))
                .weight(1f)
                .padding(start = 28.dp, top = 4.dp, bottom = 4.dp, end = 8.dp)
        )
        RadioButton(
            selected = isSelected,
            onClick = onSelectChange,
            modifier = Modifier.padding(start = 4.dp, top = 4.dp)
        )
    }
}
