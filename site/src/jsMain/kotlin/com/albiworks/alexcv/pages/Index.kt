package com.albiworks.alexcv.pages

import androidx.compose.runtime.*
import com.albiworks.alexcv.components.layouts.PageLayoutData
import com.albiworks.alexcv.toSitePalette
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
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
import kotlinx.browser.document
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.Element
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
    ctx.data.add(PageLayoutData("Alexandro Blanco Santana - Resume"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage() {
    val sitePalette = ColorMode.current.toSitePalette()
    
    Column(ResumeContainerStyle.toModifier()) {
        // Header Section
        Div(HeaderStyle.toAttrs()) {
            Row(HeaderContentStyle.toModifier(), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    "/foto_perfil.png", 
                    "Alexandro Blanco Profile Photo",
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
                        SpanText("+1 612 810 9998 ")
                    }
                    P {
                        Link("https://www.linkedin.com/in/alexandro-blanco-santana/", "LinkedIn")
                        SpanText(" | ")
                        Link("https://github.com/ti3r", "GitHub")
                        SpanText(" | ")
                        Link("mailto:ablanco.java@yahoo.com", "ablanco.java@yahoo.com")
                    }
                }
            }
        }

        // Profile Section
        Div(SectionStyle.toAttrs()) {
            Div(SectionContentStyle.toAttrs()) {
                H2(SectionTitleStyle.toAttrs()) { Text("Profile") }
                P(Modifier.margin(bottom = 0.75.cssRem).toAttrs()) {
                    Text("Senior Full Stack Software Engineer with 15+ years of experience building scalable enterprise applications and cloud infrastructure. Currently at Amazon, architecting backend services and mobile solutions for Robotics AI and Transportation Services.")
                }
                P(Modifier.margin(bottom = 0.75.cssRem).toAttrs()) {
                    Text("Expertise in AWS cloud architecture, microservices, and full-stack development with Java/Kotlin, React, and Node.js. Proven experience with containerization (Kubernetes, Docker, OpenShift), CI/CD pipelines, and modern DevOps practices.")
                }
                P {
                    Text("Results-oriented professional with proven track record leading technical initiatives, mentoring teams, and delivering high-impact solutions in fast-paced, time-sensitive environments.")
                }
            }
        }

        // Work Experience Section
        Div(AlternateSectionStyle.toModifier().backgroundColor(sitePalette.nearBackground).toAttrs()) {
            Div(AlternateSectionContentStyle.toAttrs()) {
            H2(SectionTitleStyle.toAttrs()) { Text("Work Experience") }
            
            // Amazon
            Div(Modifier.margin(bottom = 2.cssRem).toAttrs()) {
                Div(CompanyStyle.toAttrs()) { Text("Amazon, Minneapolis MN") }
                Div(JobTitleStyle.toAttrs()) { Text("Software Engineer L5") }
                Div(DateStyle.toAttrs()) { Text("March 2021 - Present") }
                P(Modifier.fontStyle(FontStyle.Italic).toAttrs()) { Text("Robotics AI & Amazon Transportation Services (ATS)") }
                P { Text("Architect and develop backend services and mobile applications supporting Amazon's middle-mile transportation network and warehouse computer vision systems. Lead infrastructure design, mentor engineers, and drive technical decisions for services handling millions of daily transactions.") }
                P(Modifier.fontWeight(FontWeight.SemiBold).margin(top = 0.5.cssRem).toAttrs()) { Text("Key Achievements:") }
                Ul {
                    Li { Text("Designed and implemented Android and iOS mobile applications enabling transportation partners to discover and execute product loads across Amazon's middle-mile network") }
                    Li { Text("Built scalable backend services using AWS Lambda, DynamoDB, and API Gateway supporting internal and external users") }
                    Li { Text("Developed and maintained internal developer tools using Node.js, React, and TypeScript") }
                    Li { Text("Developed AWS CDK infrastructure implementing comprehensive monitoring, alarms, and alerts to meet SLA requirements") }
                    Li { Text("Architected computer vision and ML solutions for COVID-19 proximity detection across Amazon distribution centers") }
                    Li { Text("Led design reviews and mentored junior engineers through code reviews and technical guidance") }
                    Li { Text("Established CI/CD pipelines ensuring continuous integration compliance and operational excellence") }
                }
                P(Modifier.fontStyle(FontStyle.Italic).fontSize(0.9.cssRem).margin(top = 0.5.cssRem).toAttrs()) { 
                    Text("Technologies: AWS (Lambda, DynamoDB, API Gateway, CDK, S3, CloudWatch), Java, Kotlin, Android, iOS, Node.js, React, TypeScript, Spring Boot, Kafka, AWS CDK") 
                }
            }

            // ICF (combined)
            Div(Modifier.margin(bottom = 2.cssRem).toAttrs()) {
                Div(CompanyStyle.toAttrs()) { Text("ICF (Olson/Next), Minneapolis MN") }
                Div(JobTitleStyle.toAttrs()) { Text("Jr. Software Architect (Promoted September 2017) | Sr. Software Engineer") }
                Div(DateStyle.toAttrs()) { Text("July 2016 - March 2021") }
                P(Modifier.fontStyle(FontStyle.Italic).toAttrs()) { Text("Tally Platform") }
                P { Text("Led architectural evolution of enterprise marketing platform over 4.5 years, progressing from senior engineer to architect. Drove adoption of modern cloud technologies, microservices architecture, and established technical standards across engineering teams.") }
                P(Modifier.fontWeight(FontWeight.SemiBold).margin(top = 0.5.cssRem).toAttrs()) { Text("Key Achievements:") }
                Ul {
                    Li { Text("Architected next-generation platform using Kafka, Elasticsearch, OpenShift, and Spring Boot") }
                    Li { Text("Designed and implemented scalable microservices architecture improving performance and enabling horizontal scaling") }
                    Li { Text("Deployed and managed containerized applications using Kubernetes, Docker, and OpenShift on Linux environments") }
                    Li { Text("Established CI/CD pipelines using Jenkins, GitLab CI, and Bamboo with automated testing and deployment") }
                    Li { Text("Implemented test automation framework using Mockito, JUnit, and SonarQube for code quality assurance") }
                    Li { Text("Developed Linux shell scripts for automation, monitoring, and deployment processes") }
                    Li { Text("Led migration from monolithic architecture to cloud-native microservices") }
                    Li { Text("Championed adoption of 12-Factor Application standards and modern architectural patterns") }
                    Li { Text("Implemented Service Oriented Architecture using enterprise design patterns") }
                    Li { Text("Coordinated cross-team development of platform features serving multiple clients") }
                    Li { Text("Mentored and managed junior developers during feature implementation") }
                }
                P(Modifier.fontStyle(FontStyle.Italic).fontSize(0.9.cssRem).margin(top = 0.5.cssRem).toAttrs()) { 
                    Text("Technologies: Kafka, Elasticsearch, OpenShift, Kubernetes, Docker, Spring Boot, Java, Spring Framework, Microservices, SOA, Linux/Unix, Shell Scripting, GitLab CI, Jenkins, Bamboo, Maven, Git, Mockito, SonarQube") 
                }
            }

            // Perficient
            Div(Modifier.margin(bottom = 2.cssRem).toAttrs()) {
                Div(CompanyStyle.toAttrs()) { Text("Perficient, Minneapolis MN") }
                Div(JobTitleStyle.toAttrs()) { Text("Sr. Software Engineer") }
                Div(DateStyle.toAttrs()) { Text("July 2015 - July 2016") }
                P(Modifier.fontStyle(FontStyle.Italic).toAttrs()) { Text("BestBuy & CaringBridge Projects") }
                P { Text("Developed RESTful APIs for financial systems and profile management serving web and mobile applications.") }
                P(Modifier.fontWeight(FontWeight.SemiBold).margin(top = 0.5.cssRem).toAttrs()) { Text("Key Achievements:") }
                Ul {
                    Li { Text("Built RESTful APIs delivering financial data for BestBuy decision-making systems") }
                    Li { Text("Developed profile and site management APIs for CaringBridge platform") }
                    Li { Text("Implemented services using Spring Boot, Spring Data, Oracle, and MongoDB") }
                }
                P(Modifier.fontStyle(FontStyle.Italic).fontSize(0.9.cssRem).margin(top = 0.5.cssRem).toAttrs()) { 
                    Text("Technologies: Java, Spring Boot, Spring Data, REST APIs, Oracle, MongoDB, Gradle") 
                }
            }

            // OLSON
            Div(Modifier.margin(bottom = 2.cssRem).toAttrs()) {
                Div(CompanyStyle.toAttrs()) { Text("OLSON, Minneapolis MN") }
                Div(JobTitleStyle.toAttrs()) { Text("Sr. Software Engineer") }
                Div(DateStyle.toAttrs()) { Text("June 2013 - July 2015") }
                P(Modifier.fontStyle(FontStyle.Italic).toAttrs()) { Text("Amtrak Guest Rewards") }
                P { Text("Developed and maintained Amtrak's loyalty program backend services and management website.") }
                P(Modifier.fontWeight(FontWeight.SemiBold).margin(top = 0.5.cssRem).toAttrs()) { Text("Key Achievements:") }
                Ul {
                    Li { Text("Built SOAP web services using Spring Framework and Hibernate") }
                    Li { Text("Maintained and enhanced management website using Node.js and AngularJS") }
                    Li { Text("Implemented Spring Batch jobs for data processing") }
                    Li { Text("Led migration from SVN to Git across multiple projects") }
                    Li { Text("Developed JMS messaging integration with external queue systems") }
                }
                P(Modifier.fontStyle(FontStyle.Italic).fontSize(0.9.cssRem).margin(top = 0.5.cssRem).toAttrs()) { 
                    Text("Technologies: Java, Spring Framework, Spring Batch, Hibernate, SOAP, JMS, Node.js, AngularJS, Git") 
                }
            }

            // Best Buy
            Div(Modifier.margin(bottom = 2.cssRem).toAttrs()) {
                Div(CompanyStyle.toAttrs()) { Text("Best Buy, Richfield MN") }
                Div(JobTitleStyle.toAttrs()) { Text("J2EE Developer") }
                Div(DateStyle.toAttrs()) { Text("April 2012 - July 2013") }
                P(Modifier.fontStyle(FontStyle.Italic).toAttrs()) { Text("Commerce API") }
                P { Text("Developed RESTful web services enabling partner integration with BestBuy's e-commerce platform.") }
                P(Modifier.fontWeight(FontWeight.SemiBold).margin(top = 0.5.cssRem).toAttrs()) { Text("Key Achievements:") }
                Ul {
                    Li { Text("Built REST APIs for order placement and product purchasing") }
                    Li { Text("Implemented BDD automated tests using Cucumber, Groovy, and Java") }
                    Li { Text("Developed interfaces with tax, pricing, and fulfillment systems") }
                    Li { Text("Established CI practices using Hudson, Jenkins, and Maven") }
                }
                P(Modifier.fontStyle(FontStyle.Italic).fontSize(0.9.cssRem).margin(top = 0.5.cssRem).toAttrs()) { 
                    Text("Technologies: Java, Spring Framework, REST APIs, Hibernate, Cucumber, BDD, Maven") 
                }
            }
            }
        }

        // Earlier Experience Section
        Div(SectionStyle.toAttrs()) {
            Div(SectionContentStyle.toAttrs()) {
                H2(SectionTitleStyle.toAttrs()) { Text("Earlier Experience") }
                
                Div(Modifier.margin(bottom = 1.cssRem).toAttrs()) {
                    P(Modifier.fontWeight(FontWeight.SemiBold).toAttrs()) { 
                        Text("AutoZone (2011-2012) - C++/Java Programmer") 
                    }
                    P { Text("Maintained electronic catalog and store applications using Java, Spring, C++, and QT Framework") }
                }
                
                Div {
                    P(Modifier.fontWeight(FontWeight.SemiBold).toAttrs()) { 
                        Text("Labinal de Mexico (2008-2010) - Systems Analyst/Programmer") 
                    }
                    P { Text("Developed Java EE web applications and .NET desktop applications") }
                }
            }
        }

        // Freelance Experience Section
        Div(AlternateSectionStyle.toModifier().backgroundColor(sitePalette.nearBackground).toAttrs()) {
            Div(AlternateSectionContentStyle.toAttrs()) {
                H2(SectionTitleStyle.toAttrs()) { Text("Freelance Experience") }
                
                // Albiworks USA
                Div(Modifier.margin(bottom = 2.cssRem).toAttrs()) {
                    Div(CompanyStyle.toAttrs()) { Text("Albiworks USA, Portland OR") }
                    Div(JobTitleStyle.toAttrs()) { Text("Principal Software Engineer") }
                    Div(DateStyle.toAttrs()) { Text("2024 - Present") }
                    P { Text("Developed desktop application connecting to custom hardware for automated electrical testing of PCB product prototypes for Polaris and TrailTech. Successfully launched in May 2025 and deployed to production lines in China.") }
                    P(Modifier.fontStyle(FontStyle.Italic).fontSize(0.9.cssRem).margin(top = 0.5.cssRem).toAttrs()) { 
                        Text("Technologies: C#, .NET 8.0, FTDI Chips and Interfaces") 
                    }
                }
                
                // Albiworks Mexico
                Div {
                    Div(CompanyStyle.toAttrs()) { Text("Albiworks, Chihuahua, Mexico") }
                    Div(JobTitleStyle.toAttrs()) { Text("CIO & Software Architect") }
                    Div(DateStyle.toAttrs()) { Text("2014 - Present") }
                    P { Text("Architected and developed cloud-based invoicing platform for Mexican market (CFDi digital invoices). Platform launched in 2014, currently in maintenance mode with periodic updates and support.") }
                    P(Modifier.fontStyle(FontStyle.Italic).fontSize(0.9.cssRem).margin(top = 0.5.cssRem).toAttrs()) { 
                        Text("Technologies: Java, JAX-RS, MongoDB, REST APIs, Maven, Jenkins") 
                    }
                }
            }
        }

        // Geographic Experience Section
        Div(SectionStyle.toAttrs()) {
            Div(SectionContentStyle.toAttrs()) {
                H2(SectionTitleStyle.toAttrs()) { Text("Geographic Experience") }
                P { Text("Extensive professional experience working across North America:") }
                Row(
                    Modifier.fillMaxWidth().justifyContent(JustifyContent.SpaceEvenly).margin(top = 1.cssRem),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Div(FlagStyle.toModifier().backgroundColor(Colors.Red).toAttrs()) {
                            SpanText("🇨🇦", Modifier.fontSize(24.px))
                        }
                        Column {
                            SpanText("Canada", Modifier.fontWeight(FontWeight.SemiBold))
                            SpanText("Remote collaboration", Modifier.fontSize(0.9.cssRem))
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Div(FlagStyle.toModifier().backgroundColor(Colors.Blue).toAttrs()) {
                            SpanText("🇺🇸", Modifier.fontSize(24.px))
                        }
                        Column {
                            SpanText("United States", Modifier.fontWeight(FontWeight.SemiBold))
                            SpanText("Minneapolis, MN", Modifier.fontSize(0.9.cssRem))
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Div(FlagStyle.toModifier().backgroundColor(Colors.Green).toAttrs()) {
                            SpanText("🇲🇽", Modifier.fontSize(24.px))
                        }
                        Column {
                            SpanText("Mexico", Modifier.fontWeight(FontWeight.SemiBold))
                            SpanText("Chihuahua, MX", Modifier.fontSize(0.9.cssRem))
                        }
                    }
                }
            }
        }

        // Education Section
        Div(AlternateSectionStyle.toModifier().backgroundColor(sitePalette.nearBackground).toAttrs()) {
            Div(AlternateSectionContentStyle.toAttrs()) {
            H2(SectionTitleStyle.toAttrs()) { Text("Education") }
            Div(Modifier.margin(bottom = 1.cssRem).toAttrs()) {
                P(Modifier.fontWeight(FontWeight.SemiBold).toAttrs()) { Text("Master's Degree: Information Technologies Administration") }
                P { Text("Monterrey's Institute of Technology and Superior Studies (ITESM), Chihuahua, Mexico") }
            }
            Div {
                P(Modifier.fontWeight(FontWeight.SemiBold).toAttrs()) { Text("Bachelor's Degree: Computer Systems Engineering") }
                P { Text("Chihuahua's Institute of Technology No. II (ITCH II), Chihuahua, Mexico") }
            }
            }
        }

        // Technical Skills Section
        Div(SectionStyle.toAttrs()) {
            Div(SectionContentStyle.toAttrs()) {
            H2(SectionTitleStyle.toAttrs()) { Text("Technical Skills") }
            Row(Modifier.gap(2.cssRem)) {
                Column(Modifier.fillMaxWidth(50.percent)) {
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).toAttrs()) { Text("Languages") }
                    P { Text("Java, Kotlin, TypeScript, Python, Groovy, Swift, SQL") }
                    
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).margin(top = 1.cssRem).toAttrs()) { Text("Backend") }
                    P { Text("Java, Kotlin, Spring Boot, Spring Framework, Kafka, Microservices, REST APIs, JSP, Swagger") }
                    
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).margin(top = 1.cssRem).toAttrs()) { Text("Frontend") }
                    P { Text("React, Node.js, TypeScript, JavaScript, AngularJS, JQuery") }
                    
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).margin(top = 1.cssRem).toAttrs()) { Text("Mobile") }
                    P { Text("Android, iOS, Jetpack Compose, Kotlin, Swift") }
                }
                Column(Modifier.fillMaxWidth(50.percent)) {
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).toAttrs()) { Text("Cloud & Infrastructure") }
                    P { Text("AWS (Lambda, DynamoDB, API Gateway, S3, CloudWatch, CDK, EventBridge, Step Functions, SQS, SNS, IAM, CloudFormation), AWS CDK, Kubernetes, OpenShift, Docker, Linux/Unix, Windows, Shell Scripting") }
                    
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).margin(top = 1.cssRem).toAttrs()) { Text("Databases") }
                    P { Text("DynamoDB, PostgreSQL, MySQL, Oracle, MongoDB, Elasticsearch, SQL, PL/SQL") }
                    
                    H3(Modifier.fontSize(1.1.cssRem).fontWeight(FontWeight.SemiBold).margin(bottom = 0.5.cssRem).margin(top = 1.cssRem).toAttrs()) { Text("DevOps & Testing") }
                    P { Text("CI/CD, GitLab CI, Jenkins, Bamboo, Maven, Gradle, Git, JUnit, Mockito, SonarQube, Postman, Cucumber, Spock, TDD/BDD") }
                }
            }
            }
        }
    }
}
