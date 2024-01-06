package com.kouta.design.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kouta.design.navigation.Route
import com.kouta.design.theme.UIComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopScreen(
    entries: List<Route>,
    onClickBack: (() -> Unit)? = null,
    onClickItem: (Route) -> Unit
) {
    Scaffold(
        topBar = {
            UiComposeTopAppBar(
                title = Route.BUTTON_TOP.title,
                onClickBack = onClickBack
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(entries) {
                UiPartsAt(title = it.title, onClick = { onClickItem(it) })
            }
        }
    }
}

@Preview
@Composable
private fun PreviewButtonTopScreen() {
    UIComposeTheme {
        BaseTopScreen(
            entries = Route.entries,
            onClickItem = {}
        )
    }
}