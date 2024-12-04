package com.mohaberabi.foodiks.features.onboarding.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mohaberabi.foodiks.R
import com.mohaberabi.foodiks.core.presentation.compose.AppScaffold
import com.mohaberabi.foodiks.core.presentation.compose.PrimaryButton
import com.mohaberabi.foodiks.core.presentation.design_system.theme.FoodiksTheme
import com.mohaberabi.foodiks.core.presentation.design_system.theme.Spacing


@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onGetStarted: () -> Unit = {},
) {


    AppScaffold { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .padding(Spacing.lg),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Text(
                text = stringResource(R.string.quote),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Black
                ),
                textAlign = TextAlign.Center
            )

            Text(text = stringResource(R.string.foodics))
            PrimaryButton(
                onClick = onGetStarted,
                label = "Get Started",
            )

        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewOnBoardingScreen() {
    FoodiksTheme {

        OnBoardingScreen()
    }
}