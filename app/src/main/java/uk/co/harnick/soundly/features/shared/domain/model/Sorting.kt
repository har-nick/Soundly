package uk.co.harnick.soundly.features.shared.domain.model

enum class SortingType(
    val displayName: String
) {
    Name("Name"),
    Date("Date created")
}

enum class SortingOrder {
    Ascending,
    Descending
}