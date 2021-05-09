package com.slngl.organizesoccerleague.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    @BaseUrlQualifier
    fun provideBaseUrl(): String = "https://6097aeb1e48ec00017872cfc.mockapi.io/"

}