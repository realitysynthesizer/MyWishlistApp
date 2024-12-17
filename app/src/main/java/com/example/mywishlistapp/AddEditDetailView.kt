package com.example.mywishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mywishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id : Long,
    viewModel: WishViewModel,
    navHostController: NavHostController
){
    val snackMessage= remember{ mutableStateOf("") }
    val scope = rememberCoroutineScope()

    if (id==0L){
        viewModel.wishtitlestate=""
        viewModel.wishDescriptionState=""
    }
    else {
        val wish = viewModel.getWishById(id).collectAsState(Wish())
        viewModel.wishtitlestate = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }
    //val loadedWish = viewModel.getWishById(id).collectAsState(initial = Wish())
    //val context = LocalContext.current

    Scaffold(topBar = { AppBarView(title = if (id == 0L) "Add Wish" else "Update Wish",{ navHostController.navigateUp() }) }) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize()
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "title", value = viewModel.wishtitlestate, onValueChange = {
                viewModel.onwishtitlechange(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "description", value = viewModel.wishDescriptionState, onValueChange = {
                viewModel.onwishDescriptionchange(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if (viewModel.wishtitlestate.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()) {
                    if (id==0L){
                        viewModel.addWish(Wish(title = viewModel.wishtitlestate.trim(), description = viewModel.wishDescriptionState.trim()))
                    }
                    else {
                        viewModel.updateWish(Wish(id, viewModel.wishtitlestate.trim(), viewModel.wishDescriptionState.trim()))
                    }
                }
                else {
                    snackMessage.value= "Enter details to add wish"
                }
                scope.launch {
                    navHostController.navigateUp()

                }
                }) {
                Text(text = if (id == 0L) "Add Wish" else "Update Wish", style = TextStyle(fontSize = 18.sp))
            }
        }
    }
}

@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(value, onValueChange, label = { Text(label) })
}
