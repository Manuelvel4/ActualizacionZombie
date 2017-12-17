package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class JuegoActivity extends AppCompatActivity {


    public static String KeyListaPersonajes="key_listaPersonajes";
    public static String KeyCartasSeleccionadas="key_cartasSeleccionadas";
    private int[] union;
    private TextView habAzul,habAmarilla, habNaranja1, habNaranja2, habRoja1, habRoja2,habRoja3,nombre;
    private ImageView foto;
    private ArrayList<Personaje> listaPersonajes;
    private ArrayList<Personaje> listaPersonajeszombie;
    private ArrayList<Personaje> listaPersonajesSelec;
    private ArrayList<Cartas> listacartas;
    private RecyclerView viewPersonajes;
    private LinearLayoutManager linlayoutmanager;
    private PersonajesAdapter adapterPersonajes;
    private int idPersonaje;
    private ImageView carta1,carta2,carta3,carta4,carta5;
    private boolean[] drop;
    private Switch modozombie;

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

        union=getIntent().getIntArrayExtra(KeyListaPersonajes);
        listaPersonajes=new ArrayList<>();
        listaPersonajeszombie=new ArrayList<>();
        listaPersonajesSelec=new ArrayList<>();
        listacartas=new ArrayList<>();

        OtrosCartas();
        EspecialesCartas();
        CuerpoCartas();
        DistanciaCartas();
        CrearPersonajes();
        CrearPersonajesZombies();
        ListaPersonajesSelec();

        viewPersonajes =(RecyclerView)findViewById(R.id.ViewPersonajes);
        linlayoutmanager =new LinearLayoutManager(this);
        viewPersonajes.setLayoutManager(linlayoutmanager);
        adapterPersonajes =new PersonajesAdapter(this,listaPersonajesSelec);
        viewPersonajes.setAdapter(adapterPersonajes);
        idPersonaje=0;

        drop=new boolean[5];

        inicio();
        PersonajeSelec();


        adapterPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idPersonaje=viewPersonajes.getChildAdapterPosition(view);
                PersonajeSelec();
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
                        Personaje p=listaPersonajesSelec.get(idPersonaje);
                        String[] c=p.getCartas();
                        if (drop[1]){
                            p.carta1=carta2.getDrawable();
                            p.carta2=carta1.getDrawable();
                            p.cartas[0]=c[1];
                            p.cartas[1]=c[0];
                        }
                        if (drop[2]){
                            p.carta1=carta3.getDrawable();
                            p.carta3=carta1.getDrawable();
                            p.cartas[0]=c[2];
                            p.cartas[2]=c[0];
                        }
                        if (drop[3]){
                            p.carta1=carta4.getDrawable();
                            p.carta4=carta1.getDrawable();
                            p.cartas[0]=c[3];
                            p.cartas[3]=c[0];
                        }
                        if (drop[4]){
                            p.carta1=carta5.getDrawable();
                            p.carta5=carta1.getDrawable();
                            p.cartas[0]=c[4];
                            p.cartas[4]=c[0];
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
                        Personaje p=listaPersonajesSelec.get(idPersonaje);
                        String[] c=p.getCartas();
                        if (drop[0]){
                            p.carta2=carta1.getDrawable();
                            p.carta1=carta2.getDrawable();
                            p.cartas[1]=c[0];
                            p.cartas[0]=c[1];
                        }
                        if (drop[2]){
                            p.carta2=carta3.getDrawable();
                            p.carta3=carta2.getDrawable();
                            p.cartas[1]=c[2];
                            p.cartas[2]=c[1];
                        }
                        if (drop[3]){
                            p.carta2=carta4.getDrawable();
                            p.carta4=carta2.getDrawable();
                            p.cartas[1]=c[3];
                            p.cartas[3]=c[1];
                        }
                        if (drop[4]){
                            p.carta2=carta5.getDrawable();
                            p.carta5=carta2.getDrawable();
                            p.cartas[1]=c[4];
                            p.cartas[4]=c[1];
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
                        Personaje p=listaPersonajesSelec.get(idPersonaje);
                        String[] c=p.getCartas();
                        if (drop[0]){
                            p.carta3=carta1.getDrawable();
                            p.carta1=carta3.getDrawable();
                            p.cartas[2]=c[0];
                            p.cartas[0]=c[2];
                        }
                        if (drop[1]){
                            p.carta3=carta2.getDrawable();
                            p.carta2=carta3.getDrawable();
                            p.cartas[2]=c[1];
                            p.cartas[1]=c[2];
                        }
                        if (drop[3]){
                            p.carta3=carta4.getDrawable();
                            p.carta4=carta3.getDrawable();
                            p.cartas[2]=c[3];
                            p.cartas[3]=c[2];
                        }
                        if (drop[4]){
                            p.carta3=carta5.getDrawable();
                            p.carta5=carta3.getDrawable();
                            p.cartas[2]=c[4];
                            p.cartas[4]=c[2];
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
                        Personaje p=listaPersonajesSelec.get(idPersonaje);
                        String[] c=p.getCartas();
                        if (drop[0]){
                            p.carta4=carta1.getDrawable();
                            p.carta1=carta4.getDrawable();
                            p.cartas[3]=c[0];
                            p.cartas[0]=c[3];
                        }
                        if (drop[1]){
                            p.carta4=carta2.getDrawable();
                            p.carta2=carta4.getDrawable();
                            p.cartas[3]=c[1];
                            p.cartas[1]=c[3];
                        }
                        if (drop[2]){
                            p.carta4=carta3.getDrawable();
                            p.carta3=carta4.getDrawable();
                            p.cartas[3]=c[2];
                            p.cartas[2]=c[3];
                        }
                        if (drop[4]){
                            p.carta4=carta5.getDrawable();
                            p.carta5=carta4.getDrawable();
                            p.cartas[3]=c[4];
                            p.cartas[4]=c[3];
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
                        Personaje p=listaPersonajesSelec.get(idPersonaje);
                        String[] c=p.getCartas();
                        if (drop[0]){
                            p.carta5=carta1.getDrawable();
                            p.carta1=carta5.getDrawable();
                            p.cartas[4]=c[0];
                            p.cartas[0]=c[4];
                        }
                        if (drop[1]){
                            p.carta5=carta2.getDrawable();
                            p.carta2=carta5.getDrawable();
                            p.cartas[4]=c[1];
                            p.cartas[1]=c[4];
                        }
                        if (drop[2]){
                            p.carta5=carta3.getDrawable();
                            p.carta3=carta5.getDrawable();
                            p.cartas[4]=c[2];
                            p.cartas[2]=c[4];
                        }
                        if (drop[3]){
                            p.carta5=carta4.getDrawable();
                            p.carta4=carta5.getDrawable();
                            p.cartas[4]=c[3];
                            p.cartas[3]=c[4];
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
                Personaje p=listaPersonajesSelec.get(idPersonaje);
                for (int i=0;i<listaPersonajes.size();i++){
                    Personaje q=listaPersonajes.get(i);
                    if (p.getNombre().equals(q.getNombre())){
                        if (modozombie.isChecked()){
                            listaPersonajesSelec.add(listaPersonajeszombie.get(i));
                            Personaje r=listaPersonajesSelec.get(listaPersonajesSelec.size()-1);
                            r.setModozombie(true);
                            CambioModo();
                        }else{
                            listaPersonajesSelec.add(listaPersonajes.get(i));
                            Personaje r=listaPersonajesSelec.get(listaPersonajesSelec.size()-1);
                            r.setModozombie(false);
                            CambioModo();
                        }
                    }
                }
                listaPersonajesSelec.remove(idPersonaje);
                idPersonaje=listaPersonajesSelec.size()-1;
                adapterPersonajes.notifyDataSetChanged();
                PersonajeSelec();

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

    private void SeleccionarCarta() {
        Intent intent=new Intent(JuegoActivity.this,CardsActivity.class);
        Personaje p=listaPersonajesSelec.get(idPersonaje);
        String[] pasocartas=p.getCartas();
        intent.putExtra(CardsActivity.Keycartas,pasocartas);
        startActivityForResult(intent,CardsActivity.pasarcartas);
    }

    private void CambioModo() {
        Personaje p=listaPersonajesSelec.get(idPersonaje);
        Personaje r=listaPersonajesSelec.get(listaPersonajesSelec.size()-1);
        r.carta1=p.carta1;
        r.carta2=p.carta2;
        r.carta3=p.carta3;
        r.carta4=p.carta4;
        r.carta5=p.carta5;
        r.level=p.level;
    }
    private void ResetDrop() {
        for (int i=0;i<drop.length;i++){
            drop[i]=false;
        }
    }
    private void inicio() {
        for (int i=0; i<listaPersonajesSelec.size();i++){
            Personaje p=listaPersonajesSelec.get(i);
            p.carta1=getDrawable(R.drawable.cartamano);
            p.carta2=getDrawable(R.drawable.cartamano);
            p.carta3=getDrawable(R.drawable.cartamano);
            p.carta4=getDrawable(R.drawable.cartamano);
            p.carta5=getDrawable(R.drawable.cartamano);
            String[] inicial=new String[5];
            for (int t=0;t<5;t++){
                inicial[t]="cartamano";
            }

            if (p.getNombre()=="watts"){
                p.carta1=getDrawable(R.drawable.cbaseballbat);
                inicial[0]="cbaseballbat";
            }
            p.setCartas(inicial);

        }
    }
    private void ListaPersonajesSelec() {
        boolean[] level=new boolean[6];
        for (int i=0;i<level.length;i++){
            level[i]=false;
        }
        for (int i=0;i<union.length;i++){
            Personaje p=listaPersonajes.get(union[i]);
            listaPersonajesSelec.add(p);
            Personaje ps=listaPersonajesSelec.get(i);
            ps.setLevel(level);
        }
    }
    private void PersonajeSelec() {
        Personaje p = listaPersonajesSelec.get(idPersonaje);
        habAzul.setText(p.getHabAzul());
        habAmarilla.setText(p.getHabAmarilla());
        habNaranja1.setText(p.getHabNaranja1());
        habNaranja2.setText(p.getHabNaranja2());
        habRoja1.setText(p.getHabRoja1());
        habRoja2.setText(p.getHabRoja2());
        habRoja3.setText(p.getHabRoja3());
        foto.setImageDrawable(p.getCara());
        nombre.setText(p.getNombre());
        carta1.setImageDrawable(p.getCarta1());
        carta2.setImageDrawable(p.getCarta2());
        carta3.setImageDrawable(p.getCarta3());
        carta4.setImageDrawable(p.getCarta4());
        carta5.setImageDrawable(p.getCarta5());
        if (p.isModozombie()){
            modozombie.setChecked(true);
        }else{
            modozombie.setChecked(false);
        }
        if (!p.level[0]){
            habAmarilla.setBackgroundColor(getColor(android.R.color.white));
        }
        if (!p.level[1]){
            habNaranja1.setBackgroundColor(getColor(android.R.color.white));
        }
        if (!p.level[2]){
            habNaranja2.setBackgroundColor(getColor(android.R.color.white));
        }
        if (!p.level[3]){
            habRoja1.setBackgroundColor(getColor(android.R.color.white));
        }
        if (!p.level[4]){
            habRoja2.setBackgroundColor(getColor(android.R.color.white));
        }
        if (!p.level[5]){
            habRoja3.setBackgroundColor(getColor(android.R.color.white));
        }
    }
    private void CrearPersonajes() {
        String nombre="watts";
        String habazul=getString(R.string.EmpezarConBateBeisbol);
        String habamarilla=getString(R.string.mas1accion);
        String habnaranja1=getString(R.string.mas1dadoCuerpoACuerpo);
        String habnaranja2=getString(R.string.Empujon);
        String habroja1=getString(R.string.dosZonasPorAccionDeMovimiento);
        String habroja2=getString( R.string.mas1accionDeCombateGratuita);
        String habroja3=getString(R.string.mas1alasTiradasDeCombate);
        Drawable foto=getDrawable(R.drawable.pwatts);
        Drawable cara=getDrawable(R.drawable.pwattscara);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Joshua";
        habazul=getString(R.string.Socorrista);
        habamarilla=getString(R.string.mas1accion);
        habnaranja1=getString(R.string.mas1acciónDeCombateADistancia);
        habnaranja2=getString(R.string.mas1aLasTiradasCuerpoACuerpo);
        habroja1=getString(R.string.dosZonasPorAccionDeMovimiento);
        habroja2=getString( R.string.mas1accionDeCombateGratuita);
        habroja3=getString(R.string.mas1alasTiradasDeCombate);
        foto=getDrawable(R.drawable.pjoshua);
        cara=getDrawable(R.drawable.pjoshuacara);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Shannon";
        habazul=getString(R.string.DisparoABocajarro);
        habamarilla=getString(R.string.mas1accion);
        habnaranja1=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja2=getString(R.string.Afortunada);
        habroja1=getString(R.string.mas1dadoCombate);
        habroja2=getString( R.string.mas1accionDeCombateGratuita);
        habroja3=getString(R.string.Escurridiza);
        foto=getDrawable(R.drawable.pshannon);
        cara=getDrawable(R.drawable.pshannoncara);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Grindlock";
        habazul=getString(R.string.Provocacion);
        habamarilla=getString(R.string.mas1accion);
        habnaranja1=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habnaranja2=getString(R.string.Escurridizo);
        habroja1=getString(R.string.mas1AlDañoCuerpoACuerpo);
        habroja2=getString( R.string.EsoEsTodoLoQueTienes);
        habroja3=getString(R.string.seisEnElDadoMas1DadoDeCombate);
        foto=getDrawable(R.drawable.pgrindlock);
        cara=getDrawable(R.drawable.pgrindlockcara);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Belle";
        habazul=getString(R.string.mas1accionDeMovimientoGratuita);
        habamarilla=getString(R.string.mas1accion);
        habnaranja1=getString(R.string.mas1aLasTiradasADistancia);
        habnaranja2=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habroja1=getString(R.string.mas1dadoCombate);
        habroja2=getString( R.string.mas1accionDeMovimientoGratuita);
        habroja3=getString(R.string.Ambidiestra);
        foto=getDrawable(R.drawable.pbelle);
        cara=getDrawable(R.drawable.pbellecara);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Kim";
        habazul=getString(R.string.Afortunada);
        habamarilla=getString(R.string.mas1accion);
        habnaranja1=getString(R.string.seisEnElDadoMas1DadoADistancia);
        habnaranja2=getString(R.string.Amano);
        habroja1=getString(R.string.mas1accionDeCombateGratuita);
        habroja2=getString( R.string.mas1alasTiradasDeCombate);
        habroja3=getString(R.string.seisEnElDadoMas1DadoCuerpoACuerpo);
        foto=getDrawable(R.drawable.pkim);
        cara=getDrawable(R.drawable.pkimcara);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));
    }
    private void CrearPersonajesZombies() {
        String nombre="watts";
        String habazul=getString(R.string.EmpezarConBateBeisbol);
        String habamarilla=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        String habnaranja1=getString(R.string.FrenesiCuerpoACuerpo);
        String habnaranja2=getString(R.string.Empujon);
        String habroja1=getString(R.string.dosZonasPorAccionDeMovimiento);
        String habroja2=getString( R.string.mas1alasTiradasDeCombate);
        String habroja3=getString(R.string.FrenesiCombate);
        Drawable foto=getDrawable(R.drawable.pwattszombie);
        Drawable cara=getDrawable(R.drawable.pwattscarazombie);
        listaPersonajeszombie.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Joshua";
        habazul=getString(R.string.Socorrista);
        habamarilla=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habnaranja1=getString(R.string.mas1aLasTiradasCuerpoACuerpo);
        habnaranja2=getString(R.string.SuperFuerza);
        habroja1=getString(R.string.mas1aLasTiradasADistancia);
        habroja2=getString( R.string.LiderNato);
        habroja3=getString(R.string.Regeneracion);
        foto=getDrawable(R.drawable.pjoshuazombie);
        cara=getDrawable(R.drawable.pjoshuacarazombie);
        listaPersonajeszombie.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Shannon";
        habazul=getString(R.string.DisparoABocajarro);
        habamarilla=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1=getString(R.string.FrenesiAdistancia);
        habnaranja2=getString(R.string.Afortunada);
        habroja1=getString(R.string.mas1dadoCombate);
        habroja2=getString( R.string.Escurridiza);
        habroja3=getString(R.string.SegadoraCombate);
        foto=getDrawable(R.drawable.pshannonzombie);
        cara=getDrawable(R.drawable.pshannoncarazombie);
        listaPersonajeszombie.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Grindlock";
        habazul=getString(R.string.Provocacion);
        habamarilla=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habnaranja1=getString(R.string.VinculoZombi);
        habnaranja2=getString(R.string.Escurridizo);
        habroja1=getString(R.string.mas1AlDañoCuerpoACuerpo);
        habroja2=getString( R.string.SegadoraCombate);
        habroja3=getString(R.string.seisEnElDadoMas1DadoDeCombate);
        foto=getDrawable(R.drawable.pgrindlockzombie);
        cara=getDrawable(R.drawable.pgrindlockcarazombie);
        listaPersonajeszombie.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Belle";
        habazul=getString(R.string.mas1accionDeMovimientoGratuita);
        habamarilla=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1=getString(R.string.mas1dadoADistancia);
        habnaranja2=getString(R.string.VinculoZombi);
        habroja1=getString(R.string.mas1dadoCombate);
        habroja2=getString( R.string.Regeneracion);
        habroja3=getString(R.string.Ambidiestra);
        foto=getDrawable(R.drawable.pbellezombie);
        cara=getDrawable(R.drawable.pbellecarazombie);
        listaPersonajeszombie.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Kim";
        habazul=getString(R.string.Afortunada);
        habamarilla=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1=getString(R.string.SegadoraCombate);
        habnaranja2=getString(R.string.Amano);
        habroja1=getString(R.string.mas1alasTiradasDeCombate);
        habroja2=getString( R.string.seisEnElDadoMas1DadoCuerpoACuerpo);
        habroja3=getString(R.string.VinculoZombi);
        foto=getDrawable(R.drawable.pkimzombie);
        cara=getDrawable(R.drawable.pkimcarazombie);
        listaPersonajeszombie.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CardsActivity.pasarcartas){
            if (resultCode==RESULT_OK){
                Personaje p=listaPersonajesSelec.get(idPersonaje);
                String[] cartasPasadas=data.getExtras().getStringArray(KeyCartasSeleccionadas);
                for (int i=0;i<listacartas.size();i++){
                    Cartas c=listacartas.get(i);
                    p.setCartas(cartasPasadas);
                    if (c.getNombre().equals(cartasPasadas[0])){
                        p.setCarta1(c.getCarta());
                    }
                    if (c.getNombre().equals(cartasPasadas[1])){
                        p.setCarta2(c.getCarta());
                    }
                    if (c.getNombre().equals(cartasPasadas[2])){
                        p.setCarta3(c.getCarta());
                    }
                    if (c.getNombre().equals(cartasPasadas[3])){
                        p.setCarta4(c.getCarta());
                    }
                    if (c.getNombre().equals(cartasPasadas[4])){
                        p.setCarta5(c.getCarta());
                    }
                }
                PersonajeSelec();
            }
        }

    }
}
