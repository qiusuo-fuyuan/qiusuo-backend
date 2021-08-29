plugins {
    id("com.qiusuo.build")
    id("com.palantir.docker") version "0.22.1"
}

// Run `gradle build docker` in terminal to build docker image
docker {
    name = "qiusuo/gateway:".plus("v1")
    copySpec.from("build").into("build")
    setDockerfile(file("../deploy/docker/Dockerfile"))
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-config-client")
    implementation("org.springframework.boot:spring-boot-starter-log4j2:2.5.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
}


configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}