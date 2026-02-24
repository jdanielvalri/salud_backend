package com.salud.utilitario;

public class NumericValidation {
    public static boolean esNuloInteger(Integer valor){
        return valor == null;
    }

    public static boolean esCeroInteger(Integer valor){
        return valor == 0;
    }

    public static boolean esCeroDecimal(double valor){
        return valor == 0.0;
    }
}
