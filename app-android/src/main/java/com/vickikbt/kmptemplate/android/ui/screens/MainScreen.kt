package com.vickikbt.kmptemplate.android.ui.screens


import androidx.compose.runtime.Composable
import com.vickikbt.kmptemplate.presentation.screens.HomeScreen
import com.vickikbt.kmptemplate.presentation.viewmodels.MainViewModel
import org.koin.androidx.compose.get

@Composable
fun MainScreen(viewModel: MainViewModel = get()) {

HomeScreen()
}
