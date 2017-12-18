package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.graphics.drawable.Drawable;
import java.io.Serializable;

/**
 * Created by PortatilDani on 17/11/2017.
 */

public class Personaje implements Serializable {
    public String nombre,habAzul, habAmarilla, habNaranja1,habNaranja2, habRoja1,HabRoja2,habRoja3;
    public Drawable foto,cara;
    public String habAzulZ, habAmarillaZ, habNaranja1Z,habNaranja2Z, habRoja1Z,HabRoja2Z,habRoja3Z;
    public Drawable fotoZ,caraZ;
    public Cartas carta1,carta2,carta3,carta4,carta5;
    public boolean invisible,modozombie;
    public boolean[] level;

    public Personaje(String nombre, String habAzul, String habAmarilla, String habNaranja1, String habNaranja2, String habRoja1, String habRoja2, String habRoja3, Drawable foto, Drawable cara, String habAzulZ, String habAmarillaZ, String habNaranja1Z, String habNaranja2Z, String habRoja1Z, String habRoja2Z, String habRoja3Z, Drawable fotoZ, Drawable caraZ) {
        this.nombre = nombre;
        this.habAzul = habAzul;
        this.habAmarilla = habAmarilla;
        this.habNaranja1 = habNaranja1;
        this.habNaranja2 = habNaranja2;
        this.habRoja1 = habRoja1;
        HabRoja2 = habRoja2;
        this.habRoja3 = habRoja3;
        this.foto = foto;
        this.cara = cara;
        this.habAzulZ = habAzulZ;
        this.habAmarillaZ = habAmarillaZ;
        this.habNaranja1Z = habNaranja1Z;
        this.habNaranja2Z = habNaranja2Z;
        this.habRoja1Z = habRoja1Z;
        HabRoja2Z = habRoja2Z;
        this.habRoja3Z = habRoja3Z;
        this.fotoZ = fotoZ;
        this.caraZ = caraZ;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHabAzul() {
        return habAzul;
    }

    public String getHabAmarilla() {
        return habAmarilla;
    }

    public String getHabNaranja1() {
        return habNaranja1;
    }

    public String getHabNaranja2() {
        return habNaranja2;
    }

    public String getHabRoja1() {
        return habRoja1;
    }

    public String getHabRoja2() {
        return HabRoja2;
    }

    public String getHabRoja3() {
        return habRoja3;
    }

    public Drawable getFoto() {
        return foto;
    }

    public Drawable getCara() {
        return cara;
    }

    public String getHabAzulZ() {
        return habAzulZ;
    }

    public String getHabAmarillaZ() {
        return habAmarillaZ;
    }

    public String getHabNaranja1Z() {
        return habNaranja1Z;
    }

    public String getHabNaranja2Z() {
        return habNaranja2Z;
    }

    public String getHabRoja1Z() {
        return habRoja1Z;
    }

    public String getHabRoja2Z() {
        return HabRoja2Z;
    }

    public String getHabRoja3Z() {
        return habRoja3Z;
    }

    public Drawable getFotoZ() {
        return fotoZ;
    }

    public Drawable getCaraZ() {
        return caraZ;
    }

    public Cartas getCarta1() {
        return carta1;
    }

    public void setCarta1(Cartas carta1) {
        this.carta1 = carta1;
    }

    public Cartas getCarta2() {
        return carta2;
    }

    public void setCarta2(Cartas carta2) {
        this.carta2 = carta2;
    }

    public Cartas getCarta3() {
        return carta3;
    }

    public void setCarta3(Cartas carta3) {
        this.carta3 = carta3;
    }

    public Cartas getCarta4() {
        return carta4;
    }

    public void setCarta4(Cartas carta4) {
        this.carta4 = carta4;
    }

    public Cartas getCarta5() {
        return carta5;
    }

    public void setCarta5(Cartas carta5) {
        this.carta5 = carta5;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isModozombie() {
        return modozombie;
    }

    public void setModozombie(boolean modozombie) {
        this.modozombie = modozombie;
    }

    public boolean[] getLevel() {
        return level;
    }

    public void setLevel(boolean[] level) {
        this.level = level;
    }
}

