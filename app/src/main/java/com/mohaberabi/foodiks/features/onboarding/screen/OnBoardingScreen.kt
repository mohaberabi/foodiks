package com.mohaberabi.foodiks.features.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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


            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.foodiks_logo),
            )
            Spacer(modifier = Modifier.height(Spacing.lg))
            Text(
                text = stringResource(R.string.quote),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Black
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(Spacing.sm))
            Text(
                text = stringResource(R.string.foodics),
                style = MaterialTheme.typography.bodyMedium.copy(
                ),
                textAlign = TextAlign.Center
            )
            PrimaryButton(

                onClick = onGetStarted,
                label = stringResource(R.string.get_started),
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