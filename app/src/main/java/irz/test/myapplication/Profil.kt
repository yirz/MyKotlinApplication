package irz.test.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Screen(
    navcontroller : NavController
) {
    Column(

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        PhotoPro()
        Spacer(modifier = Modifier.size(20.dp))
        Presentation()
        Spacer(modifier = Modifier.size(40.dp))
        LiensMail()
        Spacer(modifier = Modifier.size(5.dp))
        LiensLinkedin()
        Spacer(modifier = Modifier.size(150.dp))

        Button(onClick = {navcontroller.navigate("Films")}) {
            Text(text = "demarrer")
        }
    }
}

@Composable
fun PhotoPro(){
    Image(
        painterResource(id = R.drawable.pfp),
        contentDescription = "photo de profil",
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Presentation() {
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text(text="YANN IRZ")
        Text(text= "Eleve en école d'ingénieur 4e année")
        Text(text= "Ecole d'ingénieur ISIS - INU Champolion")
    }
}

@Composable
fun LiensMail() {
    Row(){
        Image(painterResource(id = R.drawable.gmail) ,
            modifier = Modifier.size(20.dp),
            contentDescription = "logo gmail",
        )
        Text(text = "  yann.irz@gmail.com")
    }
}

@Composable
fun  LiensLinkedin(){
    Row() {
        Image(painterResource(id = R.drawable.linkedin) ,
            modifier = Modifier.size(20.dp),
            contentDescription = "logo linkedin",
        )
        Text(text=" linesverslinkedin.com")
    }
}

