package br.com.ricardorsdev.checkin_on_events.di

import br.com.ricardorsdev.checkin_on_events.api.IApi
import br.com.ricardorsdev.network.IMainRepository
import br.com.ricardorsdev.network.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

	@Provides
	fun providesMainRepository(
		retrofit: IApi
	): IMainRepository = MainRepository(retrofit)
}