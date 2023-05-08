package uk.co.harnick.soundly.features.playlists.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.mutate
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uk.co.harnick.soundly.core.di.IoDispatcher
import uk.co.harnick.soundly.core.util.CollectionUtils.toggle
import uk.co.harnick.soundly.features.playlists.data.local.PlaylistDao
import uk.co.harnick.soundly.features.playlists.data.model.toPlaylistEntity
import uk.co.harnick.soundly.features.shared.domain.model.Playlist
import uk.co.harnick.soundly.features.shared.domain.model.SortingOrder.Ascending
import uk.co.harnick.soundly.features.shared.domain.model.SortingOrder.Descending
import uk.co.harnick.soundly.features.shared.domain.model.SortingType.Date
import uk.co.harnick.soundly.features.shared.domain.model.SortingType.Name
import uk.co.harnick.soundly.features.shared.domain.model.Sound
import uk.co.harnick.soundly.features.shared.domain.model.toPlaylist
import javax.inject.Inject

@HiltViewModel
class PlaylistsViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val dao: PlaylistDao
) : ViewModel() {
    private val _state = MutableStateFlow(PlaylistsState())
    val state = _state.asStateFlow()

    private val _savedPlaylistsFlow = dao.getAllPlaylists().flowOn(ioDispatcher)

    init {
        monitorPlaylists()
    }

    fun swapSortingOrder() {
        val oldSort = state.value.sorting
        val newOrder = when (state.value.sorting.second) {
            Ascending -> Descending
            Descending -> Ascending
        }

        _state.value = state.value.copy(
            sorting = Pair(oldSort.first, newOrder)
        )
    }

    fun swapSortingType() {
        val oldSort = state.value.sorting
        val newType = when (state.value.sorting.first) {
            Name -> Date
            Date -> Name
        }

        _state.value = state.value.copy(
            sorting = Pair(newType, oldSort.second)
        )
    }

    fun toggleCreationDialog() {
        _state.value = state.value.copy(
            creationDialogVisible = state.value.creationDialogVisible.not()
        )
    }

    fun showRenameDialog(playlist: Playlist) {
        _state.value = state.value.copy(
            renameDialogCurrentPlaylist = playlist,
            renameDialogVisible = true
        )
    }

    fun hideRenameDialog() {
        _state.value = state.value.copy(
            renameDialogCurrentPlaylist = null,
            renameDialogVisible = false
        )
    }

    private fun monitorPlaylists() {
        _savedPlaylistsFlow
            .map { entities -> entities.map { it.toPlaylist() } }
            .onEach { playlists ->
                _state.value = state.value.copy(
                    savedPlaylists = playlists
                )
            }
            .launchIn(viewModelScope)
    }

    fun enablePlaylist(playlist: Playlist) {
        _state.value = state.value.copy(
            loadedPlaylist = playlist
        )
    }

    fun deleteAllPlaylists() {
        _state.value = state.value.copy(loadedPlaylist = null)

        viewModelScope.launch(ioDispatcher) {
            dao.deleteAllPlaylists()
        }
    }

    fun deletePlaylist(playlist: Playlist) {
        _state.value = state.value.copy(loadedPlaylist = null)

        viewModelScope.launch(ioDispatcher) {
            dao.deletePlaylist(playlist.toPlaylistEntity())
        }
    }

    fun createPlaylist(name: String) {
        val newPlaylist = when (state.value.loadedPlaylist) {
            null -> Playlist(name = name, soundList = state.value.tempPlaylist.soundList)
            else -> Playlist(name = name, soundList = persistentSetOf())
        }

        toggleCreationDialog()
        savePlaylist(newPlaylist)

        if (state.value.loadedPlaylist == null) enablePlaylist(newPlaylist)
    }

    fun copyPlaylist(existingPlaylist: Playlist) {
        val cleanPlaylist = Playlist(
            name = existingPlaylist.name,
            soundList = existingPlaylist.soundList
        )

        savePlaylist(cleanPlaylist)
    }

    fun renamePlaylist(playlist: Playlist, newName: String) {
        val renamedPlaylist = playlist.copy(name = newName)

        hideRenameDialog()
        savePlaylist(renamedPlaylist)
    }

    fun savePlaylist(playlist: Playlist) {
        viewModelScope.launch(ioDispatcher) {
            dao.savePlaylist(playlist.toPlaylistEntity())
        }
    }

    fun toggleSoundInPlaylist(sound: Sound) {
        val currentPlaylist = state.value.loadedPlaylist ?: state.value.tempPlaylist
        val editedSoundList = currentPlaylist.soundList.mutate { it.toggle(sound) }
        val editedPlaylist = currentPlaylist.copy(soundList = editedSoundList)

        when (state.value.loadedPlaylist) {
            null -> _state.value = state.value.copy(tempPlaylist = editedPlaylist)
            else -> {
                _state.value = state.value.copy(loadedPlaylist = editedPlaylist)
                savePlaylist(editedPlaylist)
            }
        }
    }
}