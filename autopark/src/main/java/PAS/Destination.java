package PAS;
//Destination Class
//This class decribes the destinations available around the parking Layout

import java.awt.Point;                              //Imported this library for the Point class

public class Destination{

    //Main class begins
    //Data memeber declaration

    private int     destination_ID;                 //holds the ID number of the destination
    private String  destination_name;               //holds the name of the destination
    private Point   destination_coordinates;        //holds the coordinates of the destination which is extracted from the excel sheet using jxl in layout class

    //Method declarations

    public Destination(){

        //Constructor class to intialise the data men=mbers to certain default values
        destination_ID = 0;
        destination_name = "NULL";
        destination_coordinates = new Point(0,0);
    }

    public Destination(int id, String name , Point coord){

        //Constructor class to intialise the data men=mbers to certain given values in the parameter
        destination_ID = id;
        destination_name = name;
        destination_coordinates = new Point();
        destination_coordinates.setLocation(coord);
    }

    public int getDestID(){

        //Function to obtain the destination ID
        return destination_ID;
    }

    public String getDestName(){

        //Function to obtain the destination Name
        return destination_name;
    }

    public Point getDestCoord(){

        //Function to obtain the coordinates of the destination
        return destination_coordinates;
    }

    public double getDistance(Point slot_coordinates){

        //Function to obtain the distance between a slot and the destination
        return destination_coordinates.distance(slot_coordinates);
    }

    public void inputDestID(int id){

        //Function to input destination ID
        destination_ID = id;
    }

    public void inputDestName(String name){

        //Funciton to input the destination Name
        destination_name = name;
    }

    public void inputDestCoord(Point coord){

        //Function to input the destinaton coordinates
        destination_coordinates = coord;
    }

    public static void main(String[] args){

        //Main function of the java program

        /*
                            Tester code

        Point xy = new Point(0,2);

        Destination d1 = new Destination();
        Destination d2 = new Destination(12 , "McDonalds" , xy);

        System.out.println(d1.getDestID() + d1.getDestName() + d1.getDistance(xy));
        System.out.println(d2.getDestID() + d2.getDestName() + d2.getDistance(xy));
        */

        System.out.println("hi");
    }
}