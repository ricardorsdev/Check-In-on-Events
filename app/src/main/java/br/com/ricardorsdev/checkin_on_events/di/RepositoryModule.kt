package br.com.ricardorsdev.checkin_on_events.di

import br.com.ricardorsdev.checkin_on_events.api.IApi
import br.com.ricardorsdev.checkin_on_events.network.IMainRepository
import br.com.ricardorsdev.checkin_on_events.network.MainRepository
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