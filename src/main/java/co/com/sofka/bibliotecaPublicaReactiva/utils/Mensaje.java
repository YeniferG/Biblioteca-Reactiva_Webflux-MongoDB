package co.com.sofka.bibliotecaPublicaReactiva.utils;

import java.util.Date;

public class Mensaje {

    private boolean isDisponible;
    private String mensaje;

    public Mensaje() {
    }

    public Mensaje(boolean isDisponible, String mensaje) {
        this.isDisponible = isDisponible;
        this.mensaje = mensaje;
    }

    public boolean isDisponible() {
        return isDisponible;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Mensaje imprimirMensajeDisponibilidad(Boolean disponibilidad, Date fechaPrestamo){
        if(disponibilidad){
            return new Mensaje(true, "Recurso disponible");
        }
        return new Mensaje(false,"El recurso solicitado fue prestado el: " + fechaPrestamo);
    }

    public Mensaje imprimirMensajePrestamo(Boolean disponibilidad, Date fechaPrestamo){
        if(disponibilidad){
            return new Mensaje(true, "Recurso disponible para prestamo");
        }
        return new Mensaje(false, "Recurso no disponible, fue prestado el: " + fechaPrestamo);
    }

    public Mensaje imprimirMensajeDevolucion(Boolean disponibilidad, Date fechaPrestamo){
        if(!disponibilidad){
            return new Mensaje(true, "Recurso entregado sin problemas");
        }
        return new Mensaje(false,"Recurso no esta prestado");
    }
}