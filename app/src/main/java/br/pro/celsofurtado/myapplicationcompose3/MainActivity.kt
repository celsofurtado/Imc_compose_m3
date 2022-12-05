package br.pro.celsofurtado.myapplicationcompose3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.pro.celsofurtado.myapplicationcompose3.ui.components.ImcActivity
import br.pro.celsofurtado.myapplicationcompose3.ui.theme.MyApplicationCompose3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationCompose3Theme {
                // A surface container using the 'background' color from the theme
                Surface {
                    //Greeting("Android")
                    ImcActivity()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Image(
            painter = painterResource(id = R.drawable.gym2_bg),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 15.dp),
            contentScale = ContentScale.Crop,
            alpha = 0.6f,
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "IMC",
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Índice de Massa Corpórea",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
//                Spacer(modifier = Modifier.height(8.dp))
//                LinearProgressIndicator(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(16.dp),
//                    progress = 0.7f,
//                    color = Color.Green
//                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.height(256.dp),
                    painter = painterResource(id = R.drawable.bmi_512),
                    contentDescription = ""
                )
            }
            CardFooter()
            //CardGradient()
        }

    }
}

@Composable
fun CardFooter(){
    Card(
        modifier = Modifier
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(Color.Gray.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(20.dp),
        //elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .background(Color.Transparent),
//                .background(brush = Brush.linearGradient(
//                    colors = listOf(
//                        Color.White,
//                        Color.Black
//                    )
//                )),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Jetpack Compose",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "O Jetpack compose é uma nova forma de escrevermos interfaces para aplicações Android.",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Developed by Android",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun CardFooterPreview(){
    MyApplicationCompose3Theme {
        CardFooter()
    }
}

@Composable
fun CardGradient() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .width(400.dp)
                .height(250.dp)
                .padding(10.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(7.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Blue,
                                Color.White
                            )
                        )
                    )
            ){
                Text(text = "Esse é somente um teste!!")
            }
        }
    }
}

@Preview
@Composable
fun CardGradientPreview(){
    MyApplicationCompose3Theme {
        CardGradient()
    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    MyApplicationCompose3Theme {
        Greeting("Android")
    }
}