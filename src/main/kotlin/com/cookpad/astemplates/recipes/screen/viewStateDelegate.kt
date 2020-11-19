package com.cookpad.astemplates.recipes.screen

fun viewStateDelegate(packageName: String, screenName: String): String {
    return """
        package ${packageName}.${screenName.toLowerCase()}.data

        sealed class ${screenName}ViewStateDelegate {
        }
    """.trimIndent()
}