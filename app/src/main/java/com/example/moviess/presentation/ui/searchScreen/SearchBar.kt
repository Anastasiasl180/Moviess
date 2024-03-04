package com.example.moviess.presentation.ui.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.moviess.presentation.ui.theme.color
import com.example.moviess.presentation.ui.theme.color2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchingViewModel: SearchingViewModel,
    onClickBack: () -> Unit,
    navigateToMovie: () -> Unit,
    
) {

    Scaffold(
        topBar = {
            TopAppBar(title = { },
                colors = TopAppBarDefaults.smallTopAppBarColors(),
                actions = {
                    TextField(
                        modifier = Modifier.width(350.dp).background(
                            brush = Brush.horizontalGradient(listOf(color, color2)), shape = RoundedCornerShape(35.dp)
                        ),
                        value = searchingViewModel.textState.value,
                        onValueChange = { text ->
                            searchingViewModel.setText(text)
                        },
                        label = {
                            Text(text = "Search movie..", color = Color.White)
                        },
                        colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                        textStyle = TextStyle(color = Color.White)

                    )

                },
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(
                            imageVector = Icons.Sharp.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                })
        }
    ) { padding ->
        Box(modifier = Modifier.padding(paddingValues = padding)) {
            SearchingMovies(
                movies = searchingViewModel.stateOfListOfSearching.value.movies,
                getGenres = {
                    searchingViewModel.getGenresForMovie(it)
                }, onClick = { movie ->
                    searchingViewModel.getDetailsForMovie(movie.id)
                    navigateToMovie()

                }, viewModel = searchingViewModel)

        }
    }


}