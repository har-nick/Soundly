package uk.co.harnick.soundly.features.sounds.data.model

import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.collections.immutable.PersistentSet
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import uk.co.harnick.soundly.features.shared.domain.model.Category
import uk.co.harnick.soundly.features.shared.domain.model.Sound

@Serializable
data class SoundEntity(
    val authorImageUrl: String,
    val authorName: String,
    val authorUrl: String,
    val imageUri: String,
    val tags: Set<Category>,
    val title: String,
    val trackUri: String,
    val volume: Float = 50f,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)

fun Sound.toSoundEntity(): SoundEntity {
    return SoundEntity(
        authorImageUrl,
        authorName,
        authorUrl,
        imageUri,
        tags,
        title,
        trackUri,
        volume,
        id
    )
}

object SoundEntitySerializer {
    @TypeConverter
    fun encode(soundList: Set<Sound>): String {
        return Json.encodeToString(soundList)
    }

    @TypeConverter
    fun decode(encodedSoundList: String): Set<Sound> {
        return Json.decodeFromString(encodedSoundList)
    }
}