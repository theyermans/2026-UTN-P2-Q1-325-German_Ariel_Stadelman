/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;



import java.io.Serializable;


class Libro implements Serializable, Identificable {
    private String codigo;
    private String titulo;
    private String autor;
    private boolean disponible;
    private int vecesPrestado;

    public Libro(String codigo, String titulo, String autor) {
        if (codigo == null || codigo.trim().isEmpty()) throw new IllegalArgumentException("Codigo vacio.");
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true; // Por defecto está disponible
        this.vecesPrestado = 0;
    }

    public String getTitulo() { return titulo; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public int getVecesPrestado(){ return vecesPrestado;}
    public void incremenarPrestamo(){this.vecesPrestado++;    }

    @Override
    public String getId() { return this.codigo;    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + " | Titulo: " + titulo +" | Autor: " + autor + " | Estado: " + (disponible ? "Disponible" : "No Disponible");
    }
}

