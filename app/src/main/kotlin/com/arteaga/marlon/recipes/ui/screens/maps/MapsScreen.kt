
package com.arteaga.marlon.recipes.ui.screens.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arteaga.marlon.domain.models.Recipe
import com.arteaga.marlon.recipes.R
import com.arteaga.marlon.recipes.ui.theme.detailTitleStyle
import com.arteaga.marlon.recipes.utils.MapsUtil
import com.arteaga.marlon.recipes.utils.TestsUtil
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen(recipe: Recipe?) {

    var properties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.NORMAL,
                isTrafficEnabled = true,
                isIndoorEnabled = true,
                isBuildingEnabled = true,
            )
        )
    }

    val latitude: Double = recipe?.latitude?.toDoubleOrNull() ?: MapsUtil.DefaultLatitude
    val longitude: Double = recipe?.longitude?.toDoubleOrNull() ?: MapsUtil.DefaultLongitude
    val location = LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, MapsUtil.DefaultZoomCamera)
    }

    Scaffold(
        topBar = {
            Text(
                text = recipe?.name.orEmpty(),
                modifier = Modifier.padding(12.dp),
                style = detailTitleStyle
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(12.dp),
                onClick = {
                    properties = properties.copy(mapType = MapsUtil.getMapType(properties))
                }
            ) {
                Icon(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    imageVector = Icons.Default.Refresh,
                    contentDescription = stringResource(id = R.string.icon_maps)
                )
                Text(
                    text = "Change Map Type",
                    modifier = Modifier.padding(horizontal = 4.dp),
                )
            }
        }
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            GoogleMap(
                modifier = Modifier
                    .matchParentSize()
                    .clearAndSetSemantics {
                        contentDescription = TestsUtil.googleMap
                    },
                properties = properties,
                uiSettings = MapUiSettings(),
                cameraPositionState = cameraPositionState,
            ) {
                Marker(
                    state = MarkerState(position = location),
                    title = recipe?.name.orEmpty(),
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MapsScreenPreview() {
    MapsScreen(null)
}
