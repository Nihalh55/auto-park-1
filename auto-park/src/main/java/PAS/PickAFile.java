package PAS;
//Class to pick a file

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jxl.read.biff.BiffException;
import java.io.FileNotFoundException;
import java.util.Date;
import jxl.*;

public class PickAFile{

    //Main class
    //Data member declarations
    public String selected_file_path;                                       //holds the path of the selected file

    //Method declarations

    public void chooseFile(){

        //Function to choose file and asssign its path to selected_file_path
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xls" , "Excel sheets" , "jpg" , "Java files" , "java");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);

        if(returnVal == JFileChooser.APPROVE_OPTION) {

            System.out.println("You chose this file: " + chooser.getSelectedFile().getName());
            selected_file_path = chooser.getSelectedFile().getAbsolutePath();
        }
    }

    //Main
    public static void main(String[] args) throws FileNotFoundException , java.io.IOException, BiffException{

        //Main function begins
        
        //System.out.println("hi");
    }
}
