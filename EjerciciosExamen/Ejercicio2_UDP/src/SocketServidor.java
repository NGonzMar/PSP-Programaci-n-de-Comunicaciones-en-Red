import java.io.IOException;
import java.net.*;

public class SocketServidor {
    public static void main(String[] args){
        //Creación del socket servidor asociado con puerto '6666'
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(6666);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        byte[] receiveData = new byte[1024];
        byte[] sendData;

        while(true){

            System.out.println("*-*-*-SERVIDOR INICIADO-*-*-*");
            System.out.println("\nEsperando conexión del cliente 1...");

            //Creación del paquete con el buffer y la longitud del buffer asociado
            DatagramPacket paqueteRecibido = new DatagramPacket(receiveData, receiveData.length);

            try {
                serverSocket.receive(paqueteRecibido); //Recibimos los datos del cliente
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Declaración de variable para concer la ip del Cliente  y el puerto usado
            InetAddress direccionIP = paqueteRecibido.getAddress();
            int puerto = paqueteRecibido.getPort();

            //Obtenemos la string del datagrama
            String cadenaNumRecibido = new String(paqueteRecibido.getData()).trim();
            System.out.println("Información recibida del cliente 1: " + "'"  + cadenaNumRecibido + "'");

            System.out.println("\nEsperando conexión con el cliente 2...");

            //La variable recibida del cliente 1 procesada a tipo 'int'
            int numero = Integer.parseInt(cadenaNumRecibido.trim());

            //Enviamos el paquete recibido por el cliente 1 al cliente 2
            sendData = Integer.toString(numero).getBytes();

            DatagramPacket paqueteEnviado = new DatagramPacket(sendData, sendData.length, direccionIP, puerto);
            try {
                serverSocket.send(paqueteEnviado);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("\nInformación enviada al Cliente 2");

            //Recibimos de nuevo los datos del cliente 2 con el resultado final
            paqueteRecibido = new DatagramPacket(receiveData, receiveData.length);
            try {
                serverSocket.receive(paqueteRecibido);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Conversión del paquete recibido a String
            cadenaNumRecibido = new String(paqueteRecibido.getData());

            //Transformamos el resultado del 'factorial' a tipo 'int'
            int factorial = Integer.parseInt(cadenaNumRecibido.trim());

            //Almacenamos el factorial en el buffer 'sendData'
            sendData = Integer.toString(factorial).getBytes();

            //Enviamos el paquete al cliente 1
            paqueteEnviado = new DatagramPacket(sendData, sendData.length, direccionIP, puerto);
            try {
                serverSocket.send(paqueteEnviado);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
