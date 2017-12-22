package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class JuegoActivity extends AppCompatActivity {


    public static String KeyListaPersonajes="key_listaPersonajes";
    public static String KeyCartasSeleccionadas="key_cartasSeleccionadas";
    public static String KeyListaCartasDistancia="key_cartasDistancia";
    public static String KeyListaCartasCuerpo="key_cartasCuerpo";
    public static String KeyListaCartasEspeciales="key_cartasEspeciales";
    public static String KeyListaCartasOtras="key_cartasOtras";
    private TextView habAzul,habAmarilla, habNaranja1, habNaranja2, habRoja1, habRoja2,habRoja3,nombre;
    private ImageView foto;
    private ArrayList<Personaje> listaPersonajes;
    private RecyclerView viewPersonajes;
    private LinearLayoutManager linlayoutmanager;
    private PersonajesAdapter adapterPersonajes;
    private int idPersonaje,idPersonajeInt;
    private ImageView carta1,carta2,carta3,carta4,carta5;
    private ArrayList<Carta> CartasDistancia,CartasCuerpo,CartasEspeciales,CartasOtras;
    private boolean[] drop;
    private Switch modozombie;
    private boolean intercambiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        habAzul=(TextView) findViewById(R.id.HabAzul);
        habAmarilla=(TextView)findViewById(R.id.HabAmarilla);
        habNaranja1=(TextView) findViewById(R.id.HabNaranja1);
        habNaranja2=(TextView) findViewById(R.id.HabNaranja2);
        habRoja1=(TextView) findViewById(R.id.HabRoja1);
        habRoja2=(TextView) findViewById(R.id.HabRoja2);
        habRoja3=(TextView) findViewById(R.id.HabRoja3);
        carta1=(ImageView)findViewById(R.id.Carta1);
        carta2=(ImageView)findViewById(R.id.Carta2);
        carta3=(ImageView)findViewById(R.id.Carta3);
        carta4=(ImageView)findViewById(R.id.Carta4);
        carta5=(ImageView)findViewById(R.id.Carta5);
        foto=(ImageView)findViewById(R.id.foto);
        nombre=(TextView)findViewById(R.id.nombre);
        modozombie = (Switch) findViewById(R.id.ModoZombie);

        listaPersonajes=new ArrayList<>();
        listaPersonajes= (ArrayList<Personaje>) getIntent().getSerializableExtra(KeyListaPersonajes);
        CartasDistancia=new ArrayList<>();
        CartasDistancia=(ArrayList<Carta>)getIntent().getSerializableExtra(KeyListaCartasDistancia);
        CartasCuerpo=new ArrayList<>();
        CartasCuerpo=(ArrayList<Carta>)getIntent().getSerializableExtra(KeyListaCartasCuerpo);
        CartasEspeciales=new ArrayList<>();
        CartasEspeciales=(ArrayList<Carta>)getIntent().getSerializableExtra(KeyListaCartasEspeciales);
        CartasOtras=new ArrayList<>();
        CartasOtras=(ArrayList<Carta>)getIntent().getSerializableExtra(KeyListaCartasOtras);

        viewPersonajes =(RecyclerView)findViewById(R.id.ViewPersonajes);
        linlayoutmanager =new LinearLayoutManager(this);
        viewPersonajes.setLayoutManager(linlayoutmanager);
        adapterPersonajes =new PersonajesAdapter(this,listaPersonajes);
        viewPersonajes.setAdapter(adapterPersonajes);
        idPersonaje=0;

        drop=new boolean[5];

        PersonajeSelec();


        adapterPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (intercambiar){
                    intercambiar=false;
                    idPersonajeInt=viewPersonajes.getChildAdapterPosition(view);
                    if (idPersonaje!=idPersonajeInt){
                        Intent intent=new Intent(JuegoActivity.this,IntercambioActivity.class);
                        Personaje p=listaPersonajesSelec.get(idPersonaje);
                        String[] pasocartas=p.getCartas();
                        intent.putExtra(IntercambioActivity.KeyPersonaje,pasocartas);
                        Personaje q=listaPersonajesSelec.get(idPersonajeInt);
                        String[] pasocartas2=q.getCartas();
                        intent.putExtra(IntercambioActivity.Keycartas2,pasocartas2);
                        startActivityForResult(intent,IntercambioActivity.pasarcartas);
                    }

                }else{*/
                    idPersonaje=viewPersonajes.getChildAdapterPosition(view);
                    PersonajeSelec();
                //}

            }
        });
        carta1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                drop[0]=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Personaje p=listaPersonajes.get(idPersonaje);
                        Carta[] c=new Carta[5];
                        c[0]=p.getCarta1();
                        if (drop[1]){
                            c[1]=p.getCarta2();
                            p.carta1=c[1];
                            p.carta2=c[0];
                        }
                        if (drop[2]){
                            c[2]=p.getCarta3();
                            p.carta1=c[2];
                            p.carta3=c[0];
                        }
                        if (drop[3]){
                            c[3]=p.getCarta4();
                            p.carta1=c[3];
                            p.carta4=c[0];
                        }
                        if (drop[4]){
                            c[4]=p.getCarta5();
                            p.carta1=c[4];
                            p.carta5=c[0];
                        }
                        PersonajeSelec();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                drop[1]=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Personaje p=listaPersonajes.get(idPersonaje);
                        Carta[] c=new Carta[5];
                        c[1]=p.getCarta2();
                        if (drop[0]){
                            c[0]=p.getCarta1();
                            p.carta2=c[0];
                            p.carta1=c[1];
                          }
                        if (drop[2]){
                            c[2]=p.getCarta3();
                            p.carta2=c[2];
                            p.carta3=c[1];
                        }
                        if (drop[3]){
                            c[3]=p.getCarta4();
                            p.carta2=c[3];
                            p.carta4=c[1];
                        }
                        if (drop[4]){
                            c[4]=p.getCarta5();
                            p.carta2=c[4];
                            p.carta5=c[1];
                        }
                        PersonajeSelec();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                drop[2]=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Personaje p=listaPersonajes.get(idPersonaje);
                        Carta[] c=new Carta[5];
                        c[2]=p.getCarta3();
                        if (drop[0]){
                            c[0]=p.getCarta1();
                            p.carta3=c[0];
                            p.carta1=c[2];
                        }
                        if (drop[1]){
                            c[1]=p.getCarta2();
                            p.carta3=c[1];
                            p.carta2=c[2];
                        }
                        if (drop[3]){
                            c[3]=p.getCarta4();
                            p.carta3=c[3];
                            p.carta4=c[2];
                        }
                        if (drop[4]){
                            c[4]=p.getCarta5();
                            p.carta3=c[4];
                            p.carta5=c[2];
                        }
                        PersonajeSelec();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                drop[3]=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Personaje p=listaPersonajes.get(idPersonaje);
                        Carta[] c=new Carta[5];
                        c[3]=p.getCarta4();
                        if (drop[0]){
                            c[0]=p.getCarta1();
                            p.carta4=c[0];
                            p.carta1=c[3];
                        }
                        if (drop[1]){
                            c[1]=p.getCarta2();
                            p.carta4=c[1];
                            p.carta2=c[3];
                        }
                        if (drop[2]){
                            c[2]=p.getCarta3();
                            p.carta4=c[2];
                            p.carta3=c[3];
                        }
                        if (drop[4]){
                            c[4]=p.getCarta5();
                            p.carta4=c[4];
                            p.carta5=c[3];
                        }
                        PersonajeSelec();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                drop[4]=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Personaje p=listaPersonajes.get(idPersonaje);
                        Carta[] c=new Carta[5];
                        c[4]=p.getCarta5();
                        if (drop[0]){
                            c[0]=p.getCarta1();
                            p.carta5=c[0];
                            p.carta1=c[4];
                        }
                        if (drop[1]){
                            c[1]=p.getCarta2();
                            p.carta5=c[1];
                            p.carta2=c[4];
                        }
                        if (drop[2]){
                            c[2]=p.getCarta3();
                            p.carta5=c[2];
                            p.carta3=c[4];
                        }
                        if (drop[3]){
                            c[3]=p.getCarta4();
                            p.carta5=c[3];
                            p.carta4=c[4];
                        }
                        PersonajeSelec();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        modozombie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personaje p=listaPersonajes.get(idPersonaje);
                p.modozombie=!p.modozombie;
                PersonajeSelec();
                adapterPersonajes.notifyDataSetChanged();
            }
        });
        carta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccionarCarta();
            }
        });
        carta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccionarCarta();
            }
        });
        carta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccionarCarta();
            }
        });
        carta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccionarCarta();
            }
        });
        carta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccionarCarta();
            }
        });
    }

    private void ResetDrop() {
        for (int i=0;i<drop.length;i++){
            drop[i]=false;
        }
    }

    private void SeleccionarCarta() {
        Intent intent=new Intent(JuegoActivity.this,CardsActivity.class);
        Personaje p=listaPersonajes.get(idPersonaje);
        intent.putExtra(CardsActivity.KeyPersonaje,p);
        intent.putExtra(CardsActivity.KeyListaCartasDistancia,CartasDistancia );
        intent.putExtra(CardsActivity.KeyListaCartasCuerpo, CartasCuerpo);
        intent.putExtra(CardsActivity.KeyListaCartasEspeciales,CartasEspeciales);
        intent.putExtra(CardsActivity.KeyListaCartasOtras, CartasOtras);
        startActivityForResult(intent,CardsActivity.pasarcartas);
    }


    private void PersonajeSelec() {
        Personaje p = listaPersonajes.get(idPersonaje);
        nombre.setText(p.getNombre());
        if (p.modozombie) {
            habAzul.setText(p.getHabAzulZ());
            habAmarilla.setText(p.getHabAmarillaZ());
            habNaranja1.setText(p.getHabNaranja1Z());
            habNaranja2.setText(p.getHabNaranja2Z());
            habRoja1.setText(p.getHabRoja1Z());
            habRoja2.setText(p.getHabRoja2Z());
            habRoja3.setText(p.getHabRoja3Z());
            foto.setImageResource(p.getCaraZ());
        }else{
            habAzul.setText(p.getHabAzul());
            habAmarilla.setText(p.getHabAmarilla());
            habNaranja1.setText(p.getHabNaranja1());
            habNaranja2.setText(p.getHabNaranja2());
            habRoja1.setText(p.getHabRoja1());
            habRoja2.setText(p.getHabRoja2());
            habRoja3.setText(p.getHabRoja3());
            foto.setImageResource(p.getCara());
        }
        carta1.setImageResource(p.getCarta1().getCarta());
        carta2.setImageResource(p.getCarta2().getCarta());
        carta3.setImageResource(p.getCarta3().getCarta());
        carta4.setImageResource(p.getCarta4().getCarta());
        carta5.setImageResource(p.getCarta5().getCarta());

        if (!p.level[0]){
            habAmarilla.setBackgroundColor(getColor(android.R.color.white));
        }else{
            habAmarilla.setBackgroundColor(getColor(R.color.yellow));
        }
        if (!p.level[1]){
            habNaranja1.setBackgroundColor(getColor(android.R.color.white));
        }else{
            habNaranja1.setBackgroundColor(getColor(android.R.color.holo_orange_dark));
        }
        if (!p.level[2]){
            habNaranja2.setBackgroundColor(getColor(android.R.color.white));
        }else {
            habNaranja2.setBackgroundColor(getColor(android.R.color.holo_orange_dark));
        }
        if (!p.level[3]){
            habRoja1.setBackgroundColor(getColor(android.R.color.white));
        }else{
            habRoja1.setBackgroundColor(getColor(android.R.color.holo_red_dark));
        }
        if (!p.level[4]){
            habRoja2.setBackgroundColor(getColor(android.R.color.white));
        }else{
            habRoja2.setBackgroundColor(getColor(android.R.color.holo_red_dark));
        }
        if (!p.level[5]){
            habRoja3.setBackgroundColor(getColor(android.R.color.white));
        }else{
            habRoja3.setBackgroundColor(getColor(android.R.color.holo_red_dark));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CardsActivity.pasarcartas){
            if (resultCode==RESULT_OK){
                Personaje p=listaPersonajes.get(idPersonaje);
                Personaje pcard= (Personaje) data.getExtras().getSerializable(CardsActivity.KeyPersonaje);
                p.setCarta1(pcard.getCarta1());
                p.setCarta2(pcard.getCarta2());
                p.setCarta3(pcard.getCarta3());
                p.setCarta4(pcard.getCarta4());
                p.setCarta5(pcard.getCarta5());
                PersonajeSelec();

            }
        }

    }

   /* public void Intercambiar(View view) {
        viewPersonajes.setBackgroundColor(getColor(android.R.color.holo_green_dark));
        intercambiar=true;
    }*/
}
