package com.example.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

    //Remember garde les valeur en memoire ce qui fait que retourner sur la fonction reprend la valeur en memoire
    var nbPafs by remember { mutableIntStateOf(0) }
    var nbFlops by remember { mutableIntStateOf(0) }

    //column Principal de l'app
    Column(
        modifier= modifier.fillMaxSize()
    ) {
        AffichageScores(nbPafs=nbPafs,nbFlops=nbFlops ,modifier = Modifier.fillMaxWidth().weight(1f))

        TitreApplication(modifier = Modifier.fillMaxWidth().weight(1f))
        //ici on utilise la reference de nbPafs de ecranPrincipal pour le faire bouger vers grilleTuile
        GrilleTuiles(incrementePafs ={nbPafs++}, incrementeFlops = {nbFlops++} ,modifier= Modifier.fillMaxSize().weight(5f))


    }
}

@Composable
private fun TitreApplication(modifier:Modifier=Modifier) {

    Box(
        modifier = modifier,contentAlignment = Alignment.Center
    ){
    Text(
        text = "tape le lapin",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )}
}

@Composable
private fun AffichageScores(nbPafs: Int, nbFlops: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    )
    {
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "$nbPafs pafs",
                color=Color.Green,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
                )
        }
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentAlignment = Alignment.Center,
            ){
            Text(text = "$nbFlops flops",
                    color=Color.Red,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )}

        }
    }


@Composable
//les parametre ici sont comme des mini fonction qui ne recoive aucun parametre et renvoie rien,il sont seulement des valeur appeler
private fun GrilleTuiles(incrementePafs:()-> Unit, incrementeFlops:()-> Unit, modifier: Modifier) {
    var positionLapin by remember { mutableIntStateOf(Random.nextInt(9)) }
    Column(modifier = modifier) {
        for (i in 0..2)
            Row(
                modifier =  Modifier.fillMaxSize().weight(1f)
            ) {
                for (j in 0..2) {
                    var indexTuile=i*3+j
                    Tuile(positionLapin == indexTuile,
                       quandOnCliqueSurBouton ={estLapin -> effetCliqueTuile(estLapin =estLapin ,incrementePafs=incrementePafs, incrementeFlops = incrementeFlops, reInitPositionLapin ={positionLapin=Random.nextInt(9)} ) },
                        modifier = Modifier.padding(6.dp).fillMaxWidth().weight(1f))
                }
            }
    }
}

@Composable
private fun Tuile(estLapin : Boolean, quandOnCliqueSurBouton: (Boolean)-> Unit, modifier: Modifier) {

    Button(
        onClick = {
quandOnCliqueSurBouton(estLapin)

        },
        modifier = modifier
    ) {


        Text(
            text = if(estLapin) "lapin" else "taupe",
            fontSize = 30.sp
        )
    }
}

fun effetCliqueTuile(
    estLapin: Boolean,
    incrementePafs:()-> Unit,
    incrementeFlops:()-> Unit,
    reInitPositionLapin: () -> Unit
){if(estLapin){
    incrementePafs()
    reInitPositionLapin()
}
else{incrementeFlops()}
}

