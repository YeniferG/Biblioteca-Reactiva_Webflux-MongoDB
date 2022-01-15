package co.com.sofka.bibliotecaPublicaReactiva.collection;

import co.com.sofka.bibliotecaPublicaReactiva.utils.AreaTematica;
import co.com.sofka.bibliotecaPublicaReactiva.utils.Tipo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Recurso {

    @Id
    private String id;
    private String nombre;
    private boolean isDisponible = true;
    private LocalDate fechaPrestamo;
    private Tipo tipo;
    private AreaTematica areaTematica;


    public Recurso(){}

    public Recurso(String id, String nombre, Tipo tipo, AreaTematica areaTematica) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.areaTematica = areaTematica;
    }

    public Recurso(String nombre, Tipo tipo, AreaTematica area) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.areaTematica = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return isDisponible;
    }

    public void setDisponible(boolean disponible) {
        isDisponible = disponible;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public AreaTematica getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(AreaTematica areaTematica) {
        this.areaTematica = areaTematica;
    }
}