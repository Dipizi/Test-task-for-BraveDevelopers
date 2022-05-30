package com.dipizi007.pokedexv20.di.module

import android.content.Context
import androidx.room.Room
import com.dipizi007.pokedexv20.data.room.AppDB
import com.dipizi007.pokedexv20.data.room.PokemonDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [ContextModule::class]
)
class DaoModule {

    @Provides
    @Singleton
    fun dataBaseProvide(context: Context): AppDB {
        return Room.databaseBuilder(context, AppDB::class.java, "database-pok").build()
    }

    @Provides
    @Singleton
    fun daoProvide(db: AppDB): PokemonDAO {
        return db.getPokemonsDAO()
    }
}