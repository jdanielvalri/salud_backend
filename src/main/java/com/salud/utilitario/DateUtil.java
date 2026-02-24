package com.salud.utilitario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date convetirCadenaAFecha(String cadena) throws ParseException {
        String fechaTexto = cadena.substring(0,10);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = sdf.parse(fechaTexto);

        return fecha;
    }
    public static String convetirFechaACadena(Date fecha, String formato) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String texto = sdf.format(fecha);
        return texto;
    }
}
