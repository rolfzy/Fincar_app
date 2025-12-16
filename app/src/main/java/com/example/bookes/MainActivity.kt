package com.example.bookes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookes.ui.DetailScreen
import com.example.bookes.ui.HomeScreen
import com.example.bookes.ui.MapsScreen
import com.example.bookes.ui.MenuBar
import com.example.bookes.ui.theme.BookesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation()
                }
            }
        }


    }

}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf("home_router", "maps_router", "detail_router/{itemId}")



    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                MenuBar(navController = navController)
            }
        },
        containerColor = Color.White
    ) { innerpadding ->
        NavHost(
            navController = navController,
            startDestination = "home_router",
            modifier = Modifier.padding(innerpadding)
        ) {
            composable("home_router") {
                HomeScreen(
                    navController = navController,
                    onItemClick = { item ->
                        navController.navigate("detail_router/${item.id}")
                    }
                )
            }

            composable(
                route = "detail_router/{itemId}",
                arguments = listOf(navArgument("itemId") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId")

                DetailScreen(
                    itemId = itemId?.toInt() ?: 0,
                    onNavigateBack = { navController.popBackStack() }, onItemClick = {
                        navController.navigate("maps_router")
                    }

                )
            }


            composable(route = "maps_router") {
                MapsScreen(onItemClick = { item ->
                    navController.navigate("detail_router/${item.id}")
                }, onNavigateBack = {
                    navController.popBackStack()
                })
            }

        }
    }
}

@Composable
fun mainPreview(){
    AppNavigation()
}