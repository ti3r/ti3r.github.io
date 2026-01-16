package com.albiworks.alexcv

import androidx.compose.runtime.*

enum class Language {
    ENGLISH,
    SPANISH
}

object LanguageState {
    val current = mutableStateOf(Language.ENGLISH)
}

@Composable
fun rememberLanguage(): MutableState<Language> {
    return remember { LanguageState.current }
}
