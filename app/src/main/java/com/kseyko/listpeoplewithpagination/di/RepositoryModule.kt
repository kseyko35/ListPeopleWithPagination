package com.kseyko.listpeoplewithpagination.di

import com.kseyko.listpeoplewithpagination.repo.PersonRepository
import com.kseyko.listpeoplewithpagination.source.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║       28,August,2022       ║
╚════════════════════════════╝
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePersonRepository(source: DataSource): PersonRepository = PersonRepository(source)
}

