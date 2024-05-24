package com.pokeapi.pokemoncatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pokeapi.pokemoncatch.ui.theme.PokemonCatchTheme
import com.pokeapi.presentation.navigation.BottomNav
import com.pokeapi.presentation.navigation.MainNavHost
import com.pokeapi.presentation.navigation.screen.BottomBarScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var keep = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition{
            keep
        }
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            keep = false
        }
        setContent {

            PokemonCatchTheme {
                // A surface container using the 'background' color from the theme
                SetStatusBarColor(color = MaterialTheme.colorScheme.primary)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
//                    var navController = rememberNavController()
//                    MainNavHost(navController = navController)
                    MainScreen()
                }
            }
        }
    }
}
@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = color,
            darkIcons = false
        )
    }
}
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navigationItemContentList = listOf(
        BottomBarScreen.ListPokemon,
        BottomBarScreen.MyPokemon,
    )

    BottomNav(
        modifier = modifier,
        navigationItemContentList = navigationItemContentList,
        navController = navController,
        currentDestination = currentDestination,
    )
}