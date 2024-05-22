package com.example.lwb.statistics.presentation.main

import co.yml.charts.common.model.Point
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.Calendar
import io.github.boguszpawlowski.composecalendar.CalendarState
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import java.time.LocalDate
import java.time.YearMonth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.common.extensions.formatToSinglePrecision
import com.example.lwb.R
import com.example.lwb.core.data.entities.Day
import com.example.lwb.statistics.presentation.main.components.MainSectionCard

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
){
    val days by viewModel.days.collectAsState()
    val image by viewModel.image.collectAsState()
    val description by viewModel.description.collectAsState()
    val daysWeight: MutableList<Int> = mutableListOf()
    for (day in days){
        if (!daysWeight.contains(day.weight)){
            daysWeight.add(day.weight)
        }
    }
    Box(
        modifier = Modifier.padding(5.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ShowCalendar()
            MainSectionCard(title = "Питание", description = description, imageId = image, navController = navController, destination = "foodDiary")
            Text(text = "График веса", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            if (days.isNotEmpty() and (daysWeight.size > 1)) ShowGraphic(days = days)
            else{
                Row( modifier = Modifier.fillMaxHeight().padding(0.dp, 30.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Для отображения ваш вес должен изменяться в течение нескольких дней",
                        modifier = Modifier.fillMaxHeight(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun ShowGraphic(days: List<Day>) {
    var pointsData: MutableList<Point> = mutableListOf()
    for (i in days.indices) {
        pointsData.add(Point(days[i].date.substring(0, 2).toFloat(), days[i].weight.toFloat()))
    }

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.White)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString()  }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(10)
        .backgroundColor(Color.White)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / 5
            ((i * yScale) + yMin).formatToSinglePrecision()
        }.build()
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
        lineChartData = lineChartData
    )
}


@Composable
fun ShowCalendar() {
    Calendar(
        calendarState = rememberMonthSelectionState(),
        modifier = Modifier
            .border(1.dp, Color.Black, RoundedCornerShape(15.dp))
    )
}

private class MonthSelectionState(
    initialSelection: MutableList<LocalDate>? = null,
) : SelectionState {

    private var selection by mutableStateOf(initialSelection)

    override fun isDateSelected(date: LocalDate): Boolean =
        selection?.contains(date) == true

    override fun onDateSelected(date: LocalDate) {

    }

    companion object {
        @Suppress("FunctionName") // Factory function
        fun Saver(): Saver<MonthSelectionState, Any> = Saver(
            save = { it.selection },
            restore = { restored ->
                val selection = (restored as? MutableList<LocalDate>?)
                MonthSelectionState(selection)
            }
        )
    }
}

@Composable
private fun rememberMonthSelectionState(
    initialMonth: YearMonth = YearMonth.now(),
    minMonth: YearMonth = initialMonth.minusMonths(10000),
    maxMonth: YearMonth = initialMonth.plusMonths(10000),
    initialSelection: MutableList<LocalDate>? = mutableListOf(LocalDate.now()),
    monthState: MonthState = rememberSaveable(saver = MonthState.Saver()) {
        MonthState(
            initialMonth = initialMonth,
            minMonth = minMonth,
            maxMonth = maxMonth
        )
    },
    selectionState: MonthSelectionState = rememberSaveable(saver = MonthSelectionState.Saver()) {
        MonthSelectionState(initialSelection = initialSelection)
    }
): CalendarState<MonthSelectionState> = remember { CalendarState(monthState, selectionState) }

private val LocalDate.yearMonth: YearMonth
    get() = YearMonth.of(year, month)