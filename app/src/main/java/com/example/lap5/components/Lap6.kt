package com.example.lap5.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@SuppressLint("MutableCollectionMutableState", "DiscouragedApi")
@Composable
fun Lap6() {

    data class Card(val id: Int, val imageUrl: Int, val isMatched: Boolean)

    val context = LocalContext.current
    var cards by remember {
        mutableStateOf(
            mutableListOf<Card>().apply {
                for (i in 0..11) {
                    add(
                        Card(
                            i,
                            context.resources.getIdentifier("a$i", "drawable", context.packageName),
                            false
                        )
                    )
                    add(
                        Card(
                            i,
                            context.resources.getIdentifier("a$i", "drawable", context.packageName),
                            false
                        )
                    )
                }
            }

        )

    }
    var selectedIndices by remember { mutableStateOf(emptyList<Int>()) }


    fun handleCardClick(index: Int) {
        if (selectedIndices.size < 2 && index !in selectedIndices) {
            selectedIndices += index

            if (selectedIndices.size == 2) {
                val firstIndex = selectedIndices[0]
                val secondIndex = selectedIndices[1]
                val firstId = cards[firstIndex].id
                val secondId = cards[secondIndex].id

                if (firstId == secondId) {
                    cards = cards.mapIndexed { idx, card ->
                        if (idx == firstIndex || idx == secondIndex) card.copy(isMatched = true) else card
                    }.toMutableList()
                }
                selectedIndices = emptyList()
            }
        }
    }

    Column(modifier = Modifier.padding(30.dp)) {
        if (cards.all { it.isMatched }) {
            Text(text = "You Win!", fontSize = 32.sp, modifier = Modifier.padding(16.dp))
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(cards) { index, card ->
                    if (!card.isMatched) {
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .border(1.5.dp, Color.Gray)
                                .clickable(enabled = index !in selectedIndices) {
                                    handleCardClick(index)
                                }
                        ) {
                            Image(
                                painter = painterResource(
                                    id = card.imageUrl
                                ),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}

