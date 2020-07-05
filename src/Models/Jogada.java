package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Jogada implements Serializable {
    private static final long serialVersionUID = 1L;

    private Carta cartaMao;
    private ArrayList<Carta> cartasMesa;
    private ArrayList<Carta> mao;
    private ArrayList<Carta> mesa;
    private ArrayList<Carta> capturadas;

    public Jogada(Carta cartaMao, ArrayList<Carta> cartasMesa, ArrayList<Carta> mao, ArrayList<Carta> mesa) {
        this.cartaMao = cartaMao;
        this.cartasMesa = cartasMesa;
        this.mao = mao;
        this.mesa = mesa;
        this.capturadas = new ArrayList<Carta>();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Carta getCartaMao() {
        return cartaMao;
    }

    public void setCartaMao(Carta cartaMao) {
        this.cartaMao = cartaMao;
    }

    public ArrayList<Carta> getCartasMesa() {
        return cartasMesa;
    }

    public void setCartasMesa(ArrayList<Carta> cartasMesa) {
        this.cartasMesa = cartasMesa;
    }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public void setMao(ArrayList<Carta> mao) {
        this.mao = mao;
    }

    public ArrayList<Carta> getMesa() {
        return mesa;
    }

    public void setMesa(ArrayList<Carta> mesa) {
        this.mesa = mesa;
    }

    public ArrayList<Carta> getCapturadas() {
        return capturadas;
    }

    public void setCapturadas(ArrayList<Carta> capturadas) {
        this.capturadas = capturadas;
    }
}
