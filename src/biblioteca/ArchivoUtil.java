/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

class ArchivoUtil {
    
    // Serialización genérica (Guarda cualquier ArrayList)
    public static <T> void guardarDatos(String ruta, ArrayList<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(lista);
        }
    }

    // Deserialización genérica (Carga cualquier ArrayList)
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> cargarDatos(String ruta) throws IOException, ClassNotFoundException {
        File file = new File(ruta);
        if (!file.exists()) {
            return new ArrayList<>(); // Retorna lista vacía si es la primera vez que se abre
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<T>) ois.readObject();
        }
    }
}
