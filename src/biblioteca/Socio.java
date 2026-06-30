/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

class Socio extends Persona implements Identificable {
    private String email; // Atributo específico

    public Socio(String dni, String nombre, String apellido, String email) {
        super(dni, nombre, apellido);
        this.email = email;
    }

    @Override
    public String getId() {
        return this.dni; // El DNI es la clave para el HashMap
    }

    @Override
    public String toString() {
        return "DNI: " + dni + " | Nombre: " + apellido + ", " + nombre + " | Contacto: " + email;
    }
}