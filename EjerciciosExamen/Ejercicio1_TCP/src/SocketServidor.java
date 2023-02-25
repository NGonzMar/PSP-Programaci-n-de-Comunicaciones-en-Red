import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {
    public static void main(String[] args) throws IOException {
        int puerto = 6666;

        ServerSocket socketServer = new ServerSocket(puerto);
        System.out.println("*-*-*-SERVIDOR INICIADO-*-*-*");
        System.out.println("\nEsperando conexión del cliente 1...");

        Socket socketCliente1 = socketServer.accept();
        System.out.println("Cliente 1 conectado.");


        //Crear flujo de entrada del cliente 1
        DataInputStream flujoEntrada = new DataInputStream(socketCliente1.getInputStream());
        String numeroRecibido = flujoEntrada.readUTF();
        System.out.println("\nInformación recibida del cliente 1: " + "'" + numeroRecibido + "'");

        System.out.println("\nEsperando conexión con el cliente 2...");
        Socket socketCliente2 = socketServer.accept();
        System.out.println("Cliente 2 conectado.");

        //Crear flujo de salida hacia el cliente 2
        DataOutputStream flujoSalida = new DataOutputStream(socketCliente2.getOutputStream());
        flujoSalida.writeUTF(numeroRecibido);
        System.out.println("\nInformación mandada al cliente 2 para ser procesada.");

        //Recibir la información tratada del cliente 2
        flujoEntrada = new DataInputStream(socketCliente2.getInputStream());
        String infoTratada = flujoEntrada.readUTF();
        System.out.println("\nInformación tratada por el cliente 2:\n\t" +
                "El factorial del número introducido es " + "'" +infoTratada + "'");

        //Mandar información al cliente 1 para que pueda mostrarla
        flujoSalida = new DataOutputStream(socketCliente1.getOutputStream());
        flujoSalida.writeUTF(infoTratada);
        System.out.println("\nInformación FINAL enviada al cliente 1.");

        //Cierre de streams y sockets
        flujoEntrada.close();
        flujoSalida.close();
        socketServer.close();

        System.out.println("\n*-*-*-SERVIDOR APAGADO-*-*-*");
    }
}
