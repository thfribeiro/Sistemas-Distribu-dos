package tcpcliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPCliente {

    public static void main(String[] args) {
        String endereco = "localhost";
        int porta = 12345;
        ObjectOutputStream saida;
        ObjectInputStream entrada;
        Socket conexao;
        Scanner ler = new Scanner(System.in);
        Pessoa p = new Pessoa();
        try {
            conexao = new Socket(endereco, porta);
            System.out.println("Conectado ao servidor " + endereco + ", na porta: " + porta);
            System.out.println("Digite: FIM para encerrar a conexao");

            // Obtendo as conexoes de saida e de entrada
            saida = new ObjectOutputStream(conexao.getOutputStream());
            entrada = new ObjectInputStream(conexao.getInputStream());

            do {
                // Lendo os dados do objeto de saída
                System.out.print("..Digite seu nome: ");
                p.setNome(ler.next());
                System.out.print("..Digite sua idade: ");
                p.setIdade(ler.nextInt());
                
                saida.writeObject(p);
                saida.flush();

                // Lendo o objeto alterado enviado pelo servidor
                p = (Pessoa) entrada.readObject();
                System.out.println("Servidor>> \n" + p.toString());
                
            } while (!p.getNome().equals("FIM"));

            saida.close();
            entrada.close();
            conexao.close();

        } catch (Exception e) {
            System.err.println("erro: " + e.toString());
        }
    }
}
