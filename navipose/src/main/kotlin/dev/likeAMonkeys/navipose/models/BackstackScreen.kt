package dev.likeAMonkeys.navipose.models

/**
 * Internal screen holder, for provide unique backstack entry inside navigator
 */
internal data class BackstackScreen(
    val screen: IScreen,
    val tag: String
)