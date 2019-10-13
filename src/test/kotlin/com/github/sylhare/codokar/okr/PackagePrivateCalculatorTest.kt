import com.github.sylhare.codokar.okr.*
import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.file.Paths

internal class PackagePrivateCalculatorTest {

    // private var BASE_DIR = System.getProperty("user.dir") // main directory
    private var BASE_DIR = Paths.get("demo").toAbsolutePath().toString()

    @Test
    internal fun getRightNumberOfClasses() {
        assertEquals(2, BASE_DIR.javaClasses.size)
        assertEquals(3, BASE_DIR.kotlinClasses.size)
        assertEquals(5, BASE_DIR.allClasses.size)
    }

    @Test
    internal fun getAllPublicClasses() {
        assertEquals(1, BASE_DIR.javaPublicClasses.size)
        assertEquals(1, BASE_DIR.kotlinPublicClasses.size)
        assertEquals(2, BASE_DIR.allPublicClasses.size)
    }

    @Test
    internal fun goodPercentage() {
        assertEquals("60.0", BASE_DIR.percentagePrivateClasses)
    }


}