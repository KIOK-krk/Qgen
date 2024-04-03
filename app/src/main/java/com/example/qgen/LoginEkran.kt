package com.example.qgen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


@Composable
fun LoginEkran(
    navigiranjeEkrana: NavHostController,
    viewModel : LoginViewModel = viewModel()
) {
    var upisaniEmail by remember { mutableStateOf("") }
    var upisanaLozinka by remember { mutableStateOf("") }
    var greska = viewModel.greska.collectAsState()

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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        OutlinedTextField(
            value = upisaniEmail,
            onValueChange = { upisaniEmail = it },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            ),
            label = { Text("E-Mail") },
            singleLine = true,
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(40.dp))
                .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
        )

        OutlinedTextField(
            value = upisanaLozinka,
            onValueChange = {upisanaLozinka = it},
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            ),
            label = { Text("Lozinka") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(40.dp))
                .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
        )
        if(greska.value == true) {
            Text(
                text = "Kriva lozinka ili e-mail!",
                color = Color.Red
            )
        }
        OutlinedButton(
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = {
                if (viewModel.provjeriLogin(upisaniEmail.trim(), upisanaLozinka.trim()) == true)
                    navigiranjeEkrana.navigate("PredmetiSkenoviEkran")
            },
            modifier = Modifier
                .padding(bottom = 80.dp, top = 24.dp)
                .height(60.dp)
                .width(250.dp)
        ) {
            Text(
                color = Color.Black,
                text = "Prijavi se",
                fontSize = 26.sp
            )
        }
    }
}
