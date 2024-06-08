package com.example.kitchencompass


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage




@Composable
fun FoodHomePage() {
    val foodViewModel: FoodViewModel = viewModel()
    val foodList = foodViewModel.foodData.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (foodList.value == null) {

        } else {
            FoodDetails(foods = foodList.value!!)
        }
    }
}

@Composable
fun FoodDetails(foods: List<Category>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "KITCHEN COMPASS", modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle
                (
                color = Color.Red,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold    
                        ))
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            items(foods) { food ->
                FoodView(food = food)
            }
        }
    }
}

@Composable
fun FoodView(food: Category) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = food.strCategoryThumb,
                contentDescription = food.strCategory,
                modifier = Modifier.fillMaxSize()
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = food.strCategory,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

