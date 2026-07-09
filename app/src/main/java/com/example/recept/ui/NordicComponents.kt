package com.example.recept.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recept.R
import com.example.recept.model.Ingredient
import com.example.recept.model.displayAmount
import com.example.recept.ui.theme.CardBorder
import com.example.recept.ui.theme.CheckboxBorder
import com.example.recept.ui.theme.CheckedDim
import com.example.recept.ui.theme.CreamSurface
import com.example.recept.ui.theme.PlaceholderLabel
import com.example.recept.ui.theme.PrimaryGreen
import com.example.recept.ui.theme.StripeDark
import com.example.recept.ui.theme.StripeLight
import com.example.recept.ui.theme.TextPrimary
import com.example.recept.ui.theme.TextSecondary

@Composable
fun NordicCheckbox(
    checked: Boolean,
    size: Dp,
    cornerRadius: Dp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = if (checked) PrimaryGreen else Color.Transparent,
                shape = RoundedCornerShape(cornerRadius),
            )
            .border(
                width = 2.dp,
                color = if (checked) PrimaryGreen else CheckboxBorder,
                shape = RoundedCornerShape(cornerRadius),
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (checked) {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                tint = CreamSurface,
                modifier = Modifier.size(size - 10.dp),
            )
        }
    }
}

@Composable
fun StepCard(
    text: String,
    checked: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    checkboxSize: Dp = 24.dp,
    checkboxCorner: Dp = 8.dp,
    cornerRadius: Dp = 16.dp,
) {
    Surface(
        onClick = onToggle,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(cornerRadius),
        color = CreamSurface,
        border = BorderStroke(1.dp, CardBorder),
        shadowElevation = 1.dp,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.Top,
        ) {
            NordicCheckbox(
                checked = checked,
                size = checkboxSize,
                cornerRadius = checkboxCorner,
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = if (checked) CheckedDim else TextPrimary,
                textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
            )
        }
    }
}

@Composable
fun IngredientLabel(
    ingredient: Ingredient,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold, color = TextPrimary)) {
                append(ingredient.displayAmount())
            }
            withStyle(SpanStyle(color = TextSecondary)) {
                append("  ${ingredient.name}")
            }
        },
        style = style,
        modifier = modifier,
    )
}

@Composable
fun ImagePlaceholder(
    label: String,
    modifier: Modifier = Modifier,
    labelSize: TextUnit = 9.sp,
) {
    val stripePeriod = with(LocalDensity.current) { 14.dp.toPx() }
    Box(
        modifier = modifier.background(
            Brush.linearGradient(
                colorStops = arrayOf(
                    0f to StripeLight,
                    0.5f to StripeLight,
                    0.5f to StripeDark,
                    1f to StripeDark,
                ),
                start = Offset.Zero,
                end = Offset(stripePeriod, stripePeriod),
                tileMode = TileMode.Repeated,
            ),
        ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            fontFamily = FontFamily.Monospace,
            fontSize = labelSize,
            lineHeight = labelSize * 1.3f,
            color = PlaceholderLabel,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(6.dp),
        )
    }
}
