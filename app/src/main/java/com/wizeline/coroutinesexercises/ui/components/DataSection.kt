package com.wizeline.coroutinesexercises.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.wizeline.coroutinesexercises.ui.theme.Dimens

@Composable
fun DataSection(
    isLoading: Boolean,
    errorMessage: String?,
    content: @Composable BoxScope.() -> Unit
) {
    if (errorMessage != null) {
        Center {
            Text(
                text = errorMessage,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(Dimens.big * 2)
            )
        }
    }
    if (isLoading) {
        Center {
            CircularProgressIndicator()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        content = content
    )
}