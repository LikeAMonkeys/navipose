package dev.likeAMonkeys.navipose.view_model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class ScreenViewModel: CoroutineScope {
    private val supervisorJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + supervisorJob

    open fun onDestroy() {
        supervisorJob.cancel()
    }
}