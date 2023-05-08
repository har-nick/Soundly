package uk.co.harnick.soundly.features.playlists.presentation

import kotlinx.collections.immutable.persistentSetOf
import uk.co.harnick.soundly.features.shared.domain.model.Playlist
import uk.co.harnick.soundly.features.shared.domain.model.SortingOrder
import uk.co.harnick.soundly.features.shared.domain.model.SortingOrder.*
import uk.co.harnick.soundly.features.shared.domain.model.SortingType
import uk.co.harnick.soundly.features.shared.domain.model.SortingType.Name

data class PlaylistsState(
    val attributionSheetVisible: Boolean = true,
    val creationDialogVisible: Boolean = false,
    val loadedPlaylist: Playlist? = null,
    val renameDialogCurrentPlaylist: Playlist? = null,
    val renameDialogVisible: Boolean = false,
    val savedPlaylists: List<Playlist> = emptyList(),
    val sorting: Pair<SortingType, SortingOrder> = Pair(Name, Ascending),
    val tempPlaylist: Playlist = Playlist(name = "", soundList = persistentSetOf())
)