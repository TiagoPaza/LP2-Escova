package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private ArrayList<Carta> mao;
    private ArrayList<Carta> pilha;
    private int pontos;
    private int escovas;
    private int ouros;
    private boolean seteBelo;
    private boolean venceu;
    private Jogador adversario;

    public Jogador(String nome) {
        this.nome = nome;
        this.mao = new ArrayList<Carta>();
        this.pilha = new ArrayList<Carta>();
        this.pontos = 0;
        this.escovas = 0;
        this.ouros = 0;
        this.seteBelo = false;
        this.venceu = false;
        this.adversario = null;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public void setMao(ArrayList<Carta> mao) {
        this.mao = mao;
    }

    public ArrayList<Carta> getPilha() {
        return pilha;
    }

    public void setPilha(ArrayList<Carta> pilha) {
        this.pilha = pilha;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getEscovas() {
        return escovas;
    }

    public void setEscovas(int escovas) {
        this.escovas = escovas;
    }

    public int getOuros() {
        return ouros;
    }

    public void setOuros(int ouros) {
        this.ouros = ouros;
    }

    public boolean isSeteBelo() {
        return seteBelo;
    }

    public void setSeteBelo(boolean seteBelo) {
        this.seteBelo = seteBelo;
    }

    public boolean isVenceu() {
        return venceu;
    }

    public void setVenceu(boolean venceu) {
        this.venceu = venceu;
    }

    public Jogador getAdversario() {
        return adversario;
    }

    public void setAdversario(Jogador adversario) {
        this.adversario = adversario;
    }
}
