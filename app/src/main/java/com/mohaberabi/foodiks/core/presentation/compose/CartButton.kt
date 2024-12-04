package com.mohaberabi.foodiks.core.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mohaberabi.foodiks.R
import com.mohaberabi.foodiks.core.presentation.design_system.theme.FoodiksTheme
import com.mohaberabi.foodiks.core.presentation.design_system.theme.Spacing
import java.util.Locale


@Composable
fun CartButton(
    modifier: Modifier = Modifier,
    cartSize: String,
    cartTotal: String,
    onClick: () -> Unit = {},
    height: Dp = 60.dp,
    enabled: Boolean = true,
) {


    Button(
        shape = RoundedCornerShape(Spacing.md),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) MaterialTheme.colorScheme.primary else Color.LightGray
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing.sm)
            .height(height)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Box(
                contentAlignment = Alignment.Center,

                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .sizeIn(minWidth = 32.dp, minHeight = 32.dp),

                ) {
                Text(
                    text = cartSize,
                    style = MaterialTheme.typography.titleMedium.copy(color = if(enabled)  MaterialTheme.colorScheme.primary else Color.Gray)
                )
            }

            Spacer(modifier = Modifier.width(Spacing.sm))
            Text(
                text = "View Order",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(
                    R.string.sar,
                    cartTotal,
                ),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
            Spacer(modifier = Modifier.width(Spacing.sm))

            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = stringResource(R.string.forward)
            )
        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewCartButton() {
    FoodiksTheme {

        CartButton(
            enabled = false ,
            cartTotal = "1222.90",
            cartSize = "09"
        )
    }
}