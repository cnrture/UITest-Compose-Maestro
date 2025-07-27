package com.cnrture.uitestcomposemaestro

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import com.cnrture.uitestcomposemaestro.data.buildSampleNotes
import com.cnrture.uitestcomposemaestro.ui.home.HomeComponentKey
import com.cnrture.uitestcomposemaestro.ui.home.HomeScreen
import com.cnrture.uitestcomposemaestro.ui.theme.UITestComposeMaestroTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_ShouldDisplayNotes() {
        composeTestRule.setContent {
            UITestComposeMaestroTheme {
                HomeScreen()
            }
        }

        val notes = buildSampleNotes()
        val firstNote = notes[0]

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_TITLE.plus(firstNote.id)).assertIsDisplayed()
    }

    @Test
    fun homeScreen_ShouldDisplayNoteDetailsOnClick() {
        composeTestRule.setContent {
            UITestComposeMaestroTheme {
                HomeScreen()
            }
        }

        val notes = buildSampleNotes()
        val firstNote = notes[0]

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_TITLE.plus(firstNote.id)).performClick()

        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_DETAIL_TITLE).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_DETAIL_CONTENT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_DETAIL_CLOSE_BUTTON).assertIsDisplayed()
    }

    @Test
    fun homeScreen_ShouldDisplayLastNoteDetailsOnClick() {
        composeTestRule.setContent {
            UITestComposeMaestroTheme {
                HomeScreen()
            }
        }

        val notes = buildSampleNotes()
        val lastNote = notes.last()

        composeTestRule.waitForIdle()
        composeTestRule
            .onNode(hasScrollToNodeAction())
            .performScrollToNode(hasTestTag(HomeComponentKey.NOTE_TITLE.plus(lastNote.id)))

        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_TITLE.plus(lastNote.id)).performClick()

        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_DETAIL_TITLE).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_DETAIL_CONTENT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_DETAIL_CLOSE_BUTTON).assertIsDisplayed()
    }

    @Test
    fun homeScreen_ShouldCloseNoteDetailsOnCloseButtonClick() {
        composeTestRule.setContent {
            UITestComposeMaestroTheme {
                HomeScreen()
            }
        }

        val notes = buildSampleNotes()
        val firstNote = notes[0]

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_TITLE.plus(firstNote.id)).performClick()

        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_DETAIL_CLOSE_BUTTON).performClick()
        composeTestRule.onNodeWithTag(HomeComponentKey.NOTE_DETAIL_CLOSE_BUTTON).assertIsNotDisplayed()
    }
}