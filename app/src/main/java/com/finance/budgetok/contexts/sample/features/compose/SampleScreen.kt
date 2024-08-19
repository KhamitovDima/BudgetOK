package com.finance.budgetok.contexts.sample.features.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finance.budgetok.contexts.sample.features.mvi.SampleState

@Composable
fun SampleScreen(
    state: SampleState,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = state.text.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = onButtonClick) {
                Text(text = state.title)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSampleScreen() {
    SampleScreen(
        state = SampleState(
            title = "Click Me",
            text = 0,
        ),
        onButtonClick = { /* действие по нажатию кнопки */ }
    )
}