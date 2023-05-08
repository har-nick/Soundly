package uk.co.harnick.soundly.features.shared.domain.model

import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.toPersistentSet
import uk.co.harnick.soundly.features.playlists.data.model.PlaylistEntity

data class Playlist(
    val creationDate: Long = System.currentTimeMillis(),
    val name: String,
    val soundList: PersistentSet<Sound>,
    val id: Long? = null
)

fun PlaylistEntity.toPlaylist(): Playlist {
    return Playlist(
        creationDate,
        name,
        soundList.map { it.toSound() }.toPersistentSet(),
        id
    )
}