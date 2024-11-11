package irz.test.myapplication

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import coil.compose.AsyncImage

import androidx.compose.foundation.layout.fillMaxSize
import irz.test.myapplication.Carte
import irz.test.myapplication.Cast
import irz.test.myapplication.MainViewModel


@Composable
fun DetailFilm(navController: NavController, viewModel: MainViewModel, id: Int) {

    val filmDetail by viewModel.movie.collectAsState()

    LaunchedEffect(id) { viewModel.film_detail(id) }

    filmDetail?.let { film ->
        val filmid = film.title
        Log.w("idFilm", "ID le film : $filmid")
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 5.dp),
                    columns = GridCells.Fixed(2)
                ) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Column() {
                            Row() {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = film.title,
                                        style = MaterialTheme.typography.headlineLarge,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )

                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w500${film.poster_path}",
                                        contentDescription = film.title
                                    )
                                }
                            }
                            Row() {
                                Column() {
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
                                            text = film.release_date,
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

                                        film.genres.forEach() {
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
                            Row() {
                                Text(
                                    text = "Synopsis",
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier
                                        .padding(10.dp)
                                )
                            }
                            Row() {

                                Text(

                                    text = film.overview,

                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier
                                        .padding(10.dp)
                                )
                            }
                            Row() {
                                Text(
                                    text = "Têtes d'affiche",
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier
                                        .padding(10.dp)
                                )
                            }
                        }
                    }
                    items(film.credits.cast) { cast ->
                        Text(text = cast.name)
                        Carte(
                            route = "DetailActeur/" + cast.id,
                            chemin_img = cast.profile_path,
                            titre = cast.name,
                            date = null,
                            navController = navController,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}

