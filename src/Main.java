import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws IOException {

        int linesInFile;

        String fileName;

        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter file name: ");
        fileName = scnr.nextLine();
        ArrayEventList obj = new ArrayEventList();
        ArrayList<Event> cacheList = new ArrayList<Event>();


        try (FileInputStream fileInputStream = new FileInputStream(fileName)){

            scnr = new Scanner(fileInputStream);
            linesInFile = countLines(scnr, fileName);

            String[] currLine = null;
            Event[] eventArray = new Event[linesInFile];

            for (int i = 0; i < linesInFile; i++){
                currLine = scnr.nextLine().split(" ", 2);

                if (currLine[0].equals("I")) {
                    eventArray[i] = createEvent(currLine[1]);
                    obj.insert(eventArray[i]);
                    cacheList.add(eventArray[i]);


                }
                else if (currLine[0].equals("R")) {
                    obj.removeFirst().handle();
                }
                else if (currLine[0].equals("C")) {

                    for(Iterator<Event> iterator = cacheList.iterator(); iterator.hasNext();){
                        Event eventObj = iterator.next();
                        if (eventObj.getUniqueId() == Integer.parseInt(currLine[1])){
                            iterator.remove();
                            obj.remove(eventObj);
                        }
                    }
                }
            }

        }

        System.out.println();
        System.out.println("Future event list size " + obj.size());
        System.out.println("Future event list capacity: " + obj.capacity());

    }


    public static int countLines(Scanner scnr, String fileName) throws IOException {
        int count = 0;
        String tmp;

        try(FileInputStream inputStream = new FileInputStream(fileName)){

            scnr = new Scanner(inputStream);

            while(scnr.hasNext()){
                tmp = scnr.nextLine();
                count++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        return count;
    }
    public static Event createEvent(String duration){
        Timer newEvent = new Timer(Integer.parseInt(duration));
        return newEvent;
    }

}
