package cz.muni.fi.pv256.hw9.api

import cz.muni.fi.pv256.hw9.data.Character
import cz.muni.fi.pv256.hw9.data.CharacterList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterList

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character

    companion object {

        private const val API_ENDPOINT = "https://rickandmortyapi.com/api/"

        val apiService by lazy { create() }

        private fun create(): ApiService = Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(ApiService::class.java)
    }
}
