package com.kouta.design.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kouta.design.theme.UIComposeTheme
import com.kouta.design.vo.InputSelection

@Composable
fun NumberInputAt(
    label: String,
    selections: List<InputSelection>
) {
    BottomSheetItemAt(
        label = label,
        content = {
            Column {
                selections.forEach { selection ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            modifier = Modifier.weight(2F),
                            text = selection.label
                        )

                        TextField(
                            modifier = Modifier.weight(1F),
                            value = selection.value,
                            onValueChange = {
                                val text = (it.text.toIntOrNull() ?: 0).toString()
                                selection.onValueChange(it.copy(text = text))
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewNumberInputAt() {
    UIComposeTheme {
        NumberInputAt(
            label = "Preview",
            selections = listOf(
                InputSelection(
                    label = "label_1",
                    value = TextFieldValue(text = "text"),
                    onValueChange = {}
                )
            )
        )
    }
}