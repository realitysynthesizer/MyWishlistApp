package com.example.mywishlistapp

import androidx.compose.foundation.layout.heightIn
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {},
){
    val navigationIcon:(@Composable () -> Unit)? =
        if (!title.contains("Wishlist")) {
            @Composable{
                IconButton(onClick = onBackNavClicked) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack,null, tint = Color.White)
                }
            }
        }else null

    TopAppBar(title={ Text(title,
                            color = colorResource((R.color.white)),
                            modifier = Modifier
                                .heightIn(max = 24.dp)
                            ) },
              navigationIcon = navigationIcon,
        backgroundColor = colorResource(R.color.app_bar_color),
        elevation = 10.dp
    )

}