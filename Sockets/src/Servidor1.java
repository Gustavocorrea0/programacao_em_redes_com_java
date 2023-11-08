import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {
    // Igual a porta de cliente
    public final static int PORTA = 1025;

    public static void main(String[] args) {
        BufferedReader entrada; //Armazena o que vem do servidor
        PrintWriter saida; // Armazena o que sera enviado ao servidor
        Socket cliente = null;

        try {
            // Estabelecer conexão
            System.out.println("Ouvindo a porta " + PORTA + "...");
            ServerSocket servidor = new ServerSocket(PORTA);
            cliente = servidor.accept(); // "aceita" a conexao do cliente
            System.out.println("Conexao estabelecida com " + cliente.getInetAddress());

            // Preparar streams de entrada e saida
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));

            // Ler mensagem do cliente
            String mensagem = entrada.readLine();
            System.out.println("Cliente > " + mensagem);

            // Responder a mensagem ao cliente
            saida.println("Fala Samsungo");
            saida.flush();
            entrada.close();
            saida.close();
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            System.out.println("Encerrando conexão");
            try {
                if (cliente != null){
                    cliente.close();
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

    }
}
