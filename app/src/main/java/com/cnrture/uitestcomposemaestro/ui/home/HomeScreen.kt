package com.cnrture.uitestcomposemaestro.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cnrture.uitestcomposemaestro.data.Note
import com.cnrture.uitestcomposemaestro.data.buildSampleNotes

@Composable
fun HomeScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedNote by remember { mutableStateOf<Note?>(null) }

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .semantics { testTagsAsResourceId = true }
            .padding(24.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            items = buildSampleNotes(),
            key = { it.id },
        ) { item ->
            NoteItem(
                note = item,
                onNoteClick = { note ->
                    selectedNote = note
                    showDialog = true
                },
            )
        }
    }

    if (showDialog && selectedNote != null) {
        NoteDetailDialog(
            note = selectedNote!!,
            onDismiss = {
                showDialog = false
                selectedNote = null
            },
        )
    }
}

@Composable
private fun NoteItem(
    note: Note,
    onNoteClick: (Note) -> Unit,
) {
    Text(
        modifier = Modifier
            .background(
                color = Color.LightGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp),
            )
            .clickable(onClick = { onNoteClick(note) })
            .padding(16.dp)
            .testTag(HomeComponentKey.NOTE_TITLE.plus(note.id)),
        text = note.title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun NoteDetailDialog(
    note: Note,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Column(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .semantics { testTagsAsResourceId = true }
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.testTag(HomeComponentKey.NOTE_DETAIL_TITLE),
                    text = note.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.testTag(HomeComponentKey.NOTE_DETAIL_CONTENT),
                    text = note.content,
                    fontSize = 16.sp,
                )
                Button(
                    modifier = Modifier.testTag(HomeComponentKey.NOTE_DETAIL_CLOSE_BUTTON),
                    onClick = onDismiss,
                ) {
                    Text("Close")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}