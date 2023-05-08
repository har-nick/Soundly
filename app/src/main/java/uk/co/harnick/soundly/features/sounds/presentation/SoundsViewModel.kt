package uk.co.harnick.soundly.features.sounds.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.mutate
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.co.harnick.soundly.core.util.CollectionUtils.toggle
import uk.co.harnick.soundly.features.shared.domain.model.Category
import uk.co.harnick.soundly.features.shared.domain.model.Category.Ambient
import uk.co.harnick.soundly.features.shared.domain.model.Category.Animals
import uk.co.harnick.soundly.features.shared.domain.model.Category.Nature
import uk.co.harnick.soundly.features.shared.domain.model.Category.Noise
import uk.co.harnick.soundly.features.shared.domain.model.Category.Water
import uk.co.harnick.soundly.features.shared.domain.model.Category.Weather
import uk.co.harnick.soundly.features.shared.domain.model.Sound
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SoundsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SoundsState())
    val state = _state.asStateFlow()

    init {
        _state.value = state.value.copy(
            availableSounds = persistentSetOf(
                Sound(
                    authorImageUrl = "https://search.yahoo.com/search?p=ad",
                    authorUrl = "https://duckduckgo.com/?q=veri",
                    authorName = "Donald Ellison",
                    tags = persistentSetOf(Ambient, Water),
                    trackUri = "ex",
                    imageUri = "dictas",
                    title = "verear",
                    volume = 0.02f
                ),
                Sound(
                    authorImageUrl = "https://duckduckgo.com/?q=quas",
                    authorUrl = "https://www.google.com/#q=scelerisque",
                    authorName = "Tyrone Gonzalez",
                    tags = persistentSetOf(Animals),
                    trackUri = "per",
                    imageUri = "amet",
                    title = "commune",
                    volume = 0.06f
                ),
                Sound(
                    authorImageUrl = "https://duckduckgo.com/?q=scelerisque",
                    authorUrl = "http://www.bing.com/search?q=vitae",
                    authorName = "Randolph Cameron",
                    tags = persistentSetOf(Nature),
                    trackUri = "erroribus",
                    imageUri = "leo",
                    title = "invidunt",
                    volume = 0.1f
                ),
                Sound(
                    authorImageUrl = "http://www.bing.com/search?q=homero",
                    authorUrl = "http://www.bing.com/search?q=dolorum",
                    authorName = "Lane Meyers",
                    tags = persistentSetOf(Noise),
                    trackUri = "vituperatoribus",
                    imageUri = "reprimique",
                    title = "interdum",
                    volume = 0.1f
                ),
                Sound(
                    authorImageUrl = "https://duckduckgo.com/?q=elitr",
                    authorUrl = "https://search.yahoo.com/search?p=iriure",
                    authorName = "Rufus Underwood",
                    tags = persistentSetOf(Water),
                    trackUri = "suscipiantur",
                    imageUri = "mattis",
                    title = "curae",
                    volume = 0.2f
                ),
                Sound(
                    authorImageUrl = "https://www.google.com/#q=saepe",
                    authorUrl = "https://search.yahoo.com/search?p=dui",
                    authorName = "Jules Copeland",
                    tags = persistentSetOf(Weather),
                    trackUri = "nec",
                    imageUri = "ei",
                    title = "cum",
                    volume = 0.2f
                ),
                Sound(
                    authorImageUrl = "https://duckduckgo.com/?q=torquent",
                    authorUrl = "http://www.bing.com/search?q=fuisset",
                    authorName = "Willie Roman",
                    tags = persistentSetOf(Nature),
                    trackUri = "doctus",
                    imageUri = "expetendis",
                    title = "maiestatis",
                    volume = 0.02f
                ),
                Sound(
                    authorImageUrl = "http://www.bing.com/search?q=nam",
                    authorUrl = "http://www.bing.com/search?q=porro",
                    authorName = "Gabriela Chen",
                    tags = persistentSetOf(Ambient),
                    trackUri = "volutpat",
                    imageUri = "cras",
                    title = "inciderint",
                    volume = 0.06f
                )
            )
        )
    }

    fun pokeTheBeast() {
        val pokes = state.value.beastPokes + 1

        when {
            pokes < 6 -> _state.value = state.value.copy(beastPokes = pokes)
            else -> releaseTheBeast()
        }
    }

    private fun releaseTheBeast() {
        viewModelScope.launch {
            _state.value = state.value.copy(beastPokes = 0, beastReleased = true)

            delay(3.seconds)

            _state.value = state.value.copy(beastReleased = false)
        }
    }

    fun toggleFilter(category: Category) {
        val editedFilters = state.value.enabledFilters.mutate { it.toggle(category) }

        _state.value = state.value.copy(enabledFilters = editedFilters)
    }
}