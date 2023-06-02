package com.vickikbt.kmptemplate.pref

import com.russhwolf.settings.NSUserDefaultsSettings
import platform.Foundation.NSUserDefaults

actual class PreferenceStoreFactory() {
    actual fun create(vararg names: String): PreferenceStore {
        return StandardPreferenceStore(
            NSUserDefaultsSettings(
                NSUserDefaults.standardUserDefaults
            )
        )
    }
}
