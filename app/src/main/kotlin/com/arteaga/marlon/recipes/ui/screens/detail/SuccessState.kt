
package com.arteaga.marlon.recipes.ui.screens.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.Function
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arteaga.marlon.domain.fake.RecipeTestData
import com.arteaga.marlon.domain.models.Recipe
import com.arteaga.marlon.recipes.R
import com.arteaga.marlon.recipes.ui.theme.detailDescriptionStyle
import com.arteaga.marlon.recipes.ui.theme.detailTitleStyle
import com.arteaga.marlon.recipes.utils.TestsUtil

@Composable
fun SuccessState(isVisible: Boolean, recipe: Recipe?, goToMaps: Function<Recipe?, Unit>) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(isVisible),
        enter = expandHorizontally(),
        exit = shrinkHorizontally(),
    ) {
        Scaffold(
            modifier = Modifier.clearAndSetSemantics {
                contentDescription = TestsUtil.detailContent
                onClick {
                    goToMaps.apply(recipe)
                    true
                }
            },
            topBar = {
                Text(
                    text = recipe?.name.orEmpty(),
                    modifier = Modifier.padding(12.dp),
                    style = detailTitleStyle,
                )
            },
            floatingActionButtonPosition = FabPosition.EndOverlay,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { goToMaps.apply(recipe) },
                    modifier = Modifier.clearAndSetSemantics {
                        contentDescription = TestsUtil.clickLocation
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = stringResource(id = R.string.icon_maps),
                        )
                        Text(
                            text = stringResource(id = R.string.go_location),
                            style = detailDescriptionStyle,
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe?.image.orEmpty())
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.item_image),
                    contentScale = ContentScale.Fit,
                )

                Text(
                    text = recipe?.description.orEmpty(),
                    modifier = Modifier.padding(12.dp),
                    style = detailDescriptionStyle,
                )

                ItemInformation(
                    stringResource(id = R.string.likes_label) + (recipe?.likes ?: 0).toString(),
                    Icons.Default.Favorite
                )

                ItemInformation(
                    stringResource(id = R.string.Ingredients_label) + (recipe?.usedIngredientCount ?: 0).toString(),
                    Icons.AutoMirrored.Filled.List
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SuccessStatePreview() {
    SuccessState(true, RecipeTestData.create()) {}
}
