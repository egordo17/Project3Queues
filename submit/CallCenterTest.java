import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test class for CallCenter.
 */
public class CallCenterTest {
    private CallCenter center;

    /**
     * Setup method, runs before each test case.
     * Initializes CallCenter object for testing.
     */
    @Before
    public void setUp() {
        center = new CallCenter();
    }

    /**
     * Tests if a non-VIP call is added correctly.
     */
    @Test
    public void testAddNonVIPCall() {
        boolean added = center.addCall("Alice", false, 3);
        assertTrue(added);
    }

    /**
     * Tests if a VIP call is placed at the front of the queue.
     */
    @Test
    public void testAddVIPCallAtFront() {
        center.addCall("Bob", false, 2);
        center.addCall("Carol", true, 4); 

        int position = center.positionInLine("Carol");
        assertEquals(1, position); 
    }

    /**
     * Tests if a call is answered correctly and removed from queue.
     */
    @Test
    public void testAnswerCall() {
        center.addCall("Dan", false, 2);
        Call answered = center.answerCall();
        assertNotNull(answered);
        assertEquals("Dan", answered.getName());
    }

    /**
     * Tests if the call center is at capacity after adding 10 calls.
     */
    @Test
    public void testCallCenterAtCapacity() {
        for (int i = 0; i < 10; i++) {
            center.addCall("Caller" + i, false, 1);
        }
        assertTrue(center.isCallCenterAtCapacity());
    }

    /**
     * Tests if the call center reports the number of calls waiting correctly.
     */
    @Test
    public void testNumCallsWaiting() {
        center.addCall("Eve", false, 1);
        assertEquals(1, center.numCallsWaiting()); 
    }

    /**
     * Tests position in line returns -1 if the caller is not found.
     */
    @Test
    public void testPositionInLineNotFound() {
        assertEquals(-1, center.positionInLine("NotHere"));
    }
    /**
     * Tests position in line returns -1 if the caller is not found.
     */
    @Test
    public void testPositionInLineNull() {
        assertEquals(-1, center.positionInLine(""));
    }
    /**
     * Tests if call center reports busy when on a call.
     */
    @Test
    public void testIsBusyTrue() {
        center.addCall("Frank", false, 2);
        center.answerCall();
        assertTrue(center.isBusy());
    }

    /**
     * Tests if call center reports not busy when idle.
     */
    @Test
    public void testIsBusyFalse() {
        assertFalse(center.isBusy());
    }

    /**
     * Tests onTheLine returns null if no call is active.
     */
    @Test
    public void testOnTheLineNull() {
        assertNull(center.onTheLine());
    }

    /**
     * Tests clock ticking advances time and ends call after duration.
     */
    @Test
    public void testClockTickAndCallEnds() {
        center.addCall("Grace", false, 1);
        center.answerCall();
        center.clockTick(); 
        assertFalse(center.isBusy()); 
    }

    /**
     * Tests getClock returns correct value after several ticks.
     */
    @Test
    public void testGetClock() {
        center.clockTick();
        center.clockTick();
        assertEquals(2, center.getClock());
    }
}
