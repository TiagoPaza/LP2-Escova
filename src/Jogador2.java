import Models.Carta;
import Models.Jogador;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Jogador2 {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 6789);

        ObjectOutputStream paraServidor = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream doServidor = new ObjectInputStream(socket.getInputStream());

        String nome;

        System.out.println("Bem-vindo ao jogo de Escova! Antes de começar precisamos que você se identifique.");
        System.out.println("Digite seu nome/nick:");

        Scanner addNome = new Scanner(System.in).useDelimiter("\n");
        nome = addNome.next();

        Jogador jogador = new Jogador(nome);

        System.out.println("Ok então " + nome + ", aguarde o seu adversário. O jogo logo irá começar!");

        paraServidor.reset();
        paraServidor.writeObject(jogador);

        while (doServidor.readObject().equals("OK")) {
            ArrayList<Carta> mesa = (ArrayList<Carta>) doServidor.readObject();
            System.out.println("Cartas na mesa:");
            System.out.println(mesa);

            jogador = (Jogador) doServidor.readObject();

            ArrayList<Carta> mao = jogador.getMao();

            System.out.println("Cartas na sua mão:");
            System.out.println(mao);
        }

        socket.close();
    }
}
