import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";

    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);
        String ipRecogida;

        System.out.println(ANSI_YELLOW + "*-*-*-*-*Iniciando CLIENTE*-*-*-*-*" + ANSI_RESET);

        //Obtenemos la dirección del servidor
        InetAddress ipServidor;
        try {
            ipServidor = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Error al obtener la dirección IP del servidor: " + e.getMessage());
            return;
        }

        //Asignación de puerto
        int puerto = 6000;

        //Creación del conector con el servidor (socket)
        DatagramSocket conector;
        try {
            conector = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("Error al crear el socket: " + e.getMessage());
            return;
        }

        System.out.print("\nIndica una dirección IP (Formato CIDR, decimal): ");
        ipRecogida = sc.next();
        byte[] buffer = ipRecogida.getBytes();

        System.out.println(ANSI_GREEN + "\nIP introducida correctamente, enviando información al servidor..." + ANSI_RESET);

        DatagramPacket ipEnviada = new DatagramPacket(buffer, buffer.length, ipServidor, puerto);
        try {
            conector.send(ipEnviada);
        } catch (IOException e) {
            System.out.println("Error al enviar la información al servidor: " + e.getMessage());
            return;
        }

        System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

        System.out.println("\nRecibiendo información entrante...");

        DatagramPacket ipFinalRecibida = new DatagramPacket(buffer, buffer.length);
        try {
            conector.receive(ipFinalRecibida);
        } catch (IOException e) {
            System.out.println("Error al recibir la información: " + e.getMessage());
            return;
        }

        String infoFinalRecibida = new String(ipFinalRecibida.getData(), 0, ipFinalRecibida.getLength());

        System.out.println(ANSI_GREEN + infoFinalRecibida + ANSI_RESET);

        conector.close();
    }
}

