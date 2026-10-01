package com.example.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.ui.theme.DemoTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()//methode pour affichage standard de l'application
        setContent {//contient tout les elements graphique de notre application
            DemoTheme {
                //scalfold : permet de hierarchiser les differents composants de l'interface graphique de l'application
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                topBar= {
                        TopAppBar(
                    title={Text(text="Demo Test")},
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer)


                        ) }
                )
                { innerPadding ->
                    EcranPrincipal(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun EcranPrincipal(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier= modifier.fillMaxSize()
    ) {
        AffichageScore(modifier = Modifier.fillMaxWidth()
        )
        TitreApplication()
        GrilleTuiles(modifier=modifier)


    }
}

@Composable
private fun TitreApplication() {
    Text(
        text = "tape le lapin"
    )
}

@Composable
private fun AffichageScore(modifier: Modifier= Modifier) {
    Row(modifier = Modifier) {
        Text(text = "0 pafs")
        Text(text = "0 floops")

    }
}

@Composable
private fun GrilleTuiles(modifier: Modifier) {
    var positionLapin= Random.nextInt(9)
    Column(modifier = modifier) {
        for (i in 0..2)
            Row() {
                modifier
                for (j in 0..2) {
                    var indexTuile=i*3+j
                    Tuile(positionLapin == indexTuile, modifier = Modifier.padding(6.dp))
                }
            }
    }
}

@Composable
private fun Tuile(estLapin : Boolean,modifier: Modifier) {

    Button(
        onClick = {},
        modifier = modifier
    ) {


        Text(
            text = if(estLapin) "lapin" else "taupe",
            fontSize = 25.sp
        )
    }
}

