
package com.arteaga.marlon.recipes.ui.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arteaga.marlon.recipes.R
import com.arteaga.marlon.recipes.ui.theme.titleStyle

@Composable
fun TitleAppBar() {
    Text(
        text = stringResource(id = R.string.title_name),
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        style = titleStyle,
    )
}

@Preview(showSystemUi = true)
@Composable
fun TitleAppBarPreview() {
    TitleAppBar()
}
