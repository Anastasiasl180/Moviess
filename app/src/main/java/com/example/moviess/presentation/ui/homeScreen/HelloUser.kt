package com.example.moviess.presentation.ui.homeScreen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.scale
import kotlinx.coroutines.launch

@Composable
fun TextHello(name: String, onImageClick: () -> Unit, bitmap: Bitmap?) {
    Row(
        Modifier
            .padding(top = 30.dp, start = 110.dp)
            .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Row() {
                Text(
                    text = "Hello",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 30.sp
                )
                Text(
                    text = name,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Text(
                text = "Choose your movie",
                color = Color.Black,
                fontWeight = FontWeight.W200,
                modifier = Modifier.padding(top = 20.dp),
                fontSize = 20.sp
            )
        }


    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    bitmap: Bitmap?,
    content: @Composable () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToFavourite: () -> Unit
) {


    val items = listOf(
        NavigationItem(
            tittle = "Favourite",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.Favorite
        )
    )


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val resizedBitmap = bitmap
    var checked by remember { mutableStateOf(false) }
    val darkTheme by remember { mutableStateOf(false) }


    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))


                if (bitmap != null) {
                    NavigationDrawerItem(
                        label = { Text(text = "Profile") },
                        selected = true,
                        onClick = { navigateToProfile() },
                        icon = {
                            if (resizedBitmap != null) {
                                Image(
                                    bitmap = resizedBitmap.asImageBitmap(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clickable { scope.launch { drawerState.open() } }
                                        .size(40.dp)
                                        .clip(CircleShape)
                                    , contentScale = ContentScale.FillBounds
                                    , alignment = Alignment.Center

                                )
                            }
                        },modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                } else {
                    NavigationDrawerItem(
                        label = { Text(text = "Profile") },
                        selected = true,
                        onClick = { navigateToProfile() },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Profile",
                                Modifier
                                    .size(40.dp)


                            )
                        }, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.tittle) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            navigateToFavourite()
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon, contentDescription = item.tittle
                            )
                        }, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    NavigationDrawerItem(modifier = Modifier.padding(top = 600.dp, start = 80.dp),
                        label = { Text("Dark Mode") },
                        selected = false,
                        onClick = { },
                        icon = {
                            Switch(checked = checked, onCheckedChange = {
                                checked = it
                            })
                        }

                    )
                }
            }
        },
        drawerState = drawerState
    ) {

        Scaffold(
            topBar = {

                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
                    navigationIcon = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(start = 10.dp, top = 15.dp)
                            ) {


                                if (bitmap != null) {
                                    if (resizedBitmap != null) {
                                        Image(
                                            bitmap = resizedBitmap.asImageBitmap(),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .clickable { scope.launch { drawerState.open() } }
                                                .size(50.dp)
                                                .clip(CircleShape)
                                            , contentScale = ContentScale.FillBounds
                                            , alignment = Alignment.Center

                                        )
                                    }
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.AccountCircle,
                                        contentDescription = "avatar",
                                        modifier = Modifier
                                            .clickable { scope.launch { drawerState.open() } }
                                            .size(50.dp)
                                            .clip(CircleShape)

                                            .border(2.dp, Color.Gray, CircleShape)
                                    )
                                }
                            }
                        }

                    }, scrollBehavior = scrollBehavior
                )
            },
            content = {
                content()
            }
        )

    }
}


data class NavigationItem(
    val tittle: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector

)


