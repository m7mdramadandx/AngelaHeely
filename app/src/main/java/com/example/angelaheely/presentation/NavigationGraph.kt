package com.example.angelaheely.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.angelaheely.presentation.details.MedicineDetailsScreen
import com.example.angelaheely.presentation.home.HomeScreen
import com.example.angelaheely.presentation.landing.LandingScreen
import com.example.angelaheely.presentation.login.LoginScreen
import com.example.angelaheely.presentation.login.MainViewModel
import com.example.angelaheely.utils.Screen

@ExperimentalMaterial3Api
@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    val mainViewModel = hiltViewModel<MainViewModel>()

    NavHost(navController, startDestination = Screen.Landing.route) {
        composable(route = Screen.Landing.route) {
            LandingScreen(
                viewModel = mainViewModel,
                navigateToHomeScreen = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                },
                navigateToLoginScreen = {
                    navController.popBackStack()
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
                onClickMedicine = { medicineName ->
                    navController.navigate(Screen.MedicineDetails.route.plus("?name=$medicineName"))
                }
            )
        }
        composable(
            route = Screen.MedicineDetails.route.plus("?name={name}"),
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })
        ) {
            val name = it.arguments?.getString("name") ?: ""

            MedicineDetailsScreen(
                viewModel = mainViewModel,
                medicineNam = name,
                onClickBack = {
                    navController.navigateUp()
                }
            )
        }

    }
}