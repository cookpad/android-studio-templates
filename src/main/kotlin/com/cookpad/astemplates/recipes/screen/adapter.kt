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
        import ${packageName}.databinding.Item${screenName}Binding
        
        class ${screenName}Adapter(
            paginatorStates: LiveData<PageState<${screenName}>>
        ) : PaginatorAdapter<${screenName}>(DIFF_CALLBACK, paginatorStates) {
        
            override fun handleBindView(holder: RecyclerView.ViewHolder, position: Int) {
                getItem(position)?.let { item -> (holder as ${screenName}ViewHolder).bind(item) }
            }
        
            override fun handleOnCreateViewHolder(parent: ViewGroup, viewType: Int): ${screenName}ViewHolder {
                val binding = Item${screenName}Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ${screenName}ViewHolder(binding)
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