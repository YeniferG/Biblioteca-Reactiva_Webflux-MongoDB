package co.com.sofka.bibliotecaPublicaReactiva.dto;

import co.com.sofka.bibliotecaPublicaReactiva.utils.AreaTematica;
import co.com.sofka.bibliotecaPublicaReactiva.utils.Tipo;

import java.time.LocalDate;
import java.util.Objects;

public class RecursoDTO {

    private String id;
    private String nombre;
    private boolean isDisponible;
    private LocalDate fechaPrestamo;
    private Tipo tipo;
    private AreaTematica areaTematica;

    public RecursoDTO(){}

    public RecursoDTO(String nombre, Tipo tipo, AreaTematica areaTematica) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.areaTematica = areaTematica;
    }

    public RecursoDTO(String id, String nombre, boolean isDisponible, LocalDate fechaPrestamo, Tipo tipo, AreaTematica areaTematica) {
        this.id = id;
        this.nombre = nombre;
        this.isDisponible = isDisponible;
        this.fechaPrestamo = fechaPrestamo;
        this.tipo = tipo;
        this.areaTematica = areaTematica;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecursoDTO that = (RecursoDTO) o;
        return isDisponible == that.isDisponible && Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(fechaPrestamo, that.fechaPrestamo) && tipo == that.tipo && areaTematica == that.areaTematica;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, isDisponible, fechaPrestamo, tipo, areaTematica);
    }

    @Override
    public String toString() {
        return "RecursoDTO{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", isDisponible=" + isDisponible +
                ", fechaPrestamo=" + fechaPrestamo +
                ", tipo=" + tipo +
                ", areaTematica=" + areaTematica +
                '}';
    }
}