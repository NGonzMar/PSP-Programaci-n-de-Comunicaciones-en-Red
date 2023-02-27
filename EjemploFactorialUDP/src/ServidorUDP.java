import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket servidorSocket = new DatagramSocket(9876);
        byte[] recibirDatos = new byte[1024];
        byte[] enviarDatos;
        while (true) {
            DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
            servidorSocket.receive(recibirPaquete);
            InetAddress direccionIP = recibirPaquete.getAddress();
            int puerto = recibirPaquete.getPort();

            String mensaje = new String(recibirPaquete.getData()).trim();

            System.out.println("Recibido del cliente 1: " + mensaje);

            int numero = Integer.parseInt(mensaje.trim());
            enviarDatos = Integer.toString(numero).getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, direccionIP, 9877);
            servidorSocket.send(enviarPaquete);

            recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
            servidorSocket.receive(recibirPaquete);
            mensaje = new String(recibirPaquete.getData());
            int factorial = Integer.parseInt(mensaje.trim());
            enviarDatos = Integer.toString(factorial).getBytes();
            enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, direccionIP, puerto);
            servidorSocket.send(enviarPaquete);
        }
    }
}
