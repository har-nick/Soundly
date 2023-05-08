package uk.co.harnick.soundly.features.playlists.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import uk.co.harnick.soundly.features.playlists.data.model.PlaylistEntity

@Dao
interface PlaylistDao {
    @Query("SELECT * from playlistentity ORDER BY creationDate ASC")
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>

    @Delete
    fun deletePlaylist(playlistEntity: PlaylistEntity)

    @Query("DELETE FROM playlistentity")
    fun deleteAllPlaylists()

    @Upsert
    fun savePlaylist(playlistEntity: PlaylistEntity)
}