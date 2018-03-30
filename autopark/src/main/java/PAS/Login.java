package PAS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Login extends JFrame implements ActionListener{

    //Main class starts here
    //Data member declarations
    JPanel main_panel = new JPanel();
    JPanel center  = new JPanel();
    JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
    JLabel user_name = new JLabel("Enter Username: ");
    JLabel password = new JLabel("Enter Password: ");
    JButton submit  = new JButton("Submit");
    JTextField user_name_text = new JTextField();
    JTextField password_text = new JTextField();

    //Method Declarations
    public Login(){

        //Default constructor

        super("Test");
        setSize(600,200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Add title to the main panel
        main_panel.setLayout(new BorderLayout());
        main_panel.add(title, BorderLayout.NORTH);

        //Using the center panel
        center.setLayout(new GridLayout(5,2));
        center.add(user_name);
        center.add(user_name_text);
        center.add(password);
        center.add(password_text);
        main_panel.add(center, BorderLayout.CENTER);

        //Button add
        main_panel.add(submit, BorderLayout.SOUTH);

        add(main_panel);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e){

    }

    public static void main(String[] args) {

        File f = new File(getClass().getClassLoader().getResourceHi.txt");
        if(f.exists()) {
            System.out.println("Hi if");
        }
        else
            System.out.println("HI else");
    }

}
