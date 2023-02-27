import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class SocketCliente1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Creación del socket cliente 1
        DatagramSocket clienteSocket;
        try {
            clienteSocket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        //Declaración de la variable que contenga la direccion ip
        InetAddress direccionIP;
        try {
            direccionIP = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        int puerto = 6666;

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        System.out.println("*-*-*-CLIENTE 1 INICIADO-*-*-*");

        //Introducción de datos por teclado
        System.out.print("\nIndica un número: ");
        int numRecogido = sc.nextInt();

        //Conevrsión del numero recogido a 'String'
        String cadenaNumRecogido = Integer.toString(numRecogido);

        //Almacenaje de los datos en el buffer que se enviará al server
        sendData = cadenaNumRecogido.getBytes();

        //Enviar el datagrama al servidor
        DatagramPacket paqueteEnviado = new DatagramPacket(sendData, sendData.length, direccionIP, puerto);
        try {
            clienteSocket.send(paqueteEnviado);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Recibir el datagrama del servidor
        DatagramPacket paqueteRecibido = new DatagramPacket(receiveData, receiveData.length);
        try {
            clienteSocket.receive(paqueteRecibido);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Mostrar información final
        String cadenaPaqueteFinal = new String(paqueteRecibido.getData());
        int factorial = Integer.parseInt(cadenaNumRecogido.trim());

        System.out.println("\nEl factorial de " + numRecogido + " es: " + "'" + factorial + "'");

        clienteSocket.close();
    }
}
