package PAS;
//Layout Class
//This class decribes the parking layout which will be inputted by the user

import java.util.ArrayList;
import java.util.LinkedList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jxl.read.biff.BiffException;
import java.io.FileNotFoundException;
import java.util.Date;
import jxl.*;

import java.awt.Point;                              //Imported this library for the Point class
import java.util.Collections;                       //Imported for Collection class

public class Layout{

    //Main class begins
    //Data members declaration segment

    private String      layout_path;                            //holds the path of layout file (Excel)
    private double      average_SOU_accuracy;                   //holds the latest calculated average SOU accuracy
    private int         row_bound;                              //holds the row bound for the layout
    private int         col_bound;                               //holds the column bound for the layout
    private int         current_car_count;                      //holds the current number of cars in the layout
    private int         capacity;                               //holds the capacity of the layout,i.e, tells us the total number of slots
    private int         number_of_destinations;                 //holds the number of destinations present near the layput available for console menu
    private int         total_number_of_cars;                   //holds the number of cars in total over a timeline
    private int         total_number_of_days;                   //holds the number of days since the start of the implementation f the software on the Layout
    private LinkedList<Integer>     offense_list;               //holds the slot ids which have been marked as 'offense'
    private Destination  destination_list[];                    //holds the information of the available destinations at the Layout
    private Slot         slot_list[];                           //holds the information of the slots present at the parking layout

    //Methods declaration

    public Layout(){

        //Default constructor
        number_of_destinations = 0;
        capacity = 0;
    }

    public void getFile(){

        //Function to choose the excel file having the layout and getting its file path
        System.out.println("\nChoose your file...\n");
        PickAFile picker = new PickAFile();

        picker.chooseFile();
        layout_path = picker.selected_file_path;
    }

    public void getLayoutDimensions() throws FileNotFoundException , java.io.IOException, BiffException{

        //Function to obtain layout dimensions

        ArrayList<Integer> row_bounds = new ArrayList<Integer>();
        ArrayList<Integer> col_bounds = new ArrayList<Integer>();

        //Open the excel sheet
        FileInputStream fs = new FileInputStream(layout_path);
        Workbook wb = Workbook.getWorkbook(fs);

        //Get the required sheet from the excel sheet
        Sheet sh = wb.getSheet(0);

        int i = 0 , j = 0;                                                      //loop variables                                                     //holds cell content extracted from excel sheet

        //To find the bounds using try{..} catch{..}
        //We have to try and catch it for every row and column and then take max of it
        //To find row bound
        for(i=0;i<50;++i){

            try{
                    for(j=0;j<50;++j){

                        //Going through the rows
                        Cell cell = sh.getCell(i,j);
                    }
                }
            catch(Exception e){

            //bound hit
            row_bounds.add(j);
            }
        }

        //To find column bond
        for(j=0;j<50;++j){

            try{
                    for(i=0;i<50;++i){

                        //Going through the columns
                        Cell cell = sh.getCell(i,j);
                    }
                }
            catch(Exception e){

            //bound hit
            col_bounds.add(i);
            }
        }

        //Now we have to find the maximum in both
        Integer R = Collections.max(row_bounds);
        Integer C = Collections.max(col_bounds);

        row_bound = R.intValue();
        col_bound = C.intValue();

        //System.out.println(row_bound + " " + col_bound + " " + col_bounds.get(5));

        /*
            Problematic code

        for(i=0;i<100;++i){
            for(j=0;j<100;++j){

                cell_content = sh.getCell(i,j).getContents().charAt(0);
                if((cell_content == 'D') || (cell_content == '.') || (cell_content == 'P')){

                    if(flag_left == 0){

                        left = new Point(i,j);
                        flag_left = 1;
                    }
                    last_scanned_x = i;
                    last_scanned_y = j;
                }

                if (cell_content == 'D')
                {
                    number_of_destinations++;
                }
            }
        }

        right = new Point(last_scanned_x, last_scanned_y);

        for(j=0;j<100;++j){
            for(i=0;i<100;++i){
                cell_content = sh.getCell(i,j).getContents().charAt(0);
                if((cell_content == 'D') || (cell_content == '.') || (cell_content == 'P')){

                    if(flag_top == 0){
                        top = new Point(i,j);
                        flag_top = 1;
                    }
                    last_scanned_x = i;
                    last_scanned_y = j;
                }

                if (cell_content == 'D')
                {
                    number_of_destinations++;
                }
            }
        }

        bottom = new Point(last_scanned_x,last_scanned_y);

        */

        //System.out.println(number_of_destinations);

        //Workbook.close();                                                   //close the sheet to free up memmory
    }

    /*public void makeRectangularLayout() throws FileNotFoundException , java.io.IOException, BiffException{

        //Function to make the layout rectangular by filling out empty spots with *s and xs
        //If its along the vorder then *
        //Else an x



    }*/

    public void getNumberOfDestinationsAndSlots() throws FileNotFoundException , java.io.IOException, BiffException{

        //Function to get the number of destinations and slots

        //Open the excel sheet
        FileInputStream fs = new FileInputStream(layout_path);
        Workbook wb = Workbook.getWorkbook(fs);

        //Get the required sheet from the excel sheet
        Sheet sh = wb.getSheet(0);

        int i,j;                                                                //loop variable
        char cell_content;                                                      //holds cell content extracted from excel sheet
        Cell a;                                                                 //holds cell information as an object

        for(i=0;i<row_bound;++i){

            for(j=0;j<col_bound;++j){

                a = sh.getCell(i,j);
                cell_content = a.getContents().charAt(0);

                if(cell_content == 'D')
                    number_of_destinations++;
                else if(cell_content == 'P')
                    capacity++;
            }
        }

        //System.out.println(number_of_destinations + " " + capacity);
    }

    public void extractDestinationsFromLayout() throws FileNotFoundException , java.io.IOException, BiffException{

        //Function to get the destinations from the layout

        //Open the excel sheet
        FileInputStream fs = new FileInputStream(layout_path);
        Workbook wb = Workbook.getWorkbook(fs);

        //Get the required sheet from the excel sheet
        Sheet sh = wb.getSheet(0);

        int i =0, j=0;                                                  //loop variables

        Destination[] destination_list = new Destination[number_of_destinations];

        //We have to run through the layout and read all the 'D**'s in the excelsheet. They stand for destinations.
        //We have to identify their coordinates, assign destinaton IDs
        for(i=0;i<row_bound;++i){

            for(j = 0;j<col_bound;++j){

                //get cell and contents
                Cell a = sh.getCell(i,j);
                String cell_content = a.getContents();

                //condition check.
                if(cell_content.charAt(0) == 'D'){

                    String sub_string = cell_content.substring(1,3);
                    int id = Integer.parseInt(sub_string,10);
                    //System.out.println(id);

                    //add to destination list
                    //destination found -> create an object
                    Point p = new Point(i,j);

                    destination_list[id-1] = new Destination();
                    destination_list[id-1].inputDestID(id);
                    destination_list[id-1].inputDestCoord(p);
                }
            }
        }
        //get names for dest
    }

    public void extractSlotsFromLayout() throws FileNotFoundException , java.io.IOException, BiffException{

        //Function to extract the slots from the layout

        //Open the excel sheet
        FileInputStream fs = new FileInputStream(layout_path);
        Workbook wb = Workbook.getWorkbook(fs);

        //Get the required sheet from the excel sheet
        Sheet sh = wb.getSheet(0);

        int i , j;                                                      //loop variables

        Slot[] slot_list = new Slot[capacity];

        //We have to run through the layout and read all the 'P***'s in the excelsheet. They stand for slots.
        //We have to identify their coordinates, assign slot IDs
        for(i=0;i<row_bound;++i){

            for(j=0;j<col_bound;++j){

                //get cell and contents
                Cell a = sh.getCell(i,j);
                String cell_content = a.getContents();

                //condition check.
                if(cell_content.charAt(0) == 'P'){

                    String sub_string = cell_content.substring(1,4);
                    int id = Integer.parseInt(sub_string);
                    //System.out.println(id);

                    //slot found -> create an object
                    //assign id and coordinates
                    Point p = new Point(i,j);

                    slot_list[id-1] = new Slot();
                    slot_list[id-1].inputSlotID(id);
                    slot_list[id-1].inputSlotCoord(p);
                }
            }
        }
    }

    public void assignDistances(){

        //Function to assign distances to all slots to all destination_list
        double[] distances = new double[number_of_destinations];                //holds the distances
        int i , j;                                                              //loop variables

        for(i=0;i<capacity;++i){

            //going through the slot array list and get coordinates
            Point temp_slot_p = new Point();
            temp_slot_p = slot_list[i].getSlotCoord();

            for(j=0;j<number_of_destinations;++j){

                //creating temp variable for destination
                Destination temp_D = new Destination();
                temp_D = destination_list[j];

                //Find the shortest distance between the slot and destination
                distances[i] = temp_D.getDistance(temp_slot_p);
            }

            //assign the values to data member of slot Class
            slot_list[i].inputDistances(distances,number_of_destinations);
        }
    }

    //Main
    public static void main(String[] args) {

        //Main Function
        //Tester code

        Layout layout = new Layout();
        //layout.number_of_destinations = 5;
        layout.getFile();

        try {
            layout.getLayoutDimensions();
            //layout.getNumberOfDestinationsAndSlots();
            //layout.extractDestinationsFromLayout();
            //layout.extractSlotsFromLayout();
        }
        catch (Exception e) {
            System.out.print(e);
        }

        System.out.println("Hi");
    }
}
