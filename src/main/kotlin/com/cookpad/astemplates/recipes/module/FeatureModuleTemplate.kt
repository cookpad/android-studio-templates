package com.cookpad.astemplates.recipes.module

import com.android.tools.idea.wizard.template.*
import java.io.File

object FeatureModuleTemplate : Template {
    private val moduleName = stringParameter {
        name = "Module Name"
        default = "avocado"
        help = "The name of the module to create"
        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
    }

    private val packageName = stringParameter {
        name = "Package Name"
        default = "com.cookpad.android.${moduleName.value}"
        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
    }

    override val category: Category
        get() = Category.Application

    override val constraints: Collection<TemplateConstraint>
        get() = emptyList()

    override val description: String
        get() = "Creates a new feature module"

    override val documentationUrl: String?
        get() = null

    override val formFactor: FormFactor
        get() = FormFactor.Mobile

    override val minCompileSdk: Int
        get() = 21

    override val minSdk: Int
        get() = 21

    override val name: String
        get() = "Cookpad Module"

    override val recipe: Recipe
        get() = {
            featureModuleRecipe(it as ModuleTemplateData, moduleName.value, packageName.value)
        }

    override val revision: Int
        get() = 1

    // WizardUiContext.NewModule does not show this template in the wizard, so we need to show it as a MenuEntry.
    override val uiContexts: Collection<WizardUiContext>
        get() = listOf(WizardUiContext.MenuEntry)

    override val widgets: Collection<Widget<*>>
        get() = listOf(
            TextFieldWidget(moduleName),
            TextFieldWidget(packageName)
        )

    override fun thumb(): Thumb {
        return Thumb { findResource(this.javaClass, File("thumbs/template_new_feature.png")) }
    }
}