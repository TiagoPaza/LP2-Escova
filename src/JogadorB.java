import Models.Carta;
import Models.Jogada;
import Models.Jogador;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class JogadorB {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 2800);

        ObjectOutputStream paraServidor = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream doServidor = new ObjectInputStream(socket.getInputStream());

        String nome, idsCartasMesa;
        int opcao, idCartaMao;
        boolean cartaValida = false;

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("Bem-vindo ao jogo de Escova! Antes de começar precisamos que você se identifique.");
        System.out.println("Digite seu nome/nick:");

        nome = scanner.next();

        Jogador jogador = new Jogador(nome);
        Jogador vencedor = null;

        System.out.println("Ok então " + nome + ", aguarde o seu adversário. O jogo logo irá começar!");

        paraServidor.reset();
        paraServidor.writeObject(jogador);

        while (doServidor.readObject().equals("OK")) {
            System.out.println("Cartas na mesa:");
            ArrayList<Carta> mesa = (ArrayList<Carta>) doServidor.readObject();
            System.out.println(mesa);

            jogador = (Jogador) doServidor.readObject();

            System.out.println("Cartas na sua mão:");
            ArrayList<Carta> mao = jogador.getMao();
            System.out.println(mao);

            boolean jogou = false;

            do {
                do {
                    System.out.println("Selecione:    (1) Efetuar uma jogada    (2) Descartar carta");
                    opcao = Integer.parseInt(scanner.next());
                } while (opcao != 1 && opcao != 2);

                Carta cartaMao = null;
                ArrayList<Carta> cartasMesa = new ArrayList<Carta>();

                if (opcao == 1) {
                    do {
                        do {
                            System.out.println("Informe a carta da sua mão a ser jogada");
                            idCartaMao = Integer.parseInt(scanner.next());
                            for (Carta carta : mao) {
                                if (idCartaMao == carta.getId()) {
                                    cartaMao = carta;
                                    cartaValida = true;
                                    break;
                                }
                            }
                        } while (!cartaValida);

                        cartasMesa = new ArrayList<Carta>();
                        cartaValida = false;
                        System.out.println("Quais cartas da mesa capturar? Separe-as por espaço");
                        idsCartasMesa = scanner.next();

                        for (String idCartaMesa : idsCartasMesa.split(" ")) {
                            int id = Integer.parseInt(idCartaMesa);
                            for (Carta carta : mesa) {
                                if (id == carta.getId()) {
                                    cartaValida = true;
                                    cartasMesa.add(carta);
                                    break;
                                }
                            }
                        }
                    } while (!cartaValida);

                    Jogada jogada = new Jogada(cartaMao, cartasMesa, mao, mesa);
                    if (jogada.efetuarJogada()) {
                        System.out.println(
                                "Booa! Aguarde " + jogador.getAdversario().getNome() + "jogar.");
                        paraServidor.reset();
                        paraServidor.writeObject(jogada);

                        jogou = true;
                    } else
                        System.out.println("Opaa! Houve um erro na sua jogada, você não somou 15! Tente novamente.");
                } else {
                    do {
                        System.out.println("Digite o ID da carta a ser descartada:");
                        idCartaMao = Integer.parseInt(scanner.next());
                        for (Carta carta : mao) {
                            if (idCartaMao == carta.getId()) {
                                cartaMao = carta;
                                cartaValida = true;
                                break;
                            }
                        }
                    } while (!cartaValida);
                    Jogada jogada = new Jogada(cartaMao, cartasMesa, mao, mesa);
                    jogada.efetuarJogada();

                    System.out.println(
                            "Carta descartada. Aguarde a jogada de " + jogador.getAdversario().getNome() + ".");
                    paraServidor.reset();
                    paraServidor.writeObject(jogada);

                    jogou = true;
                }
            } while (!jogou);
        }

        if (jogador.isVenceu())
            vencedor = jogador;
        else {
            vencedor = jogador.getAdversario();
        }

        System.out.println("Booa! " + vencedor.getNome() + " venceu somando " + vencedor.getPontos() + " pontos.");

        UUID uuid = UUID.randomUUID();
        String stringUUID = uuid.toString();

        File file = new File("Public/Partidas/" + stringUUID + ".txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("Partida " + stringUUID + " entre: ");
        bufferedWriter.write(jogador.getNome() + " X " + jogador.getAdversario().getNome());
        bufferedWriter.newLine();
        bufferedWriter.write(vencedor.getNome() + " venceu somando " + vencedor.getPontos() + " pontos.");

        bufferedWriter.close();

        scanner.close();
        socket.close();
    }
}
