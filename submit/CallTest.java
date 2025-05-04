import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test class for Call class.
 * Tests functionality such as constructors, getters,
 * and duration decrement behavior.
 *
 * @author Ervin N Grodon III
 * @version Apr 03, 2025
 */
public class CallTest {
    private Call defaultCall;
    private Call regularCall;
    private Call vipCall;

    /**
     * Setup method to initialize Call objects.
     */
    @Before
    public void setUp() {
        defaultCall = new Call();
        regularCall = new Call("Alice", false, 5);
        vipCall = new Call("Bob", true, 3);
    }

    /**
     * Test default constructor values.
     */
    @Test
    public void testDefaultConstructor() {
        assertEquals("Unknown Caller", defaultCall.getName());
        assertFalse(defaultCall.isVIP());
        assertEquals(10, defaultCall.getDuration());
    }

    /**
     * Test parameterized constructor and getName().
     */
    @Test
    public void testGetName() {
        assertEquals("Alice", regularCall.getName());
        assertEquals("Bob", vipCall.getName());
    }

    /**
     * Test isVIP() returns correct status.
     */
    @Test
    public void testIsVIP() {
        assertFalse(regularCall.isVIP());
        assertTrue(vipCall.isVIP());
    }

    /**
     * Test getDuration() returns the correct call duration.
     */
    @Test
    public void testGetDuration() {
        assertEquals(5, regularCall.getDuration());
        assertEquals(3, vipCall.getDuration());
    }

    /**
     * Test decrement() reduces duration by 1.
     */
    @Test
    public void testDecrement() {
        regularCall.decrement();
        assertEquals(4, regularCall.getDuration());

        vipCall.decrement();
        assertEquals(2, vipCall.getDuration());
    }

    /**
     * Edge case: Test decrementing from 0 (should allow negative duration).
     */
    @Test
    public void testDecrementAtZero() {
        defaultCall.decrement();
        assertEquals(9, defaultCall.getDuration());
    }
}
