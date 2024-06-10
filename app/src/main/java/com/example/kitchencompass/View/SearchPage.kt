package com.example.kitchencompass.View


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.kitchencompass.Model.MealX
import com.example.kitchencompass.ViewModel.SearchViewModel

@Composable
fun SearchPageView(navigateToSecondPage:() -> Unit) {
    val searchViewModel:SearchViewModel = viewModel()
    val searchFoodData = searchViewModel.searchFoodData.observeAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        searchFoodData.value?.let { SearchedFood(meals = it) }
    }

}
@Composable
fun SearchedFood(meals:List<MealX>){
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)) {
        LazyColumn (modifier = Modifier.fillMaxSize()){
            items(meals){
                meals ->
                MealDetails(meal = meals)
            }

        }
    }
}
@Composable
fun MealDetails(meal: MealX) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Meal image
        Image(
            painter = rememberAsyncImagePainter(model = meal.strMealThumb,),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Meal name
        Text(
            text = meal.strMeal,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Category and Area
        Text(
            text = "${meal.strCategory} - ${meal.strArea}",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Instructions
        Text(
            text = "Instructions:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = meal.strInstructions,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ingredients and Measures
        Text(
            text = "Ingredients:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        meal.let {
            IngredientRow(it.strIngredient1, it.strMeasure1)
            IngredientRow(it.strIngredient2, it.strMeasure2)
            IngredientRow(it.strIngredient3, it.strMeasure3)
            IngredientRow(it.strIngredient4, it.strMeasure4)
            IngredientRow(it.strIngredient5, it.strMeasure5)
            IngredientRow(it.strIngredient6, it.strMeasure6)
            IngredientRow(it.strIngredient7, it.strMeasure7)
            IngredientRow(it.strIngredient8, it.strMeasure8)
            IngredientRow(it.strIngredient9, it.strMeasure9)
            IngredientRow(it.strIngredient10, it.strMeasure10)
            // Add more ingredients and measures as needed
        }
    }
}

@Composable
fun IngredientRow(ingredient: String?, measure: String?) {
    if (!ingredient.isNullOrBlank() && !measure.isNullOrBlank()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(
                text = ingredient,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = measure,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}