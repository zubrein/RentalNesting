package xit.zubrein.accomodation.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xit.zubrein.accomodation.BuildConfig
import xit.zubrein.accomodation.Utils.pref.PrefKeys
import xit.zubrein.accomodation.Utils.pref.PreferenceManager
import xit.zubrein.accomodation.network.data.ApiConstants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiClient {

    @Provides
    @Singleton
    fun retrofit(
        preferenceManager: PreferenceManager
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor {
                    it.proceed(
                        it.request().newBuilder().also {
                            it.addHeader(
                                "Authorization", "Bearer ${
                                    preferenceManager.getString(
                                        PrefKeys.AUTH_TOKEN
                                    )
                                }"
                            )
                        }.build()
                    )
                }
                .also { client ->
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    //Provides Api Interfaces

    @Provides
    @Singleton
    fun demoApiService(retrofit: Retrofit) : ApiService =
        retrofit.create(ApiService::class.java)
}