package com.albiworks.alexcv.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.albiworks.alexcv.Language
import com.albiworks.alexcv.Strings
import com.albiworks.alexcv.components.layouts.PageLayoutData
import com.albiworks.alexcv.rememberLanguage
import com.albiworks.alexcv.toSitePalette
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

val ResumeContainerStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
}

val HeaderStyle = CssStyle.base {
    Modifier
        .padding(2.cssRem)
        .display(DisplayStyle.Flex)
        .justifyContent(JustifyContent.Center)
}

val HeaderContentStyle = CssStyle.base {
    Modifier
        .maxWidth(800.px)
        .width(100.percent)
}

val ProfilePhotoStyle = CssStyle.base {
    Modifier
        .size(120.px)
        .borderRadius(50.percent)
        .objectFit(ObjectFit.Cover)
}

val SectionStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .padding(2.cssRem)
        .display(DisplayStyle.Flex)
        .justifyContent(JustifyContent.Center)
}

val SectionContentStyle = CssStyle.base {
    Modifier
        .maxWidth(800.px)
        .width(100.percent)
}

val AlternateSectionStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .margin(bottom = 2.cssRem)
        .padding(2.cssRem)
        .display(DisplayStyle.Flex)
        .justifyContent(JustifyContent.Center)
}

val AlternateSectionContentStyle = CssStyle.base {
    Modifier
        .maxWidth(800.px)
        .width(100.percent)
}

val SectionTitleStyle = CssStyle.base {
    Modifier
        .fontSize(1.5.cssRem)
        .fontWeight(FontWeight.Bold)
        .margin(bottom = 1.cssRem)
        .borderBottom(2.px, LineStyle.Solid, Colors.Gray)
        .padding(bottom = 0.5.cssRem)
}

val JobTitleStyle = CssStyle.base {
    Modifier
        .fontSize(1.2.cssRem)
        .fontWeight(FontWeight.Bold)
        .margin(bottom = 0.5.cssRem)
}

val CompanyStyle = CssStyle.base {
    Modifier
        .fontSize(1.1.cssRem)
        .fontWeight(FontWeight.SemiBold)
        .margin(bottom = 0.25.cssRem)
}

val DateStyle = CssStyle.base {
    Modifier
        .fontSize(0.9.cssRem)
        .fontStyle(FontStyle.Italic)
        .margin(bottom = 0.5.cssRem)
}

val FlagStyle = CssStyle.base {
    Modifier
        .size(40.px)
        .borderRadius(4.px)
        .margin(right = 1.cssRem)
}

@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData(Strings.pageTitle[Language.ENGLISH] ?: "Alexandro Blanco Santana - Resume"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage() {
    val sitePalette = ColorMode.current.toSitePalette()
    val language by rememberLanguage()
    
    Column(ResumeContainerStyle.toModifier()) {
        // Header Section
        Div(HeaderStyle.toAttrs()) {
            Row(HeaderContentStyle.toModifier(), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    "/foto_perfil.png", 
                    Strings.profilePhotoAlt[language].orEmpty(),
                    ProfilePhotoStyle.toModifier()
                )
                
                Column(Modifier.margin(left = 1.5.cssRem)) {
                    H1 {
                        SpanText("Alexandro Blanco Santana", Modifier.color(sitePalette.brand.primary))
                    }
                    P {
                        SpanText("Minneapolis, MN")
                    }
                    P {
                        Link("mailto:ablanco.java@yahoo.com", "ablanco.java@yahoo.com")
                    }
                    P {
                        Link("https://www.linkedin.com/in/alexandro-blanco-santana/", Strings.linkedIn[language].orEmpty())
                        SpanText(" | ")
                        Link("https://github.com/ti3r", Strings.gitHub[language].orEmpty())
                    }
                }
            }
        }

        // Profile Section
        Div(SectionStyle.toAttrs()) {
            Div(SectionContentStyle.toAttrs()) {
                H2(SectionTitleStyle.toAttrs()) { Text(Strings.profileTitle[language].orEmpty()) }
                P(Modifier.margin(bottom = 0.75.cssRem).toAttrs()) {
                    Text(Strings.profileParagraph1[language].orEmpty())
                }
                P(Modifier.margin(bottom = 0.75.cssRem).toAttrs()) {
                    Text(Strings.profileParagraph2[language].orEmpty())
                }
                P {
                    Text(Strings.profileParagraph3[language].orEmpty())
                }
            }
        }

        // Work Experience Section
        Div(AlternateSectionStyle.toModifier().backgroundColor(sitePalette.nearBackground).toAttrs()) {
            Div(AlternateSectionContentStyle.toAttrs()) {
            H2(SectionTitleStyle.toAttrs()) { Text(Strings.workExperienceTitle[language].orEmpty()) }
            
            // Amazon
            CollapsibleJobEntry(
                isExpanded = true,
                company = Strings.amazonCompany[language].orEmpty(),
                jobTitle = Strings.amazonJobTitle[language].orEmpty(),
                dates = Strings.amazonDates[language].orEmpty(),
                project = Strings.amazonProject[language].orEmpty(),
                description = Strings.amazonDescription[language].orEmpty(),
                achievements = listOf(
                    Strings.amazonAchievement1[language].orEmpty(),
                    Strings.amazonAchievement2[language].orEmpty(),
                    Strings.amazonAchievement3[language].orEmpty(),
                    Strings.amazonAchievement4[language].orEmpty(),
                    Strings.amazonAchievement5[language].orEmpty(),
                    Strings.amazonAchievement6[language].orEmpty(),
                    Strings.amazonAchievement7[language].orEmpty()
                ),
                technologies = "AWS (Lambda, DynamoDB, API Gateway, CDK, S3, CloudWatch), Java, Kotlin, Android, iOS, Node.js, React, TypeScript, Spring Boot, Kafka, AWS CDK",
                language = language
            )

            // ICF (combined)
            CollapsibleJobEntry(
                isExpanded = false,
                company = Strings.icfCompany[language].orEmpty(),
                jobTitle = Strings.icfJobTitle[language].orEmpty(),
                dates = Strings.icfDates[language].orEmpty(),
                project = Strings.icfProject[language].orEmpty(),
                description = Strings.icfDescription[language].orEmpty(),
                achievements = listOf(
                    Strings.icfAchievement1[language].orEmpty(),
                    Strings.icfAchievement2[language].orEmpty(),
                    Strings.icfAchievement3[language].orEmpty(),
                    Strings.icfAchievement4[language].orEmpty(),
                    Strings.icfAchievement5[language].orEmpty(),
                    Strings.icfAchievement6[language].orEmpty(),
                    Strings.icfAchievement7[language].orEmpty(),
                    Strings.icfAchievement8[language].orEmpty(),
                    Strings.icfAchievement9[language].orEmpty(),
                    Strings.icfAchievement10[language].orEmpty(),
                    Strings.icfAchievement11[language].orEmpty()
                ),
                technologies = "Kafka, Elasticsearch, OpenShift, Kubernetes, Docker, Spring Boot, Java, Spring Framework, Microservices, SOA, Linux/Unix, Shell Scripting, GitLab CI, Jenkins, Bamboo, Maven, Git, Mockito, SonarQube",
                language = language
            )

            // Perficient
            CollapsibleJobEntry(
                isExpanded = false,
                company = Strings.perficientCompany[language].orEmpty(),
                jobTitle = Strings.perficientJobTitle[language].orEmpty(),
                dates = Strings.perficientDates[language].orEmpty(),
                project = Strings.perficientProject[language].orEmpty(),
                description = Strings.perficientDescription[language].orEmpty(),
                achievements = listOf(
                    Strings.perficientAchievement1[language].orEmpty(),
                    Strings.perficientAchievement2[language].orEmpty(),
                    Strings.perficientAchievement3[language].orEmpty()
                ),
                technologies = "Java, Spring Boot, Spring Data, REST APIs, Oracle, MongoDB, Gradle",
                language = language
            )

            // OLSON
            CollapsibleJobEntry(
                isExpanded = false,
                company = Strings.olsonCompany[language].orEmpty(),
                jobTitle = Strings.olsonJobTitle[language].orEmpty(),
                dates = Strings.olsonDates[language].orEmpty(),
                project = Strings.olsonProject[language].orEmpty(),
                description = Strings.olsonDescription[language].orEmpty(),
                achievements = listOf(
                    Strings.olsonAchievement1[language].orEmpty(),
                    Strings.olsonAchievement2[language].orEmpty(),
                    Strings.olsonAchievement3[language].orEmpty(),
                    Strings.olsonAchievement4[language].orEmpty(),
                    Strings.olsonAchievement5[language].orEmpty()
                ),
                technologies = "Java, Spring Framework, Spring Batch, Hibernate, SOAP, JMS, Node.js, AngularJS, Git",
                language = language
            )

            // Best Buy
            CollapsibleJobEntry(
                isExpanded = false,
                company = Strings.bestBuyCompany[language].orEmpty(),
                jobTitle = Strings.bestBuyJobTitle[language].orEmpty(),
                dates = Strings.bestBuyDates[language].orEmpty(),
                project = Strings.bestBuyProject[language].orEmpty(),
                description = Strings.bestBuyDescription[language].orEmpty(),
                achievements = listOf(
                    Strings.bestBuyAchievement1[language].orEmpty(),
                    Strings.bestBuyAchievement2[language].orEmpty(),
                    Strings.bestBuyAchievement3[language].orEmpty(),
                    Strings.bestBuyAchievement4[language].orEmpty()
                ),
                technologies = "Java, Spring Framework, REST APIs, Hibernate, Cucumber, BDD, Maven",
                language = language
            )
            }
        }

        // Earlier Experience Section
        Div(SectionStyle.toAttrs()) {
            Div(SectionContentStyle.toAttrs()) {
                H2(SectionTitleStyle.toAttrs()) { Text(Strings.earlierExperienceTitle[language].orEmpty()) }
                
                Div(Modifier.margin(bottom = 1.cssRem).toAttrs()) {
                    P(Modifier.fontWeight(FontWeight.SemiBold).toAttrs()) { 
                        Text(Strings.autoZoneTitle[language].orEmpty()) 
                    }
                    P { Text(Strings.autoZoneDescription[language].orEmpty()) }
                }
                
                Div {
                    P(Modifier.fontWeight(FontWeight.SemiBold).toAttrs()) { 
                        Text(Strings.labinalTitle[language].orEmpty()) 
                    }
                    P { Text(Strings.labinalDescription[language].orEmpty()) }
                }
            }
        }

        // Freelance Experience Section
        Div(AlternateSectionStyle.toModifier().backgroundColor(sitePalette.nearBackground).toAttrs()) {
            Div(AlternateSectionContentStyle.toAttrs()) {
                H2(SectionTitleStyle.toAttrs()) { Text(Strings.freelanceExperienceTitle[language].orEmpty()) }
                
                // Albiworks USA
                Div(Modifier.margin(bottom = 2.cssRem).toAttrs()) {
                    Div(CompanyStyle.toAttrs()) { Text(Strings.albiworksUSACompany[language].orEmpty()) }
                    Div(JobTitleStyle.toAttrs()) { Text(Strings.albiworksUSAJobTitle[language].orEmpty()) }
                    Div(DateStyle.toAttrs()) { Text(Strings.albiworksUSADates[language].orEmpty()) }
                    P { Text(Strings.albiworksUSADescription[language].orEmpty()) }
                    P(Modifier.fontStyle(FontStyle.Italic).fontSize(0.9.cssRem).margin(top = 0.5.cssRem).toAttrs()) { 
                        Text("${Strings.technologies[language].orEmpty()} C#, .NET 8.0, FTDI Chips and Interfaces") 
                    }
                }
                
                // Albiworks Mexico
                Div {
                    Div(CompanyStyle.toAttrs()) { Text(Strings.albiworksMexicoCompany[language].orEmpty()) }
                    Div(JobTitleStyle.toAttrs()) { Text(Strings.albiworksMexicoJobTitle[language].orEmpty()) }
                    Div(DateStyle.toAttrs()) { Text(Strings.albiworksMexicoDates[language].orEmpty()) }
                    P { Text(Strings.albiworksMexicoDescription[language].orEmpty()) }
                    P(Modifier.fontStyle(FontStyle.Italic).fontSize(0.9.cssRem).margin(top = 0.5.cssRem).toAttrs()) { 
                        Text("${Strings.technologies[language].orEmpty()} Java, JAX-RS, MongoDB, REST APIs, Maven, Jenkins") 
                    }
                }
            }
        }

        // Geographic Experience Section
        Div(SectionStyle.toAttrs()) {
            Div(SectionContentStyle.toAttrs()) {
                H2(SectionTitleStyle.toAttrs()) { Text(Strings.geographicExperienceTitle[language].orEmpty()) }
                P { Text(Strings.geographicExperienceDescription[language].orEmpty()) }
                Row(
                    Modifier.fillMaxWidth().justifyContent(JustifyContent.SpaceEvenly).margin(top = 1.cssRem),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.Top) {
                        Div(FlagStyle.toModifier().toAttrs()) {
                            SpanText("🇺🇸", Modifier.fontSize(32.px))
                        }
                        Column {
                            SpanText(Strings.unitedStates[language].orEmpty(), Modifier.fontWeight(FontWeight.SemiBold))
                            SpanText(Strings.unitedStatesDescription[language].orEmpty(), Modifier.fontSize(0.9.cssRem))
                        }
                    }
                    Row(verticalAlignment = Alignment.Top) {
                        Div(FlagStyle.toModifier().toAttrs()) {
                            SpanText("🇨🇦", Modifier.fontSize(32.px))
                        }
                        Column {
                            SpanText(Strings.canadaLocation[language].orEmpty(), Modifier.fontWeight(FontWeight.SemiBold))
                            SpanText(Strings.canadaDescription[language].orEmpty(), Modifier.fontSize(0.9.cssRem))
                        }
                    }
                    Row(verticalAlignment = Alignment.Top) {
                        Div(FlagStyle.toModifier().toAttrs()) {
                            SpanText("🇲🇽", Modifier.fontSize(32.px))
                        }
                        Column {
                            SpanText(Strings.mexico[language].orEmpty(), Modifier.fontWeight(FontWeight.SemiBold))
                            SpanText(Strings.mexicoDescription[language].orEmpty(), Modifier.fontSize(0.9.cssRem))
                        }
                    }
                }
            }
        }

        // Education Section
        Div(AlternateSectionStyle.toModifier().backgroundColor(sitePalette.nearBackground).toAttrs()) {
            Div(AlternateSectionContentStyle.toAttrs()) {
            H2(SectionTitleStyle.toAttrs()) { Text(Strings.educationTitle[language].orEmpty()) }
            Div(Modifier.margin(bottom = 1.cssRem).toAttrs()) {
                P(Modifier.fontWeight(FontWeight.SemiBold).toAttrs()) { Text(Strings.mastersDegree[language].orEmpty()) }
                P { Text(Strings.itesmLocation[language].orEmpty()) }
            }
            Div {
                P(Modifier.fontWeight(FontWeight.SemiBold).toAttrs()) { Text(Strings.bachelorsDegree[language].orEmpty()) }
                P { Text(Strings.itchLocation[language].orEmpty()) }
            }
            }
        }

        // Technical Skills Section
        Div(SectionStyle.toAttrs()) {
            Div(SectionContentStyle.toAttrs()) {
            H2(SectionTitleStyle.toAttrs()) { Text(Strings.technicalSkillsTitle[language].orEmpty()) }
            Row(Modifier.gap(2.cssRem)) {
                Column(Modifier.fillMaxWidth(50.percent)) {
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).toAttrs()) { Text(Strings.languagesTitle[language].orEmpty()) }
                    P { Text("Java, Kotlin, TypeScript, Python, Groovy, Swift, SQL") }
                    
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).margin(top = 1.cssRem).toAttrs()) { Text(Strings.backendTitle[language].orEmpty()) }
                    P { Text("Java, Kotlin, Spring Boot, Spring Framework, Kafka, Microservices, REST APIs, JSP, Swagger") }
                    
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).margin(top = 1.cssRem).toAttrs()) { Text(Strings.frontendTitle[language].orEmpty()) }
                    P { Text("React, Node.js, TypeScript, JavaScript, AngularJS, JQuery") }
                    
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).margin(top = 1.cssRem).toAttrs()) { Text(Strings.mobileTitle[language].orEmpty()) }
                    P { Text("Android, iOS, Jetpack Compose, Kotlin, Swift") }
                }
                Column(Modifier.fillMaxWidth(50.percent)) {
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).toAttrs()) { Text(Strings.cloudInfrastructureTitle[language].orEmpty()) }
                    P { Text("AWS (Lambda, DynamoDB, API Gateway, S3, CloudWatch, CDK, EventBridge, Step Functions, SQS, SNS, IAM, CloudFormation), AWS CDK, Kubernetes, OpenShift, Docker, Linux/Unix, Windows, Shell Scripting") }
                    
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).margin(top = 1.cssRem).toAttrs()) { Text(Strings.databasesTitle[language].orEmpty()) }
                    P { Text("DynamoDB, PostgreSQL, MySQL, Oracle, MongoDB, Elasticsearch, SQL, PL/SQL") }
                    
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).margin(top = 1.cssRem).toAttrs()) { Text(Strings.devopsTestingTitle[language].orEmpty()) }
                    P { Text("CI/CD, GitLab CI, Jenkins, Bamboo, Maven, Gradle, Git, JUnit, Mockito, SonarQube, Postman, Cucumber, Spock, TDD/BDD") }
                }
            }
            }
        }
    }
}

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
    language: Language
) {
    var expanded by remember { mutableStateOf(isExpanded) }
    val sitePalette = ColorMode.current.toSitePalette()
    
    Div(Modifier.margin(bottom = 2.cssRem).toAttrs()) {
        // Header (always visible, clickable)
        Div(
            Modifier
                .cursor(Cursor.Pointer)
                .onClick { expanded = !expanded }
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
                    if (expanded) "▼" else "▶",
                    Modifier
                        .fontSize(1.2.cssRem)
                        .color(sitePalette.brand.primary)
                )
            }
        }
        
        // Collapsible content
        if (expanded) {
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
