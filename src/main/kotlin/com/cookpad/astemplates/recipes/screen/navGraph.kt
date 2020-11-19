package com.cookpad.astemplates.recipes.screen

fun navGraph(packageName: String, screenName: String) =
    """
    <?xml version="1.0" encoding="utf-8"?>
    <navigation xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools">
    
        <action
            android:id="@+id/action${screenName}Fragment"
            app:destination="@id/${screenName.toLowerCase()}Fragment" />
    
        <fragment
            android:id="@+id/${screenName.toLowerCase()}Fragment"
            android:name="${packageName}.${screenName}Fragment"
            tools:layout="@layout/fragment_${screenName.toLowerCase()}" />
    </navigation>
""".trimIndent()