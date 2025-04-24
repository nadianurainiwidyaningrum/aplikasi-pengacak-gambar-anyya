package com.example.pengacakgambarbyayya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pengacakgambarbyayya.ui.theme.PengacakGambarByAyyaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PengacakGambarByAyyaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PengacakGambarByAyya(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PengacakGambarByAyya(modifier: Modifier = Modifier) {

    var result by remember { mutableStateOf(1) }

    val imageResource = when (result) {
        1 -> R.drawable.anyyaaa
        2 -> R.drawable.resource
        3 -> R.drawable.angry_anya
        4 -> R.drawable.anya_forger
        5 -> R.drawable.anya_sticker
        6 -> R.drawable._____ryyourbae_____anya_forger_icon
        7 -> R.drawable._anya_forger_icon_
        8 -> R.drawable.anya_and_damian_matching_pfp
        9 -> R.drawable.anya_forger_icon
        10 -> R.drawable.anya_forger_icons_
        else -> R.drawable.anya_reaction
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Hallo, pilih Anya yang kamu suka",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { result = (1..10).random() }) {
            Text(text = stringResource(id = R.string.acak))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AcakGambarPreview() {
    PengacakGambarByAyyaTheme {
        PengacakGambarByAyya()
    }
}
