import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketCliente2 {
    public static void main(String[] args) throws IOException {
        Socket socketCliente2 = new Socket("localhost", 6666);
        System.out.println("*-*-*-CLIENTE 2 INICIADO-*-*-*");

        //Creación de flujo de entrada desde el servidor
        DataInputStream flujoEntrada = new DataInputStream(socketCliente2.getInputStream());
        String infoRecibida = flujoEntrada.readUTF();
        System.out.println("\nInformación recibida del servidor: " + infoRecibida);

        System.out.println("\nTransformando el número " + infoRecibida + " en su factorial...");
        int numFactorial = factorial(Integer.parseInt(infoRecibida));
        System.out.println("El resultado es " + "'" + numFactorial + "'");

        //Creación de flujo de salida del cliente 2
        System.out.println("\nMandando número factorial al servidor...");
        DataOutputStream flujoSalida = new DataOutputStream(socketCliente2.getOutputStream());
        flujoSalida.writeUTF(String.valueOf(numFactorial));

        //Cerrando sockets y flujos
        flujoEntrada.close();
        flujoSalida.close();
        socketCliente2.close();

        System.out.println("\n*-*-*-CLIENTE 2 APAGADO-*-*-*");
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
