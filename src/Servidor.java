import Models.Jogador;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(6789);

        Socket socketJogador1 = socket.accept();
        ObjectInputStream doJogador1 = new ObjectInputStream(socketJogador1.getInputStream());
        ObjectOutputStream paraJogador1 = new ObjectOutputStream(socketJogador1.getOutputStream());

        Socket socketJogador2 = socket.accept();
        ObjectInputStream doJogador2 = new ObjectInputStream(socketJogador2.getInputStream());
        ObjectOutputStream paraJogador2 = new ObjectOutputStream(socketJogador2.getOutputStream());

        Jogador jogador1 = (Jogador) doJogador1.readObject();
        System.out.println(jogador1.getNome() + " entrou no jogo.");

        Jogador jogador2 = (Jogador) doJogador2.readObject();
        System.out.println(jogador2.getNome() + " entrou no jogo.");

        System.out.println(" ");

        System.out.println("Inicializando a partida, aguarde...");

        jogador1.setAdversario(jogador2);
        jogador2.setAdversario(jogador1);

        socketJogador1.close();
        socketJogador2.close();

        socket.close();
    }
}
