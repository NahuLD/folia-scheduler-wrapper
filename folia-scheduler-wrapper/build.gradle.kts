plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    api(project(":platform:common"))
    api(project(":platform:bukkit"))
    api(project(":platform:folia"))
    compileOnly("org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")

    testCompileOnly("org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")
    testImplementation("org.mockito:mockito-junit-jupiter:3.9.0")
}

tasks {
    shadowJar {
        destinationDirectory.set(rootProject.buildDir)
    }

    assemble {
        dependsOn(shadowJar)
    }
}