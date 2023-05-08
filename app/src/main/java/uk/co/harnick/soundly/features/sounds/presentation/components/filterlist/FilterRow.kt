package uk.co.harnick.soundly.features.sounds.presentation.components.filterlist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.PersistentSet
import uk.co.harnick.soundly.features.shared.domain.model.Category

@Composable
fun FilterRow(enabledFilters: PersistentSet<Category>, onToggle: (Category) -> Unit) {
    val categories = Category.list

    LazyRow(Modifier.padding(bottom = 5.dp)) {
        itemsIndexed(categories) { _, category ->
            val categoryIsFirstInList = category == categories.first()
            val categoryIsLastInList = category == categories.last()
            val filterIsEnabled = enabledFilters.contains(category)

            if (categoryIsFirstInList) Spacer(Modifier.width(10.dp))

            FilterPill(category, filterIsEnabled, onToggle)

            Spacer(Modifier.width(10.dp))
        }
    }
}