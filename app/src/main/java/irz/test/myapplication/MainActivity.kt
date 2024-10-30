package irz.test.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewmodel: MainViewModel by viewModels()

        setContent {
            MyApp()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold (
        bottomBar = {
            if(currentDestination?.route != "Profil") {
                BottomNavigation(contentColor = MaterialTheme.colorScheme.primary,)
                //item profil
                { BottomNavigationItem(
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

                    })
                    //item Films
                    BottomNavigationItem(
                        icon = {
                            Image(
                                painterResource(id = R.drawable.films),
                                contentDescription = "logo Films",
                                modifier = Modifier.size(35.dp) // Adjust the size here
                            )
                        },
                        label = { Text("Films") },
                        selected = false,
                        onClick = {
                            navController.navigate("Films")
                        })

                    //item Series
                    BottomNavigationItem(
                        icon = {
                            Image(
                                painterResource(id = R.drawable.series),
                                contentDescription = "logo series",
                                modifier = Modifier.size(35.dp) // Adjust the size here
                            )
                        },
                        label = { Text("Series") },
                        selected = false,
                        onClick = {
                            navController.navigate("Series")
                        })

                    //item Actors
                    BottomNavigationItem(
                        icon = {
                            Image(
                                painterResource(id = R.drawable.actor),
                                contentDescription = "logo acteurs",
                                modifier = Modifier.size(35.dp) // Adjust the size here
                            )
                        },
                        label = { Text("Acteurs") },
                        selected = currentDestination?.route == "Acteurs",
                        onClick = {
                            navController.navigate("Acteurs")
                        })
                }

            }
        }
            ){
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = "Profil"
        ) {
            composable("Profil") { Screen(navController) }
            composable("Films") { Films(navController, viewModel())}
            composable("Series") {Series(navController, viewModel())}
            composable("Acteurs") {Acteurs(navController, viewModel())}

        }

    }

}
