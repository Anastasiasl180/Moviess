package com.example.moviess.presentation.ui.homeScreen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviess.presentation.navigation.Screens
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable

@Composable
fun MainScaffold(onClick: (Screens) -> Unit, content: @Composable () -> Unit) {

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        bottomBar = {
            MainBottomBar(onClick = onClick)
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()



            }

        }
    )
}

@Composable
fun MainBottomBar(onClick: (Screens) -> Unit) {
    val navigationBarItems = remember { NavigationBarItems.values() }
    var selectedIndex by remember { mutableStateOf(0) }

    AnimatedNavigationBar(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, bottom = 0.dp)
            .height(64.dp),
        selectedIndex = selectedIndex,
        cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
        ballAnimation = Parabolic(tween(300)),
        indentAnimation = Height(tween(300)),
      barColor = Color.Black,

        ballColor = Color.Black
    ) {
        navigationBarItems.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .noRippleClickable {
                        selectedIndex = item.ordinal
                        onClick(item.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(26.dp), imageVector = item.icon,
                    contentDescription = "Bottom Bar Icon",
                    tint = if (selectedIndex == item.ordinal) Color.White
                    else Color.White

                )
            }
        }

    }

}