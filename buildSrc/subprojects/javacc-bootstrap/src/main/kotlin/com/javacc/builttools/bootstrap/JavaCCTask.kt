package com.javacc.builttools.bootstrap

import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional
import org.gradle.kotlin.dsl.property
import org.gradle.process.JavaExecSpec
import javax.inject.Inject

open class JavaCCTask @Inject constructor(
    objectFactory: ObjectFactory
) : BaseJavaCCTask("javacc", objectFactory) {
    @Input
    val lookAhead = objectFactory.property<Int>().convention(1)

    @Optional
    @InputDirectory
    val templateSourceDirectory = objectFactory.directoryProperty()

    override fun JavaExecSpec.configureJava() {
        // The class is in the top-level package
        main = "javacc.Main"
        args("-LOOKAHEAD:${lookAhead.get()}")
        args("-BASE_SRC_DIR:${output.get()}")
        if (templateSourceDirectory.isPresent) {
            args("-TEMPLATE_SRC_DIR:${templateSourceDirectory.get()}")
        }
        // args("-OUTPUT_DIRECTORY:${allGeneratedDirectory.get()}")
        args(inputFile.get())
    }
}
