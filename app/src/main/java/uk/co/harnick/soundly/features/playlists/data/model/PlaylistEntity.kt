package uk.co.harnick.soundly.features.playlists.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import uk.co.harnick.soundly.features.shared.domain.model.Playlist
import uk.co.harnick.soundly.features.shared.domain.model.Sound
import uk.co.harnick.soundly.features.sounds.data.model.SoundEntity
import uk.co.harnick.soundly.features.sounds.data.model.toSoundEntity

@Serializable
@Entity
data class PlaylistEntity(
    val creationDate: Long = System.currentTimeMillis(),
    val name: String,
    val soundList: Set<SoundEntity>,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)

fun Playlist.toPlaylistEntity(): PlaylistEntity {
    return PlaylistEntity(
        creationDate,
        name,
        soundList.map { it.toSoundEntity() }.toSet(),
        id
    )
}

object PlaylistEntitySerializer {
    @TypeConverter
    fun encodeSoundList(soundList: Set<SoundEntity>): String {
        return Json.encodeToString(soundList)
    }

    @TypeConverter
    fun decodeSoundList(encodedSoundList: String): Set<SoundEntity> {
        return Json.decodeFromString(encodedSoundList)
    }
}