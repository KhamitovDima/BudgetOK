package com.finance.budgetok.contexts.panel.features.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.finance.budgetok.R
import com.finance.budgetok.contexts.panel.features.mvi.PanelState
import com.finance.budgetok.contexts.sample.features.mvi.SampleState

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
        TopBar()
        MonthSelector()
        IncomeSection()
        WalletSection()
        ExpenseSection()
        // Добавляем Spacer, чтобы "толкнуть" BottomNavigationBar
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "19:09", fontSize = 16.sp)
        Row {
            Icon(painter = painterResource(id = R.drawable.ic_photo_24), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(painter = painterResource(id = R.drawable.ic_album_24), contentDescription = null)
        }
    }
}

@Composable
fun MonthSelector() {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextButton(onClick = { expanded = !expanded }) {
            Text(text = "МАРТ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Icon(
                painter = painterResource(id = R.drawable.ic_album_24),
                contentDescription = null
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = { /* выбрать месяц */ }) {
                Text(text = "ФЕВРАЛЬ")
            }
            DropdownMenuItem(onClick = { /* выбрать месяц */ }) {
                Text(text = "МАРТ")
            }
        }
    }
}

@Composable
fun IncomeSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        SectionHeader("Доходы", "7 900 ₽")
        LazyRow {
            items(
                listOf(
                "Переводы" to "0 ₽",
                "Дивиденды" to "0 ₽",
                "Кэшбек" to "0 ₽",
                "+" to null
            )) { item ->
                if (item.second != null) {
                    IncomeExpenseItem(item.first, item.second!!)
                } else {
                    AddButton()
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun IncomeSection1() {
    Column(modifier = Modifier.padding(16.dp)) {
        SectionHeader("Доходы", "7 900 ₽")
        LazyRow {
            items(
                listOf(
                    "Переводы" to "0 ₽",
                    "Дивиденды" to "0 ₽",
                    "Кэшбек" to "0 ₽",
                    "+" to null
                )
            ) { item ->
                if (item.second != null) {
                    IncomeExpenseItem(item.first, item.second!!)
                } else {
                    AddButton()
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun WalletSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        SectionHeader("Кошельки", "1 030 645,5 ₽")
        LazyRow {
            items(listOf(
                "Долги" to "-65 000 ₽",
                "Накопления" to "99 330 ₽",
                "+" to null
            )) { item ->
                if (item.second != null) {
                    IncomeExpenseItem(item.first, item.second!!)
                } else {
                    AddButton()
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun ExpenseSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        SectionHeader("Расходы", "69 743 ₽")
        LazyRow {
            items(listOf(
                "Покупки" to "0 ₽",
                "Переводы" to "0 ₽",
                "Налоги" to "0 ₽",
                "+" to null
            )) { item ->
                if (item.second != null) {
                    IncomeExpenseItem(item.first, item.second!!)
                } else {
                    AddButton()
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, amount: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = amount, fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.Black)
    }
}

@Composable
fun IncomeExpenseItem(name: String, amount: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.Gray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_album_24),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(text = amount, fontSize = 14.sp, color = if (amount.startsWith("-")) Color.Red else Color.Black)
    }
}

@Composable
fun AddButton() {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(Color.LightGray, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            modifier = Modifier.size(24.dp),
            tint = Color.DarkGray
        )
    }
}

@Composable
fun BottomNavigationBar() {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_photo_24), contentDescription = null) },
            label = { Text(text = "Панель") },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_album_24), contentDescription = null) },
            label = { Text(text = "История") },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_photo_24), contentDescription = null) },
            label = { Text(text = "Отчет") },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_album_24), contentDescription = null) },
            label = { Text(text = "Настройки") },
            selected = false,
            onClick = {}
        )
    }
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