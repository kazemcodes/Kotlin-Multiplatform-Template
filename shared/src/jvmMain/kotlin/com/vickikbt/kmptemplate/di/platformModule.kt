package com.vickikbt.kmptemplate.di

import com.vickikbt.kmptemplate.pref.PreferenceStoreFactory
import org.koin.core.module.Module
import org.koin.dsl.module
actual val platformModule: Module = module {
    single {
        PreferenceStoreFactory().create("app_preferences")
    }
}