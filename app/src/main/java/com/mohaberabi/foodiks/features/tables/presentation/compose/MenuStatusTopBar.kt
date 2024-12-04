package com.mohaberabi.foodiks.features.tables.presentation.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.foodiks.R
import com.mohaberabi.foodiks.core.presentation.compose.IconText
import com.mohaberabi.foodiks.core.presentation.design_system.theme.FoodiksTheme
import com.mohaberabi.foodiks.core.presentation.design_system.theme.ForkIcon
import com.mohaberabi.foodiks.core.presentation.design_system.theme.GroupIcon
import com.mohaberabi.foodiks.core.presentation.design_system.theme.Spacing


@Composable
fun MenuStatusTopBar(
    modifier: Modifier = Modifier,
    tables: String = "02",
    guest: String = "09"

) {


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.sm, vertical = Spacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.menuu),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        )
        Spacer(modifier = Modifier.weight(1f))

        IconText(
            imageVector = ForkIcon,
            text = tables,
            iconSize = 22.dp,
            textStyle = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.width(Spacing.sm))
        IconText(
            imageVector = GroupIcon,
            text = guest,
            iconSize = 22.dp,
            textStyle = MaterialTheme.typography.bodyLarge
        )

    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewMenuTopBar() {
    FoodiksTheme {


        MenuStatusTopBar()
    }
}