package uk.co.harnick.soundly.features.shared.domain.model

import androidx.room.PrimaryKey
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.toPersistentSet
import uk.co.harnick.soundly.features.sounds.data.model.SoundEntity
import kotlin.reflect.KProperty

data class Sound(
    val authorImageUrl: String,
    val authorName: String,
    val authorUrl: String,
    val imageUri: String,
    val tags: PersistentSet<Category>,
    val title: String,
    val trackUri: String,
    val volume: Float = 0.5f,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)

fun SoundEntity.toSound(): Sound {
    return Sound(
        authorImageUrl,
        authorName,
        authorUrl,
        imageUri,
        tags.toPersistentSet(),
        title,
        trackUri,
        volume
    )
}