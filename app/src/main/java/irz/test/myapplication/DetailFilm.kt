package irz.test.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController

@Composable
fun DetailFilm (navController: NavController, viewModel: MainViewModel, id : String){
    LaunchedEffect(true){
        viewModel.film_detail(id)
    }

}
