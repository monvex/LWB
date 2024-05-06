package com.example.lwb.statistics.presentation.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.lwb.R
import com.example.lwb.statistics.presentation.main.components.MainSectionCard

@Composable
fun MainScreen(
    onNavigateFoodDiary: () -> Unit
){
    Box(
        modifier = Modifier.padding(5.dp)
    ) {

        Column(){
            ShowCalendar()
            MainSectionCard(title = "Питание", description = "", imageId = R.drawable.food, onNavigateFoodDiary)
        }
    }
}

@Composable
fun ShowCalendar() {
    Calendar(
        calendarState = rememberMonthSelectionState(),
        modifier = Modifier.border(1.dp, Color.Black, RoundedCornerShape(15.dp))
    )
}

private class MonthSelectionState(
    initialSelection: MutableList<LocalDate>? = null,
) : SelectionState {

    private var selection by mutableStateOf(initialSelection)

    override fun isDateSelected(date: LocalDate): Boolean =
        selection?.contains(date) == true

    override fun onDateSelected(date: LocalDate) {
        if (selection?.contains(date) == true)
            selection?.remove(date)
        else
            selection?.add(date)
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