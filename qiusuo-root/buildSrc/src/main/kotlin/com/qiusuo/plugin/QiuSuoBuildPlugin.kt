package com.qiusuo.plugin

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import io.spring.gradle.dependencymanagement.internal.dsl.StandardDependencyManagementExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.MavenPlugin
import org.springframework.boot.gradle.plugin.SpringBootPlugin

class QiuSuoBuildPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.task("hello")
            .doLast { println("My first build sc") }
        target.configurePlugins()
        target.configureRepositories()
    }
}

const val springCloudVersion = "Hoxton.SR8"

fun Project.configurePlugins() {
    pluginManager.apply(SpringBootPlugin::class.java)
    pluginManager.apply(DependencyManagementPlugin::class.java)
    pluginManager.apply(JavaPlugin::class.java)
    pluginManager.apply(ApplicationPlugin::class.java)
    pluginManager.apply(MavenPlugin::class.java)
    extensions.getByType(StandardDependencyManagementExtension::class.java).imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

fun Project.configureRepositories() {
    repositories.mavenLocal()
    repositories.mavenCentral()
    repositories.jcenter()
}