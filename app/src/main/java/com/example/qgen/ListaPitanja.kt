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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun ListaPitanja(
    navigiranjeEkrana: NavHostController,
    naslov: String?,
    idLekcija: String?,
    viewModel: ListaPitanjaViewModel = viewModel()
) {
    val pitanja = viewModel.svaPitanja.collectAsState().value.filter { it.idLekcije == idLekcija }
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Row() {
            IconButton(onClick = { navigiranjeEkrana.navigateUp() }) {
                Icon(
                    Icons.Default.ArrowBack, "Povratak",
                    tint = Color(0xFF1c81b8),
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 2.dp)
                )
            }
            if (naslov != null) {
                Text(
                    naslov,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Build, contentDescription = null,
                tint = Color(0xFF1c81b8),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp,end = 12.dp)
            )
            Icon(
                imageVector = Icons.Default.Create, contentDescription = null,
                tint = Color(0xFF1c81b8),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp)
            )
        }
        LazyColumn(verticalArrangement = Arrangement.Center) {
            items(pitanja) { pitanje ->
                NaslovPitanjeKartica(navigiranjeEkrana = navigiranjeEkrana, pitanje)
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
fun NaslovPitanjeKartica(navigiranjeEkrana: NavHostController, pitanje: Pitanje) {
    var popupProsiren by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(vertical = 3.5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                pitanje.tekstPitanja,
                modifier = Modifier
                    .padding(vertical = 9.dp, horizontal = 8.dp)
                    .weight(1f)
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
        pitanjeKartica(pitanje)
    }
}

@Composable
fun pitanjeKartica(pitanje: Pitanje) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(
                start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Text("Pitanje")
                OutlinedTextField(
                    value = pitanje.tekstPitanja,
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
                        .background(Color.White, RoundedCornerShape(40.dp))
                        .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                )
                Text("Odgovor 1")
                ListaPitanjaPitanjeRow(pitanje.odgovori[0])
                Text("Odgovor 2")
                ListaPitanjaPitanjeRow(pitanje.odgovori[1])
                Text("Odgovor 3")
                ListaPitanjaPitanjeRow(pitanje.odgovori[2])
                Text("Zanimljivost")

                OutlinedTextField(
                    value = pitanje.zanimljivost,
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
                        .background(Color.White, RoundedCornerShape(40.dp))
                        .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                )

            }
            Row {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFF1c81b8)),
                ) {
                    Text(
                        text = "Snimi"
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 32.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFF1c81b8)),
                ) {
                    Text(
                        text = "Obriši"
                    )
                }
            }
        }
    }
}

@Composable
fun ListaPitanjaPitanjeRow(odgovor: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = odgovor,
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
                .background(Color.White, RoundedCornerShape(40.dp))
                .padding(start = 28.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                .width(250.dp)
        )
        RadioButton(
            modifier = Modifier
                .padding(start = 4.dp, top = 4.dp),
            selected = false,
            onClick = {}
        )
    }
}
