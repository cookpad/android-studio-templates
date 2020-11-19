package com.cookpad.astemplates.recipes.screen

fun viewEventDelegate(packageName: String, screenName: String) : String {
    return """
        package ${packageName}.${screenName.toLowerCase()}.data
        
        sealed class ${screenName}ViewEventDelegate {
        }
    """.trimIndent()
}