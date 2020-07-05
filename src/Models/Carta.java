package Models;

import java.io.Serializable;

public class Carta implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String valor;
    private String naipe;
    private int pontos;

    public Carta(int id, String valor, String naipe, int pontos) {
        this.id = id;
        this.naipe = naipe;
        this.pontos = pontos;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
