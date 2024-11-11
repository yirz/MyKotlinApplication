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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import irz.test.myapplication.Carte
import irz.test.myapplication.Cast
import irz.test.myapplication.MainViewModel

@Composable
fun DetailActeur(navController: NavController, viewModel: MainViewModel, id: Int) {

    val acteurDetail by viewModel.actor.collectAsState()

    LaunchedEffect(id) { viewModel.acteur_detail(id) }

    acteurDetail?.let { acteur ->
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
                                    text = acteur.name,
                                    style = MaterialTheme.typography.headlineLarge,
                                    modifier = Modifier
                                        .padding(10.dp)
                                )

                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500${acteur.profile_path}",
                                    contentDescription = acteur.name
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
                                        text = acteur.birthday,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(top = 10.dp, start = 2.dp)
                                    )
                                }
                            }
                        }
                        Row {
                            Text(
                                text = "Biographie",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }
                        Row {

                            Text(

                                text = acteur.biography,

                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .padding(10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

