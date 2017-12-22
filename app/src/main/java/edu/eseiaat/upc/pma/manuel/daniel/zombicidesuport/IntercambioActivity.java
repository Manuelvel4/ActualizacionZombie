package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class IntercambioActivity extends AppCompatActivity {
    public static String Keycartas="key_cartas";
    public static String Keycartas2="key_cartas2";
    public static int pasarcartas=2;
    private Personaje p,q;

    private ArrayList<Carta> listacartas;
    private ArrayList<Carta> CartasDistancia,CartasCuerpo,CartasEspeciales,CartasOtras;
    private ImageView carta11,carta12,carta13,carta14,carta15,carta21,carta22,carta23,carta24,carta25;
    private TextView nombrep,nombreq;
    private boolean[] dropp,dropq;
    private int cartap,cartaq;
    private Carta c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercambio);
        carta11=(ImageView)findViewById(R.id.carta11);
        carta12=(ImageView)findViewById(R.id.carta12);
        carta13=(ImageView)findViewById(R.id.carta13);
        carta14=(ImageView)findViewById(R.id.carta14);
        carta15=(ImageView)findViewById(R.id.carta15);
        carta21=(ImageView)findViewById(R.id.carta21);
        carta22=(ImageView)findViewById(R.id.carta22);
        carta23=(ImageView)findViewById(R.id.carta23);
        carta24=(ImageView)findViewById(R.id.carta24);
        carta25=(ImageView)findViewById(R.id.carta25);
        nombrep=(TextView)findViewById(R.id.nombre1);
        nombreq=(TextView)findViewById(R.id.nombre2);
        p= (Personaje) getIntent().getSerializableExtra(Keycartas);
        q= (Personaje) getIntent().getSerializableExtra(Keycartas2);
        dropp =new boolean[5];
        dropq=new boolean[5];
        Mostrar();

        carta11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dropp[0]=true;
                cartap=1;
                c.setCarta(p.getCarta1().getCarta());
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta11.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Carta[] c=new Carta[5];
                        c[0]=p.getCarta1();
                        if (dropp[1]){
                            c[1]=p.getCarta2();
                            p.carta1=c[1];
                            p.carta2=c[0];
                        }
                        if (dropp[2]){
                            c[2]=p.getCarta3();
                            p.carta1=c[2];
                            p.carta3=c[0];
                        }
                        if (dropp[3]){
                            c[3]=p.getCarta4();
                            p.carta1=c[3];
                            p.carta4=c[0];
                        }
                        if (dropp[4]){
                            c[4]=p.getCarta5();
                            p.carta1=c[4];
                            p.carta5=c[0];
                        }
                        Mostrar();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta12.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dropp[1]=true;
                cartap=2;
                c.setCarta(p.getCarta2().getCarta());
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta12.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Carta[] c=new Carta[5];
                        c[1]=p.getCarta2();
                        if (dropp[0]){
                            c[0]=p.getCarta1();
                            p.carta2=c[0];
                            p.carta1=c[1];
                        }
                        if (dropp[2]){
                            c[2]=p.getCarta3();
                            p.carta2=c[2];
                            p.carta3=c[1];
                        }
                        if (dropp[3]){
                            c[3]=p.getCarta4();
                            p.carta2=c[3];
                            p.carta4=c[1];
                        }
                        if (dropp[4]){
                            c[4]=p.getCarta5();
                            p.carta2=c[4];
                            p.carta5=c[1];
                        }
                        Mostrar();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta13.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dropp[2]=true;
                cartap=3;
                c.setCarta(p.getCarta3().getCarta());
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta13.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Carta[] c=new Carta[5];
                        c[2]=p.getCarta3();
                        if (dropp[0]){
                            c[0]=p.getCarta1();
                            p.carta3=c[0];
                            p.carta1=c[2];
                        }
                        if (dropp[1]){
                            c[1]=p.getCarta2();
                            p.carta3=c[1];
                            p.carta2=c[2];
                        }
                        if (dropp[3]){
                            c[3]=p.getCarta4();
                            p.carta3=c[3];
                            p.carta4=c[2];
                        }
                        if (dropp[4]){
                            c[4]=p.getCarta5();
                            p.carta3=c[4];
                            p.carta5=c[2];
                        }
                        Mostrar();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta14.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dropp[3]=true;
                cartap=4;
                c.setCarta(p.getCarta4().getCarta());
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta14.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Carta[] c=new Carta[5];
                        c[3]=p.getCarta4();
                        if (dropp[0]){
                            c[0]=p.getCarta1();
                            p.carta4=c[0];
                            p.carta1=c[3];
                        }
                        if (dropp[1]){
                            c[1]=p.getCarta2();
                            p.carta4=c[1];
                            p.carta2=c[3];
                        }
                        if (dropp[2]){
                            c[2]=p.getCarta3();
                            p.carta4=c[2];
                            p.carta3=c[3];
                        }
                        if (dropp[4]){
                            c[4]=p.getCarta5();
                            p.carta4=c[4];
                            p.carta5=c[3];
                        }
                        Mostrar();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta15.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dropp[4]=true;
                cartap=5;
                c.setCarta(p.getCarta5().getCarta());
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta15.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Carta[] c=new Carta[5];
                        c[4]=p.getCarta5();
                        if (dropp[0]){
                            c[0]=p.getCarta1();
                            p.carta5=c[0];
                            p.carta1=c[4];
                        }
                        if (dropp[1]){
                            c[1]=p.getCarta2();
                            p.carta5=c[1];
                            p.carta2=c[4];
                        }
                        if (dropp[2]){
                            c[2]=p.getCarta3();
                            p.carta5=c[2];
                            p.carta3=c[4];
                        }
                        if (dropp[3]){
                            c[3]=p.getCarta4();
                            p.carta5=c[3];
                            p.carta4=c[4];
                        }
                        Mostrar();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta21.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dropq[0]=true;
                cartaq=1;
                c.setCarta(q.getCarta1().getCarta());
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta21.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Carta[] c=new Carta[5];
                        c[0]=q.getCarta1();
                        if (dropp[1]){
                            c[1]=q.getCarta2();
                            q.carta1=c[1];
                            q.carta2=c[0];
                        }
                        if (dropq[2]){
                            c[2]=q.getCarta3();
                            q.carta1=c[2];
                            q.carta3=c[0];
                        }
                        if (dropq[3]){
                            c[3]=q.getCarta4();
                            q.carta1=c[3];
                            q.carta4=c[0];
                        }
                        if (dropp[4]){
                            c[4]=p.getCarta5();
                            p.carta1=c[4];
                            p.carta5=c[0];
                        }
                        Mostrar();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta22.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dropq[1]=true;
                cartaq=2;c.setCarta(q.getCarta2().getCarta());

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta22.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Carta[] c=new Carta[5];
                        c[1]=q.getCarta2();
                        if (dropp[0]){
                            c[0]=q.getCarta1();
                            p.carta2=c[0];
                            p.carta1=c[1];
                        }
                        if (dropp[2]){
                            c[2]=p.getCarta3();
                            p.carta2=c[2];
                            p.carta3=c[1];
                        }
                        if (dropp[3]){
                            c[3]=p.getCarta4();
                            p.carta2=c[3];
                            p.carta4=c[1];
                        }
                        if (dropp[4]){
                            c[4]=p.getCarta5();
                            p.carta2=c[4];
                            p.carta5=c[1];
                        }
                        Mostrar();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta23.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dropq[2]=true;
                cartaq=3;
                c.setCarta(q.getCarta3().getCarta());
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta23.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Carta[] c=new Carta[5];
                        c[2]=p.getCarta3();
                        if (dropp[0]){
                            c[0]=p.getCarta1();
                            p.carta3=c[0];
                            p.carta1=c[2];
                        }
                        if (dropp[1]){
                            c[1]=p.getCarta2();
                            p.carta3=c[1];
                            p.carta2=c[2];
                        }
                        if (dropp[3]){
                            c[3]=p.getCarta4();
                            p.carta3=c[3];
                            p.carta4=c[2];
                        }
                        if (dropp[4]){
                            c[4]=p.getCarta5();
                            p.carta3=c[4];
                            p.carta5=c[2];
                        }
                        Mostrar();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta24.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dropq[3]=true;
                cartaq=4;
                c.setCarta(q.getCarta4().getCarta());
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta24.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Carta[] c=new Carta[5];
                        c[3]=p.getCarta4();
                        if (dropp[0]){
                            c[0]=p.getCarta1();
                            p.carta4=c[0];
                            p.carta1=c[3];
                        }
                        if (dropp[1]){
                            c[1]=p.getCarta2();
                            p.carta4=c[1];
                            p.carta2=c[3];
                        }
                        if (dropp[2]){
                            c[2]=p.getCarta3();
                            p.carta4=c[2];
                            p.carta3=c[3];
                        }
                        if (dropp[4]){
                            c[4]=p.getCarta5();
                            p.carta4=c[4];
                            p.carta5=c[3];
                        }
                        Mostrar();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta25.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dropq[4]=true;
                cartaq=5;
                c.setCarta(q.getCarta5().getCarta());
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta25.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Carta[] c=new Carta[5];
                        c[4]=p.getCarta5();
                        if (dropp[0]){
                            c[0]=p.getCarta1();
                            p.carta5=c[0];
                            p.carta1=c[4];
                        }
                        if (dropp[1]){
                            c[1]=p.getCarta2();
                            p.carta5=c[1];
                            p.carta2=c[4];
                        }
                        if (dropp[2]){
                            c[2]=p.getCarta3();
                            p.carta5=c[2];
                            p.carta3=c[4];
                        }
                        if (dropp[3]){
                            c[3]=p.getCarta4();
                            p.carta5=c[3];
                            p.carta4=c[4];
                        }
                        Mostrar();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
    private void Mostrar() {
        nombrep.setText(p.getNombre());
        carta11.setImageResource(p.getCarta1().getCarta());
        carta12.setImageResource(p.getCarta2().getCarta());
        carta13.setImageResource(p.getCarta3().getCarta());
        carta14.setImageResource(p.getCarta4().getCarta());
        carta15.setImageResource(p.getCarta5().getCarta());

        nombreq.setText(q.getNombre());
        carta21.setImageResource(q.getCarta1().getCarta());
        carta22.setImageResource(q.getCarta2().getCarta());
        carta23.setImageResource(q.getCarta3().getCarta());
        carta24.setImageResource(q.getCarta4().getCarta());
        carta25.setImageResource(q.getCarta5().getCarta());
    }
    private void ResetDrop() {
        for (int i = 0; i< dropp.length; i++){
            dropp[i]=false;
        }
    }
    private void Cambiar() {
        p.carta1.setCarta();
    }
}
