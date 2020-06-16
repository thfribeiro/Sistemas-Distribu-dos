package tpcservidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import tcpcliente.Pessoa;

public class threadservidor implements Runnable {

    /**
     * @return the conexao
     */
    public Socket getConexao() {
        return conexao;
    }

    /**
     * @param conexao the conexao to set
     */
    public void setConexao(Socket conexao) {
        this.conexao = conexao;
    }

    private Socket conexao;

    @Override
    public void run() {
        ObjectOutputStream saida = null;
        ObjectInputStream entrada = null;
        Pessoa p1 = new Pessoa();
        System.out.println("Conexao estabelecida com: " + getConexao().getInetAddress().getHostAddress());

        try {
            //Obtendo os fluxos de entrada e de saida
            saida = new ObjectOutputStream(getConexao().getOutputStream());
            entrada = new ObjectInputStream(getConexao().getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(threadservidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        do {
            try {
                // Obtendo o objeto serializado enviada pelo cliente
                p1 = (Pessoa) entrada.readObject();
                System.out.println("Cliente>> \n" + p1.toString());
                System.out.println("Alterando a idade para + 10 anos");
                p1.setIdade(p1.getIdade() + 10);
                System.out.println("Servidor>> \n" + p1.toString());
                // Enviando objeto alterado para o cliente
                saida.writeObject(p1);
                saida.flush();
            } catch (IOException iOException) {
                System.err.println("erro: " + iOException.toString());
                System.exit(0);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(threadservidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!p1.getNome().equals("FIM"));

        System.out.println("Conexao encerrada pelo cliente");
        try {
            saida.close();
            entrada.close();
            getConexao().close();
        } catch (IOException ex) {
            Logger.getLogger(threadservidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
