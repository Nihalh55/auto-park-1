package PAS;

import javax.swing.*;

public class Main {

    //Class starts

    //Data Member declarations
    static LayoutWrapper    get_layout;                                                           //gui to get layout
    static Layout           layout = new Layout();                                                               //holds layout info.

    //Method Declarations


    //Main
    public static void main(String[] args) {

        //Main begins

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        //TODO: set default close ops
        //First is a welcome dialog box -> Thank you for purchasing the product
        JFrame frame = new JFrame();                                                                                                            //frame for dialog boxes
        JOptionPane.showMessageDialog(frame, "Welcome! Thank you for purchasing our product.\n                    Press OK to continue.");

        //-----------------------------------------------------------------------------------------------------------------------------------------------

        //Next is information dialog box which will give the user a small set
        //of instructions which are:
        //1)    Input the layout -> a 10x10 grid will be shown -> P is slot,
        //      D is destination and . is a road unit -> press next to continue

        //JOptionPane.showMessageDialog(frame, "        Please input the parking layout in the upcoming 10x10 grid.\n\n" +
        //        "a) 'P' is for Parking Slot , 'D' is for destination , '.' is for road. \nb) Prompt to enter destination names will appear after input of layout.\n");

        get_layout = new LayoutWrapper(layout);
        //TODO: Save layout

        //2)    Enter destination names for the destinations mentioned in input



        //3)    Press Finish



        //-----------------------------------------------------------------------------------------------------------------------------------------------

        //Implement the above three instructions. Save the object in a bin file

        //start the layout thread
        //start ui(setup.java)

    }
}
