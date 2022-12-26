package com.wizeline.coroutinesexercises.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wizeline.coroutinesexercises.ui.theme.Dimens
import com.wizeline.coroutinesexercises.ui.theme.MoviesTheme

@Composable
fun DataSection(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String?,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier) {
        if (errorMessage != null) {
            Center {
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(Dimens.big * 2)
                )
            }
            return
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
        if (isLoading) {
            Center { CircularProgressIndicator() }
        }
    }
}

@Preview
@Composable
private fun DataSectionLoadingPreview() {
    MoviesTheme {
        DataSection(isLoading = true, errorMessage = null) {}
    }
}

@Preview
@Composable
private fun DataSectionErrorMessagePreview() {
    MoviesTheme {
        DataSection(isLoading = false, errorMessage = "Something has happened.") {}
    }
}

@Preview
@Composable
private fun DataSectionContentPreview() {
    MoviesTheme {
        DataSection(isLoading = false, errorMessage = null) {
            Text(text = "This is my data")
        }
    }
}