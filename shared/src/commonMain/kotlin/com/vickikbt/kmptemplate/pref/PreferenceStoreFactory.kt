package com.vickikbt.kmptemplate.pref

expect class PreferenceStoreFactory {
    fun create(vararg names: String): PreferenceStore
}
