import java.io.*;
import java.net.Socket;

public class Cliente2 {
    public final static int PORTA = 1025;

    public static void main(String[] args) throws IOException {
        Socket s = null;
        PrintWriter saida;
        BufferedReader entrada;
        BufferedReader teclado;
        String mensagem;
        boolean fim = false;

        try {
            // Estabelecer a comunicao | Endereco | porta de conexao
            s = new Socket("localhost", PORTA);

            // Criar streams de entrada (vem do servidor) e saida (vai para o servidor)
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            teclado = new BufferedReader(new InputStreamReader(System.in));

            // Informar o status da conexao
            System.out.println("Conectado ao Servidor: " + s.getInetAddress() + " | Porta: " + s.getPort());

            // Ler a primeira resposta do servidor
            String responta = entrada.readLine();
            System.out.println("Servidor > " + responta);

            // Enviar varias mensagens para o servidor
            while (true) {
                System.out.println("Cliente > ");
                System.out.flush();
                mensagem = teclado.readLine();
                if (mensagem.equals("fim")) {
                    fim = true;
                }
                saida.println(mensagem);
                saida.flush();

                // Ler a resposta do servidor. Caso seja vazia a conexao sera encerrado pelo servidor,
                // Caso contrario imprimir o que foi enviado pelo servidor
                mensagem = entrada.readLine();
                if (mensagem == null){
                    System.out.println("Conexao encerrada pelo servidor");
                    break;
                }

                if (fim){
                    System.out.println("Conexao encerrada");
                }

                System.out.println("Servidor > " + mensagem);
            }
            entrada.close();
            saida.close();
            teclado.close();
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            try {
                if (s != null){
                 s.close();
                }
            } catch (IOException e){
                throw new IOException(e);
            }
        }
    }
}
