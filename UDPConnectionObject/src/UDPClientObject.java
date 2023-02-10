import models.Persona;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPClientObject {
    public static void main(String[] arg) throws IOException, ClassNotFoundException, IOException {

        InetAddress ipServidor = InetAddress.getLocalHost();//Obtener dirección servidor
        int puerto = 6000; //Asignar puerto
        byte[] buffer = new byte[1024]; //Recibir y mandar información

        //Creación del conector con el servidor (socket)
        DatagramSocket conector = new DatagramSocket();

        DatagramPacket infoEnviada = new DatagramPacket(buffer, buffer.length, ipServidor, puerto);
        conector.send(infoEnviada); //Envío de información para que el server nos detecte

        //Recibiendo información
        DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
        conector.receive(recibo);
        System.out.println("Recibiendo información del servidor...");

        ObjectInputStream infoObject = new ObjectInputStream(new ByteArrayInputStream(buffer)); //Transformar de bytes a objeto
        Persona p1 = (Persona)infoObject.readObject(); //Lectura en 'p1' del objeto recibido
        infoObject.close(); //Cierre de lectura
        System.out.println("\nInformación recibida: " + p1.getNombre() + ", " +
                p1.getEdad());

        System.out.println("\nModificando información...");
        //Modificar variables para enviarle la info modificada al servidor
        p1.setEdad(23);
        p1.setNombre("Daniel");

        buffer = new byte[1024]; //Cambio del primer buffer

        ByteArrayOutputStream objetoEnviado = new ByteArrayOutputStream();
        ObjectOutput mensajeObjeto = new ObjectOutputStream(objetoEnviado);
        mensajeObjeto.writeObject(p1);
        mensajeObjeto.close();

        buffer = objetoEnviado.toByteArray();

        DatagramPacket paqueteFinal = new DatagramPacket(buffer, buffer.length, ipServidor, puerto);
        conector.send(paqueteFinal);
        System.out.println("\nInformación modificada enviada correctamente.");
    }
}
