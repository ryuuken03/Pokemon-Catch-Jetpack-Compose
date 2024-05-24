package com.pokeapi.presentation.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

/***
 * Created By Mohammad Toriq on 04/02/2024
 */
fun Modifier.noRippleClickable(
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.noRippleCombileClickable(
    onClick: () -> Unit,
    onDoubleClick: () -> Unit,
): Modifier = composed {
    combinedClickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick,
        onDoubleClick = onDoubleClick,
    )
}

@Composable
fun LazyGridState.OnBottomReached(
    buffer : Int = 0,
    loadMore : () -> Unit
){
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:
                return@derivedStateOf true
            lastVisibleItem.index >=  layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore){
        snapshotFlow { shouldLoadMore.value }
            .collect { if (it) loadMore() }
    }
}

@Composable
fun LazyListState.OnBottomReached(
    buffer : Int = 0,
    loadMore : () -> Unit
){
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:
                return@derivedStateOf true
            lastVisibleItem.index >=  layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore){
        snapshotFlow { shouldLoadMore.value }
            .collect { if (it) loadMore() }
    }
}