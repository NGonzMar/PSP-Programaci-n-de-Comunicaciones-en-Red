import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketCliente1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int numRecogido;

        Socket socketCliente1 = new Socket("localhost", 6666);
        System.out.println("*-*-*-CLIENTE 1 INICIADO-*-*-*");

        //Recogida de datos del usuario
        System.out.print("\nIndica un número: ");
        numRecogido = sc.nextInt();
        System.out.println("\nEl número introducido fue " + "'" + numRecogido + "'");

        //Crear flujo de salida hacia el server
        System.out.println("\nMandando información al servidor...");
        DataOutputStream flujoSalida = new DataOutputStream(socketCliente1.getOutputStream());
        flujoSalida.writeUTF(String.valueOf(numRecogido));

       //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

        //Crear flujo de entrada desde el server
        DataInputStream flujoEntrada = new DataInputStream(socketCliente1.getInputStream());

        System.out.println("\nInformación recibida del servidor:\n\t " +
                "El factorial del número introducido es " + "'" + flujoEntrada.readUTF() + "'");

        //Cierre de streams y sockets
        flujoSalida.close();
        flujoEntrada.close();
        socketCliente1.close();

        System.out.println("\n*-*-*-CLIENTE 1 APAGADO-*-*-*");
    }
}
