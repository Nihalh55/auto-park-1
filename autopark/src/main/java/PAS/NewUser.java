package PAS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class NewUser extends JFrame implements ActionListener{

    //Main class starts
    //Data Member declarations
    JPanel main_panel = new JPanel();
    JPanel center  = new JPanel();
    JLabel title = new JLabel("NEW USER", SwingConstants.CENTER);
    JLabel user_name = new JLabel("Enter Username for New User: ");
    JLabel password = new JLabel("Enter Password: ");
    JLabel confirm_password = new JLabel("Confirm Password: ");
    JButton submit  = new JButton("Submit");
    JTextField user_name_text = new JTextField();
    JPasswordField password_text = new JPasswordField();
    JPasswordField confirm_password_text = new JPasswordField();

    Login login;

    //Method Declarations
    public NewUser(Login login){

        //Default constructor

        super("Test");
        setSize(600,200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.login = login;

        //Add title to the main panel
        main_panel.setLayout(new BorderLayout());
        main_panel.add(title, BorderLayout.NORTH);

        //Using the center panel
        center.setLayout(new GridLayout(7,2));
        center.add(user_name);
        center.add(user_name_text);
        center.add(password);
        center.add(password_text);
        center.add(confirm_password);
        center.add(confirm_password_text);
        main_panel.add(center, BorderLayout.CENTER);

        //Button add
        submit.addActionListener(this);
        main_panel.add(submit, BorderLayout.SOUTH);

        add(main_panel);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent ev){

        JButton jb = (JButton) ev.getSource();
        if(jb == submit){

            String entered_user_name = user_name_text.getText();
            String entered_password = new String(password_text.getPassword());
            try{

                PrintWriter writer = new PrintWriter("Login_Details.txt", "UTF-8");
                writer.println(entered_user_name);
                writer.println(entered_password);
                writer.close();
            }
            catch(Exception e){

                System.out.println("ERROR IN CREATING FILE!");
            }

            JOptionPane.showMessageDialog(this, "User Created! Press OK to go back to Login Page.");
            this.setVisible(false);
            login.setVisible(true);
        }
    }

    //Main starts
    public static void main(String[] args) {

        //Login login = new Login();
        //new NewUser(login);

    }
}
