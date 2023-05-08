package uk.co.harnick.soundly.features.shared.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import uk.co.harnick.soundly.features.shared.domain.model.Screen

@ExperimentalFoundationApi
@Composable
fun NavBar(
    pagerScope: CoroutineScope,
    pagerState: PagerState
) {
    val screens = Screen.list

    NavigationBar {
        screens.forEach { screen ->
            NavBarItem(screen, pagerScope, pagerState)
        }
    }
}