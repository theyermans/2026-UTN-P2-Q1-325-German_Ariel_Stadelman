
package biblioteca;

public class Main {
    public static void main(String[] args) {
        Biblioteca gestor = new Biblioteca();
        cargarDatosPrueba(gestor); // Precarga para facilitar corrección

        ConsolaUI consola = new ConsolaUI(gestor);
        consola.iniciar();
    }

    private static void cargarDatosPrueba(Biblioteca gestor) {
        try {
            gestor.repoRecursos.agregar(new Libro("REC001", "Recurso de Prueba 1","Stadelman"));
            gestor.repoRecursos.agregar(new Libro("REC002", "Recurso de Prueba 2","Borges"));
            gestor.repoActores.agregar(new Socio("11111111", "Juan", "Perez", "juan@test.com"));
            gestor.repoActores.agregar(new Socio("22222222", "Maria", "Gomez", "maria@test.com"));
            System.out.println("ℹ️ Datos de prueba precargados en memoria.");
        } catch (Exception e) {
            System.out.println("Error precargando: " + e.getMessage());
        }
    }
}