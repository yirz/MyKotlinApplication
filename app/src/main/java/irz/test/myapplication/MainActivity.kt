package irz.test.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
                { BottomNavigationItem(
                    icon = {/**
                    Image(
                    painterResource(id = R.drawable.clap_bis),
                    contentDescription = "logo films",
                    )*/
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "icone search"
                        )
                    },
                    label = { Text("Films") },
                    selected = false,
                    onClick = {
                        navController.navigate("FilmsComposant")
                    })}





            }
        }
            ){
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = "Profil"
        ) {
            composable("Profil") { Screen(navController) }
            composable("Films") { Films(navController, viewModel()) }
        }

    }

}
