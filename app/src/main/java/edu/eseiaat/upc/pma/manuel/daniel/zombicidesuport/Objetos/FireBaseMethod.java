package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Objetos;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport.Personaje;

/**
 * Created by manue on 30/12/2017.
 */

public class FireBaseMethod {

    private DatabaseReference ref;
    private FirebaseDatabase f;

    List<Usuario> user_list;

    public List<Usuario> getUser_list() {
        return user_list;
    }

    public FireBaseMethod(DatabaseReference ref, FirebaseDatabase f, List<Usuario> user_list) {
        this.ref = ref;
        this.f = f;
        this.user_list = user_list;
    }
    public FireBaseMethod(DatabaseReference ref, FirebaseDatabase f) {
        this.ref = ref;
        this.f = f;
    }

    public FireBaseMethod(){};

    public  void  Read_toFirebase(String sala, String user, final List<Usuario> user_list ) {

         ref = f.getReference(sala)
                .child(user).child("Personajes");


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


                String authorName;
                Map<String, String> a = new HashMap<>();

                a.put(user_list.get(0).getNombre_pj(), user_list.get(0).getList().toString());

                if (user == null) {
                    authorName = "null";
                } else authorName = user.getNombre_pj();


                Log.i("D", "" + user_list.get(0).getNombre_pj());
                Log.i("D", "" + user_list.get(1).getNombre_pj());
                Log.i("D", "" + user_list.get(4).getNombre_pj());

            }


            @Override

            public void onCancelled(DatabaseError databaseError) {


            }

        });


    }

    public   List<Usuario>   Read_toFirebase(String sala, String user ) {

        ref = f.getReference(sala)
                .child(user).child("Personajes");

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


                String authorName;
                Map<String, String> a = new HashMap<>();

                a.put(user_list.get(0).getNombre_pj(), user_list.get(0).getList().toString());

                if (user == null) {
                    authorName = "null";
                } else authorName = user.getNombre_pj();


                Log.i("D", "" + user_list.get(0).getNombre_pj());
                Log.i("D", "" + user_list.get(1).getNombre_pj());
                Log.i("D", "" + user_list.get(4).getNombre_pj());

            }


            @Override

            public void onCancelled(DatabaseError databaseError) {


            }

        });

        return user_list;
    }



    public void WriteToFirebase(ArrayList<Personaje> listaPersonajesSelec, String sala2, String user3) {

        List<Usuario> pj = new ArrayList<>();

        Map<String,String>  list_push2 = new HashMap<>();

        list_push2.putAll(LlenarMapList(listaPersonajesSelec));

        Usuario user2 = new Usuario(list_push2,listaPersonajesSelec.get(0).getNombre());

        FirebaseDatabase f = FirebaseDatabase.getInstance();


        DatabaseReference ref = f.getReference(sala2);
        ref.child(user3).child("Personajes").push().setValue(user2);

    }


    public   Map<String, String> LlenarMapList(ArrayList<Personaje> listaPersonajesSelec) {

        Map<String, String> list_push = new HashMap<>();

        for (int i = 0; i<listaPersonajesSelec.size();i++) {

            list_push.put("foto", String.valueOf(listaPersonajesSelec.get(i).getFoto()));
            list_push.put("cara", String.valueOf(listaPersonajesSelec.get(i).getCara()));
            list_push.put("fotoZ", String.valueOf(listaPersonajesSelec.get(i).getFotoZ()));
            list_push.put("caraZ", String.valueOf(listaPersonajesSelec.get(i).getCaraZ()));

            list_push.put("habAzul", listaPersonajesSelec.get(i).getHabAzul());
            list_push.put("habAmarilla",listaPersonajesSelec.get(i).getHabAmarilla());
            list_push.put("habNaranja1",listaPersonajesSelec.get(i).getHabNaranja1());
            list_push.put("habNaranja2",listaPersonajesSelec.get(i).getHabNaranja2());
            list_push.put("habRoja1",listaPersonajesSelec.get(i).getHabRoja1());
            list_push.put("HabRoja2",listaPersonajesSelec.get(i).getHabRoja2());
            list_push.put("habRoja3",listaPersonajesSelec.get(i).getHabRoja3());

            list_push.put("habAzulZ",listaPersonajesSelec.get(i).getHabAzulZ());
            list_push.put("habAmarillaZ",listaPersonajesSelec.get(i).getHabAmarillaZ());
            list_push.put("habNaranja1Z",listaPersonajesSelec.get(i).getHabNaranja1Z());
            list_push.put("habNaranja2Z",listaPersonajesSelec.get(i).getHabNaranja2Z());
            list_push.put("habRoja3Z",listaPersonajesSelec.get(i).getHabRoja3Z());



            list_push.put("puntuacion", String.valueOf(listaPersonajesSelec.get(i).getPuntuacion()));

            list_push.put("vuelta", String.valueOf(listaPersonajesSelec.get(i).getVuelta()));




            list_push.put("Carta1_c",String.valueOf(listaPersonajesSelec.get(i).getCarta1().getCarta()));
            list_push.put("Carta1_n",listaPersonajesSelec.get(i).getCarta1().getNombre());

            list_push.put("Carta2_c",String.valueOf(listaPersonajesSelec.get(i).getCarta2().getCarta()));
            list_push.put("Carta2_n",listaPersonajesSelec.get(i).getCarta2().getNombre());

            list_push.put("Carta3_c",String.valueOf(listaPersonajesSelec.get(i).getCarta3().getCarta()));
            list_push.put("Carta3_n",listaPersonajesSelec.get(i).getCarta3().getNombre());

            list_push.put("Carta4_c",String.valueOf(listaPersonajesSelec.get(i).getCarta4().getCarta()));
            list_push.put("Carta4_n",listaPersonajesSelec.get(i).getCarta4().getNombre());

            list_push.put("Carta5_c",String.valueOf(listaPersonajesSelec.get(i).getCarta5().getCarta()));
            list_push.put("Carta5_n",listaPersonajesSelec.get(i).getCarta5().getNombre());



            list_push.put("invisible", String.valueOf(String.valueOf(listaPersonajesSelec.get(i).isInvisible())));
            list_push.put("modozombie", String.valueOf(listaPersonajesSelec.get(i).isModozombie()));
        }



        for (int i = 0; i<listaPersonajesSelec.size();i++) {

            int [] l = listaPersonajesSelec.get(i).getLevel();

            for (int j = 0; j<l.length;j++) {

                list_push.put("Level",String.valueOf(l[j]));


            }

            for (int j = 0; j<l.length;j++) {

                list_push.put(String.valueOf("Level"+j),String.valueOf(l[j]));


            }

        }



        return list_push;
    }





}




