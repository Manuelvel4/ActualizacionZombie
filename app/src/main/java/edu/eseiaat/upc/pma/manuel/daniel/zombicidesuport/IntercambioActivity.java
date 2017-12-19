package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class IntercambioActivity extends AppCompatActivity {
    public static String Keycartas="key_cartas";
    public static String Keycartas2="key_cartas2";
    public static int pasarcartas=2;
    private List<Carta> listacartas;

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
        listacartas.add(new Carta((R.drawable.cmashotgun),"cmashotgun"));
        listacartas.add(new Carta((R.drawable.ceviltwins),"ceviltwins"));
        listacartas.add(new Carta((R.drawable.cpistol),"cpistol"));
        listacartas.add(new Carta((R.drawable.crifle),"crifle"));
        listacartas.add(new Carta((R.drawable.csawedoff),"csawedoff"));
        listacartas.add(new Carta((R.drawable.cshotgun),"cshotgun"));
        listacartas.add(new Carta((R.drawable.csubmg),"csubmg"));
    }
    private void CuerpoCartas() {
        listacartas.add(new Carta((R.drawable.cbaseballbat),"cbaseballbat"));
        listacartas.add(new Carta((R.drawable.cchainsaw),"cchainsaw"));
        listacartas.add(new Carta((R.drawable.ccrowbar),"ccrowbar"));
        listacartas.add(new Carta((R.drawable.cfireaxe),"cfireaxe"));
        listacartas.add(new Carta((R.drawable.ckatana),"ckatana"));
        listacartas.add(new Carta((R.drawable.cmachete),"cmachete"));
        listacartas.add(new Carta((R.drawable.cpan),"cpan"));
        listacartas.add(new Carta((R.drawable.cmashotgun),"cmashotgun"));
    }
    private void EspecialesCartas() {
        listacartas.add(new Carta((R.drawable.cgoaliemask),"cgoaliemask"));
        listacartas.add(new Carta((R.drawable.cflashlight),"cflashlight"));
        listacartas.add(new Carta((R.drawable.cplentyofammo),"cplentyofammo"));
        listacartas.add(new Carta((R.drawable.cplentyofammoshotgun),"cplentyofammoshotgun"));
        listacartas.add(new Carta((R.drawable.cscope),"cscope"));
        listacartas.add(new Carta((R.drawable.cmolotov),"cmolotov"));
    }
    private void OtrosCartas() {
        listacartas.add(new Carta((R.drawable.cbagofrice),"cbagofrice"));
        listacartas.add(new Carta((R.drawable.ccannedfood),"ccannedfood"));
        listacartas.add(new Carta((R.drawable.cwater),"cwater"));
        listacartas.add(new Carta((R.drawable.cgasoline),"cgasoline"));
        listacartas.add(new Carta((R.drawable.cglassbottle),"cglassbottle"));
        listacartas.add(new Carta((R.drawable.cwound),"cwound"));
        listacartas.add(new Carta((R.drawable.cartamano),"cartamano"));
    }
}
