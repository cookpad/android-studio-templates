package com.cookpad.astemplates.recipes.screen

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import java.io.File

private const val MAIN_PATH = "src/main/"

fun RecipeExecutor.featureScreenRecipe(
    moduleData: ModuleTemplateData,
    screenName: String,
    pagination: Pagination,
    delegation: Delegation
) {
    val (projectData, srcOut, resOut, manifestOut) = moduleData
    val kotlinSrc = File(srcOut.path.replace("java", "kotlin"))
    val kotlinTestSrc = File(kotlinSrc.path.replace("main", "test"))

    val packageName = kotlinSrc.path.substringAfter("kotlin/").replace("/", ".")

    mergeXml(
        source = navGraph(packageName, screenName),
        to = File("${moduleData.projectTemplateData.rootDir.path}/ui-commons/$MAIN_PATH/res/navigation/nav_graph.xml")
    )

    if (delegation == Delegation.VMDelegateAndViewDelegate) {
        val delegateLayoutRes = "fragment_delegate_${screenName.toLowerCase()}"
        save(
            source = fragmentDelegateLayout(),
            to = resOut.resolve("layout/$delegateLayoutRes.xml")
        )
        save(
            source = fragmentLayout(pagination, delegateLayoutRes),
            to = resOut.resolve("layout/fragment_${screenName.toLowerCase()}.xml")
        )
    } else {
        save(
            source = fragmentLayout(pagination),
            to = resOut.resolve("layout/fragment_${screenName.toLowerCase()}.xml")
        )
    }

    save(
        source = viewState(packageName, screenName),
        to = kotlinSrc.resolve("${screenName.toLowerCase()}/data/${screenName}ViewState.kt")
    )

    save(
        source = singleViewState(packageName, screenName),
        to = kotlinSrc.resolve("${screenName.toLowerCase()}/data/${screenName}SingleViewState.kt")
    )

    save(
        source = viewEvent(packageName, screenName),
        to = kotlinSrc.resolve("${screenName.toLowerCase()}/data/${screenName}ViewEvent.kt")
    )

    save(
        source = viewModel(packageName, screenName, pagination, delegation),
        to = kotlinSrc.resolve("${screenName.toLowerCase()}/${screenName}ViewModel.kt")
    )

    save(
        source = viewModelTest(packageName, screenName),
        to = kotlinTestSrc.resolve("${screenName.toLowerCase()}/${screenName}ViewModelTest.kt")
    )

    save(
        source = fragment(packageName, screenName, pagination, delegation),
        to = kotlinSrc.resolve("${screenName.toLowerCase()}/${screenName}Fragment.kt")
    )

    if (delegation != Delegation.None) {
        save(
            source = viewEventDelegate(packageName, screenName),
            to = kotlinSrc.resolve("${screenName.toLowerCase()}/data/${screenName}ViewEventDelegate.kt")
        )

        save(
            source = viewStateDelegate(packageName, screenName),
            to = kotlinSrc.resolve("${screenName.toLowerCase()}/data/${screenName}ViewStateDelegate.kt")
        )

        save(
            source = viewModelDelegate(packageName, screenName),
            to = kotlinSrc.resolve("${screenName.toLowerCase()}/${screenName}ViewModelDelegate.kt")
        )

        save(
            source = viewModelDelegateTest(packageName, screenName),
            to = kotlinTestSrc.resolve("${screenName.toLowerCase()}/${screenName}ViewModelDelegateTest.kt")
        )
    }

    if (delegation == Delegation.VMDelegateAndViewDelegate) {
        save(
            source = viewDelegate(packageName, screenName),
            to = kotlinSrc.resolve("${screenName.toLowerCase()}/${screenName}ViewDelegate.kt")
        )

        save(
            source = viewEventListener(packageName, screenName),
            to = kotlinSrc.resolve("${screenName.toLowerCase()}/${screenName}ViewEventListener.kt")
        )
    }

    open(File("${screenName.toLowerCase()}/${screenName}Fragment.kt"))
}
