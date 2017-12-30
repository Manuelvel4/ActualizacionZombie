/*package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Objetos;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Personaje;

*//**
 * Created by manue on 30/12/2017.
 *//*

public class AdapterPJ {

    private String sala;
    private String user;



    public AdapterPJ(){};

    public AdapterPJ(String sala, String user) {
        this.sala = sala;
        this.user = user;
    }

    public void recuperar_lista(List<Personaje> p){

        p = new ArrayList<>();
        List<Usuario> l = new ArrayList<>();


        FireBaseMethod f = new FireBaseMethod();

        f.Read_toFirebase(sala,user,l);
       *//* String nombre, String habAzul, String habAmarilla, String habNaranja1,
                String habNaranja2, String habRoja1, String habRoja2, String habRoja3,
        int foto, int cara, String habAzulZ, String habAmarillaZ, String habNaranja1Z,
                String habNaranja2Z, String habRoja1Z, String habRoja2Z, String habRoja3Z,
        int fotoZ, int caraZ, Carta carta1, edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Carta
        carta2, edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Carta
        carta3, edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Carta carta4,
                edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Carta carta5,
        boolean invisible, boolean modozombie, int[] level, int puntuacion,int vuelta *//*

        String nombre = l.get(0).getNombre_pj();
        int foto = Integer.parseInt(l.get(0).getList().get("cara"));

        Log.i("DATA","funciona" + nombre);
        Personaje personaje = new Personaje();



    }*/





