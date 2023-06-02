package com.vickikbt.kmptemplate.pref


import com.russhwolf.settings.PreferencesSettings
import java.util.prefs.Preferences

actual class PreferenceStoreFactory() {
    private val rootNode: Preferences = Preferences.userRoot()
        .node("app/preferences")

    actual fun create(vararg names: String): PreferenceStore {
        return StandardPreferenceStore(
            PreferencesSettings(
                rootNode.node(names.joinToString(separator = "/"))
            )
        )
    }
}
