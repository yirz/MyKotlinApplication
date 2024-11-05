package irz.test.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController

@Composable
fun DetailSerie(navController: NavController, viewModel: MainViewModel, id : String){
    LaunchedEffect(true){
        viewModel.serie_detail(id)
    }
}