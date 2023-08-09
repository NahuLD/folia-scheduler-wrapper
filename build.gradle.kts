plugins {
    java
    `java-library`
    `maven-publish`
}

allprojects {
    group = "me.nahu.wrapper"
}

subprojects {
    apply {
        plugin("java")
        plugin("java-library")
        plugin("maven-publish")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = sourceCompatibility

        withSourcesJar()
        withJavadocJar()
    }

    repositories {
        mavenCentral()

        maven {
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        }

        maven {
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/public/")
        }

        maven {
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }

        maven {
            url = uri("https://nexuslite.gcnt.net/repos/paper/")
        }
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:23.0.0")

        testImplementation(platform("org.junit:junit-bom:5.9.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks {
        test {
            useJUnitPlatform()
        }
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }
    }
}