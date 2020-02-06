package org.javacc.test.plsql

import org.javacc.test.plsql.Token
import org.junit.jupiter.api.Test
import java.lang.Exception
import java.util.*
import java.util.function.Consumer

class PlsqlFuzzer {
    @Test
    internal fun name() {
        val r = Random()
        val fuzzer = PlSqlFuzzyLexer(r)
        val res = mutableListOf<String>()
        for(i in 1..1) {
            res.clear()
            val parser = PlSqlFuzzyParser(r)
            parser.tokenOutput = Consumer<Token> { res.add(it.image) }
            parser.Select()
            println(res.joinToString(" "))
            println()
//            println(fuzzer.jj_generate_S_IDENTIFIER_144())
        }
    }

    @Test
    internal fun samples() {
        val r = Random()
        for(i in 1..1000) {
            val fuzzer = PlSqlFuzzyParser(r)
            val res = mutableListOf<String>()
            fuzzer.tokenOutput = Consumer<Token> { res.add(it.image) }
            try {
                fuzzer.CompilationUnit()
                println()
                println(res.joinToString(" "))
            } catch (e: Exception) {
                // println("$i: ${e.message}")
            }
        }
    }
}
