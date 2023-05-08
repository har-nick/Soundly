package uk.co.harnick.soundly.features.shared.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.Serializer
import uk.co.harnick.soundly.features.playlists.data.local.PlaylistDao
import uk.co.harnick.soundly.features.playlists.data.model.PlaylistEntity
import uk.co.harnick.soundly.features.playlists.data.model.PlaylistEntitySerializer

@Database(
    entities = [PlaylistEntity::class],
    version = 1
)
@TypeConverters(PlaylistEntitySerializer::class)
abstract class SoundlyDatabase : RoomDatabase() {
    abstract val playlistDao: PlaylistDao
//    abstract val soundsDao:
}