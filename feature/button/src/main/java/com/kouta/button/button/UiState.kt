package com.kouta.button.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

data class UiState(
    val enabled: Boolean = true,
    val shape: SelectableShape = SelectableShape.ROUNDED_CORNER,
    val buttonColors: ButtonColors = ButtonColors(),
    val elevation: ButtonElevation = ButtonElevation(),
    val border: BorderStroke? = null,
    val contentPadding: PaddingValues = PaddingValues(8.dp),
    val interactionSource: MutableInteractionSource = MutableInteractionSource(),
) {
    enum class SelectableShape(val shape: Shape) {
        RECTANGLE(RectangleShape),
        ROUNDED_CORNER(RoundedCornerShape(8.dp)),
        CIRCLE(CircleShape),
        CUT_CORNER(CutCornerShape(8.dp));
    }

    data class ButtonColors(
        val containerColor: Color? = null,
        val contentColor: Color? = null,
        val disabledContainerColor: Color? = null,
        val disabledContentColor: Color? = null,
    )

    data class ButtonElevation(
        val defaultElevation: TextFieldValue = TextFieldValue(text = "0"),
        val pressedElevation: TextFieldValue = TextFieldValue(text = "0"),
        val focusedElevation: TextFieldValue = TextFieldValue(text = "0"),
        val hoveredElevation: TextFieldValue = TextFieldValue(text = "1"),
        val disabledElevation: TextFieldValue = TextFieldValue(text = "0"),
    )
}
