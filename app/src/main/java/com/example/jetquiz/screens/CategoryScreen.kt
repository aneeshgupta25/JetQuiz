package com.example.jetquiz.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetquiz.components.NextButton
import com.example.jetquiz.data.CategoryList
import com.example.jetquiz.model.Category
import com.example.jetquiz.util.AppColors
import com.example.jetquiz.util.ScreenConfig

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChoseCategory(
    categoryList: List<Category> = CategoryList.getCategories()
) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Chose Category",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center)
            },
                modifier = Modifier.padding(horizontal = 10.dp),
                navigationIcon = {
                    Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Navigate back",
                        tint = Color.White)
                },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(AppColors.Purple))
        },
        containerColor = AppColors.Purple
    ) {
        Box(modifier = Modifier.padding(it)) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp),
                shape = RoundedCornerShape(corner = CornerSize(25.dp)),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.weight(1f, fill = false),
                        shape = RoundedCornerShape(corner = CornerSize(25.dp)),
                        colors = CardDefaults.cardColors(Color.Transparent)
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(categoryList) { category->
                                CategoryCard(icon = category.icon, text = category.category)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    NextButton()
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    icon: ImageVector,
    text: String
) {
    Card(
        modifier = Modifier.height(120.dp),
        shape = RoundedCornerShape(corner = CornerSize(25.dp)),
        colors = CardDefaults.cardColors(AppColors.VeryLightPurple),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(corner = CornerSize(20.dp)),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Icon(imageVector = icon, contentDescription = "icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    tint = AppColors.Purple)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = text, textAlign = TextAlign.Center,
                color = AppColors.Purple,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium)
        }
    }
}