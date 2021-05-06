package com.slngl.organizesoccerleague.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Round::class], version = 1)
@TypeConverters(Converters::class)
abstract class FixtureDatabase : RoomDatabase(){
    abstract fun roundDao(): RoundDao
}