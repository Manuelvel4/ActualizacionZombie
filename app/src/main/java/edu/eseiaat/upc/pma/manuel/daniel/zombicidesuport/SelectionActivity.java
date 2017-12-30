package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Objetos.FireBaseMethod;
import edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Objetos.Usuario;

public class SelectionActivity extends AppCompatActivity{

    public static String keysala="key_sala";
    public static String keynombre="key_nombre";

    private Usuario user;

    private Map<String,String> list;

    FireBaseMethod firebase;

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
    private ArrayList<Carta> CartasDistancia,CartasCuerpo,CartasEspeciales,CartasOtras;
    private RecyclerView viewPersonajesSelec;
    private PersonajesAdapter adapterPersonajesSelec;
    private ImageView borrar;
    private Personaje watts,joshua,shannon,grindlock,belle,kim;
    private Carta mashotgun,eviltwins,pistol,rifle,sawedoff,shotgun,submg,baseballbat,chainsaw,crowbar,fireaxe,katana,machete,pan,
            goaliemask,flashligth,plentyofammo,plentyofammoshotgun,scope,molotov,bagorice,cannedfood,water,gasoline,glassbottle,wound,cartamano;


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

        CrearCartas();
        CrearListaCartas();
        CrearPersonajes();
        CrearListaPersonajes();
        PersonajeSeleccionado();
        viewPersonajes =(RecyclerView)findViewById(R.id.ListaPersonajes);
        linlayoutmanager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        viewPersonajes.setLayoutManager(linlayoutmanager);


        adapterPersonajes =new PersonajesInvisiblesAdapter(this,listaPersonajes);
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
    private void CrearCartas() {
        //Distancia
        mashotgun=new Carta((R.drawable.cmashotgun),"cmashotgun");
        eviltwins=new Carta((R.drawable.ceviltwins),"ceviltwins");
        pistol=new Carta((R.drawable.cpistol),"cpistol");
        rifle=new Carta((R.drawable.crifle),"crifle");
        sawedoff=new Carta((R.drawable.csawedoff),"csawedoff");
        shotgun=new Carta((R.drawable.cshotgun),"cshotgun");
        submg=new Carta((R.drawable.csubmg),"csubmg");

        //cuerpo a cuerpo
        baseballbat=new Carta((R.drawable.cbaseballbat),"cbaseballbat");
        chainsaw=new Carta((R.drawable.cchainsaw),"cchainsaw");
        crowbar=new Carta((R.drawable.ccrowbar),"ccrowbar");
        fireaxe=new Carta((R.drawable.cfireaxe),"cfireaxe");
        katana=new Carta((R.drawable.ckatana),"ckatana");
        machete=new Carta((R.drawable.cmachete),"cmachete");
        pan=new Carta((R.drawable.cpan),"cpan");

        //especiales
        goaliemask=new Carta((R.drawable.cgoaliemask),"cgoaliemask");
        flashligth=new Carta((R.drawable.cflashlight),"cflashlight");
        plentyofammo=new Carta((R.drawable.cplentyofammo),"cplentyofammo");
        plentyofammoshotgun=new Carta((R.drawable.cplentyofammoshotgun),"cplentyofammoshotgun");
        scope=new Carta((R.drawable.cscope),"cscope");
        molotov=new Carta((R.drawable.cmolotov),"cmolotov");

        //otras
        bagorice=new Carta((R.drawable.cbagofrice),"cbagofrice");
        cannedfood=new Carta((R.drawable.ccannedfood),"ccannedfood");
        water=new Carta((R.drawable.cwater),"cwater");
        gasoline=new Carta((R.drawable.cgasoline),"cgasoline");
        glassbottle=new Carta((R.drawable.cglassbottle),"cglassbottle");
        wound=new Carta((R.drawable.cwound),"cwound");
        cartamano=new Carta((R.drawable.cartamano),"cartamano");
    }
    private void CrearListaCartas() {
        CartasDistancia=new ArrayList<>();
        CartasDistancia.add(mashotgun);
        CartasDistancia.add(eviltwins);
        CartasDistancia.add(pistol);
        CartasDistancia.add(rifle);
        CartasDistancia.add(sawedoff);
        CartasDistancia.add(shotgun);
        CartasDistancia.add(submg);

        CartasCuerpo=new ArrayList<>();
        CartasCuerpo.add(mashotgun);
        CartasCuerpo.add(chainsaw);
        CartasCuerpo.add(crowbar);
        CartasCuerpo.add(fireaxe);
        CartasCuerpo.add(katana);
        CartasCuerpo.add(machete);
        CartasCuerpo.add(pan);

        CartasEspeciales=new ArrayList<>();
        CartasEspeciales.add(goaliemask);
        CartasEspeciales.add(flashligth);
        CartasEspeciales.add(plentyofammo);
        CartasEspeciales.add(plentyofammoshotgun);
        CartasEspeciales.add(scope);
        CartasEspeciales.add(molotov);

        CartasOtras=new ArrayList<>();
        CartasOtras.add(bagorice);
        CartasOtras.add(cannedfood);
        CartasOtras.add(water);
        CartasOtras.add(gasoline);
        CartasOtras.add(glassbottle);
        CartasOtras.add(wound);
        CartasOtras.add(cartamano);

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
        int foto=R.drawable.pwatts;
        int cara=R.drawable.pwattscara;
        String habazulZ=getString(R.string.EmpezarConBateBeisbol);
        String habamarillaZ=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        String habnaranja1Z=getString(R.string.FrenesiCuerpoACuerpo);
        String habnaranja2Z=getString(R.string.Empujon);
        String habroja1Z=getString(R.string.dosZonasPorAccionDeMovimiento);
        String habroja2Z=getString( R.string.mas1alasTiradasDeCombate);
        String habroja3Z=getString(R.string.FrenesiCombate);
        int fotoZ=R.drawable.pwattszombie;
        int caraZ=R.drawable.pwattscarazombie;
        Carta carta1=baseballbat;
        Carta carta2=cartamano;
        Carta carta3=cartamano;
        Carta carta4=cartamano;
        Carta carta5=cartamano;
        boolean invisible=false;
        boolean modozombie=false;
        int[] level=new int[5];
        for (int i=0;i<level.length;i++){
            level[i]=0;
        }
        int puntuacion=0;
        int vuelta=1;
        watts=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,
                habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ,carta1,carta2,carta3,carta4,carta5,
                invisible,modozombie,level,puntuacion,vuelta);

        nombre="Joshua";
        habazul=getString(R.string.Socorrista);
        habamarilla=getString(R.string.mas1accion);
        habnaranja1=getString(R.string.mas1acciónDeCombateADistancia);
        habnaranja2=getString(R.string.mas1aLasTiradasCuerpoACuerpo);
        habroja1=getString(R.string.dosZonasPorAccionDeMovimiento);
        habroja2=getString( R.string.mas1accionDeCombateGratuita);
        habroja3=getString(R.string.mas1alasTiradasDeCombate);
        foto=(R.drawable.pjoshua);
        cara=(R.drawable.pjoshuacara);
        habazulZ=getString(R.string.Socorrista);
        habamarillaZ=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habnaranja1Z=getString(R.string.mas1aLasTiradasCuerpoACuerpo);
        habnaranja2Z=getString(R.string.SuperFuerza);
        habroja1Z=getString(R.string.mas1aLasTiradasADistancia);
        habroja2Z=getString( R.string.LiderNato);
        habroja3Z=getString(R.string.Regeneracion);
        fotoZ=(R.drawable.pjoshuazombie);
        caraZ=(R.drawable.pjoshuacarazombie);
        carta1=cartamano;
        carta2=cartamano;
        carta3=cartamano;
        carta4=cartamano;
        carta5=cartamano;
        joshua=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,
                habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ,carta1,carta2,carta3,carta4,carta5,
                invisible,modozombie,level,puntuacion,vuelta);

        nombre="Shannon";
        habazul=getString(R.string.DisparoABocajarro);
        habamarilla=getString(R.string.mas1accion);
        habnaranja1=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja2=getString(R.string.Afortunada);
        habroja1=getString(R.string.mas1dadoCombate);
        habroja2=getString( R.string.mas1accionDeCombateGratuita);
        habroja3=getString(R.string.Escurridiza);
        foto=(R.drawable.pshannon);
        cara=(R.drawable.pshannoncara);
        habazulZ=getString(R.string.DisparoABocajarro);
        habamarillaZ=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1Z=getString(R.string.FrenesiAdistancia);
        habnaranja2Z=getString(R.string.Afortunada);
        habroja1Z=getString(R.string.mas1dadoCombate);
        habroja2Z=getString( R.string.Escurridiza);
        habroja3Z=getString(R.string.SegadoraCombate);
        fotoZ=(R.drawable.pshannonzombie);
        caraZ=(R.drawable.pshannoncarazombie);
        carta1=cartamano;
        carta2=cartamano;
        carta3=cartamano;
        carta4=cartamano;
        carta5=cartamano;
        shannon=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,
                habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ,carta1,carta2,carta3,carta4,carta5,
                invisible,modozombie,level,puntuacion,vuelta);

        nombre="Grindlock";
        habazul=getString(R.string.Provocacion);
        habamarilla=getString(R.string.mas1accion);
        habnaranja1=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habnaranja2=getString(R.string.Escurridizo);
        habroja1=getString(R.string.mas1AlDañoCuerpoACuerpo);
        habroja2=getString( R.string.EsoEsTodoLoQueTienes);
        habroja3=getString(R.string.seisEnElDadoMas1DadoDeCombate);
        foto=(R.drawable.pgrindlock);
        cara=(R.drawable.pgrindlockcara);
        habazulZ=getString(R.string.Provocacion);
        habamarillaZ=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habnaranja1Z=getString(R.string.VinculoZombi);
        habnaranja2Z=getString(R.string.Escurridizo);
        habroja1Z=getString(R.string.mas1AlDañoCuerpoACuerpo);
        habroja2Z=getString( R.string.SegadoraCombate);
        habroja3Z=getString(R.string.seisEnElDadoMas1DadoDeCombate);
        fotoZ=(R.drawable.pgrindlockzombie);
        caraZ=(R.drawable.pgrindlockcarazombie);
        carta1=cartamano;
        carta2=cartamano;
        carta3=cartamano;
        carta4=cartamano;
        carta5=cartamano;
        grindlock=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,
                habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ,carta1,carta2,carta3,carta4,carta5,
                invisible,modozombie,level,puntuacion,vuelta);

        nombre="Belle";
        habazul=getString(R.string.mas1accionDeMovimientoGratuita);
        habamarilla=getString(R.string.mas1accion);
        habnaranja1=getString(R.string.mas1aLasTiradasADistancia);
        habnaranja2=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habroja1=getString(R.string.mas1dadoCombate);
        habroja2=getString( R.string.mas1accionDeMovimientoGratuita);
        habroja3=getString(R.string.Ambidiestra);
        foto=(R.drawable.pbelle);
        cara=(R.drawable.pbellecara);
        habazulZ=getString(R.string.mas1accionDeMovimientoGratuita);
        habamarillaZ=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1Z=getString(R.string.mas1dadoADistancia);
        habnaranja2Z=getString(R.string.VinculoZombi);
        habroja1Z=getString(R.string.mas1dadoCombate);
        habroja2Z=getString( R.string.Regeneracion);
        habroja3Z=getString(R.string.Ambidiestra);
        fotoZ=(R.drawable.pbellezombie);
        caraZ=(R.drawable.pbellecarazombie);
        carta1=cartamano;
        carta2=cartamano;
        carta3=cartamano;
        carta4=cartamano;
        carta5=cartamano;
        belle=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,
                habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ,carta1,carta2,carta3,carta4,carta5,
                invisible,modozombie,level,puntuacion,vuelta);

        nombre="Kim";
        habazul=getString(R.string.Afortunada);
        habamarilla=getString(R.string.mas1accion);
        habnaranja1=getString(R.string.seisEnElDadoMas1DadoADistancia);
        habnaranja2=getString(R.string.Amano);
        habroja1=getString(R.string.mas1accionDeCombateGratuita);
        habroja2=getString( R.string.mas1alasTiradasDeCombate);
        habroja3=getString(R.string.seisEnElDadoMas1DadoCuerpoACuerpo);
        foto=(R.drawable.pkim);
        cara=(R.drawable.pkimcara);
        habazulZ=getString(R.string.Afortunada);
        habamarillaZ=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1Z=getString(R.string.SegadoraCombate);
        habnaranja2Z=getString(R.string.Amano);
        habroja1Z=getString(R.string.mas1alasTiradasDeCombate);
        habroja2Z=getString( R.string.seisEnElDadoMas1DadoCuerpoACuerpo);
        habroja3Z=getString(R.string.VinculoZombi);
        fotoZ=(R.drawable.pkimzombie);
        caraZ=(R.drawable.pkimcarazombie);
        carta1=cartamano;
        carta2=cartamano;
        carta3=cartamano;
        carta4=cartamano;
        carta5=cartamano;
        kim=new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara,
                habazulZ,habamarillaZ,habnaranja1Z,habnaranja2Z,habroja1Z,habroja2Z,habroja3Z,fotoZ,caraZ,carta1,carta2,carta3,carta4,carta5,
                invisible,modozombie,level,puntuacion,vuelta);
    }
    private void CrearListaPersonajes() {
        listaPersonajes=new ArrayList<>();
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
            descripcionPersonaje.setImageResource(p.getFotoZ());
        }else{
            habAzul.setText(p.getHabAzul());
            habAmarilla.setText(p.getHabAmarilla());
            habNaranja1.setText(p.getHabNaranja1());
            habNaranja2.setText(p.getHabNaranja2());
            habRoja1.setText(p.getHabRoja1());
            habRoja2.setText(p.getHabRoja2());
            habRoja3.setText(p.getHabRoja3());
            descripcionPersonaje.setImageResource(p.getFoto());
        }

    }

    public void Atras(View view) {
        Intent intent=new Intent(this,CrearActivity.class);

        startActivity(intent);
        finish();
    }
    public void Aceptar(View view) {
        for (int i=0;i<listaPersonajes.size();i++){
            Personaje p=listaPersonajes.get(i);
            p.modozombie=false;
        }

        PushFirebase();


        Intent intent=new Intent(this,JuegoActivity.class);
        intent.putExtra(JuegoActivity.KeyListaPersonajes, listaPersonajesSelec);
        intent.putExtra(JuegoActivity.KeyListaCartasDistancia,CartasDistancia );
        intent.putExtra(JuegoActivity.KeyListaCartasCuerpo, CartasCuerpo);
        intent.putExtra(JuegoActivity.KeyListaCartasEspeciales,CartasEspeciales);
        intent.putExtra(JuegoActivity.KeyListaCartasOtras, CartasOtras);

        //Guardamos la sala y el nombre para despues recuperarlo a travez de firebase
        intent.putExtra(JuegoActivity.keysala,getIntent().getExtras().getString(keysala));
        intent.putExtra(JuegoActivity.keynombre,getIntent().getExtras().getString(keynombre));

        startActivity(intent);
        finish();
    }

    private void PushFirebase() {

        String user = getIntent().getExtras().getString(keynombre);
        String sala = getIntent().getExtras().getString(keysala);

        firebase = new FireBaseMethod();

        //El metodo necesita la lista de pesonajes que ha usado el usuario, la sala en la que se a creado y el nombre del usuario

        firebase.WriteToFirebase(listaPersonajesSelec,sala,user);


    }


}

