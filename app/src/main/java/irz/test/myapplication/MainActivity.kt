package irz.test.myapplication


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel: MainViewModel by viewModels()

        setContent {
            val navController: NavHostController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            var text by remember { mutableStateOf("") }
            var active by remember { mutableStateOf(false) }

            Scaffold(
                topBar = {
                    if (currentDestination?.route != "Profil")
                        SearchBar(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "icone search"
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    modifier = Modifier.clickable {
                                        if (currentDestination?.route == "Films") {
                                            viewmodel.get_films_tendance()
                                        } else if (currentDestination?.route == "Acteurs") {
                                            viewmodel.get_acteurs_tendance()
                                        } else if (currentDestination?.route == "Series") {
                                            viewmodel.get_series_tendance()
                                        }
                                        if (text.isNotEmpty()) {
                                            text = ""
                                        } else {
                                            active = false
                                        }
                                    },
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "icone fermeture"
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            query = text,
                            onQueryChange = { text = it },
                            onSearch = {
                                active = false
                                if (currentDestination?.route == "Films") {
                                    viewmodel.recherche_films(it)
                                } else if (currentDestination?.route == "Acteurs") {
                                    viewmodel.recherche_acteurs(it)
                                } else if (currentDestination?.route == "Series") {
                                    viewmodel.recherche_series(it)
                                }
                            },
                            active = active,
                            onActiveChange = { active = it },
                            placeholder = {
                                Text(text = "Chercher")
                            }
                        ) {
                        }
                },
                bottomBar = {
                    if (currentDestination?.route != "Profil") {
                        BottomNavigation(contentColor = MaterialTheme.colorScheme.primary) {
                            // item profil
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Face,
                                        contentDescription = "icone profil",
                                        modifier = Modifier.size(35.dp),
                                        tint = Color.Black,
                                    )
                                },
                                label = { Text("Profil") },
                                selected = false,
                                onClick = {
                                    navController.navigate("Profil")
                                }
                            )
                            // item Films
                            BottomNavigationItem(
                                icon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.films),
                                        contentDescription = "logo Films",
                                        modifier = Modifier.size(35.dp)
                                    )
                                },
                                label = { Text("Films") },
                                selected = false,
                                onClick = {
                                    navController.navigate("Films")
                                }
                            )
                            // item Series
                            BottomNavigationItem(
                                icon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.series),
                                        contentDescription = "logo series",
                                        modifier = Modifier.size(35.dp)
                                    )
                                },
                                label = { Text("Series") },
                                selected = false,
                                onClick = {
                                    navController.navigate("Series")
                                }
                            )
                            // item Actors
                            BottomNavigationItem(
                                icon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.actor),
                                        contentDescription = "logo acteurs",
                                        modifier = Modifier.size(35.dp)
                                    )
                                },
                                label = { Text("Acteurs") },
                                selected = currentDestination?.route == "Acteurs",
                                onClick = {
                                    navController.navigate("Acteurs")
                                }
                            )
                        }
                    }
                }
            ) { paddingValues ->
                NavHost(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues),
                    navController = navController,
                    startDestination = "Profil"
                ) {
                    composable("Profil") { Screen(navController) }
                    composable("Films") { Films(navController, viewmodel) }
                    composable("Series") { Series(navController, viewmodel) }
                    composable("Acteurs") { Acteurs(navController, viewmodel) }

                    composable(
                        "DetailFilm/{filmId}",
                        arguments = listOf(navArgument("filmId") { type = NavType.IntType })) {
                        backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("filmId")
                        id?.let {
                            DetailFilm(
                                navController,
                                viewmodel,
                                id
                            )
                        }
                    }
                }
            }
        }
    }
}


