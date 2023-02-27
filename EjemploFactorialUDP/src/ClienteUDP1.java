import java.net.*;
import java.util.Scanner;

public class ClienteUDP1 {
    public static void main(String[] args) throws Exception {
        DatagramSocket clienteSocket = new DatagramSocket();
        InetAddress direccionIP = InetAddress.getByName("localhost");
        byte[] enviarDatos;
        byte[] recibirDatos = new byte[1024];
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un número: ");
        int numero = sc.nextInt();
        String mensaje = Integer.toString(numero);
        enviarDatos = mensaje.getBytes();
        DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, direccionIP, 9876);
        clienteSocket.send(enviarPaquete);
        DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
        clienteSocket.receive(recibirPaquete);
        mensaje = new String(recibirPaquete.getData());
        int factorial = Integer.parseInt(mensaje.trim());
        System.out.println("El factorial de " + numero + " es: " + factorial);
        clienteSocket.close();
    }
}
