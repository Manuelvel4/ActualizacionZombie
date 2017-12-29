package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Crear(View view) {
        Intent intent=new Intent(this,CrearActivity.class);
        startActivity(intent);


    /*    FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.child("nuevo").setValue("hola");

        Log.i("TAG",myRef.getKey());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TAG", "Value is: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

*/
        finish();
    }

    public void Cargar(View view) {
        Intent intent=new Intent(this,JuegoActivity.class);
        boolean cargar=true;
        intent.putExtra(JuegoActivity.KeyCargar,cargar);
        startActivity(intent);
        finish();
    }
}
