package com.cnrture.uitestcomposemaestro.ui.login.test

import com.canerture.kmaestro.KMaestro
import com.cnrture.uitestcomposemaestro.ui.login.LoginComponentKey

fun main() {
    loginButtonEnabledState()
    loginInvalidCredentials()
    loginDisplaysCorrectly()
    loginValidCredentials()
}

fun loginButtonEnabledState() {
    KMaestro(
        path = "maestro/login",
        yamlName = "login_button_enabled_state",
        config = {
            launchApp("com.cnrture.uitestcomposemaestro")
            assertVisible(id = LoginComponentKey.LOGIN_BUTTON, enabled = false)
            tapOn(id = LoginComponentKey.USERNAME_FIELD)
            inputText("test")
            tapOn(id = LoginComponentKey.PASSWORD_FIELD)
            inputText("password")
            assertVisible(id = LoginComponentKey.LOGIN_BUTTON, enabled = true)
        }
    )
}

fun loginInvalidCredentials() {
    KMaestro(
        path = "maestro/login",
        yamlName = "login_error_on_invalid_credentials",
        config = {
            launchApp("com.cnrture.uitestcomposemaestro")
            tapOn(id = LoginComponentKey.USERNAME_FIELD)
            inputText("invalidUser")
            tapOn(id = LoginComponentKey.PASSWORD_FIELD)
            inputText("invalidPass")
            tapOn(id = LoginComponentKey.LOGIN_BUTTON)
            assertVisible(text = "Please enter valid credentials.")
        }
    )
}

fun loginDisplaysCorrectly() {
    KMaestro(
        path = "maestro/login",
        yamlName = "login_screen_displays_correctly",
        config = {
            launchApp("com.cnrture.uitestcomposemaestro")
            assertVisible(id = LoginComponentKey.WELCOME_TEXT)
            assertVisible(id = LoginComponentKey.USERNAME_FIELD)
            assertVisible(id = LoginComponentKey.PASSWORD_FIELD)
            assertVisible(id = LoginComponentKey.LOGIN_BUTTON)
        }
    )
}

fun loginValidCredentials() {
    KMaestro(
        path = "maestro/login",
        yamlName = "login_button_valid_credentials",
        config = {
            launchApp("com.cnrture.uitestcomposemaestro")
            tapOn(id = LoginComponentKey.USERNAME_FIELD)
            inputText("test")
            tapOn(id = LoginComponentKey.PASSWORD_FIELD)
            inputText("password")
            tapOn(id = LoginComponentKey.LOGIN_BUTTON)
            assertNotVisible(id = LoginComponentKey.ERROR_DIALOG_TEXT)
        }
    )
}