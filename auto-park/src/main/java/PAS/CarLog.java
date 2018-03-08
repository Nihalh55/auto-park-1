//Car Log Class
//This class tells the log of the car that pakred at a slot

import java.sql.Time;                                       //library imported for Time class

public class CarLog{

    //Main class begins
    //Data memeber declaration

    private String  car_number_plate;                       //holds the number plate info. of the car currently occupying the slot
    private Time    entry_time;                             //holds the entry time of the car
    private Time    exit_time;                              //holds the exit time of the car

    //Method declarations

    public CarLog(){

        //Default constructor Class
        car_number_plate = "NULL";
        entry_time = new Time(0,0,0);
        exit_time = new Time(0,0,0);
    }

    public String getNumberPlate(){

        //Function to get the number plate of the car that is parked at the slot
        return car_number_plate;
    }

    public Time getEntryTime(){

        //Function to get the entry time of the car that is parked at the slot
        return entry_time;
    }

    public Time getExitTime(){

        //Function to get the exit time of the car that is parked at the slot
        return exit_time;
    }

    public void inputNumberPlate(String plate){

        
    }

    //Main
    public static void main(String[] args) {

        //System.out.println("hi");
    }
}
