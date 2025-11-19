package dev.whitespace.service

import dev.whitespace.service.bundler.Bundler
import dev.whitespace.service.bundler.Input
import io.quarkus.test.junit.QuarkusTest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.nio.file.Path
import kotlin.io.path.Path


@QuarkusTest
class BundlerTest {

    @Test
    fun `throws on zero recordables`() {
        assertThrows<IllegalArgumentException> {
            runBlocking { Bundler(Input(listOf())).run() }
        }
    }

    @Test
    fun `expects initial recordables are of fixed types`() {
        runBlocking {
            Bundler(Input.fromPath(resourcePath("/fixtures/short_recordables"))).run()
        }
    }

    companion object {
        fun resourcePath(path: String): Path {
            return Path(BundlerTest::class.java.getResource(path)!!.path)
        }
    }
}