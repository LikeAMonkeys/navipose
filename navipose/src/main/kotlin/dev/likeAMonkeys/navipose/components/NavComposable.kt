package dev.likeAMonkeys.navipose.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.likeAMonkeys.navipose.navigation.INavigationController
import dev.likeAMonkeys.navipose.navigation.INavigator
import dev.likeAMonkeys.navipose.models.IScreen

/**
 * TODO: uncommented
 */
@Composable
fun Navigator(
    startScreen: IScreen,
    enableStubs: Boolean = false,
    navigationBody: INavigator.() -> Unit
) {
    //Create navigator
    val navigator = remember {
        INavigationController.newInstance(startScreen = startScreen, isStubsEnabled = enableStubs).apply {

        }
    }

    //Fill user's screen's and settings to navigator
    navigationBody.invoke(navigator)

    //Run navigation
    navigator.startNavigation()
}