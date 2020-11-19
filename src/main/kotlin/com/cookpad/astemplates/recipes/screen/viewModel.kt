package com.cookpad.astemplates.recipes.screen

fun viewModel(
    packageName: String,
    screenName: String,
    pagination: Pagination,
    delegation: Delegation
): String {
    val paginationImportsBlock = pagination.ifStandard {
        """
            import io.reactivex.Single
            import com.cookpad.android.ui.views.paging.Paginator
            import com.cookpad.android.entity.Extra
            import ${packageName}.${screenName.toLowerCase()}.data.${screenName}
        """.trimIndent()
    }

    val delegationImportsBlock = delegation.ifNotNone {
        """
            import ${packageName}.${screenName.toLowerCase()}.data.${screenName}ViewStateDelegate
            import ${packageName}.${screenName.toLowerCase()}.data.${screenName}ViewEventDelegate
        """.trimIndent()
    }

    val classDeclarationBlock = when {
        pagination == Pagination.Standard && delegation == Delegation.VMDelegate -> {
            """
            class ${screenName}ViewModel(
                initPaginator: ((Int) -> Single<Extra<List<${screenName}>>>) -> Paginator<${screenName}>,
                private val viewModelDelegate: ${screenName}ViewModelDelegate
            ) : ViewModel() {
        """.trimIndent()
        }
        pagination == Pagination.Standard && delegation == Delegation.VMDelegateAndViewDelegate -> {
            """
            class ${screenName}ViewModel(
                initPaginator: ((Int) -> Single<Extra<List<${screenName}>>>) -> Paginator<${screenName}>,
                private val viewModelDelegate: ${screenName}ViewModelDelegate
                ) : ViewModel(), ${screenName}ViewEventListener {
            """.trimIndent()
        }
        pagination == Pagination.Standard -> {
            """
            class ${screenName}ViewModel(
                initPaginator: ((Int) -> Single<Extra<List<${screenName}>>>) -> Paginator<${screenName}>
                ) : ViewModel() {
            """.trimIndent()
        }
        delegation == Delegation.VMDelegate -> {
            """
            class ${screenName}ViewModel(private val viewModelDelegate: ${screenName}ViewModelDelegate) : ViewModel() { 
            """.trimIndent()
        }
        delegation == Delegation.VMDelegateAndViewDelegate -> {
            """
            class ${screenName}ViewModel(
                    private val viewModelDelegate: ${screenName}ViewModelDelegate
                ): ViewModel(), ${screenName}ViewEventListener {
            """.trimIndent()
        }
        else -> {
            """
                class ${screenName}ViewModel : ViewModel() {
            """.trimIndent()
        }
    }

    val paginationMembersBlock = pagination.ifStandard {
        """
            
        private val paginator: Paginator<${screenName}> = initPaginator { page ->
            error("Supply a repository call for fetching the paginator data")
        }
        val pageStates = paginator.pageStates
        """.trimIndent()
    }

    val delegationMembersBlock = delegation.ifNotNone {
        """
            
        val delegateViewState: LiveData<${screenName}ViewStateDelegate>
            get() = viewModelDelegate.viewState
        """.trimIndent()
    }

    val delegationViewBlock = delegation.ifVMDelegateAndViewDelegate {
        """

        override fun onViewEvent(viewEvent: ${screenName}ViewEventDelegate) {
            when (viewEvent) {
    
            }
        }

        """.trimIndent()
    }

    return """
        package ${packageName}.${screenName.toLowerCase()}

        import androidx.lifecycle.LiveData
        import androidx.lifecycle.MutableLiveData
        import androidx.lifecycle.ViewModel
        import com.cookpad.android.core.arch.SingleLiveData
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}SingleViewState
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}ViewEvent
        import ${packageName}.${screenName.toLowerCase()}.data.${screenName}ViewState
        import io.reactivex.disposables.CompositeDisposable
        $paginationImportsBlock
        $delegationImportsBlock

        $classDeclarationBlock

        private val disposables = CompositeDisposable()
    
        private val _viewState = MutableLiveData<${screenName}ViewState>()
        val viewState: LiveData<${screenName}ViewState>
            get() = _viewState
    
        private val _singleViewState = SingleLiveData<${screenName}SingleViewState>()
        val singleViewState: LiveData<${screenName}SingleViewState>
            get() = _singleViewState
        $paginationMembersBlock
        $delegationMembersBlock
    
        fun onViewEvent(viewEvent: ${screenName}ViewEvent) {
            when (viewEvent) {
    
            }
        }
        $delegationViewBlock
        override fun onCleared() {
            super.onCleared()
            disposables.clear()
        }
    }
    """.trimIndent()
}