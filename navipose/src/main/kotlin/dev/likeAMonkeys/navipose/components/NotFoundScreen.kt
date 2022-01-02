package dev.likeAMonkeys.navipose.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun NotFoundScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Screen not found :(",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun PreviewNotFoundScreen() {
    NotFoundScreen()
}