package com.finance.budgetok.infra.ui.compose.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Spacer for vertical margins
@Composable
fun VSpacer(value: Int) {
  Spacer(modifier = Modifier.height(value.dp))
}

// Spacer for horizontal margins
@Composable
fun HSpacer(value: Int) {
  Spacer(modifier = Modifier.width(value.dp))
}

@Composable
fun HSpacer(value: Dp) {
  Spacer(modifier = Modifier.width(value))
}
