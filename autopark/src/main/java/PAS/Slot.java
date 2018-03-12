package PAS;
/**
 * The Slot class holds all information pertaining
 * to a parking slot and provides methods to modify
 * this information.
 */

import java.util.LinkedList;
import java.util.ArrayList;

import java.awt.Point;                              //Imported this library for the Point class

public class Slot extends Thread{

    //Main class begins
    //Data memeber declaration

    private Point               slot_coordinates;                               //holds the coordinates of the slot
    private int                 slot_ID;                                        //holds the ID of the slot
    private int                 status;                                         //holds the status of the slot , i.e , 2 => car parked & 1 ==> car assigned but not parked & 0 => available &  & -1 => disabled
    private int                 car_count;                                      //holds the number of cars that parked at that slot
    private int                 offense_count;                                  //holds the number of times an offense has taken place at that slot
    private boolean             offense_flag;                                   //holds offense status
    private SOU                 sou;                                            //holds the info. of the SOU at the slot
    private Car                 assigned_car;                                   //holds info. of car assigned by the layout class
    private Car                 sou_car;                                        //holds the info. of car returned by the sou class
    private LinkedList<Car>     car_log;                            //holds information of all the cars that visited the slot
<<<<<<< HEAD
    private double              distance_to_destinations[];                       //holds the distances to various destinations present in and around the parking layout
=======
    private double[]  distance_to_destinations;                       //holds the distances to various destinations present in and around the parking layout
>>>>>>> 83676fc928624073c0ef546cff3385020f9626ef

    //Method declarations

    public Slot(){

        //Default constructor
        slot_ID         = 0;
        status          = 0;
        car_count       = 0;
        offense_count   = 0;
        offense_flag    = false;
        sou_car         = new Car();
        assigned_car    = new Car();

    }

    public Slot(int id){

        //Constructor to intialise values to data members
        slot_ID         = id;
        offense_flag    = false;
        slot_ID         = 0;
        status          = 0;
        car_count       = 0;
        offense_count   = 0;
        sou_car         = new Car();
        assigned_car    = new Car();
        sou             = new SOU(slot_ID, 5050);
    }

    /*private boolean isOffense(){

        //Function to determine if offense is taking place
        offense_flag = !(sou_car.car_number == assigned_car.car_number);
        return offense_flag;
    }*/

    public void inputSlotID(int id){

        //Function to input the slot id
        slot_ID = id;
    }

    public void inputSlotCoord(Point p){

        //Function to input the slot slot_coordinates
        slot_coordinates = p;
    }

    public void inputDistances(double[] arr, int n){

        //Function to input the distances to all destinations
        //n -> number of destinations
        distance_to_destinations = new double[n];
        distance_to_destinations = arr;
    }

    public int getSlotID(){

        //Function to obtain the slot ID
        return slot_ID;
    }

    public Point getSlotCoord(){

        //Function to obtain the coordinates of the slots
        return slot_coordinates;
    }


    public void assignCar(String car_number){

        assigned_car.inputNumberPlate(car_number);
        assigned_car.enteredNow();


    }

    //Main
    public static void main(String[] args) {

        //Main function begins

        //System.out.println("hi");
    }
}
