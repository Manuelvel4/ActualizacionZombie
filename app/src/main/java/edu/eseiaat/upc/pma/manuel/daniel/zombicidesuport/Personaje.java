package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by PortatilDani on 17/11/2017.
 */

public class Personaje{
    public String nombre,habAzul, habAmarilla, habNaranja1,habNaranja2, habRoja1,HabRoja2,habRoja3;
    public Drawable foto,cara;
    public Drawable carta1,carta2,carta3,carta4,carta5;
    public boolean invisible,modozombie;
    public boolean[] level;
    public String[] cartas;

    public Personaje(String nombre, String habAzul, String habAmarilla, String habNaranja1, String habNaranja2, String habRoja1, String habRoja2, String habRoja3, Drawable foto, Drawable cara) {
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
    }

    public String[] getCartas() {
        return cartas;
    }

    public void setCartas(String[] cartas) {
        this.cartas = cartas;
    }

    public boolean isModozombie() {
        return modozombie;
    }

    public void setModozombie(boolean modozombie) {
        this.modozombie = modozombie;
    }

    public Drawable getCarta1() {
        return carta1;
    }

    public void setCarta1(Drawable carta1) {
        this.carta1 = carta1;
    }

    public Drawable getCarta2() {
        return carta2;
    }

    public void setCarta2(Drawable carta2) {
        this.carta2 = carta2;
    }

    public Drawable getCarta3() {
        return carta3;
    }

    public void setCarta3(Drawable carta3) {
        this.carta3 = carta3;
    }

    public Drawable getCarta4() {
        return carta4;
    }

    public void setCarta4(Drawable carta4) {
        this.carta4 = carta4;
    }

    public Drawable getCarta5() {
        return carta5;
    }

    public void setCarta5(Drawable carta5) {
        this.carta5 = carta5;
    }

    public Personaje(String nombre, Drawable cara, boolean invisible) {
        this.nombre = nombre;
        this.cara = cara;
        this.invisible = invisible;
    }

    public boolean[] getLevel() {
        return level;
    }

    public void setLevel(boolean[] level) {
        this.level = level;
    }

    protected Personaje(Parcel in) {
        nombre = in.readString();
        habAzul = in.readString();
        habAmarilla = in.readString();
        habNaranja1 = in.readString();
        habNaranja2 = in.readString();
        habRoja1 = in.readString();
        HabRoja2 = in.readString();
        habRoja3 = in.readString();
        invisible = in.readByte() != 0;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
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

}

