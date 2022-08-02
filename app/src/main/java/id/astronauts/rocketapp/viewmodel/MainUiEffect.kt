package id.astronauts.rocketapp.viewmodel

sealed class MainUiEffect {
    object Vibrate : MainUiEffect()
    data class Info(val text: String) : MainUiEffect()
}
