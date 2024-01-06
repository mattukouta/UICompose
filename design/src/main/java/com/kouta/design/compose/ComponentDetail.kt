package com.kouta.design.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.kouta.design.enums.DisplayMode
import com.kouta.design.vo.SingleSelectSelection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentDetail(
    title: String,
    displayMode: DisplayMode = DisplayMode.findByValue(isSystemInDarkTheme()),
    onClickBack: () -> Unit,
    onClickDisplayMode: (DisplayMode) -> Unit = {},
    content: @Composable () -> Unit,
    sheetContent: @Composable ColumnScope.() -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            UiComposeTopAppBar(
                title = title,
                onClickBack = onClickBack,
                actions = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                sheetState.expand()
                            }
                        }
                    ) {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Filled.Settings),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.align(Alignment.Center)
            ) {
                content()
            }

            if (sheetState.isVisible) {
                ModalBottomSheet(
                    onDismissRequest = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    },
                    sheetState = sheetState
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                        SingleSelectAt(
                            label = "DarkMode",
                            selection = SingleSelectSelection(
                                candidates = DisplayMode.entries,
                                selected = displayMode
                            ),
                            onClickItem = { onClickDisplayMode(it) }
                        )

                        sheetContent()
                    }
                }
            }
        }
    }
}