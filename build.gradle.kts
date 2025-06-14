plugins {
    id("java")
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.github.node-gradle.node") version "5.0.0"
}

group = "me.jx4e"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("com.j2html:j2html:1.5.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Faker
    implementation("com.github.javafaker:javafaker:0.14") {
        exclude(group = "org.yaml", module = "snakeyaml")
    }
    implementation("org.yaml:snakeyaml:2.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    "developmentOnly"("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
}

tasks.test {
    useJUnitPlatform()
}

// --- Node.js / NPM configuration ---
node {
    version.set("16.14.0")     // your Node.js version here
    npmVersion.set("8.3.1")    // your npm version here
    download.set(true)         // auto-download node/npm
    workDir.set(file("${layout.buildDirectory}/nodejs"))      // equivalent to installDirectory in Maven plugin
    nodeProjectDir.set(file("src/main/frontend")) // frontend working directory
}

// The npmInstall task is created by the plugin by default; you can configure it if needed:
tasks.named<com.github.gradle.node.npm.task.NpmInstallTask>("npmInstall") {
    // no extra config needed for basic npm install
}

// Register the npmBuild task (runs `npm run build`):
tasks.register<com.github.gradle.node.npm.task.NpmTask>("npmBuild") {
    dependsOn("npmInstall")
    args.set(listOf("run", "build"))
}

// Make npmBuild run before processing resources:
tasks.named("processResources") {
    dependsOn("npmBuild")
}
