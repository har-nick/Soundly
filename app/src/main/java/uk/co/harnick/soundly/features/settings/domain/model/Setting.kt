package uk.co.harnick.soundly.features.settings.domain.model

interface Setting<T> {
    val title: String
    val description: String?
    val value: T
}