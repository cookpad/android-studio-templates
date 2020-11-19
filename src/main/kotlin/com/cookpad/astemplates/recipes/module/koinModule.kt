package com.cookpad.astemplates.recipes.module

fun koinModule(packageName: String, moduleName: String) =
"""
    package $packageName.di
    import org.koin.dsl.module

    // Add this Koin module to the graph in CookpadApplication::setupKoin
    val ${moduleName.toLowerCase()}Module = module {

    }
""".trimIndent()