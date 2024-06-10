package com.example.kitchencompass.View

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "foodHomePage") {
        composable("foodHomePage") {
            FoodHomePage {
                navController.navigate("description")}
            navController.navigate("search")
        }
        composable("description") {
            Description { navController.popBackStack() }
        }
        composable("searchPage"){
                SearchPageView {
                navController.navigate("searchPage")
            }
        }
    }
}