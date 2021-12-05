package dev.likeAMonkeys.navipose.navigation

/**
 * Basic screen abstraction, to use objects during navigation
 * @property backstackBehavior, behavior for screen to [NavigationController]
 */
interface IScreen {
    val backstackBehavior: BackstackBehavior get() = BackstackBehavior.BASIC
}

/**
 * Basic screen with params, used for pass navigation
 * @property T parameter type, it can be nullable (hello jetpack navigation)
 * @property data parameters, to call screen
 */
interface IParametrizedScreen<T : Any?> : IScreen {
    val data: T
}
