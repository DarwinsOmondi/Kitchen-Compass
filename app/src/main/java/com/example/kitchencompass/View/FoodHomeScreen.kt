package com.example.kitchencompass.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
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
import coil.compose.AsyncImage
import com.example.kitchencompass.Model.Category
import com.example.kitchencompass.Model.Meal
import com.example.kitchencompass.ViewModel.FoodViewModel
import com.example.kitchencompass.ViewModel.SearchViewModel


@Composable
fun FoodHomePage(navigateToSecondScreen: () -> Unit) {
    val foodViewModel: FoodViewModel = viewModel()
    val searchViewModel: SearchViewModel = viewModel()
    val searchFoodData = searchViewModel.searchFoodData.observeAsState()

    val foodList = foodViewModel.foodData.observeAsState()
    val randomFoodList = foodViewModel.randomFoodData.observeAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        if (foodList.value != null && randomFoodList.value != null || searchFoodData.value != null) {
            FoodDetails(foods = foodList.value!!, meals = randomFoodList.value!!, navigateToSecondScreen)
        } else {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Loading...", style = TextStyle(color = Color.Gray, fontSize = 16.sp))
            }
        }
    }
}

@Composable
fun FoodDetails(foods: List<Category>, meals: List<Meal>, navigateToSecondScreen: () -> Unit) {
    var searchedName by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "KITCHEN COMPASS",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(
                color = Color.Cyan,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)){
            OutlinedTextField(value = searchedName, onValueChange = {
            })
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Food Categories",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            items(foods) { food ->
                FoodView(food = food,navigateToSecondScreen)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Experience something new",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(meals) { meal ->
                RandomFoodView(meal = meal)
            }
        }
    }
}

@Composable
fun FoodView(food: Category, navigateToSecondScreen: () -> Unit) {

    Card(
        modifier = Modifier
            .clickable { navigateToSecondScreen() }
            .width(200.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = food.strCategoryThumb,
                contentDescription = food.strCategory,
                modifier = Modifier
                    .fillMaxWidth()
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

@Composable
fun RandomFoodView(meal: Meal) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = meal.strCategory,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = meal.strMeal,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            AnimatedVisibility(visible = expanded) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = meal.strMeal,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = meal.strArea,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}