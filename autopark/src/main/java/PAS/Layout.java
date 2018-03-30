package PAS;
//Layout Class
//This class decribes the parking layout which will be inputted by the user

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.awt.Point;                                                          //Imported this library for the Point class

public class Layout extends Thread{

    //Main class begins
    //Data members declaration segment

    private double                  average_SOU_accuracy;                       //holds the latest calculated average SOU accuracy
    private int                     current_car_count;                          //holds the current number of cars in the layout
    private int                     capacity;                                   //holds the capacity of the layout,i.e, tells us the total number of slots
    private int                     number_of_destinations;                     //holds the number of destinations present near the layput available for console menu
    private int                     total_number_of_cars;                       //holds the number of cars in total over a timeline
    private int                     total_number_of_days;                       //holds the number of days since the start of the implementation f the software on the Layout
    private LinkedList<Integer>     offense_list;                               //holds the slot ids which have been marked as 'offense'
    private Destination[]           destination_list;                           //holds the information of the available destinations at the Layout
    public Slot[]                   slot_list;                                  //holds the information of the slots present at the parking layout

    private boolean                 open;

    //Methods declaration

    public Layout(){

        //Default constructor
        number_of_destinations = 0;
        capacity = 0;
        open = false;
    }

    public double getAverageSOUAccuracy() {
        return average_SOU_accuracy;
    }

    public int getCurrentCarCount() {
        return current_car_count;
    }

    public int getTotalNumberOfCars() {
        return total_number_of_cars;
    }

    public void setTotalNumberOfCars(int total_number_of_cars) {
        this.total_number_of_cars = total_number_of_cars;
    }

    public int getTotalNumberOfDays() {
        return total_number_of_days;
    }

    public LinkedList<Integer> getOffenseList() {
        return offense_list;
    }

    public void inputOffenseList(LinkedList<Integer> offense_list) {
        this.offense_list = offense_list;
    }

    private void getNumberOfDestinationsAndSlots(int rows, int cols, Object[][] data){

        //Function to get the number of destinations and slots

        //Get the dataEntries and put it intp a temp array
        int r = rows;
        int c = cols;
        int i = 0 , j = 0;                                                      //loop variable

        //Go through the 2D array and extract the information
        for(i=0;i<r;++i){

            for(j=0;j<c;++j){

                if(data[i][j] == "P")
                    capacity++;
                else if(data[i][j] == "D")
                    number_of_destinations++;

                //System.out.print(data[i][j]);
            }
            //System.out.println("hi");
        }
    }

    private void extractDestinationsFromLayout(int rows, int cols, Object[][] data){

        //Function to get the destinations from the layout

        int i =0, j=0 , k=0;                                                  //loop variables
        destination_list = new Destination[number_of_destinations];

        //We have to run through the layout and read all the 'D**'s in the grid. They stand for destinations.
        //We have to identify their coordinates, assign destinaton IDs
        for(i = 0; i < rows; ++i){

            for(j = 0; j < cols; ++j){

                //condition check.
                if(data[i][j] == "D"){

                    //add to destination list
                    //destination found -> create an object
                    Point p = new Point(i,j);

                    destination_list[k] = new Destination();
                    destination_list[k].inputDestID(k+1);
                    destination_list[k].inputDestCoord(p);
                    k = k +1;
                }
            }
        }
        //get names for dest
    }

    private void extractSlotsFromLayout(int rows, int cols, Object[][] data){

        //Function to extract the slots from the grid

        int i = 0, j = 0, k = 0;                                                      //loop variables
        slot_list = new Slot[capacity];

        //We have to run through the layout and read all the 'P***'s in the grid. They stand for slots.
        //We have to identify their coordinates, assign slot IDs
        for(i = 0; i < rows; ++i){

            for(j = 0; j < cols; ++j){

                //condition check.
                if(data[i][j] == "P"){

                    //slot found -> create an object
                    //assign id and coordinates
                    Point p = new Point(i,j);
                    slot_list[k] = new Slot(k+1 );


                    slot_list[k].inputSlotCoord(p);
                    k = k + 1;
                }
            }
        }
    }

    private void assignDistances(){

        //Function to assign distances to all slots to all destination_list
        double[] distances = new double[number_of_destinations];                //holds the distances
        int i=0, j=0;                                                              //loop variables

        for(i = 0; i < capacity; ++i){

            //going through the slot array list and get coordinates
            Point temp_slot_p = slot_list[i].getSlotCoord();
            System.out.println(temp_slot_p.getX() + " " + temp_slot_p.getY());

            for(j = 0; j < number_of_destinations; ++j){

                //creating temp variable for destination
                Destination temp_D = destination_list[j];

                System.out.println(temp_D.getDestCoord().getX() + " " + temp_D.getDestCoord().getY());

                //Find the shortest distance between the slot and destination
                distances[j] = temp_D.getDistance(temp_slot_p);

            }

            //assign the values to data member of slot Class
            slot_list[i].setDistances(distances,number_of_destinations);
        }
    }

    public void extractInformation(int r, int c, Object[][] data){

        //Function to get information from grid layout
        getNumberOfDestinationsAndSlots(r,c,data);
        extractDestinationsFromLayout(r,c,data);
        extractSlotsFromLayout(r,c,data);
        assignDistances();
    }

    public int getSlotIDFromCoord(int x , int y){

        //Function to obtain slot id from coordinates
        int i = 0 ;
        Point temp;
        for(i=0;i<capacity;++i){

            temp = slot_list[i].getSlotCoord();
            if((temp.getX() == x) && (temp.getY() == y))
                return i+1;
        }

        return -1;
    }

    public int getDestIDFromCoord(int x , int y){

        //Function to obtain destination id from coordinates
        int i = 0 ;
        Point temp;
        for(i=0;i<number_of_destinations;++i){

            temp = destination_list[i].getDestCoord();
            if((temp.getX() == x) && (temp.getY() == y))
                return i+1;
        }

        return -1;
    }

    // The Real Function.

    private int getOptimalSlot(int id){

        //Function to get the slot id
        int i = 0, optimal_slot_id = 1;
        double[] temp;
        double min = 10000000;

        for(i=0;i<capacity;++i){

            if (slot_list[i].getStatus() != 0)
                continue;
            temp = slot_list[i].getDistances();
            if(min >= temp[id-1]){

                min = temp[id-1];
                optimal_slot_id = i+1;
            }
        }

        return optimal_slot_id;
    }

    @Override
    public void run() {
        System.out.println("System Start");
        open = true;
        Slot.initServer(5050);
        try {

            ServerSocket server = new ServerSocket(6060);

            while (open) {
                Socket talkToConsole = server.accept();

                DataInputStream in = new DataInputStream(new BufferedInputStream(talkToConsole.getInputStream()));
                DataOutputStream out = new DataOutputStream(talkToConsole.getOutputStream());

                String message = in.readUTF();
                String[] arr = message.split(":", 2);
                System.out.println(message);
                int wanted_Dest = Integer.parseInt(arr[0]);
                int id = getOptimalSlot(wanted_Dest);
                //out.writeUTF(String.valueOf(id));
                out.writeUTF(String.valueOf(id));
                System.out.println(id);

                talkToConsole.close();

                Socket talkToConsoleSOU = server.accept();
                in = new DataInputStream(new BufferedInputStream(talkToConsoleSOU.getInputStream()));
                out = new DataOutputStream(new BufferedOutputStream(talkToConsoleSOU.getOutputStream()));

                message = in.readUTF();
                System.out.println(message);

                String[] arr1 = message.split(":", 2);
                out.writeInt(1);
                talkToConsoleSOU.close();

                slot_list[id - 1].assignCar(arr1[1]);
                slot_list[id - 1].start();
            }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {

    }


}
