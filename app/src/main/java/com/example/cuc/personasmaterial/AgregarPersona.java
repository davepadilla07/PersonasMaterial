package com.example.cuc.personasmaterial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;

public class AgregarPersona extends AppCompatActivity {
    private EditText cajaCedula;
    private EditText cajaNombre;
    private EditText cajaApellido;
    private boolean guardado=false;
    private TextInputLayout icajaCedula;
    private TextInputLayout icajaNombre;
    private TextInputLayout icajaApellido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        cajaCedula=(EditText)findViewById(R.id.txtCedula);
        cajaNombre=(EditText)findViewById(R.id.txtNombre);
        cajaApellido=(EditText)findViewById(R.id.txtApellido);

        icajaCedula=(TextInputLayout)findViewById(R.id.CedulaPersona);
        icajaNombre=(TextInputLayout)findViewById(R.id.NombrePersona);
        icajaApellido=(TextInputLayout)findViewById(R.id.ApellidoPersona);
        guardado=false;

        cajaCedula.addTextChangedListener(new TextWatcherPersonalizado(icajaCedula,getResources().getString(R.string.error1)) {
            @Override
            public boolean estaVacio(Editable s) {
                if (TextUtils.isEmpty(s)&&!guardado) return true;
                else return false;
            }
        });
        cajaNombre.addTextChangedListener(new TextWatcherPersonalizado(icajaNombre,getResources().getString(R.string.error2)) {
            @Override
            public boolean estaVacio(Editable s) {
                if (TextUtils.isEmpty(s)&&!guardado) return true;
                else return false;
            }
        });
        cajaApellido.addTextChangedListener(new TextWatcherPersonalizado(icajaApellido,getResources().getString(R.string.error3)) {
            @Override
            public boolean estaVacio(Editable s) {
                if (TextUtils.isEmpty(s)&&!guardado) return true;
                else return false;
            }
        });

    }

    public int fotoaleatoria(){
        int foto[]={R.drawable.images,R.drawable.images2,R.drawable.images3};
        int numero=(int)(Math.random()*3);
        return foto[numero];
    }

    public boolean validarTodo(){
        if (cajaCedula.getText().toString().isEmpty()){
            icajaCedula.setError(getResources().getString(R.string.error1));
            cajaCedula.requestFocus();
            return false;
        }
        if (cajaNombre.getText().toString().isEmpty()){
            icajaNombre.setError(getResources().getString(R.string.error2));
            cajaNombre.requestFocus();
            return false;
        }
        if (cajaApellido.getText().toString().isEmpty()){
            icajaApellido.setError(getResources().getString(R.string.error3));
            cajaApellido.requestFocus();
            return false;
        }
        //Escoger por lo menos 1 pasatiempo

        return true;
    }

    public void guardar(View v){
        String urlfoto,cedula,nombre,apellido,idfoto;
        Persona p;
        if (validarTodo()){
            cedula=cajaCedula.getText().toString();

            nombre=cajaNombre.getText().toString();
            apellido=cajaApellido.getText().toString();
            idfoto=String.valueOf(fotoaleatoria());

            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),Integer.parseInt(idfoto));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
            byte[] imagenByte= baos.toByteArray();
            urlfoto = Base64.encodeToString(imagenByte,Base64.DEFAULT);

            //Le quita el espacio y la "," al final

            p = new Persona(urlfoto,cedula,nombre,apellido,idfoto);
            p.guardar(getApplicationContext());

            InputMethodManager imp = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imp.hideSoftInputFromWindow(cajaCedula.getWindowToken(),0);

            Snackbar.make(v,getResources().getString(R.string.mensaje2),Snackbar.LENGTH_SHORT).show();
            guardado=true;
            limpiar();
        }
    }

    public void limpiar(){
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");
        cajaCedula.requestFocus();
        guardado=true;
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarPersona.this,Principal.class);
        startActivity(i);
    }
}
