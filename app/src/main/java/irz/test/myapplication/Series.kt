package irz.test.myapplication

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun Series(
    navcontroller : NavController,
    viewModel: MainViewModel,
){
    val series by viewModel.shows.collectAsState()
    LaunchedEffect(true)  {
        viewModel.get_series_tendance()
    }
    series.forEach{ serie  ->  Text(text = serie.original_name)}

}