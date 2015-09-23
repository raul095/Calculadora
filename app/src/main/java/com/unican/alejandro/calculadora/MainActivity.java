package com.unican.alejandro.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener{

    Calculadora calculadora;
    //Atributos para los elementos graficos de la interfaz de usuario
    TextView textViewRes;
    Button buttonCalcula;
    Spinner spinnerOperation;
    TextView editTextFirst;
    TextView editTextSecond;
    public enum Operaciones{Suma, Resta, Multiplica, Divide}

    /**
     * Metodo que se llama cada vez que la Activity se crea
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculadora = new Calculadora();
        //Asignamos los componentes graficos
        textViewRes = (TextView) findViewById(R.id.textViewRes);
        buttonCalcula = (Button) findViewById(R.id.buttonCalcula);
        spinnerOperation = (Spinner) findViewById(R.id.spinnerOperation);
        editTextFirst = (EditText) findViewById(R.id.editTextFirst);
        editTextSecond = (EditText) findViewById(R.id.editTextSecond);
        //Asignamos el listener al boton (en este caso la propia clase)
        buttonCalcula.setOnClickListener(this);
        //Se puede implementar de otra manera creandolo directamente
        /**
        buttonCalcula.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Do something in response to button click
        }
        });
        */
        loadSpinner();
    }// onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }// onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_option1) {
            //Lanzamos la actividad de la calculadora real
            Intent intent = new Intent(this, CalculadoraRealActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }// onOptionItemSelected

    /**
     * Método para cargar el Spinner con los strings de las operaciones
     */
    private void loadSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operationsArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerOperation.setAdapter(adapter);
    }// loadSpinner


    /**
     * Método que se lanza cada vez que se pulsa algún elemento de la interfaz de usuario
     * @param v Elemento que ha lanzado el metodo
     */
    @Override
    public void onClick(View v) {
        //Comprobamos que elemento ha llamado
        if(v.getId()==R.id.buttonCalcula){
            Log.v("Test", "Boton pulsado");
            Toast.makeText(this,"Operacion realizada", Toast.LENGTH_SHORT).show();
            textViewRes.setText(calcula());

        }// if
    }// onClick

    private String calcula(){
        double op1, op2;
        String value1, value2, res;
        //Obtenemos el valor de los operadores
        value1 = editTextFirst.getText().toString();
        value2 = editTextSecond.getText().toString();
        //Comprobamos que se ha introducido algun valor numerico
        if(value1.matches("") || value2.matches("")){
            //Mostramos por el LogCat un mensaje
            Log.d("Prueba",spinnerOperation.getSelectedItem().toString());
            //Mostramos un mensaje volátil en forma de Toast al usuario
            Toast.makeText(this, "Introduce los valores", Toast.LENGTH_SHORT).show();
            res = "Introduce los operadores numericos";
            return res;
        }else{
            //Obtenemos los valores numericos de los operaderos
            calculadora.setOperador1(Double.parseDouble(editTextFirst.getText().toString()));
            calculadora.setOperador2(Double.parseDouble(editTextSecond.getText().toString()));
            //Sacamos el tipo de operacion seleccionada por el usuario
            Operaciones op = Operaciones.valueOf(spinnerOperation.getSelectedItem().toString());

            switch(op){
                case Suma:
                    res= ""+calculadora.suma();
                    break;
                case Resta:
                    res= ""+calculadora.resta();
                    break;
                case Divide:
                    res = ""+calculadora.divide();
                    break;
                case Multiplica:
                    res = ""+calculadora.multiplica();
                    break;
                default:
                    res="Otras operaciones no soportadas";
                    break;
            } //switch
            return res;
        }// if
    }// calcula

}// MainActivity
