package Models;

import java.util.ArrayList;

public class Jogo {
	private final int pontosVitoria = 6;

	private Jogador jogador1;
	private Jogador jogador2;
	private Baralho baralho;
	private ArrayList<Carta> mesa;

	public Jogo(Jogador j1, Jogador j2) {
		this.jogador1 = j1;
		this.jogador2 = j2;
		this.mesa = new ArrayList<Carta>();
	}

	public int getPontosVitoria() {
		return pontosVitoria;
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public void setBaralho(Baralho baralho) {
		this.baralho = baralho;
	}

	public ArrayList<Carta> getMesa() {
		return mesa;
	}

	public void setMesa(ArrayList<Carta> mesa) {
		this.mesa = mesa;
	}
}
