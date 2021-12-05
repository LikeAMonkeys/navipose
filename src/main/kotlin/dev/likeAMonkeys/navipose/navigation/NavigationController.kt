package dev.likeAMonkeys.navipose.navigation

import androidx.compose.runtime.*
import java.util.*

internal class NavigationController(startScreen: IScreen) : INavigationController {
    private val backStack: Deque<IScreen> = LinkedList()
    private val screenProviders = mutableMapOf<IScreen, @Composable () -> Unit>()
    private val currentScreen = mutableStateOf(startScreen)
    override var isStubsEnabled = false

    init {
        backStack.add(startScreen)
    }

    override fun addScreen(screen: IScreen, screenProvider: @Composable () -> Unit) {
        screenProviders[screen] = screenProvider
    }

    override fun navigate(screen: IScreen) {
        //todo: add args && backstack policy
        backStack.add(screen)
        currentScreen.value = screen
    }

    override fun goBack() {
        if (backStack.size > 1) {
            val screen = backStack.pollLast()

            backStack.peek()?.let {
                currentScreen.value = it
            }
        }
    }

    @Composable
    override fun startNavigation() {
        val provider = screenProviders[currentScreen.value]
        if(provider != null) {
            provider.invoke()
        } else {
            //todo: Draw stub
        }
    }
}

internal interface INavigationController : INavigator {
    var isStubsEnabled: Boolean

    @Composable
    fun startNavigation()

    companion object {
        internal fun newInstance(startScreen: IScreen): INavigationController {
            return NavigationController(startScreen)
        }
    }
}
