plugins {
    id("java")
}

group = "me.nahu.folia"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = sourceCompatibility
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}