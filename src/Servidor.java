import Models.Jogador;
import Models.Jogo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static Jogo jogo;

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

        jogo = new Jogo(jogador1, jogador2);

        int partida = 0;

        while (!jogo.jogoAcabou()) {
            partida++;
            System.out.println("Tudo pronto!");
            System.out.println(" ");
            System.out.println("O " + jogador1.getNome() + " possui " + jogador1.getPontos() + " pontos.");
            System.out.println("O " + jogador2.getNome() + " possui " + jogador2.getPontos() + " pontos.");

            jogo.novaMao();

            while (!jogo.partidaAcabou()) {
                jogo.novaRodada();

            }
        }

        paraJogador1.reset();
        paraJogador1.writeObject("FIM");
        paraJogador2.reset();
        paraJogador2.writeObject("FIM");

        socketJogador1.close();
        socketJogador2.close();

        socket.close();
    }
}
