plugins {
    id("com.qiusuo.build")
}


val queryDslVersion = "4.3.1"
val lombokVersion = "1.18.12"

dependencies {
    implementation(project(":qiusuo-platform"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")


    // Lombok
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    implementation("org.projectlombok:lombok:${lombokVersion}")

    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("com.querydsl:querydsl-jpa:${queryDslVersion}")

    implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:7.0.1")
    implementation("com.graphql-java-kickstart:graphiql-spring-boot-starter:7.0.1")
    testImplementation("com.graphql-java-kickstart:graphql-spring-boot-starter-test:7.0.1")
    implementation("javax.inject:javax.inject:1")
    implementation("javax.xml.bind:jaxb-api:2.3.1")

    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-config-client")

    implementation("org.slf4j:slf4j-api:1.7.25")
}