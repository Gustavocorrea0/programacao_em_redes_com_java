import java.io.*;
import java.net.Socket;

public class Cliente1 {
    public final static int PORTA = 1025;

    public static void main(String[] args) {
        Socket s = null;
        BufferedReader entrada; //Armazena o que vem do servidor
        PrintWriter saida; // Armazena o que sera enviado ao servidor

        try {
            // Estabelecer a comunicao | Endereco | porta de conexao
            s = new Socket("192.168.112.21", PORTA);

            // Criar streams de entrada (vem do servidor) e saida (vai para o servidor)
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

            // Informar o status da conexao
            System.out.println("Conectado ao Servidor: " + s.getInetAddress() + " | Porta: " + s.getPort());

            // Escrever algo para o servidor
            saida.println("Iae calabreso");
            saida.flush();

            // Ler a resposta do servidor
            String respostaDoServidor = entrada.readLine();
            System.out.println("Servidor > " + respostaDoServidor);
            entrada.close();
            saida.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //Fechar Socket (caso tenha sido aberto)
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}