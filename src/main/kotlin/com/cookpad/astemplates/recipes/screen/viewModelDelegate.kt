package com.cookpad.astemplates.recipes.screen

fun viewModelDelegate(packageName: String, screenName: String) : String {
    return """
        package ${packageName}.${screenName.toLowerCase()}
        
        import androidx.lifecycle.LiveData
        import androidx.lifecycle.MutableLiveData
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}ViewEventDelegate
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}ViewStateDelegate
        
        class ${screenName}ViewModelDelegate {
            private val _viewState = MutableLiveData<${screenName}ViewStateDelegate>()
            val viewState: LiveData<${screenName}ViewStateDelegate>
                get() = _viewState
        
            fun onViewEvent(viewEvent: ${screenName}ViewEventDelegate) {
                when (viewEvent) {
        
                }
            }
        }
    """.trimIndent()
}