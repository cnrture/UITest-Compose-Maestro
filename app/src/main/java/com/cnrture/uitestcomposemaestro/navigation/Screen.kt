package com.cnrture.uitestcomposemaestro.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Login : Screen

    @Serializable
    data object Home : Screen
}