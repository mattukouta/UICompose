package com.kouta.design.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kouta.design.vo.SingleSelectSelection

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun <E : Enum<E>> SingleSelectAt(
    label: String,
    selection: SingleSelectSelection<E>,
    onClickItem: (E) -> Unit
) {
    BottomSheetItemAt(
        label = label,
        content = {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                selection.candidates.forEach {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onClickItem(it) }
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = it == selection.selected, onClick = null)

                        Text(text = it.name)
                    }
                }
            }
        }
    )
}