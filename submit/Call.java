/**
 * Project 3 - Call class, used to model a caller
 * into a call center. It stores name of the caller,
 * VIP status and duration of the call.
 * 
 * @author  Ervin N Gordon III
 */
public class Call 
{
    /**
     * declare instance variables for name, vip, 
     * and duration.
     */
    private String name;
    private boolean vip;
    private int duration;
    /**
     * Default constructor set name to null
     * vip to false and duration to 0.
     */
    public Call()
    {
        // TODO put code here
        this.name = "Unknown Caller";
        this.vip = false;
        this.duration = 10;
    }

    /**
     * Constructor that initializes all instance variables
     * with values passed as parameters.
     * @param name is the name of the caller.
     * @param vip boolean true if caller is VIP, false otherwise
     * @param duration length of the call in fake units
     */
    public Call(String name, boolean vip, int duration )
    {
        // TODO
        this.name = name;
        this.vip = vip;
        this.duration = duration;
    }
    /**Gives the user access to the name of the caller.
     * @return Name is the name of the user.
     */
    public String getName()
    {
        return name;
    }

    /**Determine is the user is a vip.
     * @return vip is true or false.
     */
    public boolean isVIP()
    {
        return vip;
    }

    /**Gives the user access to ther call duration.
     * @return duration which is the amount of time the call lasted.
     */
    public int getDuration()
    {
        return duration;
    }
    /**Decrements the duration of the calls.
     */
    public void decrement()
    {
        duration--;
    }
}
