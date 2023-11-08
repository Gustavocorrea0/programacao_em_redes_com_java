import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    protected Socket socket;
    private ArrayList<ServerThread> listaThread;
    private PrintWriter saida;
    private String mensagem = "";

    public ServerThread(Socket socket, ArrayList<ServerThread> listaThread) {
        this.socket = socket;
        this.listaThread = listaThread;
    }

    // Estabelecer o comportamento da Thread
    @Override
    public void run() {
        try {
            // Ler o que vem do cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Escrever o que vai para os clientes
            saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            while (true) {
                mensagem = entrada.readLine();
                if (mensagem == null || mensagem.equals("Fim")){
                    break;
                }
                // Exibir a mensagem para todos os clientes conectados
                for (ServerThread st: listaThread) {
                    st.saida.println(mensagem);
                }
                System.out.println("Servidor recebeu a mensagem " + mensagem);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}
