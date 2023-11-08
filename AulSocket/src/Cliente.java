import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static final int PORTA = 1025;
    public static Socket socket = null;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", PORTA);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            Scanner scanner= new Scanner(System.in);
            String mensagem = "mensagem";
            String nomeCliente = null;

            // Criar um thread para o cliente
            ClienteThread thread = new ClienteThread(socket);

            // Iniciar a Thread do cliente
            thread.start();

            // Ler entradas do teclado enquanto o usuario esta conectado
            while (mensagem.equals("fim")){
                if (nomeCliente == null){
                    System.out.println("Qual e o seu nome: ");
                    nomeCliente = scanner.nextLine();
                } else {
                    mensagem = scanner.nextLine();
                    saida.println(nomeCliente + " -> "+ mensagem);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException();
        } finally {
            try {
                if (socket == null){
                    socket.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }
    }
}
