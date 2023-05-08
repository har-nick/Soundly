package uk.co.harnick.soundly.features.sounds.presentation

import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentSetOf
import uk.co.harnick.soundly.features.shared.domain.model.Category
import uk.co.harnick.soundly.features.shared.domain.model.Sound

data class SoundsState(
    val beastPokes: Int = 0,
    val beastReleased: Boolean = false,
    val enabledFilters: PersistentSet<Category> = persistentSetOf(),
    val availableSounds: PersistentSet<Sound> = persistentSetOf()
)