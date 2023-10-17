package com.example.angelaheely.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.angelaheely.presentation.details.MedicineDetailsScreen
import com.example.angelaheely.presentation.home.HomeScreen
import com.example.angelaheely.presentation.login.LoginScreen
import com.example.angelaheely.presentation.login.MainViewModel
import com.example.angelaheely.utils.Screen

@ExperimentalMaterial3Api
@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore('/')
    val context = LocalContext.current


    NavHost(navController, startDestination = Screen.Landing.route) {
        composable(route = Screen.Landing.route) {
            LandingScreen(
                viewModel = mainViewModel,
                navigateToHomeScreen = {
                    navController.navigate(Screen.Home.route)
                },
                navigateToLoginScreen = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(route = Screen.Login.route) {
            LoginScreen(
                viewModel = mainViewModel,
                onClickLogin = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }

        composable(route = Screen.Home.route) {
            HomeScreen(
                viewModel = mainViewModel,
                onClickMedicine = { medicineId ->
                    navController.navigate(Screen.MedicineDetails.route.plus("?id=$medicineId"))
                }
            )
        }
        composable(route = Screen.MedicineDetails.route.plus("?id={id}")) {
            MedicineDetailsScreen(
                viewModel = mainViewModel,
                onClickBack = {
                    navController.navigateUp()
                }
            )
        }

    }
}