package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Objetos.Usuario;

public class JuegoActivity extends AppCompatActivity {

    public static String KeyCargar="key_cargar";

    public static String KeyListaPersonajes="key_listaPersonajes";
    public static String KeyListaCartasDistancia="key_cartasDistancia";
    public static String KeyListaCartasCuerpo="key_cartasCuerpo";
    public static String KeyListaCartasEspeciales="key_cartasEspeciales";
    public static String KeyListaCartasOtras="key_cartasOtras";


    public static String keysala="key_sala";
    public static String keynombre="key_nombre";

    private TextView habAzul,habAmarilla, habNaranja1, habNaranja2, habRoja1, habRoja2,habRoja3,nombre;

    private ImageView foto;

    private ArrayList<Personaje> listaPersonajes;

    private ArrayList<Personaje> listaPersonajes2;

    private RecyclerView viewPersonajes;
    private LinearLayoutManager linlayoutmanager;
    private PersonajesAdapter adapterPersonajes;
    private int idPersonaje,idPersonajeInt;
    private int c1,c2;
    private ImageView carta1,carta2,carta3,carta4,carta5;
    private ArrayList<Carta> CartasDistancia,CartasCuerpo,CartasEspeciales,CartasOtras;
    private Switch modozombie;
    private boolean intercambiar;
    private ArrayList<BARRA> lista;
    private ArrayList<Integer> lista_Draw;
    private ArrayList<Integer> lista_red;
    private RecyclerView recy;
    private Button btn_plus, btn_less;
    private AdaptadorBarra adaptarBarra;
    private boolean cargar;


    //Declaro el firebase

    FirebaseDatabase f = FirebaseDatabase.getInstance();

    private Map<String,String> read;

    List<Usuario> user_list = new ArrayList<>();


    private void Writedata(){
        try {
            ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream("media.obj"));
            salida.writeObject(listaPersonajes);
            salida.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, R.string.NoFile, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, R.string.NoWrite, Toast.LENGTH_SHORT).show();
        }
    }
    private void Readdata(){
        try {
            ObjectInputStream entrada=new ObjectInputStream(new FileInputStream("media.obj"));
            listaPersonajes=((ArrayList<Personaje>) entrada.readObject());
            entrada.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            Toast.makeText(this, R.string.NoRead, Toast.LENGTH_SHORT).show();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        Writedata();
    }
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

        cargar=getIntent().getBooleanExtra(KeyCargar,false);

        listaPersonajes=new ArrayList<>();

        if (!cargar){
            listaPersonajes= (ArrayList<Personaje>) getIntent().getSerializableExtra(KeyListaPersonajes);
        }else{
            Readdata();
        }

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

        lista =new ArrayList<>();
        lista_Draw = new ArrayList<>();
        lista_red =new ArrayList<>();

        recy = (RecyclerView) findViewById(R.id.ViewLevel);
        btn_plus = (Button)findViewById(R.id.BTNmas);
        btn_less = (Button)findViewById(R.id.BTNmenos);

        recy.setLayoutManager( new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        llenar_DATOS();
        adaptarBarra = new AdaptadorBarra(lista);
        recy.setAdapter(adaptarBarra);

        PersonajeSelec();

        habNaranja1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personaje p=listaPersonajes.get(idPersonaje);
                if (p.level[0]==1){
                    p.level[0]=2;
                    if (p.level[1]==1){
                        p.level[1]=0;
                    }
                }
                PersonajeSelec();
            }
        });
        habNaranja2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personaje p=listaPersonajes.get(idPersonaje);
                if (p.level[1]==1){
                    p.level[1]=2;
                    if (p.level[0]==1){
                        p.level[0]=0;
                    }
                }
                PersonajeSelec();
            }
        });
        habRoja1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personaje p=listaPersonajes.get(idPersonaje);
                if (p.level[2]==1){
                    p.level[2]=2;
                    if (p.level[3]==1){
                        p.level[3]=0;
                    }
                    if (p.level[4]==1){
                        p.level[4]=0;
                    }
                }
                PersonajeSelec();
            }
        });
        habRoja2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personaje p=listaPersonajes.get(idPersonaje);
                if (p.level[3]==1){
                    p.level[3]=2;
                    if (p.level[2]==1){
                        p.level[2]=0;
                    }
                    if (p.level[4]==1){
                        p.level[4]=0;
                    }
                }
                PersonajeSelec();
            }
        });
        habRoja3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personaje p=listaPersonajes.get(idPersonaje);
                if (p.level[4]==1){
                    p.level[4]=2;
                    if (p.level[2]==1){
                        p.level[2]=0;
                    }
                    if (p.level[3]==1){
                        p.level[3]=0;
                    }
                }
                PersonajeSelec();
            }
        });
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personaje p=listaPersonajes.get(idPersonaje);
                if (p.puntuacion != 43) {
                    p.puntuacion++;
                    if (p.puntuacion==19){
                        if (p.vuelta==1){
                            p.level[0]=1;
                            p.level[1]=1;
                        }else if (p.vuelta==2){
                            if (p.level[0] == 0) {
                                p.level[0]=2;
                            }
                            if (p.level[1] == 0) {
                                p.level[1]=2;
                            }
                        }
                    }
                    if (p.puntuacion==43){
                        if (p.vuelta==1){
                            p.level[2]=1;
                            p.level[3]=1;
                            p.level[4]=1;
                        }else if (p.vuelta==2){
                            if (p.level[2] == 0) {
                                p.level[2]=1;
                            }
                            if (p.level[3] == 0) {
                                p.level[3]=1;
                            }
                            if (p.level[4] == 0) {
                                p.level[4]=1;
                            }
                        }else if (p.vuelta==3) {
                            if (p.level[2] == 0) {
                                p.level[2] = 2;
                            }
                            if (p.level[3] == 0) {
                                p.level[3] = 2;
                            }
                            if (p.level[4] == 0) {
                                p.level[4] = 2;
                            }
                        }
                    }
                    PersonajeSelec();
                }

            }
        });


        btn_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personaje p=listaPersonajes.get(idPersonaje);
                if (p.puntuacion!=0) {
                    if (p.puntuacion == 0) {
                        p.puntuacion = 1;
                    }
                    p.puntuacion--;
                    if (p.puntuacion == 18) {
                        p.level[0] = 0;
                        p.level[1] = 0;
                    }
                    if (p.puntuacion == 42) {
                        p.level[2] = 0;
                        p.level[3] = 0;
                        p.level[4] = 0;
                    }
                    PersonajeSelec();
                }
            }
        });

        adapterPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intercambiar){
                    intercambiar=false;
                    idPersonajeInt=viewPersonajes.getChildAdapterPosition(view);
                    viewPersonajes.setBackgroundColor(getColor(android.R.color.transparent));
                    if (idPersonaje!=idPersonajeInt){
                        IntercambiarCartas();
                    }

                }else{
                    idPersonaje=viewPersonajes.getChildAdapterPosition(view);
                    PersonajeSelec();
                }

            }
        });
        carta1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                c1=0;
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
                        c2=0;
                        MovimientoCarta();
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
                c1=1;
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
                        c2=1;
                        MovimientoCarta();
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
                c1=2;
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
                        c2=2;
                        MovimientoCarta();
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
                c1=3;
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
                        c2=3;
                        MovimientoCarta();
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
                c1=4;
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
                        c2=4;
                        MovimientoCarta();
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
                if (p.puntuacion>18){
                    p.level[0]=1;
                    p.level[1]=1;
                }
                if (p.puntuacion==43){
                    p.level[2]=1;
                    p.level[3]=1;
                    p.level[4]=1;

                }
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

    private void MovimientoCarta() {
        Personaje p=listaPersonajes.get(idPersonaje);
        p.intercambiar(p,c1,c2);
        PersonajeSelec();
    }

    private void IntercambiarCartas() {
        Intent intent=new Intent(JuegoActivity.this,IntercambioActivity.class);
        Personaje p=listaPersonajes.get(idPersonaje);
        Personaje q=listaPersonajes.get(idPersonajeInt);

        intent.putExtra(IntercambioActivity.Keycartas,p);
        intent.putExtra(IntercambioActivity.Keycartas2,q);
        startActivityForResult(intent,IntercambioActivity.pasarcartas);
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

        lista.clear();
        llenar_DATOS();
        for (int i=0;i<p.puntuacion;i++){
            lista.set(i, new BARRA(lista_red.get(i)));
            lista.set(i+1, new BARRA(R.drawable.puntero, lista_red.get(i+1)));
        }
        adaptarBarra.notifyDataSetChanged();
        if (p.vuelta==1){
            if (p.puntuacion<7){
                habAmarilla.setBackgroundColor(getColor(android.R.color.white));

            }else{
                habAmarilla.setBackgroundColor(getColor(R.color.yellow));
            }
        }else{
            habAmarilla.setBackgroundColor(getColor(R.color.yellow));
        }


        if (p.level[0]==0) {
            habNaranja1.setBackgroundColor(getColor(android.R.color.white));
        } else if (p.level[0] == 1) {
            habNaranja1.setBackgroundColor(getColor(android.R.color.holo_green_dark));
        } else if (p.level[0] == 2) {
            habNaranja1.setBackgroundColor(getColor(android.R.color.holo_orange_dark));
        }

        if (p.level[1]==0) {
            habNaranja2.setBackgroundColor(getColor(android.R.color.white));
        } else if (p.level[1] == 1) {
            habNaranja2.setBackgroundColor(getColor(android.R.color.holo_green_dark));
        } else if (p.level[1] == 2) {
            habNaranja2.setBackgroundColor(getColor(android.R.color.holo_orange_dark));
        }

        if (p.level[2]==0) {
            habRoja1.setBackgroundColor(getColor(android.R.color.white));
        } else if (p.level[2] == 1) {
            habRoja1.setBackgroundColor(getColor(android.R.color.holo_green_dark));
        } else if (p.level[2] == 2) {
            habRoja1.setBackgroundColor(getColor(android.R.color.holo_red_dark));
        }

        if (p.level[3]==0) {
            habRoja2.setBackgroundColor(getColor(android.R.color.white));
        } else if (p.level[3] == 1) {
            habRoja2.setBackgroundColor(getColor(android.R.color.holo_green_dark));
        } else if (p.level[3] == 2) {
            habRoja2.setBackgroundColor(getColor(android.R.color.holo_red_dark));
        }

        if (p.level[4]==0) {
            habRoja3.setBackgroundColor(getColor(android.R.color.white));
        } else if (p.level[4] == 1) {
            habRoja3.setBackgroundColor(getColor(android.R.color.holo_green_dark));
        } else if (p.level[4] == 2) {
            habRoja3.setBackgroundColor(getColor(android.R.color.holo_red_dark));
        }
    }

    private void llenar_DATOS(){

        lista_Draw.add(R.drawable.level_43);
        lista_Draw.add(R.drawable.level_42);
        lista_Draw.add(R.drawable.level_41);
        lista_Draw.add(R.drawable.level_40);
        lista_Draw.add(R.drawable.level_39);
        lista_Draw.add(R.drawable.level_38);
        lista_Draw.add(R.drawable.level_37);
        lista_Draw.add(R.drawable.level_36);
        lista_Draw.add(R.drawable.level_35);
        lista_Draw.add(R.drawable.level_34);
        lista_Draw.add(R.drawable.level_33);
        lista_Draw.add(R.drawable.level_32);
        lista_Draw.add(R.drawable.level_31);
        lista_Draw.add(R.drawable.level_30);
        lista_Draw.add(R.drawable.level_29);
        lista_Draw.add(R.drawable.level_28);
        lista_Draw.add(R.drawable.level_27);
        lista_Draw.add(R.drawable.level_26);
        lista_Draw.add(R.drawable.level_25);
        lista_Draw.add(R.drawable.level_24);
        lista_Draw.add(R.drawable.level_23);
        lista_Draw.add(R.drawable.level_22);
        lista_Draw.add(R.drawable.level_21);
        lista_Draw.add(R.drawable.level_20);
        lista_Draw.add(R.drawable.level_19);
        lista_Draw.add(R.drawable.level_18);
        lista_Draw.add(R.drawable.level_17);
        lista_Draw.add(R.drawable.level_16);
        lista_Draw.add(R.drawable.level_15);
        lista_Draw.add(R.drawable.level_14);
        lista_Draw.add(R.drawable.level_13);
        lista_Draw.add(R.drawable.level_12);
        lista_Draw.add(R.drawable.level_11);
        lista_Draw.add(R.drawable.level_10);
        lista_Draw.add(R.drawable.level_9);
        lista_Draw.add(R.drawable.level_8);
        lista_Draw.add(R.drawable.level_7);
        lista_Draw.add(R.drawable.level_6);
        lista_Draw.add(R.drawable.level_5);
        lista_Draw.add(R.drawable.level_4);
        lista_Draw.add(R.drawable.level_3);
        lista_Draw.add(R.drawable.level_2);
        lista_Draw.add(R.drawable.level_1);
        lista_Draw.add(R.drawable.level_0);

        lista_red.add(R.drawable.red_0);
        lista_red.add(R.drawable.red_1);
        lista_red.add(R.drawable.red_2);
        lista_red.add(R.drawable.red_3);
        lista_red.add(R.drawable.red_4);
        lista_red.add(R.drawable.red_5);
        lista_red.add(R.drawable.red_6);
        lista_red.add(R.drawable.red_7);
        lista_red.add(R.drawable.red_8);
        lista_red.add(R.drawable.red_9);
        lista_red.add(R.drawable.red_10);
        lista_red.add(R.drawable.red_11);
        lista_red.add(R.drawable.red_12);
        lista_red.add(R.drawable.red_13);
        lista_red.add(R.drawable.red_14);
        lista_red.add(R.drawable.red_15);
        lista_red.add(R.drawable.red_16);
        lista_red.add(R.drawable.red_17);
        lista_red.add(R.drawable.red_18);
        lista_red.add(R.drawable.red_19);
        lista_red.add(R.drawable.red_20);
        lista_red.add(R.drawable.red_21);
        lista_red.add(R.drawable.red_22);
        lista_red.add(R.drawable.red_23);
        lista_red.add(R.drawable.red_24);
        lista_red.add(R.drawable.red_25);
        lista_red.add(R.drawable.red_26);
        lista_red.add(R.drawable.red_27);
        lista_red.add(R.drawable.red_28);
        lista_red.add(R.drawable.red_29);
        lista_red.add(R.drawable.red_30);
        lista_red.add(R.drawable.red_31);
        lista_red.add(R.drawable.red_32);
        lista_red.add(R.drawable.red_33);
        lista_red.add(R.drawable.red_34);
        lista_red.add(R.drawable.red_35);
        lista_red.add(R.drawable.red_36);
        lista_red.add(R.drawable.red_37);
        lista_red.add(R.drawable.red_38);
        lista_red.add(R.drawable.red_39);
        lista_red.add(R.drawable.red_40);
        lista_red.add(R.drawable.red_41);
        lista_red.add(R.drawable.red_42);
        lista_red.add(R.drawable.red_43);
        lista.add(new BARRA(R.drawable.puntero,R.drawable.red_0));
        lista.add(new BARRA(R.drawable.level_1));
        lista.add(new BARRA(R.drawable.level_2));
        lista.add(new BARRA(R.drawable.level_3));
        lista.add(new BARRA(R.drawable.level_4));
        lista.add(new BARRA(R.drawable.level_5));
        lista.add(new BARRA(R.drawable.level_6));
        lista.add(new BARRA(R.drawable.level_7));
        lista.add(new BARRA(R.drawable.level_8));
        lista.add(new BARRA(R.drawable.level_9));
        lista.add(new BARRA(R.drawable.level_10));
        lista.add(new BARRA(R.drawable.level_11));
        lista.add(new BARRA(R.drawable.level_12));
        lista.add(new BARRA(R.drawable.level_13));
        lista.add(new BARRA(R.drawable.level_14));
        lista.add(new BARRA(R.drawable.level_15));
        lista.add(new BARRA(R.drawable.level_16));
        lista.add(new BARRA(R.drawable.level_17));
        lista.add(new BARRA(R.drawable.level_18));
        lista.add(new BARRA(R.drawable.level_19));
        lista.add(new BARRA(R.drawable.level_20));
        lista.add(new BARRA(R.drawable.level_21));
        lista.add(new BARRA(R.drawable.level_22));
        lista.add(new BARRA(R.drawable.level_23));
        lista.add(new BARRA(R.drawable.level_24));
        lista.add(new BARRA(R.drawable.level_25));
        lista.add(new BARRA(R.drawable.level_26));
        lista.add(new BARRA(R.drawable.level_27));
        lista.add(new BARRA(R.drawable.level_28));
        lista.add(new BARRA(R.drawable.level_29));
        lista.add(new BARRA(R.drawable.level_30));
        lista.add(new BARRA(R.drawable.level_31));
        lista.add(new BARRA(R.drawable.level_32));
        lista.add(new BARRA(R.drawable.level_33));
        lista.add(new BARRA(R.drawable.level_34));
        lista.add(new BARRA(R.drawable.level_35));
        lista.add(new BARRA(R.drawable.level_36));
        lista.add(new BARRA(R.drawable.level_37));
        lista.add(new BARRA(R.drawable.level_38));
        lista.add(new BARRA(R.drawable.level_39));
        lista.add(new BARRA(R.drawable.level_40));
        lista.add(new BARRA(R.drawable.level_41));
        lista.add(new BARRA(R.drawable.level_42));
        lista.add(new BARRA(R.drawable.level_43));



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
        if (requestCode==IntercambioActivity.pasarcartas){
            if (resultCode==RESULT_OK){
                Personaje p1=listaPersonajes.get(idPersonaje);
                Personaje pcard1= (Personaje) data.getExtras().getSerializable(IntercambioActivity.Keycartas);
                p1.setCarta1(pcard1.getCarta1());
                p1.setCarta2(pcard1.getCarta2());
                p1.setCarta3(pcard1.getCarta3());
                p1.setCarta4(pcard1.getCarta4());
                p1.setCarta5(pcard1.getCarta5());
                Personaje p2=listaPersonajes.get(idPersonajeInt);
                Personaje pcard2= (Personaje) data.getExtras().getSerializable(IntercambioActivity.Keycartas2);
                p2.setCarta1(pcard2.getCarta1());
                p2.setCarta2(pcard2.getCarta2());
                p2.setCarta3(pcard2.getCarta3());
                p2.setCarta4(pcard2.getCarta4());
                p2.setCarta5(pcard2.getCarta5());
                PersonajeSelec();

            }
        }

    }

   public void Intercambiar(View view) {
        viewPersonajes.setBackgroundColor(getColor(android.R.color.holo_green_dark));
        intercambiar=true;


    }


    public void Read_toFirebase() {

        //declaro los parametros
        String sala = getIntent().getExtras().getString(keysala);
        String user= getIntent().getExtras().getString(keynombre);


        // declaro la lista de usuarios donde guardare mi usuario

        //Declaro la clase donde tengo el metodo para leer y guardar los datos


        //Aplico el metodo para leer los datos

        Read_toFirebase(sala,user,user_list);


    }
    @Override

    public void onStart() {

        super.onStart();


    }





    public  void  Read_toFirebase(String sala, String user, final List<Usuario> user_list) {
        // declaro la referencia para la firebase

        DatabaseReference ref;

        ref = f.getReference(sala)
                .child(user).child("Personajes");

        listaPersonajes2 =  new ArrayList<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                // Get user information
                Usuario user = new Usuario();

                for (DataSnapshot d : dataSnapshot.getChildren()
                        ) {
                    user = d.getValue(Usuario.class);
                    user_list.add(user);
                }

                LLENAR_PJ(user_list);


                // PersonajesAdapter a = new PersonajesAdapter();


                Log.i("D", "" + user_list.get(0).getNombre_pj());
                Log.i("D", "" + user_list.get(1).getNombre_pj());
                Log.i("D", "" + user_list.get(1).getList().get("Level"));


            }


            @Override

            public void onCancelled(DatabaseError databaseError) {


            }

        });


    }

    private void LLENAR_PJ(List<Usuario> user_list) {
        listaPersonajes2.clear();
        for (int i=0;i<user_list.size();i++) {
            String nombre = user_list.get(i).getNombre_pj();
            int foto = Integer.parseInt(user_list.get(i).getList().get("foto"));
            int cara = Integer.parseInt(user_list.get(i).getList().get("cara"));
            int fotoZ = Integer.parseInt(user_list.get(i).getList().get("fotoZ"));
            int caraZ = Integer.parseInt(user_list.get(i).getList().get("caraZ"));
            String habAzul = user_list.get(i).getList().get("habAzul");
            String habAmarilla = user_list.get(i).getList().get("habAmarilla");
            String habNaranja1 = user_list.get(i).getList().get("habNaranja1");
            String habNaranja2 = user_list.get(i).getList().get("habNaranja2");
            String habRoja1 = user_list.get(i).getList().get("habRoja1");
            String HabRoja2 = user_list.get(i).getList().get("HabRoja2");
            String habRoja3 = user_list.get(i).getList().get("habRoja3");
            String habAzulZ = user_list.get(i).getList().get("habAzulZ");

            String habAmarillaZ = user_list.get(i).getList().get("habAmarillaZ");
            String habNaranja1Z = user_list.get(i).getList().get("habNaranja1Z");
            String habNaranja2Z = user_list.get(i).getList().get("habNaranja2Z");
            String habRoja3Z = user_list.get(i).getList().get("habRoja3");

            int puntuacion = Integer.parseInt(user_list.get(i).getList().get("puntuacion"));
            int vuelta = Integer.parseInt(user_list.get(i).getList().get("vuelta"));

            List<Carta> list_carta = new ArrayList<>();
            int[] list_level = new int[5];

            list_carta.add(new Carta(Integer.parseInt(user_list.get(i).getList().get("Carta1_c")), user_list.get(i).getList().get("Carta1_n")));
            list_carta.add(new Carta(Integer.parseInt(user_list.get(i).getList().get("Carta2_c")), user_list.get(i).getList().get("Carta2_n")));
            list_carta.add(new Carta(Integer.parseInt(user_list.get(i).getList().get("Carta3_c")), user_list.get(i).getList().get("Carta3_n")));
            list_carta.add(new Carta(Integer.parseInt(user_list.get(i).getList().get("Carta4_c")), user_list.get(i).getList().get("Carta4_n")));
            list_carta.add(new Carta(Integer.parseInt(user_list.get(i).getList().get("Carta5_c")), user_list.get(i).getList().get("Carta5_n")));

            String invisible = user_list.get(i).getList().get("invisible");
            String modozombie = user_list.get(i).getList().get("modozombie");
            boolean invisible_B;
            boolean modozombie_B;

            if (invisible == "true") invisible_B = true;
            else invisible_B = false;
            if (modozombie == "true") modozombie_B = true;
            else modozombie_B = false;

            list_level[0] = Integer.parseInt(user_list.get(i).getList().get("Level"));
            list_level[1] = Integer.parseInt(user_list.get(i).getList().get("Level1"));
            list_level[2] = Integer.parseInt(user_list.get(i).getList().get("Level2"));
            list_level[3] = Integer.parseInt(user_list.get(i).getList().get("Level3"));
            list_level[4] = Integer.parseInt(user_list.get(i).getList().get("Level4"));

            Personaje a2 = new Personaje(nombre, habAzul, habAmarilla, habNaranja1, habNaranja2, habRoja1, HabRoja2, habRoja3,
                    foto, cara, habAzulZ, habAmarillaZ, habNaranja1Z, habNaranja2Z, habNaranja1Z, habNaranja2Z,
                    habRoja3Z, fotoZ, caraZ, list_carta.get(0),
                    list_carta.get(1), list_carta.get(2), list_carta.get(3), list_carta.get(4),
                    invisible_B, modozombie_B, list_level, puntuacion, vuelta);



            listaPersonajes2.add(i, a2);

        }
        adapterPersonajes =new PersonajesAdapter(listaPersonajes2);
        adapterPersonajes.notifyDataSetChanged();
        viewPersonajes.setAdapter(adapterPersonajes);
    }


}
