package com.example.qgen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun LoginEkran(navigiranjeEkrana: NavHostController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .size(400.dp)
            ) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.upitnici),
                            contentDescription = null,
                            alignment = Alignment.TopCenter,
                            modifier = Modifier
                                .size(270.dp)
                                .padding(top = 32.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.crta),
                            contentDescription = null,
                            alignment = Alignment.TopCenter,
                        )
                        Image(
                            painter = painterResource(id = R.drawable.logoaplikacije),
                            contentDescription = null,
                            alignment = Alignment.TopCenter,
                            modifier = Modifier
                                .size(320.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.crta),
                            contentDescription = null,
                            alignment = Alignment.TopCenter,
                        )
                    }

                }
            }
        }
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        OutlinedButton(onClick = {
            navigiranjeEkrana.navigate("PredmetiEkran")
        },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .width(300.dp)
                .height(80.dp)
        )
        {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.googlelogo),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier
                        .size(30.dp)
                )
                Text(
                    text = "  Prijavi se s Googleom",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
    }
}
