
/**
 * Project 3 CallCenter class, it implements (for a simulation)
 * a call center, keeping track of incoming calls and answering
 * calls at the front of the queue when it their time.
 * 
 * @author Ervin N Grodon III
 * @version Mar 09, 2025
 */
public class CallCenter
{
    /**
     * Instance variables for this class.
     * Define an array for the call queue,
     * and instance variable for the call
     * that is on the line, and counters
     * for the clock (time) and for the
     * number of elements in the queue.
     */
    private Call[] callQueue; 
    private Call callOnTheLine; 
    private int clock; 
    private int size;
    /**
     * Default constructor for the CallCenter, initializes
     * all instance variables with default values.
     */
    public CallCenter()
    {
        // TODO
        this.callQueue = new Call[10]; 
        this.callOnTheLine = null;
        this.clock = 0;
        this.size = 0;
    }

    /**
     * Adds a call to the call queue.
     * 
     * @param n name of the caller.
     * @param v vip status of the caller.
     * @param d during of this call.
     * @return true if the call is added to the call queue, 
     * false otherwise.
     */
    public boolean addCall(String n, boolean v, int d)
    {
        if (n == null || n.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        }
        if (d <= 0) {
            throw new IllegalArgumentException("Call duration must be positive.");
        }
        if (size >= 10) {
            return false; // Queue is full
        }
        Call newCall = new Call(n,v,d);
        
        if (v) {
            // Shift all elements right to make space at index 0
            for (int i = Math.min(size, 9); i > 0; i--) {
                callQueue[i] = callQueue[i - 1];
            }
            callQueue[0] = newCall;
        } else {
            callQueue[size] = newCall; // Add at the end
        }
        size++;
        return true;
    }
    

    /**
     * Simulates answering the call from the call
     * in the front of the queue.
     * @return null if an error is found, or the call 
     * object if answered.
     */
    public Call answerCall()
    {
        if (callOnTheLine != null || size == 0) {
            return null; 
        }
        callOnTheLine = callQueue[0]; 
        
        for (int i = 0; i < size - 1; i++) {
            callQueue[i] = callQueue[i + 1];
        }
        callQueue[size - 1] = null; 
        size--;
        return callOnTheLine;
    

    }

    /**
     * The call center has a max capacity, at which point it
     * cannot take new calls until it answers and hangs up on
     * another call. This returns true if we are at capacity.
     * @return true if call center is full
     */
    public boolean isCallCenterAtCapacity()
    {
        return size >= callQueue.length;
    }

    /**
     * Returns the number of calls waiting to be answered.
     * @return number of calls in queue
     */
    public int numCallsWaiting()
    {
        return size;
    }

    /**
     * Given a caller name, this returns the position in 
     * the queue for a caller. If there is no call with 
     * that name, then it returns -1. Worth noting that 
     * the call at the front of the queue is at position
     * 1 (but internally it is at index 0)
     * @param n name of the caller to search for
     * @return position (1..n) or -1 if not found
     */
    public int positionInLine(String n)
    {
        if (n != null) {
            
            for (int i = 0; i < size; i++) {
                if (callQueue[i] != null && callQueue[i].getName().equals(n)) {
                    return i + 1; 

                }
            }
        }
        return -1 ;
     
    }

    /**
     * Is the call center busy, i.e., are they on a call
     * right now?
     * @return true if call center is on a call
     */
    public boolean isBusy()
    {
        return callOnTheLine != null;
    }

    /**
     * Returns the call object that is current on the call 
     * with the call center. If there is no current call, 
     * returns null.
     * @return call object for the current call.
     */
    public Call onTheLine()
    {
        if(isBusy()){
            return callOnTheLine;
        }else{
            return null;
        }
    }

    /**
     * Simulates time passing. The clock ticks and decrements
     * the time left on the call for a call that is online.
     */
    public void clockTick()
    {
        clock++;
        if (callOnTheLine != null) {
            callOnTheLine.decrement();
            if (callOnTheLine.getDuration() <= 0) {
                callOnTheLine = null; 
            }
        }
    }

    /**
     * Returns the current time in the simulation.
     * @return value of the clock
     */
    public int getClock()
    {
        return clock;
    }
}
