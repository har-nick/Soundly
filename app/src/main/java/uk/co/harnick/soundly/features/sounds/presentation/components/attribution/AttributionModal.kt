package uk.co.harnick.soundly.features.sounds.presentation.components.attribution

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributionModal(
    isVisible: Boolean
) {
    ModalDrawerSheet {
        Box(
            Modifier
                .background(Color.Red)
                .height(400.dp)
                .fillMaxWidth()
        ) {

        }
    }

//    AnimatedVisibility(
//        isVisible,
//        enter = slideInVertically(
//            initialOffsetY = { fullHeight -> fullHeight * 2 }
//        ),
//        exit = slideOutVertically(
//            targetOffsetY = { fullHeight ->  fullHeight * 2 }
//        )
//    ) {
//        ModalDrawerSheet {
//            Box(
//                Modifier
//                    .background(Color.Red)
//                    .height(400.dp)
//            ) {
//
//            }
//        }
//    }
}