package com.cookpad.astemplates.recipes.screen

fun singleViewState(packageName: String, screenName: String): String {
    return """
        package ${packageName}.${screenName.toLowerCase()}.data

        sealed class ${screenName.capitalize()}SingleViewState {
        }
    """.trimIndent()
}