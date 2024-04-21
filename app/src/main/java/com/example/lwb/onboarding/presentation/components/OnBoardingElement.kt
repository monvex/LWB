package com.example.lwb.onboarding.presentation.components

sealed class OnBoardingElement(
    val description: String,
    val buttonText: String
) {
    object First: OnBoardingElement(
        description = "Выберите свой пол",
        buttonText = "Далее"
    )

    object Second: OnBoardingElement(
        description = "Введите свой возраст",
        buttonText = "Далее"
    )

    object Third: OnBoardingElement(
        description = "Введите свой рост",
        buttonText = "Далее"
    )

    object Fourth: OnBoardingElement(
        description = "Выберите свой текущий вес",
        buttonText = "Далее"
    )

    object Fifth: OnBoardingElement(
        description = "Введите желаемый вес",
        buttonText = "Yeah, buddy"
    )
}
