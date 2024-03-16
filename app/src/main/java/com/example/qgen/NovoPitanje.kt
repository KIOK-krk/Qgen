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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun NovoPitanje(
    navigiranjeEkrana: NavHostController,
    idLekcija: String?,
    viewModel: ListaPitanjaViewModel = viewModel()
) {
    var pitanjeText by remember { mutableStateOf("") }
    var odgovor1Text by remember { mutableStateOf("") }
    var odgovor2Text by remember { mutableStateOf("") }
    var odgovor3Text by remember { mutableStateOf("") }
    var zanimljivostText by remember { mutableStateOf("") }
    var odabranTocanOdgovor by remember { mutableStateOf(0) }

    NovoPitanjeGornjiBar(navigiranjeEkrana)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp
                )
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
                    NovoPitanjePitanjeRow(
                        odgovor1Text,
                        { odgovor1Text = it },
                        odabranTocanOdgovor == 1,
                        { odabranTocanOdgovor = 1 })
                    Text("Odgovor 2")
                    NovoPitanjePitanjeRow(
                        odgovor2Text,
                        { odgovor2Text = it },
                        odabranTocanOdgovor == 2,
                        { odabranTocanOdgovor = 2 })
                    Text("Odgovor 3")
                    NovoPitanjePitanjeRow(
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
                            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                    )
                }
            }
        }
        Row {
            Button(
                onClick = {
                    navigiranjeEkrana.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF1c81b8)),
                modifier = Modifier.padding(end = 32.dp)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Otkazati", tint = Color.White)
                Text("Otkazati", color = Color.White)
            }
            Button(
                onClick = {
                    val novoPitanje = Pitanje(
                        tekstPitanja = pitanjeText,
                        odgovori = listOf(odgovor1Text, odgovor2Text, odgovor3Text),
                        tocanOdgovor = odabranTocanOdgovor + 1,
                        zanimljivost = zanimljivostText,
                        idLekcije = idLekcija ?: ""
                    )
                    viewModel.dodajNovoPitanje(novoPitanje)
                    navigiranjeEkrana.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF1c81b8)),
                modifier = Modifier.padding(start = 32.dp)
            ) {
                Icon(Icons.Default.Check, contentDescription = "Spremiti", tint = Color.White)
                Text("Spremiti", color = Color.White)
            }
        }
    }
}

@Composable
fun NovoPitanjePitanjeRow(
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
                .padding(start = 28.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                .weight(1f)
        )
        RadioButton(
            selected = isSelected,
            onClick = onSelectChange,
            modifier = Modifier.padding(start = 4.dp, top = 4.dp)
        )
    }
}

@Composable
fun NovoPitanjeGornjiBar(navigiranjeEkrana: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
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
            text = "Novo Pitanje",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp, start = 8.dp)

        )
    }
}
