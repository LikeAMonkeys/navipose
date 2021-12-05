package dev.likeAMonkeys.navipose.navigation

/**
 * Screen showing behavior, using during [INavigator.navigate] to resolve screen backstack
 */
enum class BackstackBehavior {
    SINGLE_TOP, SINGLE_INSTANCE, BASIC
}