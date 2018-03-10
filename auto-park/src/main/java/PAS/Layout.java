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

public class Layout{

    //Main class begins
    //Data members declaration segment

    private Point top, bottom, right, left;                     //coordinates for the top,bottom,left and right most parts of the layout.
    private String      layout_path;                            //holds the path of layout file (Excel)
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

    public void getFile(){

        //Function to choose the excel file having the layout and getting its file path
        System.out.println("\nChoose your file...\n");
        PickAFile picker = new PickAFile();

        picker.chooseFile();
        layout_path = picker.selected_file_path;
    }

    public void getLayoutDimensions() throws FileNotFoundException , java.io.IOException, BiffException{

        //Function to obtain layout dimensions
        try{
            //Open the excel sheet
            FileInputStream fs = new FileInputStream(layout_path);
            Workbook wb = Workbook.getWorkbook(fs);

            //Get the required sheet from the excel sheet
            Sheet sh = wb.getSheet(0);

            //We have to encapsulate the parking layout into a rectangular format
            //we get the coordinates for the top,bottom,left and right most parts of the layout.

            int flag_left = 0, flag_top = 0;                                //flag to show the top and left coordinates have been found
            int last_scanned_x , last_scanned_y;                            //last scanned cooridnates with inormation in them
            int i, j;                                                       //loop variables
            String cell_content;                                            //holds cell content extracted from excel sheet

            for(i=0;i<100;++i){

                for(j=0;j<100;++j){

                    cell_content = sh.getCell(i,j).getContents();
                    if((cell_content == "D") || (cell_content == ".") || (cell_content == "P")){

                        if(flag_left == 0){

                            left = new Point(i,j);
                            flag_left = 1;
                        }
                        last_scanned_x = i;
                        last_scanned_y = j;
                    }
                }
            }

            right = new Point(last_scanned_x,last_scanned_y);

            for(j=0;i<100;++i){

                for(i=0;j<100;++j){

                    cell_content = sh.getCell(i,j).getContents();
                    if((cell_content == "D") || (cell_content == ".") || (cell_content == "P")){

                        if(flag_top == 0){

                            top = new Point(i,j);
                            flag_top = 1;
                        }
                        last_scanned_x = i;
                        last_scanned_y = j;
                    }
                }
            }

            bottom = new Point(last_scanned_x,last_scanned_y);

        }
        catch(FileNotFoundException ex){
            System.out.println(":(");
        }
        catch(java.io.IOException ex){
            System.out.println(":(");
        }
        catch(BiffException ex){

            System.out.println(":(");
        }

        Workbook.close();                                                   //close the sheet to free up memmory
    }

    public void extractDestinationsFromLayout() throws FileNotFoundException , java.io.IOException, BiffException{

        //Function to extract the destinations from the layout_path
        try{

            //Open the excel sheet
            FileInputStream fs = new FileInputStream(layout_path);
            Workbook wb = Workbook.getWorkbook(fs);

            //Get the required sheet from the excel sheet
            Sheet sh = wb.getSheet(0);

            int i , j;                                                      //loop variables
            Cell a;                                                         //object of Cell class to access the cells in the excel sheet
            String cell_content;                                            //holds the contents of the cell
            //We have to run through the layout and read all the 'D**'s in the excelsheet. They stand for destinations.
            //We have to identify their coordinates, assign destinaton IDs
            for(i=top.getY();i<=bottom.getY();++i){

                for(j=left.getX();j<=right.getX();++j){

                    a = sh.getCell(i,j);
                    cell_content = a.getContents();

                    if(cell_content.charAt(0) == 'D'){

                        Destination temp = new Destination();
                        
                    }
                }
            }


        }
        catch(FileNotFoundException ex){
            System.out.println(":(");
        }
        catch(java.io.IOException ex){
            System.out.println(":(");
        }
        catch(BiffException ex){

            System.out.println(":(");
        }

    }


    //Main
    public static void main(String[] args){

        System.out.println("hi");
    }
}
