package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

/**
 * Created by Dani on 22/12/2017.
 */

public class BARRA {

    private int bala;
    private int nivel;

    public BARRA(int bala, int nivel) {
        this.bala = bala;
        this.nivel = nivel;
    }

    public BARRA( int level_) {

        this.nivel =level_;
    }

    public int getBala() {
        return bala;
    }

    public void setBala(int bala) {
        this.bala = bala;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
