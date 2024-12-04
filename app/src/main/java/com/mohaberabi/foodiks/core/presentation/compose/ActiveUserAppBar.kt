package com.mohaberabi.foodiks.core.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.foodiks.core.presentation.design_system.theme.AccountIcon
import com.mohaberabi.foodiks.core.presentation.design_system.theme.FoodiksTheme
import com.mohaberabi.foodiks.core.presentation.design_system.theme.ForkIcon
import com.mohaberabi.foodiks.core.presentation.design_system.theme.Spacing
import kotlin.random.Random


@Composable
fun ActiveUserAppBar(
    modifier: Modifier = Modifier,
    username: String = "Mohab Erabi",
    userId: String = "201098",
    isConnected : Boolean = Random.nextBoolean()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.sm, horizontal = Spacing.md),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconText(
            imageVector = AccountIcon,
            text = username,
            iconSize = 26.dp,
            textColor = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = userId,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.width(Spacing.sm))

        Badge(
            modifier = Modifier.size(18.dp),
            containerColor = if (isConnected) Color.Green else Color.Red
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewActiveUserAppBar() {
    FoodiksTheme {
        ActiveUserAppBar()
    }
}