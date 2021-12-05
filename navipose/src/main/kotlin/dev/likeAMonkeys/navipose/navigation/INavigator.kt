package dev.likeAMonkeys.navipose.navigation

import androidx.compose.runtime.Composable

interface INavigator {
    fun addScreen(screen: IScreen, screenProvider: @Composable () -> Unit)
    fun navigate(screen: IScreen)
    fun goBack()
}