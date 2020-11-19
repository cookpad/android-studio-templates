package com.cookpad.astemplates.recipes.screen

fun viewHolder(packageName: String, screenName: String): String {
    return """
        package ${packageName}.${screenName.toLowerCase()}

        import android.view.View
        import kotlinx.android.extensions.LayoutContainer
        import androidx.recyclerview.widget.RecyclerView
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}
        
        class ${screenName}ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        
            fun bind(${screenName.toLowerCase()}: ${screenName}) {
        
            }
        }
    """.trimIndent()
}