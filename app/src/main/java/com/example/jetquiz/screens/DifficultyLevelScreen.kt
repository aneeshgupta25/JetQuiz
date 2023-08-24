package com.example.jetquiz.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetquiz.R
import com.example.jetquiz.components.NextButton
import com.example.jetquiz.util.AppColors
import com.example.jetquiz.util.ScreenConfig

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChoseDifficultyLevel() {

    var selectedLevel by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Chose Difficulty",
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
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(corner = CornerSize(20.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxHeight()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        DifficultyLevelCard(
                            level = 1,
                            headLine = "Play Newbie",
                            tagLine = "Dive into Learning, the Easy Way!",
                            unselectedImage = R.drawable.easy_level_purple,
                            selectedImage = R.drawable.easy_level_pink,
                            selectedLevel = selectedLevel
                        ) { selectedLevel = it }
                        DifficultyLevelCard(
                            level = 2,
                            headLine = "Continuing",
                            tagLine = "Challenge Your Mind at Every Turn!",
                            unselectedImage = R.drawable.medium_level_purple,
                            selectedImage = R.drawable.medium_level_pink,
                            selectedLevel = selectedLevel
                        ) { selectedLevel = it }
                        DifficultyLevelCard(
                            level = 3,
                            headLine = "Experienced",
                            tagLine = "For the Fearless Seekers of Wisdom!",
                            unselectedImage = R.drawable.hard_level_purple,
                            selectedImage = R.drawable.hard_level_pink,
                            selectedLevel = selectedLevel
                        ) { selectedLevel = it }
                    }
                    NextButton()
                }
            }
        }
    }
}

@Composable
fun DifficultyLevelCard(
    level: Int,
    headLine: String,
    tagLine: String,
    unselectedImage: Int = R.drawable.easy_level_purple,
    selectedImage: Int = R.drawable.easy_level_purple,
    selectedLevel: Int,
    onClick: (Int) -> Unit = {}
) {

    val screenHeight = ScreenConfig.getHeight()

    ConstraintLayout {
        val (outerCard, innerCard, image) = createRefs()

        Card(
            modifier = Modifier.constrainAs(outerCard) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            colors = CardDefaults.cardColors(Color.Transparent)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 4),
                color = Color.Transparent
            ) {  }
        }

        Card(
            modifier = Modifier.constrainAs(innerCard) {
                top.linkTo(outerCard.top, margin = 60.dp)
                start.linkTo(outerCard.start)
                end.linkTo(outerCard.end)
                bottom.linkTo(outerCard.bottom)

                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }.noRippleClickable {
                onClick.invoke(level)
            },
            colors = CardDefaults.cardColors(
                if(selectedLevel == level)
                AppColors.LightPink
                else
                AppColors.VeryLightPurple
            )
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(text = "level $level",
                    style = MaterialTheme.typography.labelLarge)
                Text(text = headLine,
                    style = MaterialTheme.typography.headlineLarge)
                Text(text = tagLine,
                    style = MaterialTheme.typography.titleMedium)
            }
        }

        Surface(
            modifier = Modifier.constrainAs(image) {
                top.linkTo(innerCard.top, margin = (-60).dp)
                bottom.linkTo(innerCard.top, margin = (-60).dp)
                end.linkTo(innerCard.end, margin = 10.dp)

                width = Dimension.value(120.dp)
                height = Dimension.fillToConstraints
            },
            color = Color.Transparent
        ) {
            Image(painter = painterResource(
                id = if(selectedLevel == level) selectedImage else unselectedImage
            ),
                modifier = Modifier.noRippleClickable {onClick.invoke(level)},
                contentDescription = "",
                contentScale = ContentScale.FillBounds)
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}