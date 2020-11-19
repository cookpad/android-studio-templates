package com.cookpad.astemplates.recipes.screen

fun viewDelegate(packageName: String, screenName: String) : String {
    return """
            package ${packageName}.${screenName.toLowerCase()}
            
            import androidx.lifecycle.LifecycleOwner
            import androidx.lifecycle.LiveData
            import androidx.lifecycle.Observer
            import ${packageName}.${screenName.toLowerCase()}.data.${screenName}ViewStateDelegate
            import ${packageName}.databinding.FragmentDelegate${screenName}Binding

            class ${screenName}ViewDelegate(
                lifecycleOwner: LifecycleOwner,
                private val binding: FragmentDelegate${screenName}Binding,
                viewStates: LiveData<${screenName}ViewStateDelegate>,
                private val viewEventListener: ${screenName}ViewEventListener
            ) {
            
                init {
                    viewStates.observe(lifecycleOwner, Observer { state ->
                        when (state) {
                        }
                    })
                }
            }
    """.trimIndent()
}