package com.example.moviess.presentation.ui.profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material.icons.sharp.Email
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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


@Composable
fun ScreenOfProfile(
    viewModel: SignInViewModel,
    onClickBack: () -> Unit,
    navigateToChangePassword: () -> Unit,
    navigateToChangeUserName: () -> Unit,
    navigateToSignUp: () -> Unit
) {

    val oldImage by remember {
        mutableStateOf(
            viewModel.globalState.bitmapImage.value
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.37f)
                .background(color = Color.Gray)
        ) {
            IconButton(onClick = { onClickBack() }) {
                Icon(imageVector = Icons.Sharp.Close, contentDescription = "")

            }
            IconButton(onClick = {
                viewModel.globalState.deletePerson()
                navigateToSignUp()
            }) {
                Icon(imageVector = Icons.Sharp.Delete, contentDescription = "")
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
        )
        {

            if (oldImage != null) {
                Image(
                    bitmap = oldImage!!.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(100.dp)
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


            Text(
                text = "Anastasia" /*viewModel.globalState.username.value*/,
                modifier = Modifier.padding(top = 20.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(onClick = {
                viewModel.globalState.deletePerson()
                navigateToSignUp()
            }) {
                Icon(imageVector = Icons.Sharp.Delete, contentDescription = "")
            }

            ElevatedCard(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .shadow(16.dp, ambientColor = Color.LightGray)
                    .height(150.dp)
                    .width(350.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Column {


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp)
                        ) {
                            Icon(imageVector = Icons.Sharp.Person, contentDescription = "")
                            Text(
                                text = "Text",
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                color = Color.Black
                            )
                            IconButton(onClick = { }) {

                                Icon(
                                    imageVector = Icons.Sharp.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.Black
                                )

                            }
                        }
                        Divider(
                            thickness = 1.dp,
                            color = Color.LightGray,
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                        )
                        Column(modifier = Modifier.fillMaxSize()) {

                            Column {


                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp)
                                ) {
                                    Icon(imageVector = Icons.Sharp.Person, contentDescription = "")
                                    Text(
                                        text = "Text",
                                        modifier = Modifier
                                            .padding(start = 10.dp),
                                        color = Color.Black
                                    )
                                    IconButton(onClick = { }) {

                                        Icon(
                                            imageVector = Icons.Sharp.KeyboardArrowRight,
                                            contentDescription = null,
                                            tint = Color.Black
                                        )

                                    }
                                }

                                Divider(
                                    thickness = 1.dp,
                                    color = Color.LightGray,
                                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                                )
                            }
                        }
                    }
                }

            }

            ElevatedCard(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .shadow(16.dp, ambientColor = Color.LightGray)
                    .height(320.dp)
                    .width(350.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Person, contentDescription = "",
                            tint = Color.Gray
                        )
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.spacedBy(100.dp)
                        ) {


                            Text(
                                text = "Your username",
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                color = Color.Black
                            )
                            IconButton(onClick = { navigateToChangeUserName() }) {

                                Icon(
                                    imageVector = Icons.Sharp.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.padding(start = 0.dp)
                                )

                            }
                        }
                    }
                    Divider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Email, contentDescription = "",
                            tint = Color.Gray
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {


                            Text(
                                text = "Your email and password",
                                modifier = Modifier
                                    .padding(start = 10.dp),
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
                    Divider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Notifications,
                            contentDescription = "",
                            tint = Color.Gray
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(114.dp)) {
                            Text(
                                text = "Notifications",
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                color = Color.Black
                            )
                            IconButton(onClick = { }) {

                                Icon(
                                    imageVector = Icons.Sharp.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.Black
                                )

                            }
                        }
                    }
                    Divider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.LocationOn,
                            contentDescription = "",
                            tint = Color.Gray,
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(130.dp)) {
                            Text(
                                text = "Language",
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                color = Color.Black
                            )
                            IconButton(onClick = { }) {

                                Icon(
                                    imageVector = Icons.Sharp.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.padding(start = 20.dp)
                                )

                            }
                        }
                    }


                }
            }
        }
    }
}


/*  Button(onClick = {
      viewModel.globalState.deletePerson()
      navigateToSignUp()
  }) {
      Text(text = "Delete")
  }


}
}*/

