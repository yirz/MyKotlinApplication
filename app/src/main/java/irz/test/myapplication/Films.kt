package irz.test.myapplication
//https://api.themoviedb.org/3/trending/movie/week?api_key=317519a83cc36ab9367ba50e5aa75b40&language=fr

import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.compose.ui.Modifier



@Composable
fun Films(
    navcontroller : NavController,
    viewModel: MainViewModel,
){

    val films by viewModel.movies.collectAsState()
    LaunchedEffect(true)  {
        viewModel.get_films_tendance()
    }
    films.forEach{ film  ->  Text(text = film.original_title)}

}

@Composable
fun CarteFilm(film: Film, navController: NavController, modifier: Modifier){
    Carte(
        modifier = Modifier,
        route = "filmDetail/" + film.id,
        chemin_img = film.poster_path,
        titre = film.title,
        date = film.release_date,
        navController = navController,
    )
}