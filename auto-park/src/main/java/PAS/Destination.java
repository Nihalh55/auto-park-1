//Destination Class
//This class decribes the destinations available around the parking Layout

import java.awt.geom.Point2D;                       //Imported this library for the Point class

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

    public double getDistance(Point slot_coordinates){

        //Function to obtain the distance between a slot and the destination
        return destination_coordinates.distance(slot_coordinates);
    }
    //main
    public static void main(String[] args){

        System.out.println("hi");
    }
}
