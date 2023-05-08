package uk.co.harnick.soundly.features.shared.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.co.harnick.soundly.features.playlists.presentation.PlaylistsScreen
import uk.co.harnick.soundly.features.playlists.presentation.PlaylistsViewModel
import uk.co.harnick.soundly.features.settings.presentation.SettingsScreen
import uk.co.harnick.soundly.features.shared.domain.model.Screen
import uk.co.harnick.soundly.features.shared.domain.model.Screen.PLAYLISTS
import uk.co.harnick.soundly.features.shared.domain.model.Screen.SETTINGS
import uk.co.harnick.soundly.features.shared.domain.model.Screen.SOUNDS
import uk.co.harnick.soundly.features.sounds.presentation.SoundsScreen
import uk.co.harnick.soundly.features.sounds.presentation.SoundsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenPager(pagerState: PagerState) {
    val screens = Screen.list

    val playlistsViewModel = hiltViewModel<PlaylistsViewModel>()
    val playlistsState by playlistsViewModel.state.collectAsStateWithLifecycle()

    val soundsViewModel = hiltViewModel<SoundsViewModel>()
    val soundsState by soundsViewModel.state.collectAsStateWithLifecycle()

    HorizontalPager(
        pageCount = screens.count(),
        pageSize = PageSize.Fill,
        state = pagerState
    ) { i ->
        when (screens[i]) {
            SOUNDS -> SoundsScreen(
                playlistsState,
                soundsState,
                soundsViewModel::toggleFilter,
                playlistsViewModel::toggleSoundInPlaylist,
                soundsViewModel::pokeTheBeast
            )

            PLAYLISTS -> PlaylistsScreen(
                playlistsState,
                playlistsViewModel::copyPlaylist,
                playlistsViewModel::createPlaylist,
                playlistsViewModel::deletePlaylist,
                playlistsViewModel::deleteAllPlaylists,
                playlistsViewModel::enablePlaylist,
                playlistsViewModel::hideRenameDialog,
                playlistsViewModel::renamePlaylist,
                playlistsViewModel::showRenameDialog,
                playlistsViewModel::swapSortingOrder,
                playlistsViewModel::swapSortingType,
                playlistsViewModel::toggleCreationDialog
            )

            SETTINGS -> SettingsScreen()
        }
    }
}