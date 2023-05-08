package uk.co.harnick.soundly.features.shared.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MDIconButton(
    vectorImage: Int,
    iconSize: Dp = 24.dp,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        Modifier
            .size(iconSize + 16.dp)
            .then(modifier)
    ) {
        Icon(
            ImageVector.vectorResource(vectorImage),
            contentDescription,
            Modifier.size(iconSize)
        )
    }
}