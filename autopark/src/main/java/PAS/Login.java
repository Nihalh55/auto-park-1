package PAS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

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

    LayoutWrapper get_layout;

    //Method Declarations
    public Login(LayoutWrapper get_layout){

        //Default constructor

        super("Test");
        setSize(600,200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.get_layout = get_layout;

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
        submit.addActionListener(this);
        main_panel.add(submit, BorderLayout.SOUTH);

        add(main_panel);
        setVisible(false);
    }



    @Override
    public void actionPerformed(ActionEvent ev){

        boolean correct_user = true;
        Object obj = new Object();

        JButton jb = (JButton) ev.getSource();
        if(jb == submit){

            String entered_user_name = user_name_text.getText();
            String entered_password = password_text.getText();
            try {

                BufferedReader bufferreader = new BufferedReader(new FileReader("Login_Details.txt"));
                String read_user_name = bufferreader.readLine();
                String read_password = bufferreader.readLine();

                if((new String(read_user_name).equals(entered_user_name)) && (new String(read_password).equals(entered_password))){
                    correct_user = true;
                } else
                    correct_user = false;
            }
            catch(Exception e){

                System.out.println("ERROR IN READING FILE!");
            }

            if(correct_user){

                JOptionPane.showMessageDialog(this, "Login Successful! Press OK to continue.");
                this.setVisible(false);
                get_layout.setVisible(true);
            }
            else{

                JOptionPane.showMessageDialog(this, "Login Unsuccessful! Press OK to login again.");
                SwingUtilities.updateComponentTreeUI(this);
            }
        }
    }

    public static void main(String[] args) {

    }

}
