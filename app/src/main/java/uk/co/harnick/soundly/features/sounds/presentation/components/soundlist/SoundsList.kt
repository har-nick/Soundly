package uk.co.harnick.soundly.features.sounds.presentation.components.soundlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.PersistentSet
import uk.co.harnick.soundly.core.util.CollectionUtils.containsAny
import uk.co.harnick.soundly.features.shared.domain.model.Playlist
import uk.co.harnick.soundly.features.settings.domain.model.Theme
import uk.co.harnick.soundly.features.shared.domain.model.Category
import uk.co.harnick.soundly.features.shared.domain.model.Sound

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SoundsList(
    availableSounds: PersistentSet<Sound>,
    currentPlaylist: Playlist,
    enabledFilters: PersistentSet<Category>,
    onSoundToggle: (Sound) -> Unit,
) {
    val availableSoundsAsList = remember { availableSounds.toList() }

    LazyColumn(
        Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        itemsIndexed(
            items = availableSoundsAsList,
            key = { i, sound -> sound.trackUri }
        ) { _, sound ->
            val isEnabled = currentPlaylist.soundList.contains(sound)
            val isVisible = when {
                isEnabled -> true
                enabledFilters.isEmpty() -> true
                enabledFilters.containsAny(sound.tags) -> true
                else -> false
            }

            AnimatedVisibility(
                isVisible,
                Modifier.animateItemPlacement(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(Modifier.padding(bottom = 8.dp)) {
                    // TODO: REPLACE THEME
                    SoundCard(isEnabled, sound, onSoundToggle, Theme.Auto)
                }
            }
        }
    }
}