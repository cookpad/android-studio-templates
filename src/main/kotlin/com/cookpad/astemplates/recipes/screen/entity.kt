package com.cookpad.astemplates.recipes.screen

fun entity(packageName: String, screenName: String): String {
    return """
        package ${packageName}.${screenName.toLowerCase()}.data
        
        data class ${screenName}(val id: String)
    """.trimIndent()
}