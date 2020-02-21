import com.javacc.builttools.bootstrap.BaseJavaCCTask
import com.javacc.builttools.bootstrap.JavaCCTask

plugins {
    `javacc-bootstrap`
    id("com.github.vlsi.ide")
}

dependencies {
    implementation(files("$rootDir/bin/freemarker.jar"))
}

val javacc by tasks.registering(JavaCCTask::class) {
    description = "Generate the Java CC Main Parser"
    // For now the grammar has to be co-located with template files, so they get preference
    // over the files inside javacc.jar
    inputFile.set(file("src/main/resources/javacc/output/java/JavaCC.javacc"))
}

ide {
    tasks.withType<BaseJavaCCTask> {
        generatedJavaSources(this, output.get().asFile)
    }
}
