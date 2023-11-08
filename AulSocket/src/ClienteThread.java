import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.*;

public class ClienteThread extends Thread {
    private Socket socket;
    BufferedReader entrada;

    public ClienteThread(Socket socket) throws IIOException {
        this.socket = socket;
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        try {
            // Obter e exibir a resposta do servidor
            while (true) {
                String resposta = entrada.readLine();
                System.out.println(resposta);
            }
        } catch (IOException ex) {
            throw new RuntimeException();
        } finally {
            try {
                entrada.close();
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }
    }

}
