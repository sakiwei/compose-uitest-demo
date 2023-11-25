package com.sakiwei.compose_test.views

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CommonButton(title: String, onClick: () -> Unit = {}) {
    Button(onClick = onClick) {
        Text(title)
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_CommonButton() {
    var title by remember {
        mutableStateOf("${System.nanoTime()}")
    }
    CommonButton(title = title) {
        title = "${System.nanoTime()}"
    }
}