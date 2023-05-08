package uk.co.harnick.soundly.features.sounds.presentation.components.filterlist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import uk.co.harnick.soundly.features.shared.domain.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterPill(filter: Category, isEnabled: Boolean, onToggle: (Category) -> Unit) {
    FilterChip(
        selected = isEnabled,
        onClick = { onToggle(filter) },
        label = { Text(filter.displayName) },
        leadingIcon = {
            Icon(
                ImageVector.vectorResource(filter.icon),
                contentDescription = null,
                Modifier
                    .padding(vertical = 5.dp)
                    .size(24.dp)
            )
        }
    )
}