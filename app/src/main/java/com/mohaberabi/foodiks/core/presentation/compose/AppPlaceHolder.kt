package com.mohaberabi.foodiks.core.presentation.compose

import com.mohaberabi.foodiks.core.presentation.design_system.theme.FoodiksTheme


import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.foodiks.R
import com.mohaberabi.foodiks.core.presentation.design_system.theme.Spacing


@Composable
fun AppPlaceHolder(
    modifier: Modifier = Modifier,
    title: String = "Nothing Was Found ... Try again Later !",
    onRetry: (() -> Unit)? = null
) {


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Icon(
            painter = painterResource(id = R.drawable.ic_info),
            tint = Color.LightGray,
            contentDescription = stringResource(R.string.empty_data),
            modifier = Modifier
                .size(85.dp)
        )
        Spacer(modifier = Modifier.height(Spacing.md))
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = Spacing.md),
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            ),
        )

        Text(
            text = stringResource(id = R.string.try_again),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.DarkGray,
            ),
        )

        if (onRetry != null) {
            PrimaryButton(
                label = stringResource(R.string.try_again),
                onClick = { onRetry() }
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewPlaceHolder() {
    FoodiksTheme {

        AppPlaceHolder(
            onRetry = {},
        )
    }
}