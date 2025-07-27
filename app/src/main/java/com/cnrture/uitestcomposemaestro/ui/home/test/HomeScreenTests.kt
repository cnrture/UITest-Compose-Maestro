package com.cnrture.uitestcomposemaestro.ui.home.test

import com.canerture.kmaestro.KMaestro
import com.cnrture.uitestcomposemaestro.data.buildSampleNotes
import com.cnrture.uitestcomposemaestro.ui.home.HomeComponentKey

fun main() {
    homeDisplaysCorrectly()
    homeDisplaysNoteDetailsOnClick()
    homeDisplaysLastNoteDetailsOnClick()
    homeClosesNoteDetailsOnCloseButtonClick()
}

fun homeDisplaysCorrectly() {
    val notes = buildSampleNotes()
    val firstNote = notes[0]
    KMaestro(
        path = "maestro/home",
        yamlName = "home_displays_notes",
        config = {
            launchApp("com.cnrture.uitestcomposemaestro")
            assertVisible(id = HomeComponentKey.NOTE_TITLE.plus(firstNote.id))
        }
    )
}

fun homeDisplaysNoteDetailsOnClick() {
    val notes = buildSampleNotes()
    val firstNote = notes[0]
    KMaestro(
        path = "maestro/home",
        yamlName = "home_displays_note_details_on_click",
        config = {
            launchApp("com.cnrture.uitestcomposemaestro")
            tapOn(id = HomeComponentKey.NOTE_TITLE.plus(firstNote.id))
            assertVisible(id = HomeComponentKey.NOTE_DETAIL_TITLE)
            assertVisible(id = HomeComponentKey.NOTE_DETAIL_CONTENT)
            assertVisible(id = HomeComponentKey.NOTE_DETAIL_CLOSE_BUTTON)
        }
    )
}

fun homeDisplaysLastNoteDetailsOnClick() {
    val notes = buildSampleNotes()
    val lastNote = notes.last()
    KMaestro(
        path = "maestro/home",
        yamlName = "home_displays_last_note_details_on_click",
        config = {
            launchApp("com.cnrture.uitestcomposemaestro")
            scrollUntilVisible(id = HomeComponentKey.NOTE_TITLE.plus(lastNote.id))
            tapOn(id = HomeComponentKey.NOTE_TITLE.plus(lastNote.id))
            assertVisible(id = HomeComponentKey.NOTE_DETAIL_TITLE)
            assertVisible(id = HomeComponentKey.NOTE_DETAIL_CONTENT)
            assertVisible(id = HomeComponentKey.NOTE_DETAIL_CLOSE_BUTTON)
        }
    )
}

fun homeClosesNoteDetailsOnCloseButtonClick() {
    val notes = buildSampleNotes()
    val firstNote = notes[0]
    KMaestro(
        path = "maestro/home",
        yamlName = "home_closes_note_details_on_close_button_click",
        config = {
            launchApp("com.cnrture.uitestcomposemaestro")
            tapOn(id = HomeComponentKey.NOTE_TITLE.plus(firstNote.id))
            tapOn(id = HomeComponentKey.NOTE_DETAIL_CLOSE_BUTTON)
            assertNotVisible(id = HomeComponentKey.NOTE_DETAIL_CLOSE_BUTTON)
        }
    )
}