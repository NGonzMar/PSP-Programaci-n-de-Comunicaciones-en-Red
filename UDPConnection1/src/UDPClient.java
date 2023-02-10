import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String nombre;

        DatagramSocket socket = new DatagramSocket();// socket cliente
        byte[] buffer = new byte[1024];

        InetAddress ipServidor = InetAddress.getLocalHost();
        int puerto = 6000; // puerto por el que escucha

        System.out.print("Introduce tu nombre: ");
        nombre = sc.next();

        buffer = nombre.getBytes();
        DatagramPacket infoEnviada = new DatagramPacket(buffer, buffer.length, ipServidor, puerto);
        socket.send(infoEnviada);
    }
}
