public interface Event {

    public int getUniqueId();

    /**
     * Set the insertion time of the Event.
     * <p>
     * This method is called by the future event list, passing the simulation time of Event insertion as an argument.
     * The arrival time of the Event may also be computed here.
     *
     * @param  currentTime  the simulation time when this Event is inserted into the future event list
     */
    public void setInsertionTime(int currentTime);

    /**
     * Returns the insertion time of this Event.
     * <p>
     * Insertion time is the simulation time in which this Event is inserted into the future event list.  This should
     * not change once set.
     *
     * @return      the Event's arrival time
     */
    public int getInsertionTime();

    /**
     * Returns the arrival time of this Event.
     * <p>
     * Arrival time represents the time in which an Event is removed from the future event list for it to be
     * executed or handled.  For example, if this Event represents a network packet, then the arrival time
     * represents the time in which the network packet 'arrives' at the destination host.  If the Event is a Timer,
     * then the arrival time is the absolute time in which the timer expires.
     * <p>
     * This arrival time is what the future event list uses for sort order when inserting an Event, therefore it should
     * not change.
     *
     * @return      the Event's arrival time
     */
    public int getArrivalTime();

    /**
     * Cancel the Event.
     * <p>
     * This occurs after the Event has been removed from the future event list, probably before the arrival time has
     * been reached.  Because the time of removal may be arbitrary, this time is passed in as an argument to the
     * method.
     *
     * @param  currentTime  the simulation time when this Event is removed from the future event list
     */
    public void cancel(int currentTime);

    /**
     * Handle (or execute) the Event.
     * <p>
     * This occurs after the Event has been removed from the future event list, due to the arrival time being reached.
     * For example, if this Event represents a network packet, then calling handle() will 'process' the packet at the
     * destination host.  If the Event is a Timer, then this will execute whatever needs to be done upon timer expiry.
     */
    public void handle();

}