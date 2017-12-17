package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class IntercambioActivity extends AppCompatActivity {
    public static String Keycartas="key_cartas";
    public static String Keycartas2="key_cartas2";
    public static int pasarcartas=2;
    private List<Cartas> listacartas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercambio);
        OtrosCartas();
        EspecialesCartas();
        CuerpoCartas();
        DistanciaCartas();
    }
    private void DistanciaCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cmashotgun),"cmashotgun"));
        listacartas.add(new Cartas(getDrawable(R.drawable.ceviltwins),"ceviltwins"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cpistol),"cpistol"));
        listacartas.add(new Cartas(getDrawable(R.drawable.crifle),"crifle"));
        listacartas.add(new Cartas(getDrawable(R.drawable.csawedoff),"csawedoff"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cshotgun),"cshotgun"));
        listacartas.add(new Cartas(getDrawable(R.drawable.csubmg),"csubmg"));
    }
    private void CuerpoCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cbaseballbat),"cbaseballbat"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cchainsaw),"cchainsaw"));
        listacartas.add(new Cartas(getDrawable(R.drawable.ccrowbar),"ccrowbar"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cfireaxe),"cfireaxe"));
        listacartas.add(new Cartas(getDrawable(R.drawable.ckatana),"ckatana"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cmachete),"cmachete"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cpan),"cpan"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cmashotgun),"cmashotgun"));
    }
    private void EspecialesCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cgoaliemask),"cgoaliemask"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cflashlight),"cflashlight"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cplentyofammo),"cplentyofammo"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cplentyofammoshotgun),"cplentyofammoshotgun"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cscope),"cscope"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cmolotov),"cmolotov"));
    }
    private void OtrosCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cbagofrice),"cbagofrice"));
        listacartas.add(new Cartas(getDrawable(R.drawable.ccannedfood),"ccannedfood"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cwater),"cwater"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cgasoline),"cgasoline"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cglassbottle),"cglassbottle"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cwound),"cwound"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cartamano),"cartamano"));
    }
}
