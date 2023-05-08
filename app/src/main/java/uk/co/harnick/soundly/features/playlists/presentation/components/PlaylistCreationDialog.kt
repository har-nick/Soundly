package uk.co.harnick.soundly.features.playlists.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.collections.immutable.PersistentSet
import uk.co.harnick.soundly.features.shared.domain.model.Sound

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistCreationDialog(
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit
) {
    var playlistNameInput by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Create playlist",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(Modifier.height(16.dp))

                TextField(
                    value = playlistNameInput,
                    onValueChange = { playlistNameInput = it },
                    Modifier.fillMaxWidth(),
                    label = { Text("Name") },
                    singleLine = true
                )

                Spacer(Modifier.height(24.dp))

                TextButton(
                    onClick = { onSubmit(playlistNameInput) },
                    Modifier.align(Alignment.End),
                    enabled = playlistNameInput.isNotBlank()
                ) {
                    Text("Create")
                }
            }
        }
    }
}