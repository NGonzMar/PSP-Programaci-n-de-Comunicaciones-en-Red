import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class SocketCliente2 {
    public static void main(String[] args) {

        //Creación del socket cliente 2
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

        System.out.println("*-*-*-CLIENTE 2 INICIADO-*-*-*");

        while (true) {
            //Recibimos la información mandada por el servidor
            DatagramPacket paqueteRecibido = new DatagramPacket(receiveData, receiveData.length);
            try {
                clienteSocket.receive(paqueteRecibido);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Conversión del datagrama a una variable de tipo String
            String cadenaNumRecogido = new String(paqueteRecibido.getData());

            //Conversión necesaria de la cadena a tipo 'int'
            int numero = Integer.parseInt(cadenaNumRecogido.trim());
            System.out.println("\nInformación recibida por parte del servidor: " + "'" + numero + "'");

            //Proceso de factorial del numero recibido
            int factorial = factorial(numero);
            System.out.println("\nFactorial del numero: " + numero + " = " + "'" + factorial + "'");

            cadenaNumRecogido = Integer.toString(factorial);
            sendData = cadenaNumRecogido.getBytes();

            //Envio del resultado al servidor
            DatagramPacket paqueteEnviado = new DatagramPacket(sendData, sendData.length, direccionIP, puerto);
            try {
                clienteSocket.send(paqueteEnviado);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Función que devuelve el factorial del número introducido
    public static int factorial(int n) {
        int resultado = 1;

        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

}
