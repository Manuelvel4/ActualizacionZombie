package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Objetos;

import java.util.Map;

/**
 * Created by manue on 29/12/2017.
 */

public class Usuario {
    private Map<String,String> list;

    private String nombre_pj;

  public   Usuario(){};


    public Usuario(Map<String, String> list,String nombre_pj) {
        this.list = list;
        this.nombre_pj = nombre_pj;
    }

    public Map<String, String> getList() {
        return list;
    }

    public void setList(Map<String, String> list) {
        this.list = list;
    }

    public String getNombre_pj() {
        return nombre_pj;
    }

    public void setNombre_pj(String nombre_pj) {
        this.nombre_pj = nombre_pj;
    }
}

