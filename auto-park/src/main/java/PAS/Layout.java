package PAS;
//Layout Class
//This class decribes the parking layout which will be inputted by the user

//import jxl.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Layout{

    //Main class begins
    //Data members declaration segment

    private double      average_SOU_accuracy;                   //holds the latest calculated average SOU accuracy
    private int         current_car_count;                      //holds the current number of cars in the layout
    private int         capacity;                               //holds the capacity of the layout,i.e, tells us the total number of slots
    private int         number_of_destinations;                 //holds the number of destinations present near the layput available for console menu
    private int         total_number_of_cars;                   //holds the number of cars in total over a timeline
    private int         total_number_of_days;                   //holds the number of days since the start of the implementation f the software on the Layout
    private LinkedList<Integer>     offense_list;               //holds the slot ids which have been marked as 'offense'
    private ArrayList<Destination>  destination_list;           //holds the information of the available destinations at the Layout
    private ArrayList<Slot>         slot_list;                  //holds the information of the slots present at the parking layout


    //Methods declaration
    

    //Main
    public static void main(String[] args){

        System.out.println("hi");
    }
}
