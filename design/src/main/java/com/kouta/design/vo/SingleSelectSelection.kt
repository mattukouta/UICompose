package com.kouta.design.vo

data class SingleSelectSelection<E : Enum<E>>(
    val candidates: List<E>,
    val selected: E
)