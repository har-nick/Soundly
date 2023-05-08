package uk.co.harnick.soundly.features.playlists.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import uk.co.harnick.soundly.R

@Composable
fun PlaylistsFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = remember { { onClick() } },
        Modifier.size(96.dp)
    ) {
        Icon(
            ImageVector.vectorResource(R.drawable.ic_playlist_add),
            contentDescription = "Create a playlist",
            Modifier.size(36.dp)
        )
    }
}