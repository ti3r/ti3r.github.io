package com.albiworks.alexcv.components.sections

import androidx.compose.runtime.*
import com.albiworks.alexcv.Language
import com.albiworks.alexcv.Strings
import com.albiworks.alexcv.rememberLanguage
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.components.icons.CloseIcon
import com.varabyte.kobweb.silk.components.icons.HamburgerIcon
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.overlay.Overlay
import com.varabyte.kobweb.silk.components.overlay.OverlayVars
import com.varabyte.kobweb.silk.components.overlay.PopupPlacement
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.components.graphics.Image
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import com.albiworks.alexcv.components.widgets.IconButton
import com.albiworks.alexcv.toSitePalette
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.ColorMode.Companion.current
import org.jetbrains.compose.web.attributes.AutoComplete.Companion.language

val NavHeaderStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .padding(1.cssRem)
        .position(Position.Sticky)
        .top(0.px)
        // .backgroundColor(current.toSitePalette().nearBackground)
        .zIndex(100)
}

val NavProfilePhotoStyle = CssStyle.base {
    Modifier
        .size(4.cssRem)
        .borderRadius(50.percent)
        .objectFit(ObjectFit.Cover)
        .margin(right = 0.5.cssRem)
}

@Composable
private fun NavLink(path: String, text: String) {
    Link(path, text, variant = UndecoratedLinkVariant.then(UncoloredLinkVariant))
}

@Composable
private fun DownloadMenu() {
    var showDropdown by remember { mutableStateOf(false) }
    var showChallenge by remember { mutableStateOf(false) }
    var challengeAnswer by remember { mutableStateOf("") }
    var pendingDownload by remember { mutableStateOf("") }
    val num1 = remember { (1..10).random() }
    val num2 = remember { (1..10).random() }
    val correctAnswer = num1 + num2
    val language by rememberLanguage()
    
    Column(Modifier.position(Position.Relative)) {
        SpanText(
            "Download",
            Modifier
                .cursor(Cursor.Pointer)
                .onClick { showDropdown = !showDropdown }
        )
        
        if (showDropdown) {
            Column(
                Modifier
                    .position(Position.Absolute)
                    .top(2.cssRem)
                    .right(0.px)
                    .backgroundColor(current.toSitePalette().nearBackground)
                    .borderRadius(8.px)
                    .padding(0.5.cssRem)
                    .minWidth(120.px)
                    .zIndex(1000)
            ) {
                SpanText(
                    "PDF",
                    Modifier
                        .padding(0.5.cssRem)
                        .fillMaxWidth()
                        .cursor(Cursor.Pointer)
                        .onClick { 
                            pendingDownload = if (language == Language.ENGLISH) {
                                "/AlexandroBlanco-Resume.pdf"
                            } else {
                                "/AlexandroBlanco-Resume.pdf"
                            }
                            showChallenge = true
                            showDropdown = false
                        }
                )
                SpanText(
                    "Markdown",
                    Modifier
                        .padding(0.5.cssRem)
                        .fillMaxWidth()
                        .cursor(Cursor.Pointer)
                        .onClick { 
                            pendingDownload = if (language == Language.ENGLISH) {
                                "/AlexandroBlanco-Resume.md"
                            } else {
                                "/AlexandroBlanco-Resume-ES.md"
                            }
                            showChallenge = true
                            showDropdown = false
                        }
                )
            }
        }
        
        if (showChallenge) {
            Overlay(
                Modifier
                    .onClick { 
                        showChallenge = false
                        challengeAnswer = ""
                    }
            ) {
                Column(
                    Modifier
                        .backgroundColor(current.toSitePalette().nearBackground)
                        .padding(2.cssRem)
                        .borderRadius(12.px)
                        .gap(1.cssRem)
                        .onClick { it.stopPropagation() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SpanText(
                        if (language == Language.ENGLISH) "Verify you're human" else "Verifica que eres humano",
                        Modifier.fontSize(1.2.cssRem).fontWeight(FontWeight.Bold)
                    )
                    SpanText(
                        if (language == Language.ENGLISH) "What is $num1 + $num2?" else "¿Cuánto es $num1 + $num2?"
                    )
                    
                    org.jetbrains.compose.web.dom.Input(
                        type = org.jetbrains.compose.web.attributes.InputType.Text
                    ) {
                        style {
                            padding(0.5.cssRem)
                            borderRadius(4.px)
                            width(100.px)
                        }
                        value(challengeAnswer)
                        onInput { event -> 
                            challengeAnswer = event.value
                        }
                    }
                    
                    Row(Modifier.gap(0.5.cssRem)) {
                        org.jetbrains.compose.web.dom.Button({
                            style {
                                padding(0.5.cssRem, 1.cssRem)
                                borderRadius(4.px)
                                property("cursor", "pointer")
                            }
                            onClick {
                                if (challengeAnswer.toIntOrNull() == correctAnswer) {
                                    window.open(pendingDownload, "_blank")
                                    showChallenge = false
                                    challengeAnswer = ""
                                }
                            }
                        }) {
                            org.jetbrains.compose.web.dom.Text(
                                if (language == Language.ENGLISH) "Submit" else "Enviar"
                            )
                        }
                        
                        org.jetbrains.compose.web.dom.Button({
                            style {
                                padding(0.5.cssRem, 1.cssRem)
                                borderRadius(4.px)
                                property("cursor", "pointer")
                            }
                            onClick {
                                showChallenge = false
                                challengeAnswer = ""
                            }
                        }) {
                            org.jetbrains.compose.web.dom.Text(
                                if (language == Language.ENGLISH) "Cancel" else "Cancelar"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MenuItems() {
    NavLink("/", "Resume")
    DownloadMenu()
    NavLink("mailto:ablanco.java@yahoo.com", "Contact")
}

@Composable
private fun ColorModeButton() {
    var colorMode by ColorMode.currentState
    var language by rememberLanguage()

    IconButton(onClick = { colorMode = colorMode.opposite },) {
        if (colorMode.isLight) MoonIcon() else SunIcon()
    }
    Tooltip(ElementTarget.PreviousSibling, Strings.toggleColorMode[language].orEmpty(), placement = PopupPlacement.BottomRight)
}

@Composable
private fun LanguageToggleButton() {
    var language by rememberLanguage()
    
    IconButton(onClick = { 
        language = if (language == Language.ENGLISH) Language.SPANISH else Language.ENGLISH
    }) {
        SpanText(
            if (language == Language.ENGLISH) "🇲🇽" else "🇺🇸",
            Modifier.fontSize(2.cssRem)
        )
    }
    Tooltip(ElementTarget.PreviousSibling, Strings.toggleLanguage[language].orEmpty(), placement = PopupPlacement.BottomRight)
}

@Composable
private fun HamburgerButton(onClick: () -> Unit) {
    IconButton(onClick) {
        HamburgerIcon()
    }
}

@Composable
private fun CloseButton(onClick: () -> Unit) {
    IconButton(onClick) {
        CloseIcon()
    }
}

val ProfileFadeInAnim = Keyframes {
    from {
        Modifier.opacity(0).scale(0.8)
    }

    to {
        Modifier.opacity(1).scale(1)
    }
}

val SideMenuSlideInAnim = Keyframes {
    from {
        Modifier.translateX(100.percent)
    }

    to {
        Modifier
    }
}

// Note: When the user closes the side menu, we don't immediately stop rendering it (at which point it would disappear
// abruptly). Instead, we start animating it out and only stop rendering it when the animation is complete.
enum class SideMenuState {
    CLOSED,
    OPEN,
    CLOSING;

    fun close() = when (this) {
        CLOSED -> CLOSED
        OPEN -> CLOSING
        CLOSING -> CLOSING
    }
}

@Composable
fun NavHeader() {
    var isScrolled by remember { mutableStateOf(false) }
    var showProfile by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        val handleScroll: (dynamic) -> Unit = { _ ->
            val scrollY = window.scrollY
            val shouldShow = scrollY > 350
            if (shouldShow != isScrolled) {
                isScrolled = shouldShow
                if (shouldShow) {
                    showProfile = true
                }
            }
        }
        window.addEventListener("scroll", handleScroll, false)
    }
    
    Row(NavHeaderStyle.toModifier(), verticalAlignment = Alignment.CenterVertically) {
        if (showProfile) {
            key(isScrolled) {
                Link("/",
                    Modifier
                        .animation(
                            ProfileFadeInAnim.toAnimation(
                                duration = 800.ms,
                                timingFunction = AnimationTimingFunction.EaseOut,
                                direction = if (isScrolled) AnimationDirection.Normal else AnimationDirection.Reverse,
                                fillMode = AnimationFillMode.Forwards
                            )
                        )
                        .onAnimationEnd { 
                            if (!isScrolled) showProfile = false
                        }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            "/foto_perfil.png", 
                            "Alexandro Blanco Profile Photo",
                            NavProfilePhotoStyle.toModifier()
                        )
                        SpanText("A. Blanco", 
                            Modifier
                                .fontSize(1.2.cssRem)
                                .fontWeight(FontWeight.Bold)
                                .color(current.toSitePalette().brand.primary)
                        )
                    }
                }
            }
        }

        Spacer()

        Row(Modifier.gap(1.5.cssRem).displayIfAtLeast(Breakpoint.MD), verticalAlignment = Alignment.CenterVertically) {
            MenuItems()
            LanguageToggleButton()
            ColorModeButton()
        }

        Row(
            Modifier
                .fontSize(1.5.cssRem)
                .gap(1.cssRem)
                .displayUntil(Breakpoint.MD),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var menuState by remember { mutableStateOf(SideMenuState.CLOSED) }

            LanguageToggleButton()
            ColorModeButton()
            HamburgerButton(onClick =  { menuState = SideMenuState.OPEN })

            if (menuState != SideMenuState.CLOSED) {
                SideMenu(
                    menuState,
                    close = { menuState = menuState.close() },
                    onAnimationEnd = { if (menuState == SideMenuState.CLOSING) menuState = SideMenuState.CLOSED }
                )
            }
        }
    }
}

@Composable
private fun SideMenu(menuState: SideMenuState, close: () -> Unit, onAnimationEnd: () -> Unit) {
    Overlay(
        Modifier
            .setVariable(OverlayVars.BackgroundColor, Colors.Transparent)
            .onClick { close() }
    ) {
        key(menuState) { // Force recompute animation parameters when close button is clicked
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(clamp(8.cssRem, 33.percent, 10.cssRem))
                    .align(Alignment.CenterEnd)
                    // Close button will appear roughly over the hamburger button, so the user can close
                    // things without moving their finger / cursor much.
                    .padding(top = 1.cssRem, leftRight = 1.cssRem)
                    .gap(1.5.cssRem)
                    .backgroundColor(current.toSitePalette().nearBackground)
                    .animation(
                        SideMenuSlideInAnim.toAnimation(
                            duration = 200.ms,
                            timingFunction = if (menuState == SideMenuState.OPEN) AnimationTimingFunction.EaseOut else AnimationTimingFunction.EaseIn,
                            direction = if (menuState == SideMenuState.OPEN) AnimationDirection.Normal else AnimationDirection.Reverse,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
                    .borderRadius(topLeft = 2.cssRem)
                    .onClick { it.stopPropagation() }
                    .onAnimationEnd { onAnimationEnd() },
                horizontalAlignment = Alignment.End
            ) {
                CloseButton(onClick = { close() })
                Column(Modifier.padding(right = 0.75.cssRem).gap(1.5.cssRem).fontSize(1.4.cssRem), horizontalAlignment = Alignment.End) {
                    MenuItems()
                }
            }
        }
    }
}
