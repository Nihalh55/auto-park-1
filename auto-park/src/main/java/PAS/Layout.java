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

public class Layout{

    //Main class begins
    //Data members declaration segment

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

        //Function to choose the excel file having the layput and getting its file path
        System.out.println("\nChoose your file...\n");
        PickAFile picker = new PickAFile();

        picker.chooseFile();
        layout_path = picker.selected_file_path;
    }

    public void extractInformationFromExcelSheet() throws FileNotFoundException , java.io.IOException, BiffException{

        //Function to obtain information from the layout present in the excel sheet
        try{
            //Open the excel sheet
            FileInputStream fs = new FileInputStream(layout_path);
            Workbook wb = Workbook.getWorkbook(fs);

            //Get the required sheet from the excel sheet
            Sheet sh = wb.getSheet(0);

            //get the cell content
            String CellGetContent = sh.getCell(0,0).getContents();
            System.out.println(CellGetContent);
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
