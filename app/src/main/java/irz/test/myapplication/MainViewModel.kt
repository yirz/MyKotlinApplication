package irz.test.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Query


class MainViewModel (): ViewModel(){

    val apikey = "317519a83cc36ab9367ba50e5aa75b40"

    val movies = MutableStateFlow<List<Film>>(listOf())

    val actors = MutableStateFlow<List<Acteur>>(listOf())

    val shows = MutableStateFlow<List<Serie>>(listOf())

    val movie = MutableStateFlow(Film())
    fun film_detail(id: String) {
        viewModelScope.launch {
            movie.value = service.detail_film(id, apikey)
        }
    }

    val show = MutableStateFlow(Serie())

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun get_films_tendance(){
        viewModelScope.launch {
            movies.value = service.films_tendance(apikey).results
        }
        }
    fun get_acteurs_tendance(){
        viewModelScope.launch {
            actors.value = service.acteurs_tendance(apikey).results
            }
        }
    fun get_series_tendance(){
        viewModelScope.launch {
            shows.value = service.series_tendance(apikey).results
        }
    }
    fun recherche_films(query: String){
        viewModelScope.launch {
            movies.value = service.recherche_films(apikey, query).results
        }
    }
    fun recherche_series(query: String){
        viewModelScope.launch {
            shows.value = service.recherche_series(apikey, query).results
        }
    }
    fun recherche_acteurs(query: String){
        viewModelScope.launch {
            actors.value = service.recherche_acteurs(apikey, query).results
        }
    }

    val filmDetail = MutableStateFlow(FilmDetail())
    val serieDetail = MutableStateFlow(SerieDetail())

    }

