package com.example.angelaheely.utils

sealed class Screen(val route: String) {

    object Landing : Screen(route = "Landing")
    object Login : Screen(route = "Login")
    object Home : Screen(route = "Home")
    object MedicineDetails : Screen(route = "MedicineDetails")
}