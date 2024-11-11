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
fun Acteurs(
    navcontroller: NavController,
    viewModel: MainViewModel,
) {
    val acteurs by viewModel.actors.collectAsState()
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
            items(acteurs) { acteur ->
                CarteActeur(acteur, navcontroller, modifier = Modifier.clickable {

                    navcontroller.navigate("DetailActeur/" + acteur.id)
                })
            }
        }
    }
}

@Composable
fun CarteActeur(acteur: Acteur, navController: NavController, modifier: Modifier) {
    Carte(
        route = "DetailActeur/" + acteur.id,
        chemin_img = acteur.profile_path,
        titre = acteur.name,
        date = null,
        navController = navController,
        modifier = modifier
    )
}