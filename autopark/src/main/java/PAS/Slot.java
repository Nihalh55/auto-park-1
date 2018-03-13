package PAS;
/**
 * The Slot class holds all information pertaining
 * to a parking slot and provides methods to modify
 * this information.
 */

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.awt.Point;

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
    private Socket              socket   = null;
    private ServerSocket        server;
    private DataInputStream     in       = null;

    public Slot(int id, ServerSocket Server){

        slot_ID         = id;
        offense_flag    = false;
        slot_ID         = 0;
        status          = 0;
        car_count       = 0;
        offense_count   = 0;
        sou_car         = new Car();
        assigned_car    = new Car();
        server          = Server;
    }

    public void inputSlotID(int id){
        slot_ID = id;
    }

    public void inputSlotCoord(Point p){
        slot_coordinates = p;
    }

    public void inputDistances(double[] arr, int n){
        distance_to_destinations = arr;
    }

    public void assignCar(String car_number){
        assigned_car.inputNumberPlate(car_number);
        assigned_car.enteredNow();
        status = 1;
    }

    public boolean isOffense(){
        offense_flag = !(sou_car.getNumberPlate() == assigned_car.getNumberPlate());
        return offense_flag;
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

    @Override
    public void run() {
        String message = null;
        try
        {
            socket = server.accept();
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            try
            {
                message = in.readUTF();
            }
            catch(IOException i)
            {
                System.out.println(i);
            }

            socket.close();
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

    //Main
    public static void main(String[] args) {

        //Main function begins

        //System.out.println("hi");
    }

}
