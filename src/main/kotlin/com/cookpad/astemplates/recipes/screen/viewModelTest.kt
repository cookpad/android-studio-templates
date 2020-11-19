package com.cookpad.astemplates.recipes.screen

fun viewModelTest(packageName: String, screenName: String) : String {
    return """
            package ${packageName}.${screenName.toLowerCase()}
            
            import androidx.arch.core.executor.testing.InstantTaskExecutorRule
            import com.cookpad.android.test.rules.RxJavaTestRule
            import org.junit.After
            import org.junit.Before
            import org.junit.Rule
            import org.junit.Test
            
            class ${screenName}ViewModelTest {
            
                @Suppress("unused")
                @get:Rule
                val rxJavaTestRule = RxJavaTestRule()
            
                @Suppress("unused")
                @get:Rule
                val instantTaskExecutorRule = InstantTaskExecutorRule()
            
                @Before
                fun setup() {
            
                }
            
                @Test
                fun `given CONTEXT when SOMETHING HAPPENED then EXPECTATION SHOULD BE MET`() {
                    TODO("Implement a real test here and rename this test after that.")
                }
            }
    """.trimIndent()
}