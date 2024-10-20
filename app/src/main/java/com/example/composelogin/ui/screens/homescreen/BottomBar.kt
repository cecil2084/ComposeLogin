package com.example.composelogin.ui.screens.homescreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composelogin.topLevelRoutes
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.theme.LocalStuddyColors

@Composable
fun StuddyBottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        topLevelRoutes.forEach { topLevelRoute ->

            StuddyBottomBarIcon(
                onClick = {
                    navController.navigate(topLevelRoute.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = topLevelRoute.icon,
                contentDescription = topLevelRoute.name,
                selected = currentDestination == topLevelRoute.route
            )
        }
    }
}

@Composable
fun StuddyBottomBarIcon(
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    contentDescription: String,
    selected: Boolean?
) {
    var color = LocalStuddyColors.current.primary500
    if (selected == true){
        color = LocalStuddyColors.current.warning700
    } else {
        LocalStuddyColors.current.warning500
    }

    Button(
        onClick = onClick,
        contentPadding = PaddingValues(StuddyDimensions.iconPadding),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = contentDescription,
            modifier = Modifier.size(StuddyDimensions.iconSmall),
            tint = color
        )
    }
}