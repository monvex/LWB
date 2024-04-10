package com.example.lwb.navigation

import com.example.lwb.R
sealed class BottomItem(var title: String, var iconId: Int, var route: String, var position: Position) {
    object KnowledgeBase: BottomItem("База\nЗнаний", R.drawable.ic_knowledge_base, "knowledge_base", Position.LEFT)
    object MainPage: BottomItem("Главная", R.drawable.ic_main_page, "main_page", Position.CENTER)
    object Settings: BottomItem("Настройки", R.drawable.ic_settings, "settings", Position.RIGHT)
}

enum class Position{
    RIGHT,
    CENTER,
    LEFT
}