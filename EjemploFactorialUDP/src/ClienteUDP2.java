import java.net.*;

public class ClienteUDP2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket clienteSocket = new DatagramSocket(9877);
        byte[] enviarDatos;
        byte[] recibirDatos = new byte[1024];
        while (true) {
            DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
            clienteSocket.receive(recibirPaquete);
            String mensaje = new String(recibirPaquete.getData());
            int numero = Integer.parseInt(mensaje.trim());
            int factorial = 1;
            for (int i = 1; i <= numero; i++) {
                factorial *= i;
            }
            mensaje = Integer.toString(factorial);
            enviarDatos = mensaje.getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, recibirPaquete.getAddress(), recibirPaquete.getPort());
            clienteSocket.send(enviarPaquete);
        }
    }
}
