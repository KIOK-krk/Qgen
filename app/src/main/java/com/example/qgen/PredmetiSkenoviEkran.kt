package com.example.qgen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun PredmetiSkenoviEkran(
    navigiranjeEkrana: NavHostController,
    viewModel: PredmetiViewModel = viewModel()
) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Predmeti", "Skenovi")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> PredmetiEkran(navigiranjeEkrana, viewModel, false)
            1 -> SkenoviEkran()
        }
    }
}