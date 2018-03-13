package PAS;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.awt.Point;

/**
 * The Slot class holds all information pertaining
 * to a parking slot and provides methods to access
 * this information.
 *
 * It extends the Thread class. The thread class is
 * used to communicate, with the SOU Mobile Controller,
 * without interrupting application flow.
 */
public class Slot extends Thread{

    private Point               slot_coordinates;                               //holds the coordinates of the slot
    private int                 slot_ID;                                        //holds the ID of the slot
    private int                 status;                                         //holds the status of the slot , i.e , 2 => car parked & 1 ==> car assigned but not parked & 0 => available &  & -1 => disabled
    private int                 car_count;                                      //holds the number of cars that parked at that slot
    private int                 offense_count;                                  //holds the number of times an offense has taken place at that slot
    private boolean             offense_flag;                                   //holds offense status
    private Car                 assigned_car;                                   //holds info. of car assigned by the layout class
    private Car                 sou_car;                                        //holds the info. of car returned by the sou class
    private LinkedList<Car>     car_log;                                        //holds information of all the cars that visited the slot
    private double[]            distance_to_destinations;                       //holds the distances to various destinations present in and around the parking layout
    private Socket              talkToApp;
    private DataInputStream     in = null;

    /**
     * Class Constructor.
     * Creates a Slot object that can hold the information for a specific parking slot.
     * @param id        Slot Identification Number
     * @param socket    Socket that the object would use to communicate with the SOU instance.
     */
    public Slot(int id, Socket socket){

        slot_ID         = id;
        offense_flag    = false;
        status          = 0;
        car_count       = 0;
        offense_count   = 0;
        sou_car         = new Car();
        assigned_car    = new Car();
        talkToApp       = socket;
    }

    /**
     * Assigns the coordinates of the slot to provided coordinates.
     * @param p The Point object that holds the coordinates to be assigned to the slot.
     *          @see java.awt.Point
     */
    public void inputSlotCoord(Point p){
        slot_coordinates = p;
    }

    /**
     * Assigns the array of distances (to destinations) to provided distances array.
     * @param arr   Array to be assigned.
     * @param n     Number of destinations.
     */
    public void inputDistances(double[] arr, int n){
        distance_to_destinations = Arrays.copyOf(arr, n);
    }

    public double[] getDistance_to_destinations() {
        return distance_to_destinations;
    }

    public int getSlotID(){
        return slot_ID;
    }

    public Point getSlotCoord(){
        return slot_coordinates;
    }

    public int getStatus() {
        return status;
    }

    public int getCar_count() {
        return car_count;
    }

    public int getOffense_count() {
        return offense_count;
    }

    public String getCarNumber() { return sou_car.getNumberPlate(); }

    public void assignCar(String car_number){
        assigned_car.inputNumberPlate(car_number);
        assigned_car.enteredNow();
        status = 1;
    }

    public boolean isOffense(){
        offense_flag = !(sou_car.getNumberPlate() == assigned_car.getNumberPlate());
        return offense_flag;
    }
    @Override
    public void run() {
        String message = null;
        try
        {
            in = new DataInputStream(new BufferedInputStream(talkToApp.getInputStream()));

            try
            {
                message = in.readUTF();
            }
            catch(IOException i)
            {
                System.out.println(i);
            }

            talkToApp.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
        String[] arr = message.split(":",2);
        int id_check = Integer.parseInt(arr[0]);
        if (slot_ID != id_check) {
            System.out.println("App ID mismatch");
        }
        else {
            sou_car.inputNumberPlate(arr[1]);
            if(!isOffense())
                status = 2;
            else status = 3;
        }
    }

    public static void main(String[] args) {

        int PORT = 5050;
        ServerSocket server;
        try {
            server = new ServerSocket(PORT);
            Socket socket = server.accept();
            Slot test = new Slot(23, socket);
            test.assignCar("6GDG486");
            test.start();
        }
        catch(Exception e) {
            System.out.print(e);
        }
    }
}