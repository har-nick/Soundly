package uk.co.harnick.soundly.features.shared.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uk.co.harnick.soundly.features.shared.domain.model.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowScope.NavBarItem(
    screen: Screen,
    pagerScope: CoroutineScope,
    pagerState: PagerState
) {
    val iconRepresentsCurrentPage = pagerState.targetPage == screen.ordinal
    val iconSize by animateDpAsState(
        if (iconRepresentsCurrentPage) 24.dp else 35.dp,
        label = "NavBarItem",
    )

    NavigationBarItem(
        selected = iconRepresentsCurrentPage,
        onClick = {
            pagerScope.launch {
                pagerState.animateScrollToPage(screen.ordinal)
            }
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(screen.icon),
                contentDescription = "Travel to the ${screen.name} page",
                Modifier.size(iconSize)
            )
        },
        label = {
            Text(
                screen.displayName,
                style = MaterialTheme.typography.labelLarge
            )
        },
        alwaysShowLabel = false
    )
}