package uk.co.harnick.soundly.features.sounds.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import uk.co.harnick.soundly.R

@Composable
fun TheBeast(beastReleased: Boolean) {
    AnimatedVisibility(
        beastReleased,
        enter = fadeIn() + slideInVertically(
            initialOffsetY = { fullHeight -> - fullHeight }
        ),
        exit = fadeOut() + slideOutVertically(
            targetOffsetY = { fullHeight -> - fullHeight }
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painterResource(R.drawable.the_beast),
                contentDescription = "FEAR HIM FOR HE HAS COME",
                Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
            )

            Text(
                "THE BEAST HAS AWOKEN",
                Modifier.fillMaxWidth(),
                color = Color.Red,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )
        }
    }

}