package com.albiworks.alexcv

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.link

/**
 * @property nearBackground A useful color to apply to a container that should differentiate itself from the background
 *   but just a little.
 */
class SitePalette(
    val nearBackground: Color,
    val cobweb: Color,
    val brand: Brand,
) {
    class Brand(
        val primary: Color = Color.rgb(102, 255, 204),
        val accent: Color = Color.rgb(0xF3DB5B),
    )
}

object SitePalettes {
    val light = SitePalette(
        nearBackground = Color.rgb(0xBFD4D8),
        cobweb = Colors.LightGray,
        brand = SitePalette.Brand(
            primary = Color.rgb(0x004D60),
            accent = Color.rgb(0xA67C52),
        )
    )
    val dark = SitePalette(
        nearBackground = Color.rgb(0x006B85),
        cobweb = Colors.LightGray.inverted(),
        brand = SitePalette.Brand(
            primary = Color.rgb(0xA8DADC),
            accent = Color.rgb(0xDDA15E),
        )
    )
}

fun ColorMode.toSitePalette(): SitePalette {
    return when (this) {
        ColorMode.LIGHT -> SitePalettes.light
        ColorMode.DARK -> SitePalettes.dark
    }
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    ctx.theme.palettes.light.background = Color.rgb(0xD4E4E7)
    ctx.theme.palettes.light.color = Colors.Black
    ctx.theme.palettes.light.link.default = Color.rgb(0x004D60)
    ctx.theme.palettes.light.link.visited = Color.rgb(0x003845)
    
    // ctx.theme.palettes.dark.background = Color.rgb(12,34,56) //HEX 123456
    ctx.theme.palettes.dark.background = Color.rgb(0x004760)
    ctx.theme.palettes.dark.color = Colors.White
    ctx.theme.palettes.dark.link.default = Color.rgb(0xA8DADC)
    ctx.theme.palettes.dark.link.visited = Color.rgb(0x8B9DC3)
}
