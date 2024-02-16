package com.example.moviess.presentation.ui.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.moviess.presentation.navigation.Screens
import com.exyte.animatednavbar.utils.noRippleClickable

enum class NavigationBarItems(val icon:ImageVector,val route:Screens) {

    Home(icon = Icons.Default.Home, route = Screens.HOME),
    Like(icon = Icons.Default.Favorite, route = Screens.LIKED),
    Search(icon = Icons.Default.Search, route = Screens.SEARCH)

}
