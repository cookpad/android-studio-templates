package com.cookpad.astemplates.recipes.module

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.findResource
import java.io.File

private const val TEMPLATE_PATH = "template_new_module/"
private const val MAIN_PATH = "src/main/"
private const val KOTLIN_PATH = "$MAIN_PATH/kotlin/"

fun RecipeExecutor.featureModuleRecipe(
    moduleData: ModuleTemplateData,
    moduleName: String,
    packageName: String
) {
    val rootModule = File("${moduleData.projectTemplateData.rootDir.path}/$moduleName")
        .apply { mkdir() }

    mergeXml(
        source = "$TEMPLATE_PATH/AndroidManifest.xml".readContents()
            .replace("#packageName", packageName),
        to = rootModule.resolve("$MAIN_PATH/AndroidManifest.xml")
    )

    save(
        source = "$TEMPLATE_PATH/build.gradle".readContents(),
        to = rootModule.resolve("build.gradle")
    ).also {
        addIncludeToSettings(moduleName)
    }

    save(
        source = "$TEMPLATE_PATH/baseline.xmk".readContents(),
        to = rootModule.resolve("detekt/baseline.xmk")
    )

    save(
        source = "$TEMPLATE_PATH/lint-baseline.xml".readContents(),
        to = rootModule.resolve("lint-baseline.xml")
    )

    val koinModulePath = "$KOTLIN_PATH/$packageName/di/${moduleName.capitalize()}Module.kt"
    save(
        source = koinModule(packageName, moduleName),
        to = rootModule.resolve(koinModulePath)
    ).also {
        open(rootModule.resolve(koinModulePath))
    }
}

fun String.readContents() = findResource(FeatureModuleTemplate.javaClass, File(this)).readText()