package PAS;

//Setup class
//Used to do the initial setup of the system
//Shows a page where we have to enter a layout if none exists
//After entering the layout one should be able to see the layout changing dynamically
//We should be able to see the log too at the bottom
//One clicking a layout slot we should see the details of the slot
//I should save the layout also in some format which I should decide

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Setup extends JFrame{

    //Main class starts
    //Data member declaration

    //The main panel
    JPanel main_panel = new JPanel();
    JPanel main_panel_stuff = new JPanel();
    JPanel main_panel_title = new JPanel();
    ImageIcon car_image = new ImageIcon("Car.png");
    JLabel car_title_image = new JLabel(car_image);
    JLabel title = new JLabel("PARKING ASSIGNMENT SOFTWARE" ,SwingConstants.CENTER);

    //Now for the south part
    JPanel south = new JPanel();
    JTextArea log_area=new JTextArea(7,70);
    JScrollPane log_area_scroll=new JScrollPane(
            log_area,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    //Now for the east side
    JPanel east = new JPanel();
    JPanel button_panel = new JPanel();
    JTextArea info_area = new JTextArea(16,15);
    JButton stats_button = new JButton("View Statistics");
    JButton resolve_button = new JButton("Resolve Issue");
    JButton disable_button = new JButton("Disable Slot");
    JButton layout_button = new JButton("Layout Option");
    JProgressBar progress_bar = new JProgressBar();

    //Now for the center part
    JPanel center = new JPanel();
    JButton[][] layout_units = new JButton[10][10];
    ImageIcon p = new ImageIcon("P.png");

    public Setup(){

        //Default constructor

        super("Test");
        setSize(800,700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //----------------------------------------------------------------------

        main_panel_stuff.setLayout(new BorderLayout());

        //----------------------------------------------------------------------

        //the layout button array screen for center part
        center.setLayout(new GridLayout(10,10));
        center.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        int i = 0 , j = 0;
        for(i=0;i<10;++i){

            for(j=0;j<10;++j){

                layout_units[i][j] = new JButton(" ");
                center.add(layout_units[i][j]);
            }
        }


        //----------------------------------------------------------------------

        //text area south side
        Color c = new Color(211,211,211);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        log_area.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(1, 1, 5, 1)) ,
                BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), " Dynamic Log" , TitledBorder.CENTER , TitledBorder.ABOVE_TOP)));
        log_area.setBackground(c);
        log_area.setLineWrap(true);
        log_area.setWrapStyleWord(true);
        log_area.setEditable(false);
        log_area_scroll.setPreferredSize(new Dimension(25,75));
        south.add(log_area);

        //----------------------------------------------------------------------

        //east side
        east.setLayout(new BorderLayout());
        east.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        //east side text area -> placed at south side
        c = new Color(211,150,155);
        info_area.setBackground(c);
        info_area.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(1, 3, 5, 3)) ,
                BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Information" , TitledBorder.CENTER , TitledBorder.ABOVE_TOP)));
        info_area.setLineWrap(true);
        info_area.setWrapStyleWord(true);
        info_area.setEditable(false);

        //east side buttons -> north side of east panel
        button_panel.setLayout(new GridLayout(7,7 , 3 , 8));

        stats_button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        resolve_button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        disable_button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        layout_button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        progress_bar.setString("0 Cars");
        progress_bar.setStringPainted(true);

        button_panel.add(stats_button);
        button_panel.add(resolve_button);
        button_panel.add(disable_button);
        button_panel.add(layout_button);
        button_panel.add(progress_bar);

        east.add(info_area, BorderLayout.SOUTH);
        east.add(button_panel , BorderLayout.CENTER);

        //----------------------------------------------------------------------

        main_panel_stuff.add(south, BorderLayout.SOUTH);
        //main_panel_stuff.add(log_area_scroll,BorderLayout.SOUTH);
        main_panel_stuff.add(east , BorderLayout.EAST);
        main_panel_stuff.add(center,BorderLayout.CENTER);

        //main_panel_title.setLayout(new GridLayout(1,2));
        title.setFont(new Font("Serif" , Font.BOLD , 18));
        main_panel_title.add(title);
        //main_panel_title.add(car_title_image);

        main_panel.setLayout(new FlowLayout());
        main_panel.add(main_panel_title);
        main_panel.add(main_panel_stuff);

        add(main_panel);
        setVisible(true);


    }

    //Method declaration
    public static void main(String[] args) {

        new Setup();
    }

}
