package com.mohaberabi.foodiks.core.presentation.compose

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.mohaberabi.foodiks.core.presentation.design_system.theme.Spacing


@Composable
fun IconText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    imageVector: ImageVector,
    contentDescription: String? = null,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall.copy(color = textColor),
    iconSize: Dp = Spacing.md,
    text: String,
    textMaxLines: Int = 1,
) {


    Row(
        modifier = modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(iconSize)
                .padding(end = Spacing.xs),
            tint = color,
            imageVector = imageVector,
            contentDescription = contentDescription
        )

        Text(
            text = text,
            maxLines = textMaxLines,
            overflow = TextOverflow.Ellipsis,
            style = textStyle
        )
    }
}