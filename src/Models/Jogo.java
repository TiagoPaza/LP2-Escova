package Models;

import java.util.ArrayList;
import java.util.Collections;

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

	public void novaMao() {
		this.mesa = new ArrayList<Carta>();
		this.baralho = new Baralho();
		this.embaralhaCartas();
		this.distribuiCartasMesa();
	}

	public void novaRodada() {
		this.distribuiCartasJogadores();
	}

	private void embaralhaCartas() {
		Collections.shuffle(baralho.getCartas());
	}

	public void distribuiCartasJogadores() {
		int distribuicoes = 3;

		if (baralho.getCartas().size() < 6)
			distribuicoes = baralho.getCartas().size() / 2;

		for (int i = 1; i <= distribuicoes; i++) {
			jogador1.getMao().add(baralho.getCartas().remove(baralho.getCartas().size() - 1));
			jogador2.getMao().add(baralho.getCartas().remove(baralho.getCartas().size() - 1));
		}
	}

	public void distribuiCartasMesa() {
		int distribuicoes = 4;

		if (baralho.getCartas().size() < 4) {
			distribuicoes = baralho.getCartas().size();
		}

		for (int i = 0; i < distribuicoes; i++) {
			this.getMesa().add(baralho.getCartas().remove(baralho.getCartas().size() - 1));
		}
	}

	public boolean jogoAcabou() {
		return jogador1.getPontos() >= getPontosVitoria() || jogador2.getPontos() >= getPontosVitoria();
	}

	public boolean partidaAcabou() {
		return this.getBaralho().getCartas().size() == 0;
	}
}
