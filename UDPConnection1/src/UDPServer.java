import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(6000);

        byte[] buffer = new byte[1024];
        DatagramPacket recibo;

        recibo = new DatagramPacket(buffer, buffer.length);
        socket.receive(recibo); //recibo datagrama

        String mensaje = new String(recibo.getData()).trim();// obtengo String
        System.out.println("Cliente conectado con nombre: "+ mensaje);
    }
}
