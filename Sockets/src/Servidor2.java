import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2 {
    public final static int PORTA = 1025;

    public static void main(String[] args) {
        PrintWriter saida;
        BufferedReader entrada;
        Socket cliente = null;
        String mensagem;

        try {
            // Estabelecer a conecxao
            System.out.println("Ouvindo da porta " + PORTA + "...");
            ServerSocket servidor = new ServerSocket(PORTA);
            cliente = servidor.accept();

            // Preparar streams de entrada e saida
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));

            saida.println("Conexao estabelecida com " + cliente.getInetAddress());
            saida.flush();

            // Ler mensagens vinddas do cliente e retorna - las
            do {
                mensagem = entrada.readLine();
                // Retorna um ACK para o cliente
                saida.println("recebida a mensagem " + mensagem);
                saida.flush();
                System.out.println("Cliente > " + mensagem);

                // Imprimir mensagem do cliente na tela
                System.out.println("Cliente > " + mensagem);
                entrada.close();
                saida.close();
            } while (!mensagem.equals("fim"));

            entrada.close();
            saida.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Encerrando a conexao...");
            try {
                if(cliente != null){
                    cliente.close();
                }
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
