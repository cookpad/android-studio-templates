package com.cookpad.astemplates.recipes.screen

import com.android.tools.idea.wizard.template.*
import java.io.File

object FeatureScreenTemplate : Template {
    private val screenName = stringParameter {
        name = "Screen Name"
        default = "avocado"
        help = "The name of the screen to create"
        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
    }

    private val pagination = enumParameter<Pagination> {
        name = "Add pagination support"
        default = Pagination.None
        help = "Provides a basic pagination implementation"
    }

    private val delegation = enumParameter<Delegation> {
        name = "Add delegation support"
        default = Delegation.None
        help = "Provides a basic delegation implementation"
    }

    override val category: Category
        get() = Category.Other

    override val constraints: Collection<TemplateConstraint>
        get() = emptyList()

    override val description: String
        get() = "Creates a new feature screen"

    override val documentationUrl: String?
        get() = null

    override val formFactor: FormFactor
        get() = FormFactor.Mobile

    override val minCompileSdk: Int
        get() = 21

    override val minSdk: Int
        get() = 21

    override val name: String
        get() = "Cookpad Feature Screen"

    override val recipe: Recipe
        get() = {
            featureScreenRecipe(
                it as ModuleTemplateData,
                screenName.value.capitalize(),
                pagination.value,
                delegation.value
            )
        }

    override val revision: Int
        get() = 1

    override val uiContexts: Collection<WizardUiContext>
        get() = listOf(WizardUiContext.MenuEntry)

    override val widgets: Collection<Widget<*>>
        get() = listOf(
            TextFieldWidget(screenName),
            EnumWidget(pagination),
            EnumWidget(delegation)
        )

    override fun thumb(): Thumb {
        return Thumb { findResource(this.javaClass, File("thumbs/template_new_feature.png")) }
    }
}