package com.kouta.design.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kouta.design.theme.UIComposeTheme

@Composable
fun UiPartsAt(
    title: String,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 20.dp),
        text = title,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun PreviewUiPartsAt() {
    UIComposeTheme {
        UiPartsAt(
            "ボタン一覧",
            onClick = {}
        )
    }
}