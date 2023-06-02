package com.vickikbt.kmptemplate.di

import com.vickikbt.kmptemplate.pref.PreferenceStoreFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single {
        PreferenceStoreFactory(androidContext()).create("app_preferences")
    }
}