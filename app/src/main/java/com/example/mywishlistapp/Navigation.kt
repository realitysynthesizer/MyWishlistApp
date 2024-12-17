package com.example.mywishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(viewModel: WishViewModel = viewModel(), navHostController: NavHostController = rememberNavController()){
    NavHost(navHostController, startDestination = Screen.homescreen.route) {
        composable(Screen.homescreen.route) {
            HomeView(navHostController, viewModel)
        }
        composable(Screen.addScreen.route + "/{id}", arguments = listOf(navArgument("id"){
            type = NavType.LongType
            defaultValue=0L
        })) {
            val id = it.arguments?.getLong("id") ?: 0L
            AddEditDetailView(/*viewModel.wishIdState*/id, viewModel, navHostController)
        }

    }
}