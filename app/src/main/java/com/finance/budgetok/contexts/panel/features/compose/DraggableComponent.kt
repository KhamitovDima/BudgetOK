package com.finance.budgetok.contexts.panel.features.compose

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finance.budgetok.R
import com.finance.budgetok.infra.ui.compose.components.VSpacer
import com.google.gson.Gson
import java.util.UUID

private const val categoryItemTransferAction = "action_categoryItem"
private const val categoryItemTransferData = "data_categoryItem"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryItem(
    data: CategoryItemData,
    modifier: Modifier = Modifier
) {
    val categoryItems = remember {
        mutableStateMapOf<UUID, CategoryItemData>()
    }

    var bgColor by remember { mutableStateOf(data.backgroundColor) }

    val dollarIconPainter = painterResource(id = R.drawable.ic_photo_24)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title Text
        Text(
            text = data.title,
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
                    color = bgColor,
                    shape = CircleShape
                )
                .dragAndDropSource(
                    drawDragDecoration = drawDragDecoration(dollarIconPainter)
                ) {
                    detectTapGestures(
                        onLongPress = {
                            startTransfer(
                                DragAndDropTransferData(
                                    clipData = ClipData.newIntent(
                                        "categoryItem",
                                        Intent(categoryItemTransferAction).apply {
                                            putExtra(
                                                categoryItemTransferAction,
                                                Gson().toJson(data)
                                            )
                                        },
                                    )
                                )
                            )
                        }
                    )
                }
                .dragAndDropTarget(
                    shouldStartDragAndDrop = { event ->
                        event
                            .mimeTypes()
                            .contains(ClipDescription.MIMETYPE_TEXT_INTENT)
                    },
                    target = object : DragAndDropTarget {
                        override fun onDrop(event: DragAndDropEvent): Boolean {
                            val foodItem =
                                event.toAndroidDragEvent().clipData.categoryItem() ?: return false
                            categoryItems[foodItem.id] = foodItem
                            bgColor = data.backgroundColor
                            Log.d("qwerty", "onDrop")
                            return true
                        }

                        override fun onEntered(event: DragAndDropEvent) {
                            super.onEntered(event)
                            Log.d("qwerty", "onEntered")
                            bgColor = Color.Red
                        }
                        //еще метода не хватает
                        override fun onExited(event: DragAndDropEvent) {
                            super.onExited(event)
                            Log.d("qwerty", "onDrop")
                            bgColor = data.backgroundColor
                        }

                    }
                )
                .clip(RoundedCornerShape(16.dp))
        ) {
            Icon(
                imageVector = data.icon,
                contentDescription = null,
                tint = data.iconTint,
                modifier = Modifier.size(36.dp)
            )
        }

        VSpacer(value = 8)

        // Amount Text
        Text(
            text = data.amount,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun drawDragDecoration(
    painter: Painter,
): DrawScope.() -> Unit {
    return {
        // Размер и цвет круга
        val circleRadius = 30.dp.toPx()
        val circleColor = Color.Gray

        // Размер и цвет значка
        val iconSize = 24.dp.toPx()

        // Рисуем круг
        drawCircle(
            color = circleColor,
            radius = circleRadius,
            center = center
        )

        // Рисуем значок доллара внутри круга
        translate(center.x - iconSize / 2, center.y - iconSize / 2) {
            drawIntoCanvas { canvas ->
                with(painter) {
                    draw(
                        size = Size(iconSize, iconSize)
                    )
                }
            }
        }
    }
}

private fun ClipData.categoryItem(): CategoryItemData? {
    return (0 until itemCount)
        .mapNotNull(::getItemAt).firstNotNullOfOrNull { item ->
            item.intent?.getStringExtra(categoryItemTransferData)?.takeIf { it.isNotEmpty() }
        }?.let { Gson().fromJson(it, CategoryItemData::class.java) }
}

// Модель данных
data class CategoryItemData(
    val id: UUID,
    val title: String,
    val amount: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconTint: Color
)

fun getIncomeItems(): List<CategoryItemData> {
    return listOf(
        CategoryItemData(
            UUID.randomUUID(),
            "Тинькофф",
            "20 094 ₽",
            Icons.Default.Face,
            Color.Yellow,
            Color.Black
        ),
        CategoryItemData(
            UUID.randomUUID(),
            "Месячный фонд",
            "24 253 ₽",
            Icons.Default.Face,
            Color.Blue,
            Color.Black
        ),
        CategoryItemData(
            UUID.randomUUID(),
            "Подушка",
            "843 562,37 ₽",
            Icons.Default.Face,
            Color.Magenta,
            Color.Black
        ),
        CategoryItemData(
            UUID.randomUUID(),
            "Отдых",
            "6 530 ₽",
            Icons.Default.Face,
            Color.Cyan,
            Color.Black
        ),
        CategoryItemData(
            UUID.randomUUID(),
            "Тинькофф",
            "20 094 ₽",
            Icons.Default.Face,
            Color.Yellow,
            Color.Black
        ),
        CategoryItemData(
            UUID.randomUUID(),
            "Месячный фонд",
            "24 253 ₽",
            Icons.Default.Face,
            Color.Blue,
            Color.Black
        ),
        CategoryItemData(
            UUID.randomUUID(),
            "Подушка",
            "843 562,37 ₽",
            Icons.Default.Face,
            Color.Magenta,
            Color.Black
        ),
        CategoryItemData(
            UUID.randomUUID(),
            "Отдых",
            "6 530 ₽",
            Icons.Default.Face,
            Color.Cyan,
            Color.Black
        )
    )
}

