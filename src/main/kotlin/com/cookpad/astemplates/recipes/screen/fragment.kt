package com.cookpad.astemplates.recipes.screen

fun fragment(
    packageName: String,
    screenName: String,
    pagination: Pagination,
    delegation: Delegation
): String {
    val paginationImportsBlock = pagination.ifStandard {
        """
        import com.cookpad.android.core.extensions.lazyUnsynchronized
        import kotlinx.android.synthetic.main.fragment_${screenName.toLowerCase()}.recyclerView
        import androidx.recyclerview.widget.LinearLayoutManager
        """.trimIndent()
    }

    val delegationImportsBlock = delegation.ifNotNone {
        """
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}ViewStateDelegate
        """.trimIndent()
    }

    val paginationMembersBlock = pagination.ifStandard {
        """
            
        private val ${screenName.toLowerCase()}Adapter by lazyUnsynchronized {
            ${screenName}Adapter(viewModel.pageStates)
        }
        """.trimIndent()
    }

    val paginationOnViewCreatedBlock = pagination.ifStandard  {
        """
            
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ${screenName.toLowerCase()}Adapter.apply {
                bindToLifecycle(viewLifecycleOwner.lifecycle)
            }
        }
        """.trimIndent()
    }

    val delegationOnViewCreatedBlock = when (delegation) {
        Delegation.VMDelegate -> {
            """
                
        viewModel.delegateViewState.observe(viewLifecycleOwner, Observer(::handleViewStateDelegate))
        """.trimIndent()
        }
        Delegation.VMDelegateAndViewDelegate -> {
            """
                
        ${screenName}ViewDelegate(viewLifecycleOwner, binding.delegateLayout, viewModel.delegateViewState, viewModel)
        """.trimIndent()
        }
        else -> ""
    }

    val handleViewStateDelegateBlock = delegation.ifNotNone {
        """
            
        private fun handleViewStateDelegate(singleViewState: ${screenName}ViewStateDelegate) {
            when(singleViewState) {
    
            }
        }
            """.trimIndent()
    }

    val paginationOnDestroyViewBlock = pagination.ifStandard {
        """
            
        override fun onDestroyView() {
            binding.recyclerView.adapter = null
            super.onDestroyView()
        }
        """.trimIndent()
    }

    return """
        package ${packageName}.${screenName.toLowerCase()}
        import android.os.Bundle
        import android.view.View
        import androidx.fragment.app.Fragment
        import androidx.lifecycle.Observer
        import ${packageName}.R
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}SingleViewState
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}ViewState
        import org.koin.androidx.viewmodel.ext.android.viewModel
        import com.cookpad.android.core.viewbinding.viewBinding
        import ${packageName}.databinding.Fragment${screenName}Binding
        $paginationImportsBlock
        $delegationImportsBlock
        
        class ${screenName}Fragment : Fragment(R.layout.fragment_${screenName.toLowerCase()}) {
        
            // TODO: Implement the Koin dependency wiring for ${screenName}ViewModel.
            private val viewModel: ${screenName}ViewModel by viewModel()
            
            private val binding by viewBinding(Fragment${screenName}Binding::bind)
            $paginationMembersBlock
            
            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)
                viewModel.viewState.observe(viewLifecycleOwner, Observer(::handleViewState))
                viewModel.singleViewState.observe(viewLifecycleOwner, Observer(::handleSingleViewState))
                $delegationOnViewCreatedBlock$paginationOnViewCreatedBlock
            }
        
            private fun handleViewState(viewState: ${screenName}ViewState) {
                when(viewState) {
        
                }
            }
        
            private fun handleSingleViewState(singleViewState: ${screenName}SingleViewState) {
                when(singleViewState) {
        
                }
            }
            $handleViewStateDelegateBlock$paginationOnDestroyViewBlock
        }    
    """.trimIndent()
}