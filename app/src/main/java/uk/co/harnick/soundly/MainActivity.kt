package uk.co.harnick.soundly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import uk.co.harnick.soundly.core.ui.theme.SoundlyTheme
import uk.co.harnick.soundly.features.settings.domain.model.InterfaceSettings
import uk.co.harnick.soundly.features.shared.presentation.components.NavBar
import uk.co.harnick.soundly.features.shared.presentation.components.ScreenPager

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val interfaceSettings = InterfaceSettings

            SoundlyTheme(
                interfaceSettings.materialYouEnabled.value,
                interfaceSettings.theme.value
            ) {
                val pagerScope = rememberCoroutineScope()
                val pagerState = rememberPagerState()

                Scaffold(
                    bottomBar = { NavBar(pagerScope, pagerState) }
                ) {
                    Box(Modifier.padding(bottom = it.calculateBottomPadding())) {
                        ScreenPager(pagerState)
                    }
                }
            }
        }
    }
}