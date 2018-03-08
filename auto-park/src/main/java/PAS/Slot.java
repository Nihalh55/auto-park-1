//Slot Class
//This class decribes the slots available at the parking Layout

import java.util.ArrayList;

public class Slot{

    //Main class begins
    //Data memeber declaration

    private int     slot_ID;                                        //holds the ID of the slot
    private int     status;                                         //holds the status of the slot , i.e , 0 => car parked & 1 => available & -1=> disabled
    private String  car_number_plate;                               //holds the number plate info. of the car currently occupying the slot
    private int     car_count;                                      //holds the number of cars that parked at that slot
    private int     offense_flag;                                   //holds offense status , i.e , 0=> No offense , 1=> offense
    private int     offense_count;                                  //holds the number of times an offense has taken place at that slot
    private 
    private ArrayList<Double>   distance_to_destination;            //holds the distances to various destinations present in and around the parking layout



    //Main
    public static void main(String[] args) {

        //System.out.println("hi");
    }
}
