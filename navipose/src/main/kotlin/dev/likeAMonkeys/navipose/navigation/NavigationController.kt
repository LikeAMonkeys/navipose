package dev.likeAMonkeys.navipose.navigation

import androidx.compose.runtime.*
import dev.likeAMonkeys.navipose.components.NotFoundScreen
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
            //Todo: reduce screen removing
            val screen = backStack.pollLast()

            backStack.peek()?.let {
                currentScreen.value = it
            }
        }
    }

    @Composable
    override fun startNavigation() {
        val provider = screenProviders[currentScreen.value]

        when {
            //If any provider found for current screen - show them
            provider != null -> provider.invoke()
            //Show stub if they are enabled
            isStubsEnabled -> NotFoundScreen()
            //Crash navigation, if it's required
            else -> throw IllegalArgumentException(
                "Screen ${currentScreen.value} not found in current navigation graph!"
            )
        }
    }
}

/**
 * todo: uncommented
 */
internal interface INavigationController : INavigator {
    var isStubsEnabled: Boolean

    /**
     * todo: uncommented
     */
    @Composable
    fun startNavigation()

    companion object {
        internal fun newInstance(startScreen: IScreen): INavigationController {
            return NavigationController(startScreen)
        }
    }
}
