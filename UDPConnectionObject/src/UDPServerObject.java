import models.Persona;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerObject {
    public static void main(String[] arg) throws IOException, ClassNotFoundException, IOException {

        DatagramSocket conector = new DatagramSocket(6000);

        while (true) {
            System.out.println("Esperando conexión...");

            byte[] buffer = new byte[1024];

            DatagramPacket infoRecibida = new DatagramPacket(buffer, buffer.length);
            conector.receive(infoRecibida); //Recibir información de nuestro cliente

            //Obtener ip del cliente mediante el paquete recibido
            InetAddress ipCliente = infoRecibida.getAddress();

            //Obtener puerto mediante el paquete recibido
            int puerto = infoRecibida.getPort();


            //Envío de objeto al cliente conectado
            System.out.println("\nEnviando información al cliente...");
            ByteArrayOutputStream mensaje = new ByteArrayOutputStream();

            ObjectOutput infoObject = new ObjectOutputStream(mensaje); //Traducción a información comprendida

            Persona p1 = new Persona("Nuria", 20); //Creación del objeto que enviaremos
            infoObject.writeObject(p1); //Escritura del objeto en el mensaje que se enviará
            infoObject.close(); //Cierre de escritura

            buffer = mensaje.toByteArray(); //Guardar el mensaje en el buffer de bytes

            DatagramPacket paqueteFinal = new DatagramPacket(buffer, buffer.length, ipCliente, puerto); //Paquete dirigido a cliente
            conector.send(paqueteFinal); //Envio de paquete final
            System.out.println("\nEnvio de persona: " + p1.getNombre() + ", " +
                    p1.getEdad() + " realizado correctamente.");


            System.out.println("\nRecibiendo información modificada por el cliente...");
            //Recibir información modificada del cliente
            buffer = new byte[1024];
            DatagramPacket infoModificadaRecibida = new DatagramPacket(buffer, buffer.length);
            conector.receive(infoModificadaRecibida);

            ObjectInputStream infoModificada = new ObjectInputStream(new ByteArrayInputStream(buffer));
            Persona p2 = (Persona)infoModificada.readObject();

            System.out.println("\nNombre: " + p2.getNombre() + "\n" +
                    "Edad: " + p2.getEdad());

            infoModificada.close();
        }
    }
}
