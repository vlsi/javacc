package com.javacc.builttools.bootstrap

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.*
import java.io.File
import org.gradle.kotlin.dsl.property
import org.gradle.process.JavaExecSpec

abstract class BaseJavaCCTask(
    folderName: String,
    objectFactory: ObjectFactory
) : DefaultTask() {
    @Classpath
    val javaCCClasspath = objectFactory.property<Configuration>()
        .convention(project.configurations.named(JavaCCPlugin.JAVACC_CLASSPATH_CONFIGURATION_NAME))

    @InputFile
    val inputFile = objectFactory.property<File>()

    @OutputDirectory
    val output = objectFactory.directoryProperty()
        .convention(project.layout.buildDirectory.dir("$folderName/$name"))

    @Input
    val sourceSetName = objectFactory.property<String>()
        .convention("main")

    abstract fun JavaExecSpec.configureJava()

    @TaskAction
    fun run() {
        // Remove existing files in the output directories
        project.delete(output.asFileTree)
        // Generate sources
        project.javaexec {
            classpath = javaCCClasspath.get()
            configureJava()
        }
    }
}
