package com.example.moviess.presentation.ui.changesInProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moviess.R
import com.example.moviess.presentation.ui.login_screen.SignInViewModel

@Composable
fun Scaffold(content: @Composable () -> Unit) {

    androidx.compose.material3.Scaffold(modifier = Modifier
        .fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {

        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()


            }

        }
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenOfProfile(
    viewModel: SignInViewModel,
    navigateToSignUp: () -> Unit,
    navigateToChangePassword: () -> Unit,
    navigateToChangeUserName: () -> Unit
) {

    val oldImage by remember {
        mutableStateOf(
            viewModel.globalState.bitmapImage.value
        )
    }


    var openDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Box() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        )
        {
            if (oldImage != null) {
                Image(
                    bitmap = oldImage!!.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(150.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = CircleShape
                        )
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Black)
                        .size(100.dp)

                )

            }
            ElevatedCard(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .height(300.dp), elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ), colors = CardDefaults.cardColors(containerColor = Color.DarkGray)


            ) {


                Column(
                    modifier = Modifier
                        .padding(top = 29.dp)
                        .background(Color.Transparent),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {


                    Card(modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(60.dp)
                        .clickable { openDialog = true }) {
                        Row(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "Your username",
                                modifier = Modifier,
                                color = Color.Black
                            )
                            IconButton(onClick = { navigateToChangeUserName() }) {

                                Icon(
                                    imageVector = Icons.Sharp.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.Black
                                )

                            }
                        }


                    }
                    Card(modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(60.dp)
                        .clickable { }) {
                        Row {
                            Text(
                                text = "Your email and password",
                                modifier = Modifier,
                                color = Color.Black
                            )
                            IconButton(onClick = { navigateToChangePassword() }) {

                                Icon(
                                    imageVector = Icons.Sharp.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.Black
                                )

                            }
                        }


                    }
                    Card(modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(60.dp)
                        .clickable { }) {
                        Row {
                            Text(text = "Text", modifier = Modifier, color = Color.Black)
                            IconButton(onClick = { /*TODO*/ }) {

                                Icon(
                                    imageVector = Icons.Sharp.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.Black
                                )

                            }
                        }


                    }


                }
            }
            Button(onClick = {
                viewModel.globalState.deletePerson()
                navigateToSignUp()
            }) {
                Text(text = "Delete")
            }


        }
    }
}
