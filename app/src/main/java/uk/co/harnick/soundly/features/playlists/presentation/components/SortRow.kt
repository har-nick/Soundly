package uk.co.harnick.soundly.features.playlists.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import uk.co.harnick.soundly.R
import uk.co.harnick.soundly.features.shared.domain.model.SortingOrder
import uk.co.harnick.soundly.features.shared.domain.model.SortingOrder.Ascending
import uk.co.harnick.soundly.features.shared.domain.model.SortingType
import uk.co.harnick.soundly.features.shared.presentation.components.MDIconButton

@Composable
fun SortRow(
    currentSorting: Pair<SortingType, SortingOrder>,
    onSwapSortingOrder: () -> Unit,
    onSwapSortingType: () -> Unit
) {
    val sortingType = currentSorting.first
    val sortingOrder = currentSorting.second
    val sortingName = sortingType.displayName

    val orderRotation by animateFloatAsState(
        targetValue = if (sortingOrder == Ascending) 0f else 180f,
        label = "SortingRotateAnim"
    )

    Row(
        Modifier
            .padding(end = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    )  {
        TextButton(
            onClick = { onSwapSortingType() }
        ) {
            Text(sortingName)
        }

        MDIconButton(
            vectorImage = R.drawable.ic_arrow_upward,
            contentDescription = "Change sorting order",
            modifier = Modifier.rotate(orderRotation),
            onClick = { onSwapSortingOrder() }
        )
    }
}