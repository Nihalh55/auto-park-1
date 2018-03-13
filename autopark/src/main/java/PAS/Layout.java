package PAS;
//Layout Class
//This class decribes the parking layout which will be inputted by the user

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Point;                                                          //Imported this library for the Point class
import java.util.Collections;                                                   //Imported for Collection class

public class Layout{

    //Main class begins
    //Data members declaration segment

    private double                  average_SOU_accuracy;                       //holds the latest calculated average SOU accuracy
    private int                     current_car_count;                          //holds the current number of cars in the layout
    private int                     capacity;                                   //holds the capacity of the layout,i.e, tells us the total number of slots
    private int                     number_of_destinations;                     //holds the number of destinations present near the layput available for console menu
    private int                     total_number_of_cars;                       //holds the number of cars in total over a timeline
    private int                     total_number_of_days;                       //holds the number of days since the start of the implementation f the software on the Layout
    private LinkedList<Integer>     offense_list;                               //holds the slot ids which have been marked as 'offense'
    private Destination[]           destination_list;                           //holds the information of the available destinations at the Layout
    private Slot[]                  slot_list;                                  //holds the information of the slots present at the parking layout

    //Methods declaration

    public Layout(){

        //Default constructor
        number_of_destinations = 0;
        capacity = 0;
    }

    /*public void getLayoutDimensions() throws FileNotFoundException , java.io.IOException, BiffException{

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
                    System.out.println(cell.getContents());
                    System.out.println(i + " " + j);
                }
            }
            catch(Exception e){

                //bound hit
                col_bounds.add(j);
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
                row_bounds.add(i);
            }
        }

        //Now we have to find the maximum in both
        Integer R = Collections.max(row_bounds);
        Integer C = Collections.max(col_bounds);

        row_bound = R.intValue();
        col_bound = C.intValue();

        System.out.println(row_bound + " " + col_bound + " " + col_bounds.get(5));

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



        //System.out.println(number_of_destinations);

        //Workbook.close();                                                   //close the sheet to free up memmory
    }*/

    /*public void makeRectangularLayout() throws FileNotFoundException , java.io.IOException, BiffException , WriteException{

        //Function to make the layout rectangular by filling out empty spots with *s and xs
        //If its along the vorder then *
        //Else an x

        //Open the excel sheet
        FileInputStream fs = new FileInputStream(layout_path);
        Workbook    wb = Workbook.getWorkbook(fs);
        Sheet       sh = wb.getSheet(0);
        Cell a;

        //Create a copy
        WritableWorkbook copy = Workbook.createWorkbook(new File("/home/nihalh55/Desktop/Temp.xls"), wb);
        WritableSheet    sheet_copy = copy.getSheet(0);
        WritableCell     cell_copy;

        //Now for every cell from (0,0) to (row_bound,col_bound) should have some value

        int i =0  , j = 0;                                              //loop variables

        for(i=0;i<row_bound;++i){

            for(j=0;j<col_bound;++j){

                try{
                    a = sh.getCell(i,j);
                    System.out.println(i + " " + j);
                    char content = a.getContents().charAt(0);
                    if(!(content == 'P' || content == 'D' || content == '.')){

                        //Empty cell encountered
                        //Border cells should have *s
                        if(i ==(row_bound-1) || j == (col_bound-1)){

                            Label l = new Label(j, i, "*");
                            cell_copy = (WritableCell) l;
                            sheet_copy.addCell(cell_copy);
                        }

                        else {

                            Label l = new Label(j, i, "x");
                            cell_copy = (WritableCell) l;
                            sheet_copy.addCell(cell_copy);
                        }
                        System.out.println("hi1" + i + " " + j);
                    }
                }
                catch(Exception e){

                    //Empty cell encountered
                    //Border cells should have *s

                    if(i ==(row_bound-1) || j == (col_bound-1)){

                        Label l = new Label(j, i, "*");
                        cell_copy = (WritableCell) l;
                        sheet_copy.addCell(cell_copy);
                    }

                    else {

                        Label l = new Label(j, i, "x");
                        cell_copy = (WritableCell) l;
                        sheet_copy.addCell(cell_copy);
                    }
                    System.out.println("hi2" + i + " " + j);
                }
            }
        }

        copy.write();
        copy.close();
    }*/

    public void getNumberOfDestinationsAndSlots(int rows, int cols, Object[][] data){

        //Function to get the number of destinations and slots

        //Get the dataEntries and put it intp a temp array
        int r = rows;
        int c = cols;
        int i = 0 , j = 0;                                                      //loop variable

        //Go through the 2D array and extract the information
        for(i=0;i<r;++i){

            for(j=0;j<c;++j){

                if(data[i][j] == "P")
                    capacity++;
                else if(data[i][j] == "D")
                    number_of_destinations++;

                //System.out.print(data[i][j]);
            }
            //System.out.println("hi");
        }
        /*
        //Open the excel sheet
        FileInputStream fs = new FileInputStream(layout_path);
        Workbook wb = Workbook.getWorkbook(fs);

        //Get the required sheet from the excel sheet
        Sheet sh = wb.getSheet(0);

        int i,j;                                                                //loop variable
        c)har cell_content;                                                      //holds cell content extracted from excel sheet
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
        */
        //System.out.println(number_of_destinations + " " + capacity);
    }

    public void extractDestinationsFromLayout(int rows, int cols, Object[][] data){

        //Function to get the destinations from the layout

        int i =0, j=0 , k=0;                                                  //loop variables
        destination_list = new Destination[number_of_destinations];

        //We have to run through the layout and read all the 'D**'s in the grid. They stand for destinations.
        //We have to identify their coordinates, assign destinaton IDs
        for(i=0;i<rows;++i){

            for(j=0;j<cols;++j){

                //condition check.
                if(data[i][j] == "D"){

                    //add to destination list
                    //destination found -> create an object
                    Point p = new Point(i,j);

                    destination_list[k] = new Destination();
                    destination_list[k].inputDestID(k+1);
                    destination_list[k].inputDestCoord(p);
                    k = k +1;
                }
            }
        }
        //get names for dest
    }

    public void extractSlotsFromLayout(int rows, int cols, Object[][] data){

        //Function to extract the slots from the grid

        int i = 0, j = 0, k = 0;                                                      //loop variables
        slot_list = new Slot[capacity];

        //We have to run through the layout and read all the 'P***'s in the grid. They stand for slots.
        //We have to identify their coordinates, assign slot IDs
        for(i=0;i<rows;++i){

            for(j=0;j<cols;++j){

                //condition check.
                if(data[i][j] == "P"){

                    //slot found -> create an object
                    //assign id and coordinates
                    Point p = new Point(i,j);

                    //slot_list[k] = new Slot();
                    //slot_list[k].inputSlotID(k+1);
                    //slot_list[k].inputSlotCoord(p);
                    k = k + 1;
                }
            }
        }
    }

    public void assignDistances(){

        //Function to assign distances to all slots to all destination_list
        double[] distances = new double[number_of_destinations];                //holds the distances
        int i=0, j=0;                                                              //loop variables

        for(i=0;i<capacity;++i){

            //going through the slot array list and get coordinates
            Point temp_slot_p = slot_list[i].getSlotCoord();
            System.out.println(temp_slot_p.getX() + " " + temp_slot_p.getY());

            for(j=0;j<number_of_destinations;++j){

                //creating temp variable for destination
                Destination temp_D = destination_list[j];

                System.out.println(temp_D.getDestCoord().getX() + " " + temp_D.getDestCoord().getY());

                //Find the shortest distance between the slot and destination
                distances[j] = temp_D.getDistance(temp_slot_p);

            }

            //assign the values to data member of slot Class
            slot_list[i].inputDistances(distances,number_of_destinations);
        }
    }

    public void extractInformation(int r, int c, Object[][] data){

        //Function to get information from grid layout
        getNumberOfDestinationsAndSlots(r,c,data);
        extractDestinationsFromLayout(r,c,data);
        extractSlotsFromLayout(r,c,data);
        assignDistances();
    }

    //Main
    public static void main(String[] args) {

        //Main Function
        //Tester code
        //System.out.println("Hi");
    }
}
