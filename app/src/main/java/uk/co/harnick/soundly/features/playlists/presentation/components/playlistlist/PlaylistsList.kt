package uk.co.harnick.soundly.features.playlists.presentation.components.playlistlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.co.harnick.soundly.features.shared.domain.model.Playlist
import uk.co.harnick.soundly.features.shared.domain.model.SortingOrder
import uk.co.harnick.soundly.features.shared.domain.model.SortingOrder.Descending
import uk.co.harnick.soundly.features.shared.domain.model.SortingType
import uk.co.harnick.soundly.features.shared.domain.model.SortingType.Date
import uk.co.harnick.soundly.features.shared.domain.model.SortingType.Name

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlaylistsList(
    sorting: Pair<SortingType, SortingOrder>,
    loadedPlaylist: Playlist? = null,
    savedPlaylists: List<Playlist>,
    onCopy: (Playlist) -> Unit,
    onDelete: (Playlist) -> Unit,
    onEnable: (Playlist) -> Unit,
    onRenameRequest: (Playlist) -> Unit
) {
    val sortingType = sorting.first
    val sortingOrder = sorting.second

    val sortedPlaylists = when (sortingType) {
        Name -> savedPlaylists.sortedBy { it.name }
        Date -> savedPlaylists.sortedBy { it.creationDate }
    }.let {
        if (sortingOrder == Descending) it.reversed() else it
    }

    LazyColumn(
        Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        itemsIndexed(
            items = sortedPlaylists,
            key = { _, playlist -> playlist.id ?: playlist.creationDate }
        ) { _, playlist ->
            Box(
                Modifier
                    .animateItemPlacement()
                    .padding(bottom = 8.dp)
            ) {
                PlaylistCard(loadedPlaylist, playlist, onCopy, onEnable, onDelete, onRenameRequest)
            }
        }
    }
}