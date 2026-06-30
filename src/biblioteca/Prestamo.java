/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;


import java.io.Serializable;
import java.time.LocalDate;


class Prestamo implements Serializable, Identificable {
    private int idTransaccion;
    private Libro recurso;
    private Socio actor;
    private LocalDate fechaInicio;
    private boolean activo;

    public Prestamo(int idTransaccion, Libro recurso, Socio actor) {
        this.idTransaccion = idTransaccion;
        this.recurso = recurso;
        this.actor = actor;
        this.fechaInicio = LocalDate.now();
        this.activo = true;
    }

    public Libro getRecurso() { return recurso; }
    public Socio getActor() { return actor; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public boolean isActivo() { return activo; }
    
    public void finalizarTransaccion() {
        this.activo = false;
        this.recurso.setDisponible(true); // Libera el recurso
    }

    @Override
    public String getId() {
        return String.valueOf(idTransaccion);
    }

    @Override
    public String toString() {
        return "ID: " + idTransaccion + " | Actor: " + actor.getId() + " | Libro: " + recurso.getId() +" | Fecha: "+ fechaInicio + " | Activo: " + (activo ? "Si" : "No");
    }
}
