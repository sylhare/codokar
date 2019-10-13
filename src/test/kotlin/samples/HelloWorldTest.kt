package samples

import org.junit.Assert.assertEquals
import org.junit.Test

internal class HelloWorldTest {

    @Test
    fun greetingTest() {
        assertEquals("Hello, world!", HelloWorld().greeting())
    }
}