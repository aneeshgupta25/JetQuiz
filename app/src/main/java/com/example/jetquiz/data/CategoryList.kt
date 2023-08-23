package com.example.jetquiz.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Theaters
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material.icons.rounded.AccountCircle
import com.example.jetquiz.model.Category

object CategoryList {
    fun getCategories(): List<Category> {
        return listOf(
            Category(null, "Any Category", Icons.Default.SentimentSatisfied),
            Category(9, "General Knowledge", Icons.Default.School),
            Category(10, "Books", Icons.Default.AutoStories),
            Category(11, "Films", Icons.Default.Movie),
            Category(12, "Music", Icons.Default.MusicNote),
            Category(13, "Musical & Theaters", Icons.Default.Theaters),
            Category(14, "Television", Icons.Default.Tv),
            Category(15, "Video Games", Icons.Default.VideogameAsset),
            Category(17, "Science & Nature", Icons.Default.NaturePeople),
            Category(18, "Computers", Icons.Default.Computer),
            Category(21, "Sports", Icons.Default.SportsSoccer),
            Category(26, "Celebrities", Icons.Default.Person),
        )
    }
}