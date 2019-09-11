package com.example.viniciuscanastacio.firebaseprimeirospassos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //Obejetos de manipulaão do banco de dados
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText editTextNome;
    private EditText editTextIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextIdade = findViewById(R.id.editTextIdade);

        conectarBanco();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void salvarDado(View v){
        //Insert
        String uuid = UUID.randomUUID().toString();
        databaseReference
                .child("dicionario")
                .child("123")
                .child("valor")
                .setValue(editTextNome.getText().toString());

    /*    databaseReference
                .child("dicionario")
                .child(uuid)
                .child("idade")
                .setValue(editTextIdade.getText().toString());*/
        editTextNome.setText("");
      /*  editTextIdade.setText("");*/
    }

    public void apagarDado(View v){
        databaseReference.child("dicionario").child("123").removeValue();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemLeitura){
            Intent intent = new Intent(MainActivity.this, LeituraActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
