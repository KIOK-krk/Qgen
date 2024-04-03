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
    NavHost(navController = navigiranjeEkrana, startDestination = "LoginEkran") {
        composable("AIgeneriranje/{idLekcije}/{upute}/{oznakaLekcije}") { backStackEntry ->
            AIgeneriranje(
                navigiranjeEkrana,
                idLekcije = backStackEntry.arguments?.getString("idLekcije"),
                upute = backStackEntry.arguments?.getString("upute"),
                oznakaLekcije = backStackEntry.arguments?.getString("oznakaLekcije")
            )
        }
        composable("PredmetiSkenoviEkran") { PredmetiSkenoviEkran(navigiranjeEkrana) }
        composable("ListaPitanja/{naslov}/{idLekcije}/{AIupute}/{oznakaLekcije}") { backStackEntry ->
            ListaPitanja(
                navigiranjeEkrana,
                naslov = backStackEntry.arguments?.getString("naslov"),
                idLekcija = backStackEntry.arguments?.getString("idLekcije"),
                AIupute = backStackEntry.arguments?.getString("AIupute"),
                oznakaLekcije = backStackEntry.arguments?.getString("oznakaLekcije")
            )
        }
        composable("LoginEkran") { LoginEkran(navigiranjeEkrana) }
        composable("NovoPitanje/{idLekcije}") { backStackEntry ->
            NovoPitanje(
                navigiranjeEkrana,
                idLekcija = backStackEntry.arguments?.getString("idLekcije")
            )
        }
    }
}

@Preview
@Composable
fun NavigiranjeEkranaPreview() {
    NavigiranjeEkrana()
}
