package PAS;

import java.net.*;
import java.io.*;

public class Server
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       = null;
    private String          message  = "";

    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            socket = server.accept();

            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            try
            {
                message = in.readUTF();
                // System.out.println(message);

            }
            catch(IOException i)
            {
                System.out.println(i);
            }

            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public String getMessage()
    {
        return message;
    }

    public static void main(String args[])
    {
        Server server = new Server(5050);
    }
}