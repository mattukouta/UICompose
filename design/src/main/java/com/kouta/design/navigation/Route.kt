package com.kouta.design.navigation

enum class Route(val title: String) {
    // メイン画面用
    MAIN_TOP("UI一覧"),
    BUTTON_TOP("ボタン一覧"),
    TEXT_TOP("テキスト一覧"),
    TEXT_FIELD_TOP("テキストフィールド一覧"),
    DIALOG_TOP("ダイアログ一覧"),
    SNACK_BAR_TOP("スナックバー一覧"),
    // ボタン一覧
    BUTTON("Button");

    companion object {
        fun mainTopEntries() = listOf(
            BUTTON_TOP,
            TEXT_TOP,
            TEXT_FIELD_TOP,
            DIALOG_TOP,
            SNACK_BAR_TOP,
        )

        fun buttonEntries() = listOf(
            BUTTON
        )
    }
}
