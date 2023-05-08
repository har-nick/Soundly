package uk.co.harnick.soundly.features.settings.domain.model

import android.os.Build

object InterfaceSettings {
    val materialYouEnabled: MaterialYouEnabledSetting = MaterialYouEnabledSetting()
    val theme: ThemeSetting = ThemeSetting()
}

class MaterialYouEnabledSetting(
    override val value: Boolean = Build.VERSION.SDK_INT > 30
) : Setting<Boolean> {
    override val title = "Material You"
    override val description = null
}

class ThemeSetting(
    override val value: Theme = Theme.Auto
) : Setting<Theme> {
    override val title = "Theme"
    override val description = null
}

enum class Theme(
    val displayName: String
) {
    Auto("Follow system theme"),
    Light("Light"),
    Dark("Dark"),
    AMOLED("AMOLED")
}