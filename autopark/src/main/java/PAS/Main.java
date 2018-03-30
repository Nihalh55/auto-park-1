package PAS;

import javax.swing.*;
import java.io.File;

public class Main {

    //Class starts

    //Data Member declarations
    static LayoutWrapper    get_layout;                                                           //gui to get layout
    static Layout           layout = new Layout();                                                               //holds layout info.
    static Login            login;
    static NewUser          new_user;

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

        //Next we have to do the login part of the Project
        //Each time on start a login page will appear.
        //Only one user login allowed -> no multiple users
        //So it checks if file containing user details present
        //if no then new user , else old user

        //If new then -> Take Name, email ID and pass word (email verification?)
        //Then go back to login

        //if old user then login, if wrong login repeat otherwise
        //else continue

        File login_file_check = new File("Login_Details.txt");
        while(!(login_file_check.exists())){

            //user account not made
            new_user = new NewUser(login);
        }

        //User account exists
        login = new Login();
        login.setVisible(true);



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
