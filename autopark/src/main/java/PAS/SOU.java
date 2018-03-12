package PAS;

import java.io.IOException;

/**
 * The SOU class communicates with the SOU android app
 * running on mobile hardware
 * It gets the number plate returned by the SOU App.
 */

public class SOU{

    private int    id;                     // Should be the same as the app ID
    private int    port;
    private String car_number_plate;    // Stores the value returned by the app

    public SOU(int ID, int Port){
        id = ID;
        port = Port;
    }

    public void talkToApp() throws IOException {
        Server sou_app = new Server(port);
        String message = sou_app.getMessage();
        String[] arr = message.split(":",2);

        int id_check = Integer.parseInt(arr[0]);
        if (id != id_check) {
            throw new IOException("App ID mismatch");
        }
        else {
            car_number_plate = arr[1];
        }
    }

    public String getNumberPlate()
    {
        return car_number_plate;
    }

    public static void main(String[] args) {

        SOU test = new SOU(23, 5050);
        try {
            test.talkToApp();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.print(test.getNumberPlate());
        }
    }

}

