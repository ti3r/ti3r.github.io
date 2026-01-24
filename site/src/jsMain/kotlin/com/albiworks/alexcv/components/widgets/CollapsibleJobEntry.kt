package com.albiworks.alexcv.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.albiworks.alexcv.Language
import com.albiworks.alexcv.Strings
import com.albiworks.alexcv.pages.CompanyStyle
import com.albiworks.alexcv.pages.DateStyle
import com.albiworks.alexcv.pages.JobTitleStyle
import com.albiworks.alexcv.toSitePalette
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul


@Composable
fun CollapsibleJobEntry(
    isExpanded: Boolean,
    company: String,
    jobTitle: String,
    dates: String,
    project: String,
    description: String,
    achievements: List<String>,
    technologies: String,
    language: Language,
    sectionNo: Int,
    onExpandToggle: (Int, Boolean) -> Unit,
) {
    val sitePalette = ColorMode.current.toSitePalette()

    Div(Modifier.margin(bottom = 2.cssRem).toAttrs()) {
        // Header (always visible, clickable)
        Div(
            Modifier
                .cursor(Cursor.Pointer)
                .onClick { onExpandToggle(sectionNo, !isExpanded) }
                .toAttrs()
        ) {
            Row(
                Modifier.fillMaxWidth().justifyContent(JustifyContent.SpaceBetween),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Div(CompanyStyle.toAttrs()) { Text(company) }
                    Div(JobTitleStyle.toAttrs()) { Text(jobTitle) }
                    Div(DateStyle.toAttrs()) { Text(dates) }
                }
                SpanText(
                    if (isExpanded) "▼" else "▶",
                    Modifier
                        .fontSize(1.5.cssRem)
                        .color(sitePalette.brand.primary)
                        .transition(Transition.of("transform", 300.ms))
                )
            }
        }

        // Collapsible content with animation
        Div(
            Modifier
                .maxHeight(if (isExpanded) 2000.px else 0.px)
                .opacity(if (isExpanded) 1 else 0)
                .overflow(Overflow.Hidden)
                .transition(
                    Transition.of("max-height", 400.ms),
                    Transition.of("opacity", 300.ms)
                )
                .toAttrs()
        ) {
            Div(Modifier.margin(top = 0.5.cssRem).toAttrs()) {
                P(Modifier.fontStyle(FontStyle.Italic).toAttrs()) { Text(project) }
                P { Text(description) }
                P(Modifier.fontWeight(FontWeight.SemiBold).margin(top = 0.5.cssRem).toAttrs()) {
                    Text(Strings.keyAchievements[language].orEmpty())
                }
                Ul {
                    achievements.forEach { achievement ->
                        Li { Text(achievement) }
                    }
                }
                P(Modifier.fontStyle(FontStyle.Italic).fontSize(0.9.cssRem).margin(top = 0.5.cssRem).toAttrs()) {
                    Text("${Strings.technologies[language].orEmpty()} $technologies")
                }
            }
        }
    }

    Hr {  }
}