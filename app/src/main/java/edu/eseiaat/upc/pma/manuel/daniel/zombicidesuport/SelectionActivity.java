package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

public class SelectionActivity extends AppCompatActivity{

    public static String keysala="key_sala";
    public static String keynombre="key_nombre";

    private RecyclerView viewPersonajes;
    private List<Personaje> listaPersonajes;
    private List<String> listaUsuarios;
    private PersonajesAdapter adapterPersonajes;
    private ArrayAdapter<String> adapterUsuarios;
    private LinearLayoutManager linlayoutmanager;
    private ImageView descripcionPersonaje;
    private TextView habAzul,habAmarilla, habNaranja1, habNaranja2, habRoja1, habRoja2,habRoja3;
    private CheckBox modoZombie;
    private int idPersonaje=0;
    private boolean personajeDrop =false;
    private int idPersonajeSelec=0;
    private boolean personajeSelecDrop=false;
    private ArrayList<Personaje> listaPersonajesSelec;
    private RecyclerView viewPersonajesSelec;
    private PersonajesAdapter adapterPersonajesSelec;
    private ImageView borrar;
    private boolean[] visibilidad;
    private Personaje watts,joshua,shannon,grindlock,belle,kim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        descripcionPersonaje =(ImageView)findViewById(R.id.DescripcionPersonaje);
        habAzul=(TextView) findViewById(R.id.HabAzul);
        habAmarilla=(TextView)findViewById(R.id.HabAmarilla);
        habNaranja1=(TextView) findViewById(R.id.HabNaranja1);
        habNaranja2=(TextView) findViewById(R.id.HabNaranja2);
        habRoja1=(TextView) findViewById(R.id.HabRoja1);
        habRoja2=(TextView) findViewById(R.id.HabRoja2);
        habRoja3=(TextView) findViewById(R.id.HabRoja3);
        modoZombie=(CheckBox)findViewById(R.id.ModoZombie);
        borrar=(ImageView)findViewById(R.id.Borrar);

        TextView Sala=(TextView)findViewById(R.id.Sala);
        ListView viewUsuarios=(ListView)findViewById(R.id.ViewUsuarios);
        Sala.setText(getIntent().getExtras().getString(keysala));
        listaUsuarios=new ArrayList<>();
        listaUsuarios.add(getIntent().getExtras().getString(keynombre));
        adapterUsuarios=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaUsuarios);
        viewUsuarios.setAdapter(adapterUsuarios);

        CrearPersonajes();
        listaPersonajes=new ArrayList<>();
        CrearLista();
        PersonajeSeleccionado();
        viewPersonajes =(RecyclerView)findViewById(R.id.ListaPersonajes);
        linlayoutmanager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        viewPersonajes.setLayoutManager(linlayoutmanager);


        adapterPersonajes =new PersonajesAdapter(this,listaPersonajes);
        viewPersonajes.setAdapter(adapterPersonajes);

        listaPersonajesSelec =new ArrayList<>();
        viewPersonajesSelec =(RecyclerView)findViewById(R.id.ListaSeleccionados);
        linlayoutmanager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        viewPersonajesSelec.setLayoutManager(linlayoutmanager);
        adapterPersonajesSelec =new PersonajesAdapter(this,listaPersonajesSelec);
        viewPersonajesSelec.setAdapter(adapterPersonajesSelec);

        borrar.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        v.setBackgroundColor(getColor(android.R.color.holo_red_light));
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundColor(getColor(android.R.color.holo_red_dark));
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundColor(getColor(android.R.color.holo_red_light));
                        break;
                    case DragEvent.ACTION_DROP:

                        if (personajeSelecDrop){
                            Personaje p=listaPersonajesSelec.get(idPersonajeSelec);
                            p.setInvisible(false);
                            listaPersonajesSelec.remove(idPersonajeSelec);
                        }
                        if (personajeDrop){
                            Personaje p=listaPersonajes.get(idPersonaje);
                            p.setInvisible(false);
                        }

                        adapterPersonajes.notifyDataSetChanged();
                        adapterPersonajesSelec.notifyDataSetChanged();
                        personajeDrop=false;
                        personajeSelecDrop=false;
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        v.setBackgroundColor(getColor(android.R.color.transparent));
                    default:
                        break;
                }
                return true;
            }
        });
        viewPersonajesSelec.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        v.setBackgroundColor(getColor(android.R.color.holo_green_light));
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundColor(getColor(android.R.color.holo_green_dark));
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundColor(getColor(android.R.color.holo_green_light));
                        break;
                    case DragEvent.ACTION_DROP:
                        if (personajeDrop==true){
                            Personaje p=listaPersonajes.get(idPersonaje);
                            p.setInvisible(true);
                            adapterPersonajes.notifyDataSetChanged();
                            listaPersonajesSelec.add(p);
                            adapterPersonajesSelec.notifyDataSetChanged();
                            viewPersonajesSelec.smoothScrollToPosition(listaPersonajesSelec.size()-1);

                        }
                        personajeDrop=false;
                        personajeSelecDrop=false;
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        v.setBackgroundColor(getColor(android.R.color.transparent));
                    default:
                        break;
                };
                return true;
            }
        });
        modoZombie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<listaPersonajes.size();i++){
                    Personaje p=listaPersonajes.get(i);
                    p.modozombie=!p.modozombie;
                }
                PersonajeSeleccionado();
                adapterPersonajes.notifyDataSetChanged();
                adapterPersonajesSelec.notifyDataSetChanged();
             }
        });
        adapterPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idPersonaje=viewPersonajes.getChildAdapterPosition(view);
                PersonajeSeleccionado();
            }
        });
        adapterPersonajes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                personajeDrop=true;
                idPersonaje=viewPersonajes.getChildAdapterPosition(view);
                Personaje p=listaPersonajes.get(idPersonaje);
                PersonajeSeleccionado();
                if (!p.isInvisible()){
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(data, shadowBuilder, view, 0);
                    //view.startDragAndDrop(data,shadowBuilder,view,0);
                    return true;
                }else{
                    Toast.makeText(SelectionActivity.this, R.string.PersonajeYaSeleccionado, Toast.LENGTH_SHORT).show();
                }
                return false;
           }
        });
        adapterPersonajesSelec.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                idPersonajeSelec=viewPersonajesSelec.getChildAdapterPosition(view);
                personajeSelecDrop=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;
            }
        });
    }

    private void CrearLista() {
        listaPersonajes.add(watts);
        listaPersonajes.add(joshua);
        listaPersonajes.add(shannon);
        listaPersonajes.add(grindlock);
        listaPersonajes.add(belle);
        listaPersonajes.add(kim);
    }
    private void PersonajeSeleccionado() {
        Personaje p = listaPersonajes.get(idPersonaje);
        if (modoZombie.isChecked()) {
            habAzul.setText(p.getHabAzulZ());
            habAmarilla.setText(p.getHabAmarillaZ());
            habNaranja1.setText(p.getHabNaranja1Z());
            habNaranja2.setText(p.getHabNaranja2Z());
            habRoja1.setText(p.getHabRoja1Z());
            habRoja2.setText(p.getHabRoja2Z());
            habRoja3.setText(p.getHabRoja3Z());
            descripcionPersonaje.setImageDrawable(p.getFotoZ());
        }else{
            habAzul.setText(p.getHabAzul());
            habAmarilla.setText(p.getHabAmarilla());
            habNaranja1.setText(p.getHabNaranja1());
            habNaranja2.setText(p.getHabNaranja2());
            habRoja1.setText(p.getHabRoja1());
            habRoja2.setText(p.getHabRoja2());
            habRoja3.setText(p.getHabRoja3());
            descripcionPersonaje.setImageDrawable(p.getFoto());
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
        String habazulZ=getString(R.string.EmpezarConBateBeisbol);
        String habamarillaZ=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        String habnaranja1Z=getString(R.string.FrenesiCuerpoACuerpo);
        String habnaranja2Z=getString(R.string.Empujon);
        String habroja1Z=getString(R.string.dosZonasPorAccionDeMovimiento);
        String habroja2Z=getString( R.string.mas1alasTiradasDeCombate);
        String habroja3Z=getString(R.string.FrenesiCombate);
        Drawable fotoZ=getDrawable(R.drawable.pwattszombie);
        Drawable caraZ=getDrawable(R.drawable.pwattscarazombie);
        watts=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ);

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
        habazulZ=getString(R.string.Socorrista);
        habamarillaZ=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habnaranja1Z=getString(R.string.mas1aLasTiradasCuerpoACuerpo);
        habnaranja2Z=getString(R.string.SuperFuerza);
        habroja1Z=getString(R.string.mas1aLasTiradasADistancia);
        habroja2Z=getString( R.string.LiderNato);
        habroja3Z=getString(R.string.Regeneracion);
        fotoZ=getDrawable(R.drawable.pjoshuazombie);
        caraZ=getDrawable(R.drawable.pjoshuacarazombie);
        joshua=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ);

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
        habazulZ=getString(R.string.DisparoABocajarro);
        habamarillaZ=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1Z=getString(R.string.FrenesiAdistancia);
        habnaranja2Z=getString(R.string.Afortunada);
        habroja1Z=getString(R.string.mas1dadoCombate);
        habroja2Z=getString( R.string.Escurridiza);
        habroja3Z=getString(R.string.SegadoraCombate);
        fotoZ=getDrawable(R.drawable.pshannonzombie);
        caraZ=getDrawable(R.drawable.pshannoncarazombie);
        shannon=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ);

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
        habazulZ=getString(R.string.Provocacion);
        habamarillaZ=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habnaranja1Z=getString(R.string.VinculoZombi);
        habnaranja2Z=getString(R.string.Escurridizo);
        habroja1Z=getString(R.string.mas1AlDañoCuerpoACuerpo);
        habroja2Z=getString( R.string.SegadoraCombate);
        habroja3Z=getString(R.string.seisEnElDadoMas1DadoDeCombate);
        fotoZ=getDrawable(R.drawable.pgrindlockzombie);
        caraZ=getDrawable(R.drawable.pgrindlockcarazombie);
        grindlock=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ);

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
        habazulZ=getString(R.string.mas1accionDeMovimientoGratuita);
        habamarillaZ=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1Z=getString(R.string.mas1dadoADistancia);
        habnaranja2Z=getString(R.string.VinculoZombi);
        habroja1Z=getString(R.string.mas1dadoCombate);
        habroja2Z=getString( R.string.Regeneracion);
        habroja3Z=getString(R.string.Ambidiestra);
        fotoZ=getDrawable(R.drawable.pbellezombie);
        caraZ=getDrawable(R.drawable.pbellecarazombie);
        belle=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ);

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
        habazulZ=getString(R.string.Afortunada);
        habamarillaZ=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1Z=getString(R.string.SegadoraCombate);
        habnaranja2Z=getString(R.string.Amano);
        habroja1Z=getString(R.string.mas1alasTiradasDeCombate);
        habroja2Z=getString( R.string.seisEnElDadoMas1DadoCuerpoACuerpo);
        habroja3Z=getString(R.string.VinculoZombi);
        fotoZ=getDrawable(R.drawable.pkimzombie);
        caraZ=getDrawable(R.drawable.pkimcarazombie);
        kim=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ);
    }
    public void Atras(View view) {
        Intent intent=new Intent(this,CrearActivity.class);
        startActivity(intent);
        finish();
    }
    public void Aceptar(View view) {
        Intent intent=new Intent(this,JuegoActivity.class);
        Personaje p=listaPersonajesSelec.get(idPersonaje);
        intent.putExtra(JuegoActivity.KeyListaPersonajes, p);
        startActivity(intent);
        finish();
    }
}

