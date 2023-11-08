package comThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor3 {
    public final static int PORTA = 1025;
    public static Socket cliente;

    public static void main(String[] args) {
        // Criar lista de threads ativas (clientes conectados)
        ArrayList<ServerThread> listaThread = new ArrayList<ServerThread>();

        try {
            ServerSocket servidor = new ServerSocket();
            // Deixar servidor em loop para ele sempre aceitar novas conexões
            while (true) {
                System.out.println("Ouvindo porta " + PORTA + "...");
                cliente = servidor.accept();

                // Criar o thread para o socket estabelecido
                ServerThread thread = new ServerThread(cliente, listaThread);
                // Adicionar essa thread a lista de todos as threads ativas
                listaThread.add(thread);
                // Inicializar Thread
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            try {
                if (cliente != null) {
                    cliente.close();
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }
}
