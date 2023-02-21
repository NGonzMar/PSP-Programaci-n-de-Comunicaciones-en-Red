import java.io.*;
import java.net.*;

public class SocketServidor {
    public static void main(String args[]) throws IOException  {
        ServerSocket servidor;
        servidor = new ServerSocket(6000);
        System.out.println("Servidor iniciado...");

        //Cada vez que un cliente solicite la conexión, se activará un hilo
        while (true) {
            Socket cliente = new Socket();
            cliente=servidor.accept();//esperando cliente
            HiloServidor hilo = new HiloServidor(cliente);
            hilo.start();
        }
    }
}//..