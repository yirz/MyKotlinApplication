package irz.test.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ComposanExam(
    navcontroller: NavController,
    viewModel: MainViewModel,
) {
    val collections by viewModel.collections.collectAsState()
    LaunchedEffect(true) {
        viewModel.get_acteurs_tendance()
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(collections) { collection ->
                CarteCollection(collection, navcontroller, modifier = Modifier.clickable {

                })
            }
        }
    }
}

@Composable
fun CarteCollection(collection: collection, navController: NavController, modifier: Modifier) {
    Carte(
        route = null.toString(),
        chemin_img = collection.poster_path,
        titre = collection.name,
        date = null,
        navController = navController,
        modifier = modifier
    )
}