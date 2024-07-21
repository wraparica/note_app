package com.example.noteapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()
    val noteViewModel: NoteViewModel = viewModel(factory = AppViewModelProvider.Factory)
    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "Home",
            modifier = modifier.fillMaxSize()
        ) {
            composable("Home") { HomeScreen(navController, noteViewModel) }
            composable("AddNote") { AddNote(navController, noteViewModel) }
        }
    }
}