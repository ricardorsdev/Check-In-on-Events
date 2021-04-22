package br.com.ricardorsdev.checkin_on_events.di

import br.com.ricardorsdev.checkin_on_events.api.IApi
import br.com.ricardorsdev.checkin_on_events.utils.Constants
import br.com.ricardorsdev.checkin_on_events.utils.CustomDeserializer
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
object ApiModule {

	@Provides
	fun providesOkHttpClient() : OkHttpClient = OkHttpClient.Builder()
			.addNetworkInterceptor(StethoInterceptor())
			.build()

	@Provides
	fun providesGson(): Gson =
		GsonBuilder().registerTypeAdapter(List::class.java,CustomDeserializer()).create()

	@Provides
	fun providesRetrofit(): Retrofit = Retrofit.Builder()
			.baseUrl(Constants.BASE_URL)
			.client(providesOkHttpClient())
			.addConverterFactory(GsonConverterFactory.create(providesGson()))
			.addCallAdapterFactory(CoroutineCallAdapterFactory())
			.build()

	@Provides
	fun provideApi(retrofit: Retrofit): IApi = retrofit.create(IApi::class.java)

}