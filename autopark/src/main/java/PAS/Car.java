package PAS;
/**
 * The Car class holds all information that the
 * Slot class would need with respect to Cars.
 */

import java.time.LocalDateTime;

public class Car{

    //Main class begins
    //Data memeber declaration

    String car_number_plate;                                        //holds the number plate info. of the car currently occupying the slot
    private LocalDateTime entry_time;                                //holds the entry time of the car
    private LocalDateTime exit_time;                                 //holds the exit time of the car
    // Image?

    //Method declarations

    public String getNumberPlate(){

        //Function to get the number plate of the car that is parked at the slot */
        return car_number_plate;
    }

    public void enteredNow()
    {
        //Function to obtain current local time. Triggered when car is detected by SOU on entry
        entry_time = LocalDateTime.now();
    }

    public void leftNow()
    {
        //Function to obtain current local time. Triggered when car is leaving
        exit_time = LocalDateTime.now();
    }

    public LocalDateTime getEntryTime()
    {
        //Function to get the entry time of the car that is parked at the slot
        return entry_time;
    }

    public LocalDateTime getExitTime()
    {
        //Function to get the exit time of the car that is parked at the slot
        return exit_time;
    }

    public void inputNumberPlate(String plate){

        //Function to input the number plate of the car
        car_number_plate = plate;
    }

    //Main
    public static void main(String[] args) {

        //Main function begins

        //System.out.println("hi");
    }
}