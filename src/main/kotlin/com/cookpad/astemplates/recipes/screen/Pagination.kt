package com.cookpad.astemplates.recipes.screen

enum class Pagination {
    Standard,
    None;
}

fun Pagination.ifStandard(callback: () -> String): String {
    return if (this == Pagination.Standard) callback()
    else ""
}