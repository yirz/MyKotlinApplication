package irz.test.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Series(
    navcontroller : NavController,
    viewModel: MainViewModel,
){
    val series by viewModel.shows.collectAsState()

    LaunchedEffect(true) {
        viewModel.get_series_tendance()
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(series) { serie ->
                CarteSeries(serie, navcontroller, modifier = Modifier.clickable {
                    navcontroller.navigate("FilmDetail/" + serie.id)
                })
            }
        }
    }
}

@Composable
fun CarteSeries(series : Serie, navController: NavController, modifier: Modifier) {
    Carte(
        modifier = Modifier,
        route = "filmDetail/" + series.id,
        chemin_img = series.poster_path,
        titre = series.name,
        date = series.first_air_date,
        navController = navController,
    )
}