package uk.co.harnick.soundly.features.sounds.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.co.harnick.soundly.R
import uk.co.harnick.soundly.features.shared.presentation.components.MDIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundlyHeader(
    onTitleTap: () -> Unit
) {
    var overFlowExpanded by remember { mutableStateOf(false) }

    MediumTopAppBar(
        title = {
            Text(
                "Soundly",
                Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onTitleTap() }
                )
            )
        },
        actions = {
            MDIconButton(
                vectorImage = R.drawable.ic_attribution,
                iconSize = 30.dp,
                contentDescription = "View attributions",
                onClick = {}
            )

            Box {
                MDIconButton(
                    vectorImage = R.drawable.ic_more_vert,
                    iconSize = 30.dp,
                    contentDescription = "Open main header menu",
                    onClick = { overFlowExpanded = true }
                )

                DropdownMenu(overFlowExpanded, onDismissRequest = { overFlowExpanded = false }) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                "Send feedback",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        onClick = { /*TODO*/ }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(
                                "Report a bug", style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        onClick = { /*TODO*/ }
                    )
                }
            }
        }
    )
}