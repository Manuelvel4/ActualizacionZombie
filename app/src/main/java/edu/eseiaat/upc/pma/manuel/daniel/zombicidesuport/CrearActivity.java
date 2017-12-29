package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrearActivity extends AppCompatActivity {

    private EditText Sala;
    private EditText Nombre;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ref;

    {
        ref = database.getReference("Tuto");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        Sala=(EditText)findViewById(R.id.Sala);
        Nombre=(EditText)findViewById(R.id.Nombre);
    }

    public void Aceptar(View view) {
        Intent intent=new Intent(this,SelectionActivity.class);
        String textSala=Sala.getText().toString();
        String textNombre=Nombre.getText().toString();



        String a = "s";
        ref.push().setValue( a);




        intent.putExtra(SelectionActivity.keysala,textSala);
        intent.putExtra(SelectionActivity.keynombre,textNombre);
        startActivity(intent);
        finish();
    }

    public void Cancelar(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
