package id.astronauts.rocketapp

sealed class MainUiEffect {
    object Vibrate : MainUiEffect()
    data class Info(val text: String) : MainUiEffect()
}
