package com.example.notebookonsupabase2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notebookonsupabase2.presentation.screen.ListDetailsScreen
import com.example.notebookonsupabase2.presentation.screen.ListScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "listScreen") {
                composable("listScreen") {
                    ListScreen(navController)
                }
                composable(
                    "listDetails/{listId}/{listTitle}",
                    arguments = listOf(
                        navArgument("listId") { type = NavType.StringType },
                        navArgument("listTitle") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val listId = UUID.fromString(backStackEntry.arguments?.getString("listId")!!)
                    val listTitle = backStackEntry.arguments?.getString("listTitle")!!
                    ListDetailsScreen(listId, listTitle)
                }
            }
        }
    }
}



