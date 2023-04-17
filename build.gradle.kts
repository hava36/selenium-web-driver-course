plugins {
    id("java")
}

group = "ru.raiffeisen.education"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.8.3")
}

tasks.test {
    useJUnitPlatform()
}