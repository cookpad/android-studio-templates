package com.cookpad.astemplates.recipes.screen

fun fragmentLayout(pagination: Pagination, delegateLayoutRes: String? = null): String {
    val delegateBlock = if (delegateLayoutRes != null) {
        """
            
<include
        android:id="@+id/delegateLayout"
        layout="@layout/$delegateLayoutRes"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
    """.trimIndent()
    } else {
        ""
    }

    val paginationBlock = pagination.ifStandard {
        """
            
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
         """.trimIndent()
    }

    return """
        <?xml version="1.0" encoding="utf-8"?>
        <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            $paginationBlock$delegateBlock
        </androidx.constraintlayout.widget.ConstraintLayout>
    """.trimIndent()
}