package com.example.qgen

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginEkran(navigiranjeEkrana: NavHostController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(painter = painterResource(id = R.drawable.upitnici), contentDescription = null)
        Box(modifier = Modifier
            .padding(bottom = 128.dp,top = 348.dp)){

        }
        Button(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.googlelogo),
                contentDescription = null)
            Text(" Prijavi se s Google računom")
        }
    }
}