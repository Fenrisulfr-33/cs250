package cs250.hw3;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class TCPServer {
    static DataInputStream din;
    static DataOutputStream dout1;
    static DataOutputStream dout2;
    static Scanner userInput = new Scanner(System.in);
    static Socket clientSocket1;
    static Socket clientSocket2;
    static ServerSocket serverSocket;

    public static int receiveNum() {
        try {
            int response = din.readInt();
            return response;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return -1;
    }

    public static void sendNumber(DataOutputStream dout, int numToSend) {
        try {
            dout.writeInt(numToSend);
            dout.flush(); // By flushing the stream, it means to clear the stream of any element that may
                          // be or maybe not inside the stream
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static void cleanUp() {
        try {
            serverSocket.close();
            clientSocket1.close();
            clientSocket2.close();
            dout1.close();
            dout2.close();
            din.close();

            System.out.println("Connections Closed");
            System.exit(0);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void validatePort(int portNum) {
        if (portNum <= 1024 || portNum > 65535) {
            System.err.println("Port number must be between 1025 -> 65,535");
        }
    }

    private static void printTask3(String hostName, int messagesReceived, long sumReceived){
            System.out.println(hostName);
            System.out.println("\tMessages received: " + messagesReceived);
            System.out.println("Sum received: " + sumReceived);
    }

    public static void main(String[] args) {
        // java cs250.hw3.TCPServer <port-number> <seed> <number-Of-Messages>
        int port = Integer.parseInt(args[0]);
        int seed = Integer.parseInt(args[1]);
        int numOfMessages = Integer.parseInt(args[2]);

        validatePort(port);

        try {
            System.out.println("IP Address: " + InetAddress.getLocalHost() + "\nPort Number " + port);

            // Initialize Necessary Objects
            serverSocket = new ServerSocket(port);
            Random random = new Random(seed);
            System.out.println("waiting for client...");
            clientSocket1 = serverSocket.accept(); // Blocking call --> waits here until a request comes in from a
                                                   // client
            clientSocket2 = serverSocket.accept(); // Blocking call --> waits here until a request comes in from a
                                                   // client
            System.out.println("Clients Connected!");
            System.out.println("Sending config to clients...");
            dout1 = new DataOutputStream(clientSocket1.getOutputStream()); // Instantiates dout so we can then use it to
                                                                           // send data to the client
            dout2 = new DataOutputStream(clientSocket2.getOutputStream()); // Instantiates dout so we can then use it to
                                                                           // send data to the client

            int randomSeed1 = random.nextInt();

            sendNumber(dout1, numOfMessages);
            sendNumber(dout1, randomSeed1);

            System.out.println(clientSocket1.getInetAddress().getHostName() + " " + randomSeed1);

            int randomSeed2 = random.nextInt();

            sendNumber(dout2, numOfMessages);
            sendNumber(dout2, randomSeed2);

            System.out.println(clientSocket2.getInetAddress().getHostName() + " " + randomSeed2);

            System.out.println("Finished sending config to clients.");

            // TODO: task 3

            System.out.println("Starting to listen for client messages...");
            int numOfReceivedMessages = 0;
            long receiverSum = 0;
            // CATCH messages
            for (int i = 0; i < numOfMessages; i++){
                receiverSum = receiveNum();
                numOfReceivedMessages++;
            }
            System.out.println("Finished listening for client messages");

            // Print format
            printTask3(clientSocket1.getInetAddress().getHostAddress(), numOfReceivedMessages, receiverSum);
            printTask3(clientSocket2.getInetAddress().getHostAddress(), numOfReceivedMessages, receiverSum);

        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    /**
     * java cs250.hw3.TCPServer 1345 42 30
     * 
     * IP Address: neptune/129.82.44.122
     * Port Number 1345
     * waiting for client...
     * Clients Connected!
     * Sending config to clients...
     * earth.cs.colostate.edu -1170105035
     * jupiter.cs.colostate.edu 234785527
     * Finished sending config to clients.
     * Starting to listen for client messages...
     * Finished listening for client messages.
     * earth.cs.colostate.edu
     *      Messages received: 30
     *      Sum received: -6382135587
     * jupiter.cs.colostate.edu
     *      Messages received: 30
     *      Sum received: -2496366311
     */
}
