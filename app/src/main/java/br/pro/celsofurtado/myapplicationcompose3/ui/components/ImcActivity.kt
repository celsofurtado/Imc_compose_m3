package br.pro.celsofurtado.myapplicationcompose3.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.pro.celsofurtado.myapplicationcompose3.R
import br.pro.celsofurtado.myapplicationcompose3.ui.theme.MyApplicationCompose3Theme

import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import kotlin.math.pow

@Composable
fun ImcActivity() {
    var imc by rememberSaveable { mutableStateOf("0.00") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.gym_bg2),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 12.dp),
            contentScale = ContentScale.Crop,
            alpha = 0.2f
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CardHeader()
            FormImc()
            ResultCard(imc)
        }

    }
}

@Composable
fun CardHeader() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 32.dp,
                    bottomStart = 32.dp
                )
            )
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bmi_512),
                contentDescription = "",
                modifier = Modifier.size(72.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Calculadora IMC",
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                text = "O índice de massa corporal - IMC - é uma medida internacional usada para calcular se uma pessoa está no peso ideal."
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormImc() {

    var peso by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    val errorMessage = "Valor incorreto!"


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Preencha os seus dados:",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        if (isError) error(errorMessage)
                    },
                value = peso,
                onValueChange = {
                    peso = it
                    isError = validate(peso)
                },
                label = {
                    Text(if (isError) "Qual o seu peso?" else "Qual o seu peso?*")
                },
                supportingText = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Limit: ${peso.length}/3",
                        textAlign = TextAlign.End,
                        color = Color.White
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.ic_balance_24), contentDescription = "")
                },
                singleLine = true,
                isError = isError,
                keyboardActions = KeyboardActions { validate(peso) },
                textStyle = TextStyle(fontSize = 32.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { error("Ocorreu um erro") },
                value = altura,
                onValueChange = { altura = it },
                label = {
                    Text(text = "Qual a sua altura?")
                },
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.ic_height_24), contentDescription = "")
                },
                singleLine = true,
                isError = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                textStyle = TextStyle(fontSize = 32.sp)
            )
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    var imc = calculaImc(peso.toDouble(), altura.toDouble())
                    Log.i("xpto", imc.toString())
                },
            ) {
                Text(
                    text = "Calcular IMC",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun ResultCard(imc: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Seus resultados",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = imc,
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
            Text(
                text = "Parabéns! Seu peso é ideal.",
                fontSize = 20.sp,
            )
        }
    }
}

@Preview
@Composable
fun ImcActivityPreview() {
    MyApplicationCompose3Theme {
        ImcActivity()
    }
}

fun validate(text: String): Boolean {
    return text.length in 1..3
}

fun calculaImc(peso: Double, altura: Double) = peso / altura.pow(2)