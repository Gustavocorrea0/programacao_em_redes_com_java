package comThread;

import javax.imageio.IIOException;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket socket;
    private ArrayList<ServerThread> listaThread; //ArrayList que armazena todas as threads
    private PrintWriter saida;
    private String mensagem;
    public boolean fim = false;

    public ServerThread(Socket socket, ArrayList<ServerThread> listaThreads){
        this.socket = socket;
        this.listaThread = listaThreads;
    }

    // Definir o comportamento da Thread
    @Override
    public void run(){
        try {
            // Ler o que vem do cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Escrever o que sera enviado para o cliente
            // true no final habilita o auto Flush :)
            saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            while (true){
                // Ler mensagem do cliente
                mensagem = entrada.readLine();

                if (mensagem == null || mensagem.equals("Fim")){
                    break;
                }

                // Exibir o mensagem para todos os clientes conectados
                for (ServerThread st : listaThread){
                    st.saida.println(mensagem);
                }
                System.out.println("Servidor recebeu a mensagem " + mensagem);
            }
            entrada.close();
            saida.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
