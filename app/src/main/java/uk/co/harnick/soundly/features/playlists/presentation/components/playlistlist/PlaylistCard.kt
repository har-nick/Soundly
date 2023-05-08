package uk.co.harnick.soundly.features.playlists.presentation.components.playlistlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uk.co.harnick.soundly.R
import uk.co.harnick.soundly.features.shared.domain.model.Playlist
import uk.co.harnick.soundly.features.shared.presentation.components.MDIconButton

@Composable
fun PlaylistCard(
    loadedPlaylist: Playlist? = null,
    playlist: Playlist,
    onCopy: (Playlist) -> Unit,
    onEnable: (Playlist) -> Unit,
    onDelete: (Playlist) -> Unit,
    onRenameRequest: (Playlist) -> Unit
) {
    val thisIsLoadedPlaylist = loadedPlaylist?.id == playlist.id
    var overFlowExpanded by remember { mutableStateOf(false) }

    Card(
        Modifier
            .clip(CardDefaults.shape)
            .clickable(
                onClick = { onEnable(playlist) },
                onClickLabel = ("Enable ${playlist.name}")
            )
    ) {
        Row(
            Modifier
                .padding(start = 16.dp, top = 8.dp, end = 24.dp, bottom = 8.dp)
                .height(56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier
                    .weight(0.6f)
                    .padding(horizontal = 20.dp)
            ) {
                Text(playlist.name, style = MaterialTheme.typography.titleMedium)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = thisIsLoadedPlaylist,
                    onClick = { onEnable(playlist) }
                )

                Box {
                    MDIconButton(
                        vectorImage = R.drawable.ic_more_vert,
                        contentDescription = "Open options for playlist ${playlist.name}",
                        onClick = { overFlowExpanded = true }
                    )

                    DropdownMenu(
                        overFlowExpanded,
                        onDismissRequest = { overFlowExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Copy",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            },
                            onClick = {
                                onCopy(playlist)
                                overFlowExpanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Rename",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            },
                            onClick = {
                                onRenameRequest(playlist)
                                overFlowExpanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Delete",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            },
                            onClick = { onDelete(playlist) }
                        )

                        Divider()

                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Export",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            },
                            onClick = { /*TODO*/ }
                        )
                    }
                }
            }
        }
    }
}