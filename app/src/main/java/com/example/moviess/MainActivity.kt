package com.example.moviess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviess.presentation.navigation.Navigation
import com.example.moviess.presentation.ui.homeScreen.HomeScreen
import com.example.moviess.presentation.ui.homeScreen.MainScaffold
import com.example.moviess.presentation.ui.homeScreen.MovieViewModel
import com.example.moviess.presentation.ui.theme.MoviessTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviessTheme {
                Navigation()


            }

        }
    }
}



