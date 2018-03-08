package PAS;

import java.util.LinkedList;

/**
 * The Slot class holds all information pertaining
 * to a parking slot and provides methods to modify
 * this information.
 */

public class Slot{

    // Slot characteristics
    private int slot_id;
    private double[] distances;
    private int status;          
    // Check reqs;
    //holds the status of the slot , i.e , 2 => car parked & 1 ==> car assigned but not parked & 0 => available &  & -1 => disabled

    // w.r.t Layout class
    private Car assigned_car;    // Car assigned by the layout class
    private LinkedList<Car> car_log;  

    // w.r.t SOU class
    private Car sou_car;       // Car returned by the sou class
    private boolean offense_flag;
    private int offense_count;

    // SOU object
    // SOU sou

    /*
    private void souComms()
    {
        while (!sou.detect());
        sou_car.car_number = sou.getCarNumber();
        
    }
    
    private bool isOffense()
    {
        offense_flag = !(sou_car.car_number == assigned_car.car_number);
        return offense_flag;
    }

    */

    public Slot(int id){
        slot_id = id;
        offense_flag = false;
    }

    public void setDistances(double[] dist){
        distances = dist;
    }

    public double[] getDistances(){
        return distances;
    }

    public void assignCar(String car_number)
    {
        assigned_car.car_number = car_number;
        assigned_car.enteredNow();
    }
    


}