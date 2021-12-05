// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.likeAMonkeys.navipose.navigation.INavigator
import dev.likeAMonkeys.navipose.navigation.IScreen
import dev.likeAMonkeys.navipose.navigation.Navigator

@Composable
@Preview
fun App() {
    DesktopMaterialTheme {
        Navigator(Screens.Main) {
            addScreen(Screens.Main) { MainScreen(this) }
            addScreen(Screens.SubScreen) { SubScreen(this)}
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

@Composable
fun MainScreen(navigator: INavigator) {
    Button(onClick = { navigator.navigate(Screens.SubScreen) }) {
        Text("Hello, go to subScreen")
    }
}

@Composable
fun SubScreen(navigator: INavigator) {
    Button(onClick = { navigator.goBack() }) {
        Text("Go back")
    }
}

sealed class Screens: IScreen {
    object Main : Screens()
    object SubScreen : Screens()
}