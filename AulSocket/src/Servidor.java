import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    public static final int PORTA = 1025;
    public static Socket cliente;

    public static void main(String[] args) {
        // Criar lista de Threads (cliente) ativas
        ArrayList<ServerThread> listaThreads = new ArrayList<ServerThread>();

        try {
            // Deixar o servidor em loop para aceitar novas conexoes
            ServerSocket sevidor = new ServerSocket();
            while (true) {
                // Estabelecer socket
                System.out.println("Ouvindo a porta " + PORTA + "...");
                cliente = sevidor.accept();
                // Criar a thread para o socket estabelecido
                ServerThread thread = new ServerThread(cliente, listaThreads);
                // Adicionar a thread a lista de clientes
                listaThreads.add(thread);
                // Inicializar a Thread
                thread.start();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            System.out.println("Encerrando conexao");
            try {
                if (cliente != null){
                    cliente.close();
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("CONEXAO ENCERRADA");
        }
    }
}
