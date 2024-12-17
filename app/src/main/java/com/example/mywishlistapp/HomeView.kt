package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DismissDirection
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FloatingActionButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FractionalThreshold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mywishlistapp.data.Wish

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(navHostController: NavHostController, viewModel: WishViewModel){
    val context = LocalContext.current
    Scaffold(topBar = { AppBarView("Wishlist", onBackNavClicked = { Toast.makeText(context, "Back Pressed", Toast.LENGTH_SHORT).show() })},
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navHostController.navigate(route =Screen.addScreen.route + "/0L")
                /*will add navigation to add wish screen*/},
                    modifier = Modifier.padding(20.dp), contentColor = Color.White, backgroundColor = Color.Black) {
                    Icon(Icons.Default.Add, null)
                }
            }
    ){ paddingValues ->
        val allWishes = viewModel.getAllWishes.collectAsState(initial = emptyList())
        LazyColumn(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            items(allWishes.value, key= {it.id} ){ wish ->

                val dismissState = rememberDismissState(confirmStateChange = {
                    if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart){
                        viewModel.deleteWish(wish)
                    }
                    true
                })

                SwipeToDismiss(directions = setOf(DismissDirection.EndToStart), state = dismissState, background = {
                    val color by animateColorAsState(
                        if (dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red
                        else Color.Transparent
                    )
                    Card(modifier = Modifier.fillMaxSize().padding(start = 8.dp,top = 8.dp, end =  8.dp),
                        elevation = 10.dp,
                        backgroundColor = color) {
                        Box(modifier = Modifier.fillMaxSize(), Alignment.CenterEnd){
                            Icon(Icons.Default.Delete , contentDescription = null, tint = Color.White, modifier =  Modifier.padding(end = 16.dp))

                        }
                    }
                },
                    dismissThresholds = {FractionalThreshold(0.50F)}
                    ) {
                    WishItem(wish) {
                        val id = wish.id
                        navHostController.navigate(Screen.addScreen.route + "/$id")

                    }
                }

            }

        }

    }

}

@Composable
fun WishItem(wish : Wish, onclick: () -> Unit){
    Card(modifier = Modifier.fillMaxWidth().padding(start = 8.dp,top = 8.dp, end =  8.dp).clickable { onclick() },
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colorScheme.surfaceContainer) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(wish.title, fontWeight = FontWeight.ExtraBold)
            Text(wish.description)
        }
    }
}