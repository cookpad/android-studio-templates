package com.cookpad.astemplates.recipes.screen

fun adapter(packageName: String, screenName: String) : String {
    return """
        package ${packageName}.${screenName.toLowerCase()}

        import android.view.LayoutInflater
        import android.view.View
        import android.view.ViewGroup
        import androidx.lifecycle.LiveData
        import androidx.recyclerview.widget.DiffUtil
        import androidx.recyclerview.widget.RecyclerView
        import com.cookpad.android.ui.views.paging.PageState
        import com.cookpad.android.ui.views.paging.PaginatorAdapter
        import ${packageName}.R
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}
        import ${packageName}.${screenName.toLowerCase()}.${screenName}ViewHolder
        
        class ${screenName}Adapter(
            paginatorStates: LiveData<PageState<${screenName}>>
        ) : PaginatorAdapter<${screenName}>(DIFF_CALLBACK, paginatorStates) {
        
            override fun handleBindView(holder: RecyclerView.ViewHolder, position: Int) {
                getItem(position)?.let { item -> (holder as ${screenName}ViewHolder).bind(item) }
            }
        
            override fun handleOnCreateViewHolder(parent: ViewGroup, viewType: Int): ${screenName}ViewHolder {
                val root = LayoutInflater.from(parent.context).inflate(R.layout.item_${screenName.toLowerCase()}, parent, false)
                return ${screenName}ViewHolder(root)
            }
        
            companion object {
                private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<${screenName}>() {
                    override fun areContentsTheSame(oldItem: ${screenName}, newItem: ${screenName}) = oldItem == newItem
                    override fun areItemsTheSame(oldItem: ${screenName}, newItem: ${screenName}) = oldItem.id == newItem.id
                }
            }
        }
    """.trimIndent()
}