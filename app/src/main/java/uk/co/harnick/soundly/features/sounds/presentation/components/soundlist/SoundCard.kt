package uk.co.harnick.soundly.features.sounds.presentation.components.soundlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import uk.co.harnick.soundly.features.settings.domain.model.Theme
import uk.co.harnick.soundly.features.settings.domain.model.Theme.AMOLED
import uk.co.harnick.soundly.features.settings.domain.model.Theme.Auto
import uk.co.harnick.soundly.features.settings.domain.model.Theme.Dark
import uk.co.harnick.soundly.features.settings.domain.model.Theme.Light
import uk.co.harnick.soundly.features.shared.domain.model.Sound

@Composable
fun SoundCard(isEnabled: Boolean, sound: Sound, onToggle: (Sound) -> Unit, theme: Theme) {
    var volume by remember { mutableStateOf(sound.volume) }

    Card(
        Modifier
            .clip(CardDefaults.shape)
            .clickable(
                onClick = { onToggle(sound) },
                onClickLabel = (if (isEnabled) "Disable" else "Enable").plus(" ${sound.title}")
            )
    ) {
        Row(
            Modifier
                .padding(start = 16.dp, top = 8.dp, end = 24.dp, bottom = 8.dp)
                .height(64.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = sound.imageUri,
                contentDescription = null,
                Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(Color.Red)
                    .size(56.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )

            Column(
                Modifier
                    .weight(0.6f)
                    .padding(horizontal = 20.dp)
            ) {
                Text(sound.title, style = MaterialTheme.typography.titleMedium)

                Text(sound.authorName, style = MaterialTheme.typography.bodyMedium)
            }

            Switch(checked = isEnabled, onCheckedChange = remember { { onToggle(sound) } })
        }

        // Fixes missing divider in dark theme
        val dividerColor = when (theme) {
            Auto -> when (isSystemInDarkTheme()) {
                true -> MaterialTheme.colorScheme.outline
                false -> MaterialTheme.colorScheme.outlineVariant
            }

            Light -> MaterialTheme.colorScheme.outlineVariant
            AMOLED, Dark -> MaterialTheme.colorScheme.outline
        }

        AnimatedVisibility(
            isEnabled, Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
        ) {
            Divider(color = dividerColor)

            Slider(
                value = volume,
                onValueChange = { volume = it },
                valueRange = 0f..1f
                )
        }
    }
}