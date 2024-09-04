package com.finance.budgetok.contexts.panel.features.categoriesattraction.compose

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.finance.budgetok.contexts.panel.domain.DecimalFormatter
import com.finance.budgetok.contexts.panel.domain.DecimalInputVisualTransformation

@Composable
fun DecimalInputField(
    modifier: Modifier = Modifier,
    decimalFormatter: DecimalFormatter
) {

    var text by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { str ->
            text = decimalFormatter.cleanup(str)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Decimal,
        ),
        visualTransformation = DecimalInputVisualTransformation(decimalFormatter)
    )
}