package irz.test.myapplication

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun films_tendance(@Query("api_key") api_key: String): Films

    @GET("trending/tv/week")
    suspend fun series_tendance(@Query("api_key") api_key: String): Series

    @GET("trending/person/week")
    suspend fun acteurs_tendance(@Query("api_key") api_key: String): Acteurs

    @GET("search/movie?")
    suspend fun recherche_films(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Films

    @GET("search/tv?")
    suspend fun recherche_series(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Series

    @GET("search/person?")
    suspend fun recherche_acteurs(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Acteurs

    @GET("movie/{filmId}?language=fr&append_to_response=credits")
    suspend fun detail_film(
        @Path("filmId") filmId: Int,
        @Query("api_key") api_key: String
    ): FilmDetail

    @GET("tv/{serieId}?language=fr&append_to_response=credits")
    suspend fun detail_serie(
        @Path("serieId") serieId: Int,
        @Query("api_key") api_key: String
    ): SerieDetail

    @GET("person/{acteurId}?language=fr&append_to_response=credits")
    suspend fun detail_acteur(
        @Path("acteurId") acteurId: Int,
        @Query("api_key") api_key: String
    ): Acteur

    @GET("search/collection?")
    suspend fun recherche_collection(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): collections
}