package com.kouta.design.vo

import androidx.compose.ui.text.input.TextFieldValue

data class InputSelection(
    val label: String,
    val value: TextFieldValue,
    val onValueChange: (TextFieldValue) -> Unit
)
