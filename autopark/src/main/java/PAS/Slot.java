package PAS;


import java.io.*;
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

    private static ServerSocket server;

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
    private boolean             exitFlag;

    /**
     * Class Constructor.
     * Creates a Slot object that can hold the information for a specific parking slot.
     * @param id        Slot Identification Number
     */
    public Slot(int id){

        slot_ID         = id;
        offense_flag    = false;
        status          = 0;
        car_count       = 0;
        offense_count   = 0;
        sou_car         = new Car();
        assigned_car    = new Car();
        exitFlag        = false;
        car_log         = new LinkedList<Car>();
    }

    public static void initServer(int PORT)
    {
        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void setDistances(double[] arr, int n){
        distance_to_destinations = Arrays.copyOf(arr, n);
    }

    //TODO: Java Doc for getDistances

    public double[] getDistances() {
        return distance_to_destinations;
    }

    //TODO: Java Doc for getSlotID

    public int getSlotID() {
        return slot_ID;
    }

    //TODO: Java Doc for getSlotID

    public Point getSlotCoord() {
        return slot_coordinates;
    }

    //TODO: Java Doc for getStatus

    public int getStatus() {
        return status;
    }

    //TODO: Java Doc for getCar_count

    public int getCar_count() {
        return car_count;
    }

    //TODO: Java Doc for getOffense_count

    public int getOffense_count() {
        return offense_count;
    }

    //TODO: Java Doc for getCarNumber

    public String getCarNumber() {
        return sou_car.getNumberPlate();
    }

    //TODO: Java Doc for assignCar

    public void assignCar(String car_number){
        assigned_car.inputNumberPlate(car_number);
        assigned_car.enteredNow();
        status = 1;
    }

    //TODO: Java Doc for isOffense

    public boolean isOffense(){
        offense_flag = !(sou_car.getNumberPlate() == assigned_car.getNumberPlate());
        return offense_flag;
    }

    //TODO: Java Doc for run
    @Override
    public void run() {

        while (!exitFlag) {
            String message = null;
            try {
                Socket talkToApp = server.accept();
                DataInputStream in = new DataInputStream(new BufferedInputStream(talkToApp.getInputStream()));
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(talkToApp.getOutputStream()));
                try {
                    message = in.readUTF();
                    String[] arr = message.split(":", 2);
                    int id_check = Integer.parseInt(arr[0]);

                    int write = (slot_ID == id_check)? 1:0;

                    out.writeInt(write);
                    System.out.println(message);

                    if (write == 1) {
                        if (!arr[1].equals("Exit")) {
                            sou_car.inputNumberPlate(arr[1]);
                            if (!isOffense())
                                status = 2;
                            else status = 3;
                            exitFlag = true;
                        } else {
                            exitFlag = false;
                        }
                    } else {
                        System.out.println("APP Slot ID mismatch");
                    }
                } catch (IOException i) {
                    i.printStackTrace();
                }

                in.close();
//                out.close();
                talkToApp.close();

            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        while(exitFlag) {
            String msg;
            try {
                Socket talkToApp = server.accept();
                DataInputStream in = new DataInputStream(new BufferedInputStream(talkToApp.getInputStream()));
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(talkToApp.getOutputStream()));

                try {
                    msg = in.readUTF();
                    String[] arr = msg.split(":", 2);
                    int id_check = Integer.parseInt(arr[0]);

                    int write = (slot_ID == id_check)? 1:0;

                    out.writeInt(write);

                    System.out.println(msg + "");

                    if (write == 1) {
                        sou_car.inputNumberPlate("");
                        assigned_car.leftNow();
                        status = 0;
                        car_log.add(assigned_car);
                        System.out.println(assigned_car.getEntryTime()+" to "+assigned_car.getExitTime());
                        sou_car = new Car();
                        assigned_car = new Car();
                        exitFlag = false;
                    } else {
                        System.out.println("APP Slot ID mismatch");
                    }
                } catch (IOException i) {
                    i.printStackTrace();
                }
                in.close();
                //out.close();
                talkToApp.close();

            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        int PORT = 5050;
        ServerSocket server;
        try {

            Slot test = new Slot(23);
            test.initServer(PORT);
            test.assignCar("6GDG486");
            test.start();
            System.out.println("lalalalalala");
        }
        catch(Exception e) {
            System.out.print(e);
        }
    }
}