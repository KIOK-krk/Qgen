package com.example.qgen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ListaPredmeta(navigiranjeEkrana: NavHostController) {
    Column {
        Row{
            Text(text = "Predmeti",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(all =10.dp)
                    .clickable { navigiranjeEkrana.navigate("ListaPitanja") }
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
