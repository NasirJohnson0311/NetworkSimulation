import java.util.Timer;

public class ArrayEventList extends Timer implements FutureEventList {
    private static int numElements = 0;
    private int simulationTime = 0;
    private  int size = 5;
    private Event[] futureEventList = new Event[size];

    public Event removeFirst(){
        Event firstEvent = null;
        firstEvent = futureEventList[0];


        simulationTime = futureEventList[0].getArrivalTime();       // Might need to edit

        Event[] tmp = new Event[numElements - 1];
        int tmpIndex = 0;
        for (int eventIndex = 1; eventIndex < numElements; eventIndex++){
            tmp[tmpIndex] = futureEventList[eventIndex];
            tmpIndex++;
        }

        futureEventList = new Event[size];

        for (int i = 0; i < tmp.length; i++){
            futureEventList[i] = tmp[i];
        }

        numElements--;
        return firstEvent;
    }
    public boolean remove(Event e){
        boolean found = true;
        if (binarySearch(e, futureEventList, 0, numElements - 1) > 0){

            int index = binarySearch(e, futureEventList, 0, numElements - 1);
            futureEventList[index].cancel(getSimulationTime());

            Event[] tmp = new Event[numElements - 1];
            int tmpIndex = 0;
            for (int eventIndex = 0; eventIndex < numElements; eventIndex++){

                if (futureEventList[eventIndex].getUniqueId() == futureEventList[index].getUniqueId()){
                    continue;
                }
                tmp[tmpIndex] = futureEventList[eventIndex];
                tmpIndex++;
            }

            futureEventList = new Event[size];
            for (int i = 0; i < tmp.length; i++){
                futureEventList[i] = tmp[i];
            }

            numElements--;

            return found;
        }
        else{
            return false;
        }
    }
    public void insert(Event e){

        ArrayEventList sizeObj = new ArrayEventList();

        if (numElements == futureEventList.length){

            Event[] tmp = new Event[size];
            int index = 0;
            for (Event obj : futureEventList){
                tmp[index] = obj;
                index++;
            }

            futureEventList = new Event[size *= 2];
            index = 0;
            for (Event obj : tmp){
                futureEventList[index] = obj;
                index++;
            }

            futureEventList[index] = e;
            numElements++;
        }

        else{

            int index = 0;
            for (Event obj : futureEventList){
                if (obj == null){
                    futureEventList[index] = e;
                }
                else{
                    index++;
                }
            }

            numElements++;
        }

        e.setInsertionTime(simulationTime);     // Might need to edit

        if (numElements >= 2) {
            for (int i = 0; i < (numElements); i++) {
                for (int j = 0; j < (numElements); j++) {
                    if (futureEventList[j].getArrivalTime() > futureEventList[i].getArrivalTime()) {
                        Event tmp = futureEventList[i];
                        futureEventList[i] = futureEventList[j];
                        futureEventList[j] = tmp;
                    }
                }
            }
        }

        System.out.println(e);

    }
    public int size(){
        /*
        int size = 0;

        for (int index = 0; index < futureEventList.length; index++){
            if (futureEventList[index] == null){
                break;
            }
            else{
                size++;
            }
        }

         */
        return numElements;
    }
    public int capacity(){
        return futureEventList.length;
    }
    public int getSimulationTime(){
        return simulationTime;
    }

    private int binarySearch(Event e, Event[] futureEventList, int left, int right){

        if (left > right){
            return -1;
        }

        int middle = (left + right) / 2;

        if (e.getArrivalTime() < futureEventList[middle].getArrivalTime()){
            return binarySearch(e, futureEventList , left, middle - 1);
        } else if (e.getArrivalTime() > futureEventList[middle].getArrivalTime()) {
            return binarySearch(e, futureEventList, middle + 1, right);
        }
        else {
            if (e.getUniqueId() != futureEventList[middle].getUniqueId()){
                for (int i = 0; i < numElements; i++){
                    if (futureEventList[i].getUniqueId() == e.getUniqueId()){
                        return i;
                    }
                }
            }
            return middle;
        }


    }

}
