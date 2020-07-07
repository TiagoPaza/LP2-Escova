import Models.Jogada;
import Models.Jogador;
import Models.Jogo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static Jogo jogo;

    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(2800);

        Socket socketJogadorA = socket.accept();
        ObjectInputStream doJogadorA = new ObjectInputStream(socketJogadorA.getInputStream());
        ObjectOutputStream paraJogadorA = new ObjectOutputStream(socketJogadorA.getOutputStream());

        Socket socketJogadorB = socket.accept();
        ObjectInputStream doJogadorB = new ObjectInputStream(socketJogadorB.getInputStream());
        ObjectOutputStream paraJogadorB = new ObjectOutputStream(socketJogadorB.getOutputStream());

        Jogador jogadorA = (Jogador) doJogadorA.readObject();
        System.out.println(jogadorA.getNome() + " entrou no jogo.");

        Jogador jogadorB = (Jogador) doJogadorB.readObject();
        System.out.println(jogadorB.getNome() + " entrou no jogo.");

        System.out.println(" ");

        System.out.println("Inicializando a partida, aguarde...");

        jogadorA.setAdversario(jogadorB);
        jogadorB.setAdversario(jogadorA);

        jogo = new Jogo(jogadorA, jogadorB);

        int partida = 0;

        while (!jogo.jogoAcabou()) {
            partida++;

            System.out.println("Tudo pronto!");
            System.out.println(" ");
            System.out.println("O " + jogadorA.getNome() + " possui " + jogadorA.getPontos() + " pontos.");
            System.out.println("O " + jogadorB.getNome() + " possui " + jogadorB.getPontos() + " pontos.");

            jogo.novaMao();

            while (!jogo.partidaAcabou()) {
                jogo.novaRodada();

                while (!jogo.rodadaAcabou()) {
                    solicitaJogada(paraJogadorA, jogadorA);
                    recebeJogada(doJogadorA, jogadorA);
                    solicitaJogada(paraJogadorB, jogadorB);
                    recebeJogada(doJogadorB, jogadorB);
                }
            }
        }

        paraJogadorA.reset();
        paraJogadorA.writeObject("FIM");
        paraJogadorB.reset();
        paraJogadorB.writeObject("FIM");

        socketJogadorA.close();
        socketJogadorB.close();

        socket.close();
    }

    public static void solicitaJogada(ObjectOutputStream paraJogador, Jogador jogador) {
        try {
            paraJogador.reset();
            paraJogador.writeObject("OK");

            paraJogador.reset();
            paraJogador.writeObject(jogo.getMesa());

            paraJogador.reset();
            paraJogador.writeObject(jogador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void recebeJogada(ObjectInputStream doJogador, Jogador jogador) {
        try {
            Jogada jogada = (Jogada) doJogador.readObject();

            jogo.setMesa(jogada.getMesa());
            jogador.setMao(jogada.getMao());

            if (jogada.getCapturadas().size() > 0) {
                jogador.insereNaPilha(jogada.getCapturadas());

                if (jogo.getMesa().size() == 0) {
                    jogador.setEscovas(jogador.getEscovas() + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
