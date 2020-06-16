package tpcservidor;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import tcpcliente.Pessoa;

public class TCPServidor {

    public static void main(String[] args) {
        int porta = 12345;

        ObjectOutputStream saida;
        ObjectInputStream entrada;
        boolean sair = false;
        Pessoa p1 = new Pessoa();

        try {
            // criando um socket para ouvir na porta e com uma fila de tamanho 10
            ServerSocket servidor = new ServerSocket(porta, 20);
            
            while (!sair) {
                System.out.println("Ouvindo na porta: " + porta);

                // Ficará bloqueado até algum cliente se conectar
                Socket conexao;
                conexao = servidor.accept();
                threadservidor tc = new threadservidor();
                tc.setConexao(conexao);
				new Thread(tc).start();
            }

        } catch (IOException e) {
            System.err.println("Erro: " + e.toString());
            System.exit(0);
        }

    }
}
