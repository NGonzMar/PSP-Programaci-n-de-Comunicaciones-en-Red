import java.io.IOException;
import java.net.*;

public class UDPServer {
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_CYAN = "\u001B[36m";

public static void main(String[] args) {
    int puerto = 6000;

    System.out.println(ANSI_CYAN + "*-*-*-*-*Iniciando SERVIDOR*-*-*-*-*" + ANSI_RESET);

    DatagramSocket conector = null;

    try {
        conector = new DatagramSocket(puerto);
    } catch (SocketException e) {
        System.out.println("Error al crear el socket: " + e.getMessage());
    }

    System.out.println("\nEsperando conexión entrante...");
    byte[] buffer = new byte[1024];

    DatagramPacket ipRecibida = new DatagramPacket(buffer, buffer.length);

    try {
        conector.receive(ipRecibida);
    } catch (IOException e) {
        System.err.println("Error al recibir el paquete: " + e.getMessage());
    }

    String infoRecibida = new String(ipRecibida.getData()).trim();

    InetAddress ipCliente = ipRecibida.getAddress();

    System.out.println("\nRecibiendo información del cliente: " + ipCliente);

    System.out.println(ANSI_GREEN + "\nInformación recibida: " + infoRecibida + ANSI_RESET);

    System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

    System.out.println("\nTransformando a formato binario...");

    InetAddress inetAddress = null;

    try {
        inetAddress = InetAddress.getByName(infoRecibida);
    } catch (UnknownHostException e) {
        System.err.println("Error al obtener la dirección IP: " + e.getMessage());
    }

    byte[] address = inetAddress.getAddress();

    StringBuilder sb = new StringBuilder();
    for (byte b : address) {
        sb.append(String.format("%8s", Integer.toBinaryString(b & 0xff)).replace(' ', '0')).append(" ");
    }

    System.out.println("\nIP address: " + infoRecibida);
    System.out.println("Binary: " + sb.toString());

    System.out.println("\nEnviando información al cliente...");

    String ipABinario = sb.toString();
    buffer = ipABinario.getBytes();

    DatagramPacket ipFinalEnviada = new DatagramPacket(buffer, buffer.length, ipCliente, puerto);
    try {
        conector.send(ipFinalEnviada);
    } catch (IOException e) {
        System.err.println("Error al enviar el paquete: " + e.getMessage());

    }

    System.out.println(ANSI_GREEN + "\nMensaje enviado." + ANSI_RESET);
    }
}
