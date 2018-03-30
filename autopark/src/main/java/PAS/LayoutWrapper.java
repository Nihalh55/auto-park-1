package PAS;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;
import java.net.Socket;

public class LayoutWrapper extends JFrame implements ActionListener{

    //Main class begins
    //Class declarations
    class EditableTableModel extends AbstractTableModel {

        //Class forthe table model->model

        String[] columnTitles;
        Object[][] dataEntries;
        int rowCount;

        public EditableTableModel(String[] columnTitles, Object[][] dataEntries) {

            //Constructor
            this.columnTitles = columnTitles;
            this.dataEntries = dataEntries;
        }

        public int getRowCount(){

            //To get row rowCount
            return dataEntries.length;
        }

        public int getColumnCount(){

            //Function to get column count
            return columnTitles.length;
        }

        public Object getValueAt(int row, int column){

            //Function to get the value atcell (i,j)
            return dataEntries[row][column];
        }

        public String getColumnName(int column){
            return columnTitles[column];
        }

        public Class getColumnClass(int column){


            return getValueAt(0, column).getClass();
        }

        public boolean isCellEditable(int row, int column){

            //Function to tell that the cell is editable
            return true;
        }

        public void setValueAt(Object value, int row, int column){

            //Function to modify value at cell (i,j)
            dataEntries[row][column] = value;
        }

    }

    //------------------------------------------------------------------------------------------------------------------

    //Data member declarations

    private int     number_of_rows;                                             //holds the number of rows of the grid
    private int     number_of_cols;                                             //holds the number of columns
    public  Object[][] dataEntries;                                             //holds information
    private Layout  layout;

    //------------------------------------------------------------------------------------------------------------------

    //Data member declarations for the GUI
    JPanel main_panel = new JPanel();
    JPanel north = new JPanel();
    JPanel center = new JPanel();
    JPanel east = new JPanel();
    JPanel south = new JPanel();
    JLabel title = new JLabel("INPUT LAYOUT" , SwingConstants.CENTER);
    JButton next_button = new JButton("Next");
    JTextArea instructions = new JTextArea(10,15);
    JTable table;

    //------------------------------------------------------------------------------------------------------------------

    //Method declarations

    public LayoutWrapper(Layout layout){

        //Default constructor which will start of the gui
        super("Layout Wrapper");

        //Data member declarations
        number_of_cols = 10;
        number_of_rows = 10;
        this.layout = layout;

        //------------------------------------------------------------------------------------------------------------------

        //GUI work
        main_panel.setLayout(new BorderLayout());

        //Center part of the layout -> Table
        int i = 0 , j = 0;                                                      //loop variables

        //Create the column tiles
        String[] columnTitles = new String[number_of_cols];
        for(i=0;i<number_of_cols;++i){
            columnTitles[i] = String.valueOf(i);
        }

        //Setting the data entries to " "
        dataEntries = new Object[number_of_rows][number_of_cols];
        for(i = 0; i < number_of_rows; ++i){

            for(j=0;j<number_of_cols;++j){

                dataEntries[i][j] = new Object();
                dataEntries[i][j] = " ";
            }
        }

        //create a table model so that the Jtable created can interrogate the model
        TableModel model = new EditableTableModel(columnTitles,dataEntries);

        //create the Table
        table = new JTable(model);
        table.createDefaultColumnsFromModel();

        //Now we have to create a combo box so that from each cell the user can choose either P ,D or .
        String[] choices = { "P","D", "." , " "};
        JComboBox comboBox = new JComboBox(choices);

        //Apply this combo box to all cells
        for(i=0;i<number_of_cols;++i){

            table.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(comboBox));
        }

        center.add(new JScrollPane(table));

        //------------------------------------------------------------------------------------------------------------------

        //North part -> title
        title.setFont(new Font("Serif" , Font.BOLD , 18));
        north.add(title);

        //------------------------------------------------------------------------------------------------------------------

        //East side -> Instructions
        Color c = new Color(211,211,211);
        instructions.setBackground(c);
        instructions.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(1, 3, 5, 3)) ,
                BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Instructions" , TitledBorder.CENTER , TitledBorder.ABOVE_TOP)));
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setEditable(false);
        instructions.setText("Follow these instructions while inputting the layout:\n\n\n"
                + "1) 'P' is for parking Slot and 'D' is for Destination.\n\n"
                + "2) '.' is stands one lane of the road.");

        east.add(instructions);

        //------------------------------------------------------------------------------------------------------------------

        //South part -> Button
        next_button.addActionListener(this);
        south.add(next_button);

        //------------------------------------------------------------------------------------------------------------------

        main_panel.add(center , BorderLayout.CENTER);
        main_panel.add(north, BorderLayout.NORTH);
        main_panel.add(east , BorderLayout.EAST);
        main_panel.add(south , BorderLayout.SOUTH);

        add(main_panel);
        setVisible(false);
        setSize(700,400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    layout.join();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

        //TODO: Mouse hover
        //TODO: get the table size fixed
        //TODO: Add images
        //TODO: what happens if you close in between ?

    public void displayData(){

        //Function to display the entries
        int i , j;
        for(i=0;i<number_of_rows;++i){

            for(j=0;j<number_of_cols;++j){

                System.out.print(dataEntries[i][j]);
            }
            System.out.println(" ");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Function to control what happens when next button is pressed

        JButton jb = (JButton) e.getSource();
        if(jb == next_button){

            layout.extractInformation(number_of_rows,number_of_cols,dataEntries);
            JOptionPane.showMessageDialog(this, "Your input has been successfully acquired. Press OK to continue.\n");
            this.setVisible(false);
            AdminGUI startGui = new AdminGUI(dataEntries , layout);
            //TODO: Uncomment layout start
            layout.start();
        }


    }

    //main
    public static void main(String[] args){

        //Layout l = new Layout();
        //new LayoutWrapper(l);

        //Main function of the java program

        //InputLayout l = new InputLayout();
        //l.createTable();

        //Test t = new Test();
        //t.createTable();
        //t.displayData();
        //System.out.println("hi");
    }
}
