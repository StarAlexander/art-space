@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                var isSearch by remember {
                    mutableStateOf(false)
                }
            MyApplicationTheme {
                Box(modifier = Modifier.fillMaxSize()){

                    Column(modifier=Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(40.dp)) {
                        ImageContainer(res = listOfPics.list[pic.value].id)
                        TextColumns(title=listOfPics.list[pic.value].title,author=listOfPics.list[pic.value].author)
                        ButtonRows(changePositive = {viewModel.changePositive()}, changeNegative = {viewModel.changeNegative()})

                    }
                    FloatingActionButton(onClick = { isSearch=true },modifier= Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 16.dp, bottom = 16.dp)) {
                        Icon(Icons.Default.Search, contentDescription =null )
                    }
                    if (isSearch){
                        Surface(modifier=Modifier.fillMaxSize(), color = Color(0f,0f,0f,0.3f)) {

                        }

                        SearchBar(onClick={isSearch=false},viewModel=viewModel)


                    }
                }

            }
        }
    }
}

@Composable
fun ImageContainer(modifier:Modifier=Modifier, @DrawableRes res:Int){
    Surface(modifier=modifier.shadow(16.dp)) {
        Image(painter = painterResource(id = res),contentDescription ="glass",modifier= modifier
            .padding(16.dp)
            .height(300.dp))
    }

}

@Composable
fun ButtonRows(modifier:Modifier=Modifier,changePositive:()->Unit,changeNegative:()->Unit){
    Row {
        Button(onClick = changeNegative,modifier=modifier.padding(24.dp)) {
            Text("previous")
        }
        Button(onClick = changePositive,modifier=modifier.padding(24.dp)) {
            Text("next")
        }
    }
}

@Composable
fun TextColumns(modifier:Modifier=Modifier,title:Int,author:Int){
    Column(modifier= modifier
        .fillMaxWidth()
        .padding(20.dp)
        .background(color = Color(192, 199, 230))
        .height(80.dp), verticalArrangement = Arrangement.SpaceAround) {
        Text(text = stringResource(id = title), fontSize = 30.sp,fontFamily= FontFamily.SansSerif,modifier=modifier.padding(start=5.dp))
        Text(text = stringResource(id = author), fontStyle = FontStyle.Normal, fontFamily = FontFamily.SansSerif, fontWeight= FontWeight.Bold,modifier=modifier.padding(5.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier:Modifier=Modifier,
              onClick:()->Unit,
              viewModel:PictureViewModel){
    var input by remember {
        mutableStateOf("")
    }
    Column {


        OutlinedTextField(leadingIcon = {
            Icon(Icons.Default.Search,
                contentDescription =null )
        },
            value = input,
            onValueChange ={input=it},
            label = {
                Text(text = "Search art")
                    },
            modifier= Modifier
            .background(Color(255, 255, 255))
            .fillMaxWidth(),
            trailingIcon = {

                    Icon( Icons.Default.Close, contentDescription =null,modifier=modifier.clickable(onClick=onClick) )


            })
        LazyColumn{
            items(listOfPics.list){
                val text= stringResource(id = it.title)
                if (text.lowercase().startsWith(input.lowercase()) && input!="") {
                    Row(modifier= Modifier
                        .background(Color(255, 255, 255))
                        .fillMaxWidth()
                        .clickable {
                            val ind=listOfPics.list.indexOf(it)
                            viewModel.setPicId(ind)
                            onClick()

                        }) {

                        Text(text = text,
                            modifier=Modifier.padding(8.dp))
                    }
                }

            }

        }
    }
}

@Preview(showBackground = true,showSystemUi=true)
@Composable
fun GreetingPreview() {

    MyApplicationTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier=Modifier.padding(42.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(40.dp)) {
                ImageContainer(res = R.drawable.the_scream)
                TextColumns(title=R.string.scream, author = R.string.scream_author)
                ButtonRows(changePositive = {}, changeNegative = {},modifier=Modifier.padding(bottom=32.dp))

            }
            FloatingActionButton(onClick = { /*TODO*/ },modifier= Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)) {
                Icon(Icons.Default.Search, contentDescription =null )
            }

            //    Surface(modifier=Modifier.fillMaxSize(), color = Color(0f,0f,0f,0.3f)) {

               // }
             //   SearchBar(onClick={})








        }

    }
}