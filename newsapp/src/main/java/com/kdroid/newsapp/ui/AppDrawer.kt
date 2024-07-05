package com.kdroid.newsapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LineStyle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdroid.newsapp.R
import com.kdroid.newsapp.ui.theme.ComposeDemoTheme


@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterests: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(modifier) {
        NewsLogo()
        NavigationDrawerItem(label = { Text(text = stringResource(id = R.string.home)) },
            icon = { Icon(Icons.Filled.Home, null) },
            selected = currentRoute == NewsDestinations.HOME_ROUTE,
            onClick = { navigateToHome();closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(label = { Text(text = stringResource(id = R.string.interests)) },
            icon = { Icon(Icons.Filled.LineStyle, null) },
            selected = currentRoute == NewsDestinations.INTERESTS_ROUTE,
            onClick = { navigateToInterests();closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}

@Composable
private fun NewsLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            painterResource(R.drawable.ic_jetnews_logo),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            painter = painterResource(R.drawable.ic_jetnews_wordmark),
            contentDescription = stringResource(R.string.app_name),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    ComposeDemoTheme {
        AppDrawer(currentRoute = NewsDestinations.HOME_ROUTE,
            navigateToHome = {},
            navigateToInterests = {},
            closeDrawer = { })
    }
}
