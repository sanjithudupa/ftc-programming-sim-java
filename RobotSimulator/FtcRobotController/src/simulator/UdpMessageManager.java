package simulator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpMessageManager {

    static final int PORT_NUMBER = 8888;

    public static void send(String message) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        byte[] inputBytes = message.getBytes();

        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket sendPacket = new DatagramPacket(inputBytes, inputBytes.length, address, PORT_NUMBER);

        socket.send(sendPacket);

        socket.close();
    }

    public static String get(String message) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        byte[] inputBytes = message.getBytes();
        byte[] receiveBytes = new byte[1024];

        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket sendPacket = new DatagramPacket(inputBytes, inputBytes.length, address, PORT_NUMBER);
        DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);

        socket.send(sendPacket);

        socket.receive(receivePacket);
        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

        socket.close();
        return response;
    }
}
