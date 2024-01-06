package com.kouta.design.enums

enum class Enable(val value: Boolean) {
    ENABLED(true),
    DISABLED(false);

    companion object {
        fun findByValue(enabled: Boolean) = if (enabled) ENABLED else DISABLED
    }
}
