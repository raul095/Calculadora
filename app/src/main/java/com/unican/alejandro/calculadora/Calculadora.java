package com.unican.alejandro.calculadora;

/**
 * Created by Alejandro on 23/09/2015.
 */
public class Calculadora {
    double operador1, operador2;
    double resultado;

    /**
     * Constructor de la clase
     * Inicializamos todos los atributos a 0.0
     */
    public Calculadora(){
        this.operador1 = 0.0;
        this.operador2 = 0.0;
        this.resultado = 0.0;
    }// Calculadora

    public void setOperador1 (double op1){
        this.operador1=op1;
    }// setOperador1

    public void setOperador2 (double op2){
        this.operador2=op2;
    }// setOperador2

    public double getOperador1() {
        return operador1;
    }// getOperador1

    public double getOperador2() {
        return operador2;
    }// getOperador2

    public double suma(){
        resultado = operador1+operador2;
        return resultado;
    }// suma

    public double resta(){
        resultado = operador1-operador2;
        return resultado;
    }// resta

    public double divide(){
        resultado = operador1/operador2;
        return resultado;
    }// divide

    public double multiplica(){
        resultado = operador1*operador2;
        return resultado;
    }// multiplica
}// Calculadora
