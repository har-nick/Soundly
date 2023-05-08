package uk.co.harnick.soundly.features.playlists.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.co.harnick.soundly.features.playlists.presentation.components.NoPlaylistsNotice
import uk.co.harnick.soundly.features.playlists.presentation.components.PlaylistCreationDialog
import uk.co.harnick.soundly.features.playlists.presentation.components.PlaylistRenameDialog
import uk.co.harnick.soundly.features.playlists.presentation.components.PlaylistsFAB
import uk.co.harnick.soundly.features.playlists.presentation.components.PlaylistsHeader
import uk.co.harnick.soundly.features.playlists.presentation.components.SortRow
import uk.co.harnick.soundly.features.playlists.presentation.components.playlistlist.PlaylistsList
import uk.co.harnick.soundly.features.shared.domain.model.Playlist

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistsScreen(
    playlistsState: PlaylistsState,
    copyPlaylist: (Playlist) -> Unit,
    createNewPlaylist: (String) -> Unit,
    deletePlaylist: (Playlist) -> Unit,
    deleteAllPlaylists: () -> Unit,
    enablePlaylist: (Playlist) -> Unit,
    hideRenameDialog: () -> Unit,
    renamePlaylist: (Playlist, String) -> Unit,
    showRenameDialog: (Playlist) -> Unit,
    swapSortingOrder: () -> Unit,
    swapSortingType: () -> Unit,
    toggleCreationDialog: () -> Unit
) {
    val noSavedPlaylists = playlistsState.savedPlaylists.isEmpty()

    Scaffold(
        topBar = { PlaylistsHeader(deleteAllPlaylists) },
        floatingActionButton = { PlaylistsFAB(toggleCreationDialog) }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (playlistsState.creationDialogVisible) {
                PlaylistCreationDialog(toggleCreationDialog, createNewPlaylist)
            }

            if (playlistsState.renameDialogVisible) {
                PlaylistRenameDialog(
                    playlistsState.renameDialogCurrentPlaylist !!,
                    hideRenameDialog,
                    renamePlaylist
                )
            }

            SortRow(playlistsState.sorting, swapSortingOrder, swapSortingType)

            when (noSavedPlaylists) {
                true -> NoPlaylistsNotice()
                else -> PlaylistsList(
                    playlistsState.sorting,
                    playlistsState.loadedPlaylist,
                    playlistsState.savedPlaylists,
                    copyPlaylist,
                    deletePlaylist,
                    enablePlaylist,
                    showRenameDialog
                )
            }
        }
    }
}