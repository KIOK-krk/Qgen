package com.example.qgen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qgen.ui.theme.QgenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QgenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigiranjeEkrana()
                }
            }
        }
    }
}

@Composable
fun NavigiranjeEkrana() {
    val navigiranjeEkrana = rememberNavController()
    NavHost(navController = navigiranjeEkrana, startDestination = "PredmetiEkran") {
        composable("ListaPredmeta") { ListaPredmeta(navigiranjeEkrana) }
        composable("AIgeneriranje") { AIgeneriranje(navigiranjeEkrana) }
        composable( "PredmetiEkran") { PredmetiEkran(navigiranjeEkrana) }
    }
}
@Preview
@Composable
fun NavigiranjeEkranaPreview(){
    NavigiranjeEkrana()
}