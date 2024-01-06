package com.kouta.design.enums

enum class DisplayMode {
    DARK_MODE,
    LIGHT_MODE;

    companion object {
        fun findByValue(isSystemInDarkTheme: Boolean) = if (isSystemInDarkTheme) DARK_MODE else LIGHT_MODE
    }
}