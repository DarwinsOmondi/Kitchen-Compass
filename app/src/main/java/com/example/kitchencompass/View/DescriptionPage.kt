package com.example.kitchencompass.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.kitchencompass.Model.Category
import com.example.kitchencompass.ViewModel.FoodViewModel

@Composable
fun Description(navigateToFirstScreen: () -> Unit) {
    val viewModel: FoodViewModel = viewModel()
    val categories = viewModel.foodData.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        IconButton(onClick =  navigateToFirstScreen ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back",)
        }

        Spacer(modifier = Modifier.height(16.dp))

        categories.value?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                for (category in it) {
                    DescriptionView(category = category)
                }
            }
        }
    }
}

@Composable
fun DescriptionView(category: Category) {
    var expanded by remember {
        mutableStateOf(true)
    }
    Card(
        modifier = Modifier
            .clickable {
                expanded = !expanded
            }
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = category.strCategoryThumb
                ),
                contentDescription = category.strCategory,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.77f)  // Maintain aspect ratio of 16:9
            )
            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(visible = expanded) {
                Column {
                    Text(
                        text = category.strCategory,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = category.strCategoryDescription,
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}
