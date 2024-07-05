package com.kdroid.newsapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LineStyle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdroid.newsapp.R
import com.kdroid.newsapp.ui.NewsDestinations
import com.kdroid.newsapp.ui.theme.ComposeDemoTheme

@Composable
fun AppNavRail(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterests: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        header = {
            Icon(
                painter = painterResource(id = R.drawable.ic_jetnews_logo),
                contentDescription = stringResource(id = R.string.app_name),
                Modifier.padding(vertical = 12.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        modifier = modifier
    ) {
        Spacer(Modifier.weight(1f))

        NavigationRailItem(
            selected = currentRoute == NewsDestinations.HOME_ROUTE,
            onClick = navigateToHome,
            icon = { Icon(Icons.Filled.Home, stringResource(R.string.interests)) },
            label = { Text(stringResource(R.string.home)) },
            alwaysShowLabel = false
        )

        NavigationRailItem(
            selected = currentRoute == NewsDestinations.INTERESTS_ROUTE,
            onClick = navigateToInterests,
            icon = { Icon(Icons.Filled.LineStyle, stringResource(R.string.interests)) },
            label = { Text(stringResource(R.string.interests)) },
            alwaysShowLabel = false
        )

        Spacer(Modifier.weight(1f))
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppNavRail() {
    ComposeDemoTheme {
        AppNavRail(
            currentRoute = NewsDestinations.HOME_ROUTE,
            navigateToHome = {},
            navigateToInterests = {},
        )
    }
}