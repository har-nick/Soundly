package uk.co.harnick.soundly.core.di

import android.content.Context
import androidx.room.Room
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.co.harnick.soundly.features.playlists.data.local.PlaylistDao
import uk.co.harnick.soundly.features.shared.data.local.SoundlyDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun provideCoilImageLoader(
        @ApplicationContext appContext: Context
    ): ImageLoader {
        return ImageLoader(appContext)
            .newBuilder()
            .crossfade(300)
            .memoryCache {
                MemoryCache.Builder(appContext)
                    .maxSizePercent(0.40)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.02)
                    .build()
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideSoundlyDatabase(
        @ApplicationContext appContext: Context
    ): SoundlyDatabase {
        return Room.databaseBuilder(
            appContext,
            SoundlyDatabase::class.java,
            "SoundlyDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun providePlaylistDao(db: SoundlyDatabase): PlaylistDao = db.playlistDao
}