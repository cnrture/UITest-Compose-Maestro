package com.cnrture.uitestcomposemaestro

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.cnrture.uitestcomposemaestro.ui.login.LoginComponentKey
import com.cnrture.uitestcomposemaestro.ui.login.LoginScreen
import com.cnrture.uitestcomposemaestro.ui.theme.UITestComposeMaestroTheme
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginScreen_DisplaysCorrectly() {
        composeTestRule.setContent {
            UITestComposeMaestroTheme {
                LoginScreen(onNavigateHome = {})
            }
        }

        composeTestRule.onNodeWithTag(LoginComponentKey.WELCOME_TEXT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(LoginComponentKey.USERNAME_FIELD).assertIsDisplayed()
        composeTestRule.onNodeWithTag(LoginComponentKey.PASSWORD_FIELD).assertIsDisplayed()
        composeTestRule.onNodeWithTag(LoginComponentKey.LOGIN_BUTTON).assertIsDisplayed()
    }

    @Test
    fun loginScreen_DisplaysErrorOnInvalidCredentials() {
        composeTestRule.setContent {
            UITestComposeMaestroTheme {
                LoginScreen(onNavigateHome = {})
            }
        }

        composeTestRule.onNodeWithTag(LoginComponentKey.USERNAME_FIELD).performTextInput("invalidUser")
        composeTestRule.onNodeWithTag(LoginComponentKey.PASSWORD_FIELD).performTextInput("invalidPass")
        composeTestRule.onNodeWithTag(LoginComponentKey.LOGIN_BUTTON).performClick()
        composeTestRule.onNodeWithTag(LoginComponentKey.ERROR_DIALOG_TEXT).assertIsDisplayed()
    }

    @Test
    fun loginScreen_NavigatesToHomeOnValidCredentials() {
        composeTestRule.setContent {
            UITestComposeMaestroTheme {
                LoginScreen(onNavigateHome = {})
            }
        }

        composeTestRule.onNodeWithTag(LoginComponentKey.USERNAME_FIELD).performTextInput("test")
        composeTestRule.onNodeWithTag(LoginComponentKey.PASSWORD_FIELD).performTextInput("password")
        composeTestRule.onNodeWithTag(LoginComponentKey.LOGIN_BUTTON).performClick()

        composeTestRule.onNodeWithTag(LoginComponentKey.ERROR_DIALOG_TEXT).assertIsNotDisplayed()
    }

    @Test
    fun loginScreen_DisplaysLoginButtonEnabledState() {
        composeTestRule.setContent {
            UITestComposeMaestroTheme {
                LoginScreen(onNavigateHome = {})
            }
        }

        composeTestRule.onNodeWithTag(LoginComponentKey.LOGIN_BUTTON).assertIsNotEnabled()

        composeTestRule.onNodeWithTag(LoginComponentKey.USERNAME_FIELD).performTextInput("test")
        composeTestRule.onNodeWithTag(LoginComponentKey.PASSWORD_FIELD).performTextInput("password")

        composeTestRule.onNodeWithTag(LoginComponentKey.LOGIN_BUTTON).assertIsEnabled()
    }
}