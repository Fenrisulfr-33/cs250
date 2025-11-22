package cs250.hw3;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class TCPClient {
    static DataInputStream dataIn;
    static DataOutputStream dataOut;
    static Scanner userInput = new Scanner(System.in);
    static Socket clientSocket;

    /**
     * Reads an int from the input stream
     * if and incorrect value is read, the EXIT_NUM will be returned
     * 
     * @return
     */
    public static int receiveNum() {
        try {
            int response = dataIn.readInt();
            return response;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }

    /**
     * Writes an int to the output stream
     * By flushing the stream, it means to clear the stream of any element that may
     * be or maybe not inside the stream
     * 
     * @param numToSend
     */
    public static void sendNumber(int numToSend) {
        try {
            dataOut.writeInt(numToSend);
            dataOut.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * This method cleans up all of the connections by closing them
     * then exiting.
     * This prevents a lot of problems, so its good practice to always make sure
     * connections close.
     */
    public static void cleanUp() {
        try {
            clientSocket.close();
            dataOut.close();
            dataIn.close();

            System.out.println("Connections Closed");
            System.exit(0);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        // java cs250.hw3.TCPClient <server-host> <server-port>
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            // Initialize Necessary Objects
            clientSocket = new Socket(host, port); // Establishes a connection to the server
            dataOut = new DataOutputStream(clientSocket.getOutputStream()); // Instantiates out so we can then use it to
                                                                            // send data to the client
            dataIn = new DataInputStream(clientSocket.getInputStream()); // Instantiates in so we can then use it to
                                                                         // receive data from the client
            int numMessages = dataIn.readInt();
            int seed = dataIn.readInt();
            System.out.println("number of messages = " + numMessages);
            System.out.println("seed = " + seed);

            // TODO: task 3
            Thread.sleep(10000);

            Random random = new Random(seed);
            int numOfSentMessages = 0;
            long senderSum = 0;

            System.out.println("Starting to send messages to the server...");

            for (int i = 0; i < numMessages; i++){
                int randomInt = random.nextInt();
                sendNumber(randomInt);
                numOfSentMessages++;
                senderSum += randomInt;
            }

            System.out.println("Finished sending messages to server.");
            System.out.println("Total messages sent: " + numOfSentMessages);
            System.out.println("Sum of messages sent: " + senderSum);

        } catch (IOException error) {
            System.err.println(error.getMessage());
        } catch (InterruptedException error){
            System.err.println(error.getMessage());
        }

        /**
         * Run one of these commands to connect to machine
         * 
         *      ssh C837375722@earth.cs.colostate.edu
         *      ssh C837375722@jupiter.cs.colostate.edu
         *      ssh C837375722@moth.cs.colostate.edu
         * 
         * Run this command in both machines.
         *      
         *      java Documents/cs250/hw3/TCPClient.java neptune 1345
         * 
         */

        /**
         * jupiter machine expected output
         *
         * Received config
         * number of messages = 30
         * seed = 234785527
         * Starting to send messages to server...
         * Finished sending messages to server.
         * Total messages sent: 30
         * Sum of messages sent: -2496366311
         */

        /**
         * earth machine expected output
         * 
         * Received config
         * number of messages = 30
         * seed = -1170105035
         * Starting to send messages to server...
         * Finished sending messages to server.
         * Total messages sent: 30
         * Sum of messages sent: -6382135587
         * 
         */
    }

}
