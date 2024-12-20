package com.mohaberabi.foodiks.features.tables.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.foodiks.core.domain.model.CategoryModel
import com.mohaberabi.foodiks.core.domain.model.ProductModel
import com.mohaberabi.foodiks.core.presentation.compose.NetworkImage
import com.mohaberabi.foodiks.core.presentation.design_system.theme.FoodiksTheme
import com.mohaberabi.foodiks.core.presentation.design_system.theme.Spacing
import kotlin.math.min


@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: ProductModel,
    onClick: () -> Unit = {},
    cartQty: Int,
) {

    Box(
        modifier = Modifier.sizeIn(minHeight = Spacing.lg, minWidth = Spacing.lg),
        contentAlignment = Alignment.TopEnd,
    ) {

        ElevatedCard(

            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp,

                ),
            shape = RoundedCornerShape(Spacing.lg),
            colors = CardDefaults.elevatedCardColors(

                containerColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = modifier
                .padding(Spacing.md)
                .heightIn(min = 220.dp),
            onClick = onClick,
        ) {

            Column {
                NetworkImage(
                    modifier = Modifier
                        .aspectRatio(4f / 3f),
                    url = product.image ?: "",
                )
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = product.name,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = Spacing.xlg, vertical = Spacing.sm)
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    text = product.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = Spacing.xlg).padding(bottom = Spacing.xs)
                )
            }
        }

        if (cartQty > 0) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.error)
                    .sizeIn(minWidth = 34.dp, minHeight = 34.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "${cartQty}",
                    modifier = Modifier.padding(Spacing.sm),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }

}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewProductCard() {
    FoodiksTheme {

        ProductCard(
            cartQty = 2,
            product = ProductModel(
                "1",
                "Tilabia fish",
                200.0,
                CategoryModel("", ""),
                "grilled fish with amazing sauce and also healthy espcailly for progrmmmers",
                ""
            )
        )
    }
}