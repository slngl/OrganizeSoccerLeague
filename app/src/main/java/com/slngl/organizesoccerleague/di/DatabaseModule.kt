package com.slngl.organizesoccerleague.di

import android.content.Context
import androidx.room.Room
import com.slngl.organizesoccerleague.db.FixtureDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        FixtureDatabase::class.java,
        "fixtureDB"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: FixtureDatabase) = database.roundDao()
}