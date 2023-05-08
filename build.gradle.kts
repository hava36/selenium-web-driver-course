plugins {
    id("java")
}

group = "ru.raiffeisen.education"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.8.3")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testCompileOnly("org.projectlombok:lombok:1.18.8")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.8")
}

tasks.test {
    useJUnitPlatform()
}