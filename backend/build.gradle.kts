plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("java")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")
    
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

tasks.bootJar {
    archiveFileName.set("draw-steel-backend.jar")
}
