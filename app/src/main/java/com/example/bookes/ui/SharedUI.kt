package com.example.bookes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class MenuItem(
    val id: String,
    val icon: ImageVector,
    val route: String,
    val relatedRoutes : List<String> = emptyList()
)


@Composable
fun MenuBar(navController: NavController) {
    val menuList = listOf(
        MenuItem("Home", Icons.Default.Home,"home_router" ),
        MenuItem("Search", Icons.Default.Search,"maps_router"),
        MenuItem("car", Icons.Default.DirectionsCar,"detail_router/3", relatedRoutes = listOf("detail_router")),
        MenuItem("Wallet", Icons.Default.Paid,"null"),
        MenuItem("Setting", Icons.Default.Settings,"null"),
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(16.dp)
            .border(width = 2.dp, shape = CircleShape, color = black)
            .clip(CircleShape)
            .background(CardBackground)
            ,
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(menuList) { item ->

                val isSelected = currentRoute?.let { route ->
                    route.startsWith(item.route) || item.relatedRoutes.any() {route.startsWith(it)}
                } == true
                val backgroundColor = if (isSelected == true) Gold else black
                val iconColor = if (isSelected == true) black else Color.White

                Box(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                        .background(backgroundColor)
                        .clickable {
                            navController.navigate(item.route){
                                popUpTo(navController.graph.startDestinationId){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        ,
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.id,
                        tint = iconColor
                    )

                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuBarPreview() {
    MenuBar(navController = NavController(LocalContext.current))

}