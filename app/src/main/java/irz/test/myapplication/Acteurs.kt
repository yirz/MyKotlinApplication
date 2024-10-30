package irz.test.myapplication

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun Acteurs(
    navcontroller : NavController,
    viewModel: MainViewModel,
){
    val actors by viewModel.actors.collectAsState()
    LaunchedEffect(true)  {
        viewModel.get_acteurs_tendance()
    }
    actors.forEach{ acteur  ->  Text(text = acteur.name) }
}