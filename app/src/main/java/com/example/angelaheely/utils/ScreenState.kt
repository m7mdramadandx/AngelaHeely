package com.example.angelaheely.utils

data class ScreenState<T>(
    val isLoading: Boolean = true,
    val items: List<T>? = listOf(),
    val item: T? = null,
    val error: String = "",
)