package com.example.qgen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                    Image(
                        painter = painterResource(id = R.drawable.upitnici),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .size(270.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.logoaplikacije),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .size(270.dp)
                    )
                }
            }
        }
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

    }
}
