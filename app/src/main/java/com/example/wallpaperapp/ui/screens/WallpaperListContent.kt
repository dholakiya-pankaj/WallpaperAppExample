package com.example.wallpaperapp.ui.screens

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.wallpaperapp.R
import com.example.wallpaperapp.database.entity.WallpaperEntity

@Composable
fun WallpaperListContent(
    viewModel: WallpaperViewModel,
    paddingValues: PaddingValues,
    wallpapers: LazyPagingItems<WallpaperEntity>
) {

    val cellConfiguration =
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            StaggeredGridCells.Adaptive(minSize = 150.dp)
        } else StaggeredGridCells.Fixed(2)

    LazyVerticalStaggeredGrid(
        columns = cellConfiguration,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        items(
            count = wallpapers.itemCount,
            key = wallpapers.itemKey(
                key = { wallpaper -> wallpaper.wallpaperId }
            ),
            contentType = wallpapers.itemContentType()
        ) { index ->
            val item = wallpapers[index]
            item?.let { wallpaper ->
                AsyncImage(
                    imageUrl = wallpaper.urls?.smallImage,
                    placeholderResId = R.drawable.ic_placeholder_image,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun AsyncImage(
    imageUrl: String?,
    placeholderResId: Int,
    modifier: Modifier = Modifier,
    viewModel: WallpaperViewModel
) {
    // Remember the state of the loaded image
    val imageBitmap = remember(imageUrl) { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(key1 = imageUrl) {
        viewModel.downloadMyWallpaper(imageUrl) { bitmap ->
            imageBitmap.value = bitmap
        }
    }

    Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
        val imageModifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .clip(RoundedCornerShape(16.dp))

        imageBitmap.value?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = imageModifier
            )
        } ?: Image(
            painter = painterResource(id = placeholderResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
    }
}