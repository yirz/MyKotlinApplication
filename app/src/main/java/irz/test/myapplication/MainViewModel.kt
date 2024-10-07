package irz.test.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainViewModel (): ViewModel(){
    val apikey = "317519a83cc36ab9367ba50e5aa75b40"

    val movies = MutableStateFlow<List<Film>>(listOf())

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(TmdbAPI::class.java)


    fun get_films_tendance(){
        viewModelScope.launch {
            movies.value = api.films_tendance(apikey).results
        }
    }

}