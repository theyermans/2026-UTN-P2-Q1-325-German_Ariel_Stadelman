
package biblioteca;

import java.util.ArrayList;
import java.util.HashMap;

class Repositorio<T extends Identificable> {
    // Uso de HashMap para búsquedas O(1) ultra rápidas
    private HashMap<String, T> mapa = new HashMap<>();

    public void agregar(T objeto) throws Exception {
        if (mapa.containsKey(objeto.getId())) {
            throw new Exception("Error: El registro con ID/Codigo '" + objeto.getId() + "' ya existe.");
        }
        mapa.put(objeto.getId(), objeto);
    }

    public ArrayList<T> listar() {
        return new ArrayList<>(mapa.values()); // Se convierte a ArrayList para serializar y listar
    }

    public T buscar(String id) {
        return mapa.get(id); 
    }

    public void cargarDesdeLista(ArrayList<T> lista) {
        mapa.clear();
        if (lista != null) {
            for (T obj : lista) {
                mapa.put(obj.getId(), obj);
            }
        }
    }
}
