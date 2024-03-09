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
import com.example.moviess.presentation.ui.moviesByGenres.GenreList
import com.example.moviess.presentation.ui.moviesByGenres.GenresViewModel
import com.example.moviess.presentation.ui.poster_screen.DetailsViewModel
import com.example.moviess.presentation.ui.poster_screen.PagerDetails
import com.example.moviess.presentation.ui.profile_screen.editingScreens.ChangePassword
import com.example.moviess.presentation.ui.profile_screen.editingScreens.ChangingUserName
import com.example.moviess.presentation.ui.profile_screen.Scaffold
import com.example.moviess.presentation.ui.profile_screen.ScreenOfProfile
import com.example.moviess.presentation.ui.homeScreen.Drawer
import com.example.moviess.presentation.ui.homeScreen.HomeScreen
import com.example.moviess.presentation.ui.homeScreen.MainScaffold
import com.example.moviess.presentation.ui.homeScreen.MovieViewModel
import com.example.moviess.presentation.ui.login_screen.SignInViewModel
import com.example.moviess.presentation.ui.login_screen.SignInScreen
import com.example.moviess.presentation.ui.login_screen.reset.ResetScreen
import com.example.moviess.presentation.ui.search_screen.SearchBar
import com.example.moviess.presentation.ui.search_screen.SearchingViewModel
import com.example.moviess.presentation.ui.signup_screen.SignUpScreen
import com.example.moviess.presentation.ui.signup_screen.settingAvatar.Avatar
import com.example.moviess.presentation.ui.whishList.WishList
import com.example.moviess.presentation.ui.whishList.WishListViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SIGNUP.route) {

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
                            navController.navigate(Screens.GENRES.route)

                        })
                }
            }, navigateToProfile = {
                navController.navigate(Screens.PROFILE.route)
            }, navigateToFavourite = {
                navController.navigate(Screens.LIKED.route)
            })


        }
        composable(Screens.GENRES.route) {
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
            val viewModel = hiltViewModel<WishListViewModel>()
            WishList(viewModel = viewModel, onClickBack = {
                navController.popBackStack()
            })

        }
        composable(Screens.SIGNUP.route) {
            SignUpScreen(navigateToSignIn = {
                navController.navigate(Screens.SIGNIN.route)
            }, navigateToAvatar = {
                navController.navigate(Screens.AVATAR.route){
                    popUpTo(Screens.SIGNUP.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(Screens.SIGNIN.route) {
            SignInScreen(navigateToHome = {
                navController.navigate(Screens.HOME.route){
                    popUpTo(Screens.SIGNUP.route){
                        inclusive = true
                    }
                }

            }, navigateToReset = {navController.navigate(Screens.RESETINGPASSWORD.route)})

        }
        composable(Screens.RESETINGPASSWORD.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
           ResetScreen(viewModel, backToSignIn = {
               navController.navigate(Screens.SIGNIN.route)
           })
        }
        composable(Screens.AVATAR.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            Avatar(viewModel = viewModel, navigateToHome = {
                navController.navigate(Screens.HOME.route){
                    popUpTo(Screens.AVATAR.route){
                        inclusive = true
                    }
                }
            })
        }

        composable(Screens.PROFILE.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            Scaffold {
                ScreenOfProfile(viewModel = viewModel, onClickBack = {
                    navController.popBackStack()
                }, navigateToChangePassword = {
                    navController.navigate(Screens.CHANGEDPASSWORD.route)
                }, navigateToChangeUserName = {
                    navController.navigate(Screens.CHANGEUSERNAME.route)
                }, navigateToSignUp = {
                    navController.navigate(Screens.SIGNUP.route){
                        popUpTo(Screens.HOME.route){
                            inclusive = true
                        }
                    }

                })
            }

        }
        composable(Screens.CHANGEDPASSWORD.route){
            val viewModel = hiltViewModel<SignInViewModel>()
            ChangePassword(viewModel, onClickBack = {
                navController.popBackStack()
            })
        }
        composable(Screens.CHANGEUSERNAME.route){
            val viewModel = hiltViewModel<SignInViewModel>()
            ChangingUserName(viewModel = viewModel, onClickBack = {
                navController.popBackStack()
            })
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