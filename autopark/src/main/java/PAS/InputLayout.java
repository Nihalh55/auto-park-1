package PAS;

//test Class
//file created to test out stuff

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;
import java.net.Socket;

public class InputLayout{

    //Main class begins

    //Class decalarations
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

    //Data member decalarations

    private int     number_of_rows;                                             //holds the number of rows of the grid
    private int     number_of_cols;                                             //holds the number of columns
    public  Object[][] dataEntries;                                             //holds information
    private Layout  layout;
    private static Socket server;
    //Method decalarations

    public InputLayout(Socket Server){

        number_of_cols = 20;
        number_of_rows = 20;
        server = Server;
        layout = new Layout(server);
    }

    public void createTable(){

        //Function to create the grid Layout

        int i = 0 , j = 0;                                                      //loop variables
        //Creates a frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create column titles
        String[] columnTitles = new String[number_of_cols];
        for(i=0;i<number_of_cols;++i){

            columnTitles[i] = new String();
            columnTitles[i] = String.valueOf(i);
        }

        //setting the data enteries to "."
        dataEntries = new Object[number_of_rows][number_of_cols];
        for(i = 0; i < number_of_rows; ++i){

            for(j=0;j<number_of_cols;++j){

                dataEntries[i][j] = new Object();
                dataEntries[i][j] = ".";
            }
        }

        //create a table model so that the Jtable created can interrogate the model
        TableModel model = new EditableTableModel(columnTitles,dataEntries);

        //create the Table
        JTable table = new JTable(model);
        table.createDefaultColumnsFromModel();

        //Now we have to create a combo box so that from each cell the user can choose either P ,D or .
        String[] choices = { "P","D", "."};
        JComboBox comboBox = new JComboBox(choices);

        //Apply this combobox to all cells
        for(i=0;i<number_of_cols;++i){

            table.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(comboBox));
        }

        frame.add(new JScrollPane(table));
        frame.setSize(500, 500);
        frame.setVisible(true);

        //After input is done we have to extract the information
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                    layout.extractInformation(number_of_rows,number_of_cols,dataEntries);
                }
        });

    }

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

    public int getNumberOfRowsInGrid(){

        //Function to obtain the number of rows in the grid
        return number_of_rows;
    }

    public int getNumberOfColsInGrid(){

        //Function to obtain the number of columns in the grid
        return number_of_cols;
    }

    //main
    public static void main(String[] args){

        //Main function of the java program
        int PORT = 5050;
        try{
            ServerSocket server = new ServerSocket(PORT);
        }
        catch (Exception e) {
            System.out.print(e);
        }

        InputLayout l = new InputLayout(server);
        l.createTable();

        //Test t = new Test();
        //t.createTable();
        //t.displayData();
        //System.out.println("hi");
    }
}
