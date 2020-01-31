package com.mpierucci.lasttraintolondon.settings.di

import com.mpierucci.lasttraintolondon.settings.data.SettingsRepositoryImpl
import com.mpierucci.lasttraintolondon.settings.domain.SettingsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class SettingsModule {
    @Binds
    abstract fun bindSettingsRepository(repository: SettingsRepositoryImpl): SettingsRepository
}