package com.cookpad.astemplates.recipes.screen

fun viewHolder(packageName: String, screenName: String): String {
    return """
        package ${packageName}.${screenName.toLowerCase()}

        import android.view.View
        import kotlinx.android.extensions.LayoutContainer
        import androidx.recyclerview.widget.RecyclerView
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}
        import ${packageName}.databinding.Item${screenName}Binding
        
        class ${screenName}ViewHolder(private val binding: Item${screenName}Binding) : RecyclerView.ViewHolder(binding.root) {
        
            fun bind(${screenName.toLowerCase()}: ${screenName}) {
        
            }
        }
    """.trimIndent()
}