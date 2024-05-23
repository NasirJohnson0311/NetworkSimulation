
public class Timer implements Event {

    private static int nextInt = 0;
    private int uniqueId;
    private int duration;
    private int insertionTime;
    private int arrivalTime;

    public Timer(){
        this.uniqueId = nextInt++;
    }
    public Timer(int duration){
        this.uniqueId = nextInt++;
        this.duration = duration;
        this.insertionTime = 0;
        this.arrivalTime = insertionTime + duration;       // Might need to edit
    }

    public int getUniqueId(){
        return uniqueId;
    }
    public void setInsertionTime(int currentTime){
        insertionTime = currentTime;
    }
    public int getInsertionTime(){
        return insertionTime;
    }
    public int getArrivalTime(){
        return (duration + insertionTime);
    }
    public void cancel(int currentTime){
        System.out.println("Timer " + uniqueId + " canceled at time: " + currentTime);
    }
    public void handle(){
        System.out.println("Timer " + uniqueId + " handled (arrival time: " + getArrivalTime() + ")");
    }

    public String toString(){
        return ("Timer " + uniqueId + " (insertion time: " + getInsertionTime() + ", arrival time: " + getArrivalTime() + ")");
    }

}
