package com.salud.utilitario;

public class StringValidation {

    public static boolean isVacio(String valor){
        if(valor==null){
            return true;
        }
        return valor.trim().isEmpty();
    }
}
