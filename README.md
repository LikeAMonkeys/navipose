# Navipose
Compose desktop navigation library

## Features
Now navipose supports basic screen navigation between few screens

### Examples
At first you should create something like simple graph
```kotlin
//Each screen inside graph should implement IScreen interface
sealed class Screens: IScreen {
    object Main : Screens()
    object SubScreen : Screens()
}
```
Than create navigator inside your composable function
```kotlin
//Specify start screen
Navigator(Screens.Main) {
  //Add screens to navigation graph
  addScreen(Screens.Main) { MainScreen(this) }
  addScreen(Screens.SubScreen) { SubScreen(this)}
}
```

And your composable functions should be like that
```kotlin
@Composable
fun MainScreen(navigator: INavigator) {
  //On Navigate to second screen on click
  Button(onClick = { navigator.navigate(Screens.SubScreen) }) {
    Text("Hello, go to subScreen")
  }
}

@Composable
fun SubScreen(navigator: INavigator) {
  //Navigate back
  Button(onClick = { navigator.goBack() }) {
    Text("Go back")
  }
}
```


## Future
Future features
- [ ] Maven central or jitpack repository
- [ ] Stub screen on wrong navigation
- [ ] Navigation arguments
- [ ] Complete basic navigation flow
- [ ] Back results
- [ ] Controller support
- [ ] Utilities functions for lifecycle/navigation
- [ ] Nested/Parallel navigation support (now it's possible, but was not planned)
