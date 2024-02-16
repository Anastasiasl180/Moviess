package com.example.moviess.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviess.presentation.ui.Genres.GenreList
import com.example.moviess.presentation.ui.Genres.GenresViewModel
import com.example.moviess.presentation.ui.Poster.DetailsViewModel
import com.example.moviess.presentation.ui.Poster.PagerDetails
import com.example.moviess.presentation.ui.changesInProfile.Scaffold
import com.example.moviess.presentation.ui.changesInProfile.ScreenOfProfile
import com.example.moviess.presentation.ui.homeScreen.Drawer
import com.example.moviess.presentation.ui.homeScreen.HomeScreen
import com.example.moviess.presentation.ui.homeScreen.MainScaffold
import com.example.moviess.presentation.ui.homeScreen.MovieViewModel
import com.example.moviess.presentation.ui.login_screen.SignInViewModel
import com.example.moviess.presentation.ui.login_screen.SignIpScreen
import com.example.moviess.presentation.ui.searchScreen.SearchBar
import com.example.moviess.presentation.ui.searchScreen.SearchingViewModel
import com.example.moviess.presentation.ui.signup_screen.SignUpScreen
import com.example.moviess.presentation.ui.signup_screen.SignUpViewModel
import com.example.moviess.presentation.ui.signup_screen.continuedRegistration.SecondPart
import com.example.moviess.presentation.ui.whishList.WishList

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HOME.route) {

        composable(Screens.HOME.route) {
            val viewModel = hiltViewModel<MovieViewModel>()

            Drawer(bitmap = viewModel.imageBitMap.value, content = {
                MainScaffold(onClick = {
                    navController.navigate(it.route) {
                        popUpTo(Screens.HOME.route) {
                            inclusive = false
                        }
                    }
                }) {
                    HomeScreen(
                        viewModel = viewModel,
                        navigateToDetails = { navController.navigate(Screens.DETAILS.route) },
                        navigateToJenre = {
                            navController.navigate(Screens.JENRES.route)

                        })
                }
            }, navigateToProfile = {
                navController.navigate(Screens.PROFILE.route)
            }, navigateToFavourite = {
                navController.navigate(Screens.LIKED.route)
            })

        }
        composable(Screens.JENRES.route) {
            val viewModel = hiltViewModel<GenresViewModel>()
            GenreList(viewModel = viewModel, navigateToPoster = {
                navController.navigate(Screens.DETAILS.route)
            })
        }
        composable(Screens.SEARCH.route) {
            val viewModel = hiltViewModel<SearchingViewModel>()
            SearchBar(searchingViewModel = viewModel, onClickBack = {
                navController.popBackStack()
            }, navigateToMovie = {
                navController.navigate(Screens.DETAILS.route)

            })
        }
        composable(Screens.DETAILS.route) {
            val viewModel = it.sharedViewModel<DetailsViewModel>(navController)
            PagerDetails(viewModel = viewModel, onClickBack = {
                navController.navigate(Screens.HOME.route)
            })


        }


        composable(Screens.LIKED.route) {
            WishList(onClick = {

            })

        }
        composable(Screens.SIGNUP.route) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(navigateToSignIn = {
                navController.navigate(Screens.SIGNIN.route)
            }, navigateToAvatar = {
                navController.navigate(Screens.AVATAR.route)

            })
        }
        composable(Screens.SIGNIN.route) {
            SignIpScreen(navigateToHome = {
                navController.navigate(Screens.HOME.route)
            })
        }
        composable(Screens.AVATAR.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            SecondPart(viewModel = viewModel, navigateToHome = {
                navController.navigate(Screens.HOME.route)
            })
        }

        composable(Screens.PROFILE.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            Scaffold {
                ScreenOfProfile(viewModel = viewModel)
            }

        }


    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}