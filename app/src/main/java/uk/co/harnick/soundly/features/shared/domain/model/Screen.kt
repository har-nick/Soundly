package uk.co.harnick.soundly.features.shared.domain.model

import uk.co.harnick.soundly.R

enum class Screen(val displayName: String, val icon: Int) {
    SOUNDS("Sounds", R.drawable.ic_music_note),
    PLAYLISTS("Playlists", R.drawable.ic_queue_music),
    SETTINGS("Settings", R.drawable.ic_settings);

    // TODO: REPLACE WITH ENTRIES WHEN KOTLIN > 1.9
    companion object {
        val list = listOf(SOUNDS, PLAYLISTS, SETTINGS)
    }
}