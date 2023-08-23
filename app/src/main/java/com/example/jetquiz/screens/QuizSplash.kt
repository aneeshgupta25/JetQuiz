package com.example.jetquiz.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetquiz.R
import com.example.jetquiz.util.AppColors
import com.example.jetquiz.util.ScreenConfig

@Preview
@Composable
fun QuizSplash() {

    val screenHeight = ScreenConfig.getHeight()
    val screenWidth = ScreenConfig.getWidth()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = AppColors.Purple)
    ) {
        val topRightCircleTopLine = createGuidelineFromTop(-0.1f)
        val topRightCircleEndLine = createGuidelineFromEnd(-0.5f)

        val bottomLeftCircleBottomLine = createGuidelineFromBottom(-0.1f)
        val bottomLeftCircleStartLine = createGuidelineFromStart(-0.5f)

        val upperSmallCircleTopLine = createGuidelineFromTop(0.25f)
        val upperSmallCircleStartLine = createGuidelineFromStart(0.15f)

        val lowerSmallCircleBottomLine = createGuidelineFromBottom(0.25f)
        val lowerSmallCircleEndLine = createGuidelineFromEnd(0.15f)

        val (
            topRightCircle,
            bottomLeftCircle,
            upperSmallCircle,
            lowerSmallCircle,
            appIcon,
            appText
        ) = createRefs()
        
        Surface(
            modifier = Modifier.constrainAs(topRightCircle) {
                top.linkTo(topRightCircleTopLine)
                end.linkTo(topRightCircleEndLine)

                width = Dimension.value(screenHeight/2)
                height = Dimension.value(screenHeight/2)
            },
            shape = CircleShape,
            border = BorderStroke(width = 5.dp, color = AppColors.LightPurple),
            color = Color.Transparent
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(screenWidth / 5),
                shape = CircleShape,
                color = AppColors.LightPurple
            ) {}
        }

        Surface(
            modifier = Modifier.constrainAs(bottomLeftCircle) {
                bottom.linkTo(bottomLeftCircleBottomLine)
                start.linkTo(bottomLeftCircleStartLine)

                width = Dimension.value(screenHeight/2)
                height = Dimension.value(screenHeight/2)
            },
            shape = CircleShape,
            border = BorderStroke(width = 5.dp, color = AppColors.LightPurple),
            color = Color.Transparent
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(screenWidth / 5),
                shape = CircleShape,
                color = AppColors.LightPurple
            ) {}
        }

        Surface(
            modifier = Modifier.constrainAs(upperSmallCircle) {
                top.linkTo(upperSmallCircleTopLine)
                start.linkTo(upperSmallCircleStartLine)

                height = Dimension.value(50.dp)
                width = Dimension.value(50.dp)
            },
            shape = CircleShape,
            color = AppColors.LightPurple
        ){}

        Surface(
            modifier = Modifier.constrainAs(lowerSmallCircle) {
                bottom.linkTo(lowerSmallCircleBottomLine)
                end.linkTo(lowerSmallCircleEndLine)

                height = Dimension.value(30.dp)
                width = Dimension.value(30.dp)
            },
            shape = CircleShape,
            color = AppColors.LightPurple
        ){}

        Surface(
            modifier = Modifier.constrainAs(appIcon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            color = Color.Transparent
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetquizlogo),
                contentDescription = "App Logo",
                modifier = Modifier.height(screenHeight/8),
                contentScale = ContentScale.FillHeight
            )
        }

        Surface(
            modifier = Modifier.constrainAs(appText) {
                top.linkTo(appIcon.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            color = Color.Transparent
        ) {
            Text(text = "JetQuiz",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.White)
        }


    }
}