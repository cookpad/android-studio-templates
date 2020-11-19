package com.cookpad.astemplates.recipes

import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.cookpad.astemplates.recipes.module.FeatureModuleTemplate
import com.cookpad.astemplates.recipes.screen.FeatureScreenTemplate

class CustomWizardTemplateProvider : WizardTemplateProvider() {
    override fun getTemplates() = listOf(FeatureModuleTemplate, FeatureScreenTemplate)
}