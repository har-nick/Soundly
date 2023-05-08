package uk.co.harnick.soundly.features.shared.domain.model

import uk.co.harnick.soundly.R

enum class Category(val displayName: String, val icon: Int) {
    Ambient("Ambient", R.drawable.ic_spatial_audio),
    Animals("Animals", R.drawable.ic_sound_detection_dog_barking),
    Nature("Nature", R.drawable.ic_nature),
    Noise("Noise", R.drawable.ic_blur_on),
    Water("Water", R.drawable.ic_waves),
    Weather("Weather", R.drawable.ic_rainy_light);

    // TODO: REPLACE WITH ENTRIES WHEN KOTLIN > 1.9
    companion object {
        val list = listOf(Ambient, Animals, Nature, Noise, Water, Weather)
    }
}