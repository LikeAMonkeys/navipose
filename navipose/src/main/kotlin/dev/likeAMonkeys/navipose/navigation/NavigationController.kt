package dev.likeAMonkeys.navipose.navigation

import androidx.compose.runtime.*
import dev.likeAMonkeys.navipose.components.NotFoundScreen
import dev.likeAMonkeys.navipose.models.BackstackScreen
import dev.likeAMonkeys.navipose.models.IScreen
import java.util.*

internal class NavigationController(
    startScreen: IScreen,
    private val isStubsEnabled: Boolean
) : INavigationController {
    private val backStack: Deque<BackstackScreen> = LinkedList()
    private val screenProviders = mutableMapOf<IScreen, @Composable () -> Unit>()
    private val currentScreen: MutableState<BackstackScreen> by lazy {
        val screen = createBackstackScreen(startScreen)
        mutableStateOf(screen)
    }

    override val currentScreenTag: String
        get() = currentScreen.value.tag

    init {
        backStack.add(currentScreen.value)
    }

    override fun addScreen(screen: IScreen, screenProvider: @Composable () -> Unit) {
        screenProviders[screen] = screenProvider
    }

    override fun navigate(screen: IScreen) {
        //todo: add args && backstack policy
        val backstackScreen = createBackstackScreen(screen)
        backStack.add(backstackScreen)
        currentScreen.value = backstackScreen
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
        val provider = screenProviders[currentScreen.value.screen]

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

    private fun createBackstackScreen(screen: IScreen): BackstackScreen {
        val className = screen::class.simpleName
        val tagPrefix = "$className:"

        val screenNumber = backStack.count { it.tag.startsWith(tagPrefix) }.inc()

        return BackstackScreen(screen = screen, tag = "$tagPrefix$screenNumber")
    }
}

/**
 * Internal navigation controller, for manage screens, backstack, etc
 * @property currentScreenTag current backstack screen tag for current controller
 */
internal interface INavigationController : INavigator {
    val currentScreenTag: String

    /**
     * Composable function, for run navigation, watching current screen and show them
     */
    @Composable
    fun startNavigation()

    companion object {
        internal fun newInstance(startScreen: IScreen, isStubsEnabled: Boolean): INavigationController {
            return NavigationController(startScreen, isStubsEnabled)
        }
    }
}
