import com.github.sylhare.codokar.okr.*
import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.file.Paths

internal class PackagePrivateCalculatorTest {

    companion object {
        // private var BASE_DIR = System.getProperty("user.dir") // main directory
        private var DEMO_DIR = Paths.get("demo").toAbsolutePath().toString() // demo directory
    }

    @Test
    internal fun getRightNumberOfClasses() {
        print(DEMO_DIR)
        assertEquals(2, DEMO_DIR.javaClasses.size)
        assertEquals(3, DEMO_DIR.kotlinClasses.size)
        assertEquals(5, DEMO_DIR.allClasses.size)
    }

    @Test
    internal fun getAllPublicClasses() {
        assertEquals(1, DEMO_DIR.javaPublicClasses.size)
        assertEquals(1, DEMO_DIR.kotlinPublicClasses.size)
        assertEquals(2, DEMO_DIR.allPublicClasses.size)
    }

    @Test
    internal fun goodPercentage() {
        assertEquals("60.0", DEMO_DIR.percentagePrivateClasses)
    }


}