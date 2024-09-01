package com.finance.budgetok.contexts.panel.features.compose

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.finance.budgetok.R
import com.finance.budgetok.contexts.panel.features.mvi.PanelState
import com.finance.budgetok.contexts.sample.features.mvi.SampleState
import com.finance.budgetok.infra.ui.compose.components.HSpacer
import com.finance.budgetok.infra.ui.compose.components.VSpacer

@Composable
fun FinanceScreen(
    state: PanelState,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ExpandableSection(title = "Доходы", amount = "7 900 ₽", isExpandable = true) {
            // Содержимое секции доходов
            IncomeSection()
        }

        VSpacer(value = 20)

        ExpandableSection(title = "Доходы", amount = "7 900 ₽", isExpandable = false) {
            // Содержимое секции доходов
            IncomeSection()
        }

    }
}


@Composable
fun IncomeSection() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(listOf(
            CategoryItemData("Тинькофф", "20 094 ₽", Icons.Default.Face, Color.Yellow, Color.Black),
            CategoryItemData("Месячный фонд", "24 253 ₽", Icons.Default.Face, Color.Blue, Color.Black),
            CategoryItemData("Подушка", "843 562,37 ₽", Icons.Default.Face, Color.Magenta, Color.Black),
            CategoryItemData("Отдых", "6 530 ₽", Icons.Default.Face, Color.Cyan, Color.Black),
            CategoryItemData("Тинькофф", "20 094 ₽", Icons.Default.Face, Color.Yellow, Color.Black),
            CategoryItemData("Месячный фонд", "24 253 ₽", Icons.Default.Face, Color.Blue, Color.Black),
            CategoryItemData("Подушка", "843 562,37 ₽", Icons.Default.Face, Color.Magenta, Color.Black),
            CategoryItemData("Отдых", "6 530 ₽", Icons.Default.Face, Color.Cyan, Color.Black)
        )) { item ->
            CategoryItem(
                title = item.title,
                amount = item.amount,
                icon = item.icon,
                backgroundColor = item.backgroundColor,
                iconTint = item.iconTint
            )
        }
    }
}

@Composable
fun WalletSection() {

}

@Composable
fun ExpandableSection(
    title: String,
    amount: String,
    isExpandable: Boolean,
    onToggleExpand: () -> Unit = {},
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = if (isExpandable) !expanded else true }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Добавим стрелку, которая будет меняться в зависимости от состояния секции
            if (isExpandable) {
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
                }
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = amount,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.End
            )
        }
        AnimatedVisibility(visible = expanded) {
            content()
        }
    }
}

@Composable
fun CategoryItem(
    title: String,
    amount: String,
    icon: ImageVector,
    backgroundColor: Color,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title Text
        Text(
            text = title,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            ),
            textAlign = TextAlign.Center
        )

        VSpacer(value = 8)

        // Circle with Icon
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(36.dp)
            )
        }

        VSpacer(value = 8)

        // Amount Text
        Text(
            text = amount,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
    }
}

// Модель данных
data class CategoryItemData(
    val title: String,
    val amount: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconTint: Color
)

fun getIncomeItems(): List<CategoryItemData> {
    return listOf(
        CategoryItemData("Тинькофф", "20 094 ₽", Icons.Default.Face, Color.Yellow, Color.Black),
        CategoryItemData("Месячный фонд", "24 253 ₽", Icons.Default.Face, Color.Blue, Color.Black),
        CategoryItemData("Подушка", "843 562,37 ₽", Icons.Default.Face, Color.Magenta, Color.Black),
        CategoryItemData("Отдых", "6 530 ₽", Icons.Default.Face, Color.Cyan, Color.Black)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFinanceScreen() {
    FinanceScreen(
        state = PanelState(
            title = "Click Me",
            text = 0,
        ),
        onButtonClick = { /* действие по нажатию кнопки */ }
    )
}