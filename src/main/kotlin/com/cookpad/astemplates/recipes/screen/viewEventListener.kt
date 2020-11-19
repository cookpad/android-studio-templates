package com.cookpad.astemplates.recipes.screen

fun viewEventListener(packageName: String, screenName: String) : String {
    return """
            package ${packageName}.${screenName.toLowerCase()}
            
            import ${packageName}.${screenName.toLowerCase()}.data.${screenName}ViewEventDelegate
            
            interface ${screenName}ViewEventListener {
                fun onViewEvent(viewEvent: ${screenName}ViewEventDelegate)
            }        
    """.trimIndent()
}