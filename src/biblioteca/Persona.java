
package biblioteca;


import java.io.Serializable;

abstract class Persona implements Serializable {
    protected String dni;
    protected String nombre;
    protected String apellido;

    public Persona(String dni, String nombre, String apellido) {
        if (dni == null || dni.trim().isEmpty()) throw new IllegalArgumentException("DNI vacio.");
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("Nombre vacio.");
        
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
