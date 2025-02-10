
package com.arteaga.marlon.recipes.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.Function
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.arteaga.marlon.domain.fake.RecipeTestData
import com.arteaga.marlon.domain.models.Recipe
import com.arteaga.marlon.recipes.R
import com.arteaga.marlon.recipes.ui.theme.cardStyle
import com.arteaga.marlon.recipes.utils.TestsUtil

@Composable
fun SuccessState(
    isVisible: Boolean,
    recipes: List<Recipe>,
    goToDetail: Function<Recipe?, Unit>,
) {
    var showInput by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(TextFieldValue("")) }

    AnimatedVisibility(
        visibleState = MutableTransitionState(isVisible),
        enter = expandHorizontally(),
        exit = shrinkHorizontally()
    ) {
        Scaffold(
            modifier = Modifier.clearAndSetSemantics {
                contentDescription = TestsUtil.homeContent
                onClick {
                    goToDetail.apply(RecipeTestData.create())
                    true
                }
            },
            topBar = {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = if (showInput) Arrangement.End else Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AnimatedVisibility(
                            visibleState = MutableTransitionState(showInput),
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut(),
                        ) {
                            TextField(
                                placeholder = { Text(text = stringResource(id = R.string.search)) },
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                                value = text,
                                onValueChange = { newText ->
                                    text = newText
                                }
                            )
                        }
                        ExtendedFloatingActionButton(
                            onClick = {
                                showInput = !showInput
                                text = text.copy("")
                            },
                        ) {
                            Icon(
                                imageVector = if (showInput) Icons.Default.Close else Icons.Default.Search,
                                contentDescription = stringResource(id = R.string.icon_search),
                            )
                            AnimatedVisibility(
                                visibleState = MutableTransitionState(!showInput),
                                enter = expandHorizontally() + fadeIn(),
                                exit = shrinkHorizontally() + fadeOut(),
                            ) {
                                Text(
                                    text = stringResource(id = R.string.search_label),
                                    modifier = Modifier.padding(horizontal = 4.dp),
                                )
                            }
                        }
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {

                val items: List<Recipe> = if (!showInput) {
                    recipes
                } else {
                    recipes.filter { it.name.lowercase().contains(text.text.lowercase()) }
                }

                items.forEach { item ->
                    ItemCard(goToDetail, item)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ItemCard(
    goToDetail: Function<Recipe?, Unit>,
    item: Recipe
) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(true),
        enter = scaleIn(),
        exit = scaleOut(),
    ) {
        Card(
            onClick = { goToDetail.apply(item) },
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.image)
                        .crossfade(true)
                        .transformations(
                            CircleCropTransformation()
                        )
                        .build(),
                    contentDescription = stringResource(R.string.item_image),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(96.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.card_description,
                            item.name, item.likes, item.usedIngredientCount
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        style = cardStyle,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SuccessStatePreview() {
    SuccessState(true, emptyList()) {}
}
