package com.example.lwb.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(
    navController: NavController
) {
    val listItems = listOf(
        BottomItem.KnowledgeBase,
        BottomItem.MainPage,
        BottomItem.Settings
    )
    androidx.compose.material.BottomNavigation(
        modifier = Modifier
            .fillMaxHeight(0.065f),
        backgroundColor = Color.White
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach {item->
            BottomNavigationItem(
                modifier = getItemModifier(item),
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                    } },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = "Icon"
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.DarkGray,
                interactionSource = NoRippleInteractSource()

            )
        }
    }
}

fun getItemModifier(item: BottomItem): Modifier{
    return when(item.position){
        Position.LEFT -> Modifier
            .background(Color.Black, shape = RoundedCornerShape(30.dp, 0.dp))
        Position.CENTER -> Modifier
            .background(Color.Black)
        Position.RIGHT -> Modifier
            .background(Color.Black, shape = RoundedCornerShape(0.dp, 30.dp))
    }
}