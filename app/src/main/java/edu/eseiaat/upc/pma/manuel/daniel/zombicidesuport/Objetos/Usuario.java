package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Objetos;

import java.util.ArrayList;

import edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Personaje;

/**
 * Created by manue on 29/12/2017.
 */

public class Usuario {
    private  String nombre;
    private ArrayList<Personaje> list;

    public Usuario(String nombre, ArrayList<Personaje> list) {
        this.nombre = nombre;
        this.list = list;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Personaje> getList() {
        return list;
    }

    public void setList(ArrayList<Personaje> list) {
        this.list = list;
    }
}

