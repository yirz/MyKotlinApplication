package irz.test.myapplication


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import irz.test.myapplication.Carte
import irz.test.myapplication.MainViewModel

@Composable
fun DetailSerie(navController: NavController, viewModel: MainViewModel, id: Int) {

    val serieDetail by viewModel.show.collectAsState()

    LaunchedEffect(id) { viewModel.serie_detail(id) }

    serieDetail?.let { serie ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Column {
                        Row {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = serie.name,
                                    style = MaterialTheme.typography.headlineLarge,
                                    modifier = Modifier
                                        .padding(10.dp)
                                )

                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500${serie.poster_path}",
                                    contentDescription = serie.name
                                )
                            }
                        }
                        Row {
                            Column {
                                Text(
                                    text = "Details",
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier
                                        .padding(10.dp)
                                )
                                Row {
                                    Text(
                                        text = "Date : ",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(top = 10.dp, start = 10.dp, bottom = 10.dp)
                                    )
                                    Text(
                                        text = serie.first_air_date,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(top = 10.dp, start = 2.dp)
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Genre(s) : ",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(
                                                top = 10.dp,
                                                start = 10.dp,
                                                bottom = 10.dp
                                            ),
                                    )

                                    serie.genres.forEach() {
                                        Text(
                                            text = it.name + " ",
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier
                                                .padding(top = 10.dp, start = 2.dp),
                                        )
                                    }
                                }
                            }
                        }
                        Row {
                            Text(
                                text = "Synopsis",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }
                        Row {

                            Text(

                                text = serie.overview,

                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }
                        Row {
                            Text(
                                text = "Têtes d'affiche",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }
                        Column {
                            serie.credits.cast.forEach { cast ->
                                val navcontroller = navController
                                Carte(
                                    route = "DetailActeur/" + cast.id,
                                    chemin_img = cast.profile_path,
                                    titre = cast.name,
                                    date = null,
                                    navController = navController,
                                    modifier = Modifier.clickable {
                                        navcontroller.navigate("DetailActeur/" + cast.id)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

