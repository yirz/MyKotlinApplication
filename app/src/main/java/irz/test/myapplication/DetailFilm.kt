package irz.test.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun DetailFilm (navController: NavController, viewModel : MainViewModel, id: String){
        viewModel.film_detail(id)
        val filmDetail by viewModel.filmDetail.collectAsState()

}