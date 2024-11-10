package irz.test.myapplication
//https://api.themoviedb.org/3/trending/movie/week?api_key=317519a83cc36ab9367ba50e5aa75b40&language=fr

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
//import androidx.media3.common.util.Log


@Composable
fun Films(
    navcontroller : NavController,
    viewModel: MainViewModel,
) {
    val films by viewModel.movies.collectAsState()

    LaunchedEffect(true) {
        viewModel.get_films_tendance()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(films) { film ->
                CarteFilm(film, navcontroller, modifier = Modifier.clickable {
                    navcontroller.navigate("DetailFilm/" + film.id)
                })
            }
        }
    }
}
    @Composable
    fun CarteFilm(film: Film, navController: NavController, modifier: Modifier) {
        Carte(

            modifier = modifier,
            route = "DetailFilm/" + film.id,
            chemin_img = film.poster_path,
            titre = film.title,
            date = film.release_date,
            navController = navController,

        )
    }