package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.PictureViewModel
import com.example.myapplication.data.listOfPics
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val viewModel: PictureViewModel by viewModels()

        setContent {
                val pic=viewModel.pic.collectAsState()
            MyApplicationTheme {
                Column(modifier=Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
                    ImageContainer(res = listOfPics.list[pic.value].first)
                    TextColumns(title=listOfPics.list[pic.value].second)
                    ButtonRows(changePositive = {viewModel.changePositive()}, changeNegative = {viewModel.changeNegative()})

                }
            }
        }
    }
}

@Composable
fun ImageContainer(modifier:Modifier=Modifier, @DrawableRes res:Int){
    Surface(modifier=modifier.shadow(16.dp)) {
        Image(painter = painterResource(id = res),contentDescription ="glass",modifier=modifier.padding(16.dp))
    }

}

@Composable
fun ButtonRows(modifier:Modifier=Modifier,changePositive:()->Unit,changeNegative:()->Unit){
    Row {
        Button(onClick = changePositive,modifier=modifier.padding(24.dp)) {
            Text("previous")
        }
        Button(onClick = changeNegative,modifier=modifier.padding(24.dp)) {
            Text("next")
        }
    }
}

@Composable
fun TextColumns(modifier:Modifier=Modifier,title:Int){
    Column(modifier= modifier
        .fillMaxWidth()
        .padding(20.dp)
        .background(color = Color(192, 199, 230))
        .height(80.dp), verticalArrangement = Arrangement.SpaceAround) {
        Text(text = stringResource(id = title), fontSize = 30.sp,fontFamily= FontFamily.SansSerif,modifier=modifier.padding(start=5.dp))
        Text(text = "name field", fontStyle = FontStyle.Normal, fontFamily = FontFamily.SansSerif, fontWeight= FontWeight.Bold,modifier=modifier.padding(5.dp))
    }
}

@Preview(showBackground = true,showSystemUi=true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Column(modifier=Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
            ImageContainer(res = R.drawable.glass)
            TextColumns(title=R.string.glass)
            ButtonRows(changePositive = {}, changeNegative = {})

        }
    }
}