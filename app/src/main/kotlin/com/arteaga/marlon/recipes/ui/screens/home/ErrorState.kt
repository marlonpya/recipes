
package com.arteaga.marlon.recipes.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arteaga.marlon.recipes.R
import com.arteaga.marlon.recipes.utils.TestsUtil

@Composable
fun ErrorState(isVisible: Boolean, retry: () -> Unit = {}) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(isVisible),
        enter = expandHorizontally(),
        exit = shrinkHorizontally()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                Modifier.fillMaxSize().clickable {
                    retry()
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(stringResource(id = R.string.error_image_url))
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.error_image),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(96.dp)
                        .clearAndSetSemantics {
                            contentDescription = TestsUtil.errorImage
                        }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ErrorStatePreview() {
    ErrorState(true)
}
