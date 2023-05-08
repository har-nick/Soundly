package uk.co.harnick.soundly.features.sounds.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.co.harnick.soundly.features.playlists.presentation.PlaylistsState
import uk.co.harnick.soundly.features.shared.domain.model.Category
import uk.co.harnick.soundly.features.shared.domain.model.Sound
import uk.co.harnick.soundly.features.sounds.presentation.components.SoundlyHeader
import uk.co.harnick.soundly.features.sounds.presentation.components.TheBeast
import uk.co.harnick.soundly.features.sounds.presentation.components.attribution.AttributionModal
import uk.co.harnick.soundly.features.sounds.presentation.components.filterlist.FilterRow
import uk.co.harnick.soundly.features.sounds.presentation.components.soundlist.SoundsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundsScreen(
    playlistsState: PlaylistsState,
    soundsState: SoundsState,
    onFilterToggle: (Category) -> Unit,
    onSoundToggle: (Sound) -> Unit,
    onTitleTap: () -> Unit
) {
    TheBeast(soundsState.beastReleased)

    Scaffold(
        topBar = { SoundlyHeader(onTitleTap) }
    ) {
        Column(
            Modifier.padding(top = it.calculateTopPadding())
        ) {
            FilterRow(soundsState.enabledFilters, onFilterToggle)
            SoundsList(
                soundsState.availableSounds,
                playlistsState.loadedPlaylist ?: playlistsState.tempPlaylist,
                soundsState.enabledFilters,
                onSoundToggle
            )
        }
    }
}