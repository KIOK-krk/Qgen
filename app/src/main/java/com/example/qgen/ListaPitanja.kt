package com.example.qgen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ListaPitanja(navigiranjeEkrana: NavHostController, naslov: String?) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = null,
                tint = Color(0xFF1c81b8),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp)
            )
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
            items(31) {
                Card(
                    shape = RoundedCornerShape(
                        7.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(vertical = 3.5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text("Pitanje $it",
                            modifier = Modifier
                                .padding(top = 9.dp, start = 25.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Default.Add, contentDescription = null,
                            tint = Color(0xFF1c81b8),
                            modifier = Modifier
                                .padding(top = 10.dp, end = 15.dp)
                                .clickable { navigiranjeEkrana.navigate("AIgeneriranje") }
                        )
                    }
                }
            }
        }
    }
    Column (verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End){
        ExtendedFloatingActionButton(
            onClick = {navigiranjeEkrana.navigate("AIgeneriranje")  },
            icon = { Icon(Icons.Filled.Add, null) },
            text = { Text(text = "Kreiraj nova AI pitanja") },
            modifier = Modifier
                .padding(bottom = 16.dp,end = 16.dp)
        )
    }
}



