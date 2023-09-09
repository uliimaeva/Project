package tat.neft.plugins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tat.neft.R

class LemonApp: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            Lemon()
        }
    }
}

@Composable
fun Lemon(modifier: Modifier = Modifier) {

    var result  by remember {
        mutableStateOf(1)
    }
    var item  by remember {
        mutableStateOf(1)
    }

    var click = 0

    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val text = when (result) {
        1 -> "Нажми чтобы выбрать лимон"
        2 -> "Нажимай пока не выжмешь сок"
        3 -> "Нажми чтобы выпить сок"
        else -> "Нажми чтобы сделать сок еще раз"
    }


    Surface(modifier = Modifier.fillMaxSize(), color = Color.White){
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                if (result == 4){
                    result = 1
                    item = (2..4).random()
                }
                else {
                    if (result == 2){
                        if (item == click) { result ++}
                        else {click++}
                    }
                    else {result ++}
                }

            }, Modifier.size( 300.dp, 300.dp)
            ) {
                Image(painter = painterResource(id = imageResource), contentDescription = result.toString())
            }
            Text(text = text, Modifier.padding(15.dp))
        }
    }
}


@Preview
@Composable
private fun AppPreview(){
    Lemon()
}