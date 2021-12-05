package dev.likeAMonkeys.navipose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun Navigator(
    startScreen: IScreen,
    enableStubs: Boolean = false,
    navigationBody: INavigator.() -> Unit
) {
    val navigator = remember {
        INavigationController.newInstance(startScreen).apply {
            isStubsEnabled = enableStubs
        }
    }
    navigationBody.invoke(navigator)
    navigator.startNavigation()
}