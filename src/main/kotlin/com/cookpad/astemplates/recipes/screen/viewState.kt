package com.cookpad.astemplates.recipes.screen

fun viewState(packageName: String, screenName: String): String {
    return """
        package ${packageName}.${screenName.toLowerCase()}.data

        sealed class ${screenName}ViewState {
        }
    """.trimIndent()
}