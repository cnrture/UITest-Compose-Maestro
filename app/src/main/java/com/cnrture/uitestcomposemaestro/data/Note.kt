package com.cnrture.uitestcomposemaestro.data

data class Note(
    val id: Long,
    val title: String,
    val content: String,
)

fun buildSampleNotes(): List<Note> {
    return List(100) { index ->
        Note(
            id = index.toLong(),
            title = "Note #$index",
            content = "This is the content of note #$index. " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        )
    }
}
