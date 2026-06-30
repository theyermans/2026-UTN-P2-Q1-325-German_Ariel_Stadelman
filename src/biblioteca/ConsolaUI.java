
package biblioteca;



import java.util.Scanner;

class ConsolaUI {
    private final Biblioteca biblioteca;
    private final Scanner scanner;

    public ConsolaUI(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;
        do {
            System.out.println("\n===== SISTEMA GESTOR PRINCIPAL =====");
            System.out.println("1. Registrar Socio      | 2. Registrar Libro");
            System.out.println("3. Listar Socio        | 4. Listar Libros disponibles");
            System.out.println("5. buscar libro po codigo   | 6. Registrar Prestamo");
            System.out.println("7. Registrar devolucion| 8. Mostrar prestamos activos");
            System.out.println("9. Guardar informe ");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un numero valido.");
            } catch (Exception e) {
                System.out.println(" Error de operacion: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void procesarOpcion(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                System.out.print("DNI: "); String dni = scanner.nextLine();
                System.out.print("Nombre: "); String nom = scanner.nextLine();
                System.out.print("Apellido: "); String ape = scanner.nextLine();
                System.out.print("Email: "); String email = scanner.nextLine();
                biblioteca.repoActores.agregar(new Socio(dni, nom, ape, email));
                System.out.println("Actor registrado.");
                break;
            case 2:
                System.out.print("Codigo unico: "); String cod = scanner.nextLine();
                System.out.print("Nombre/Titulo: "); String tit = scanner.nextLine();
                System.out.print("Autor: "); String aut = scanner.nextLine();
                
                biblioteca.repoRecursos.agregar(new Libro(cod, tit, aut));
                System.out.println("Recurso registrado.");
                
                break;
            case 3:
                biblioteca.repoActores.listar().forEach(System.out::println);
                
                break;
            case 4:
                System.out.println("Libros Disponibles");
                
                biblioteca.repoRecursos.listar().stream().filter(Libro::isDisponible).forEach(System.out::println);
                break;
            case 5:
                System.out.print("Ingrese codigo del libro: ");
                Libro encontrado = biblioteca.repoRecursos.buscar(scanner.nextLine());
                System.out.println(encontrado != null? encontrado : "Libro no encontrado");                
                
                break;
            case 6:
                System.out.println("Codigo del libro"); String codLiro = scanner.nextLine();
                System.out.println("DNI del socio"); String dniSocio = scanner.nextLine();
                biblioteca.registrarTransaccion(codLiro, dniSocio);
                break;
            case 7:
                System.out.println("ID del prestamo a devolver: ");
                biblioteca.finalizarTransaccion(scanner.nextLine());
                break;
            case 8:
                biblioteca.repoTransacciones.listar().stream()
                      .filter(Prestamo::isActivo)
                      .forEach(System.out::println);
                break;
            case 9:
                biblioteca.exportar("informe.txt");
                break;
            case 10:
               
                break;
            case 0:
                System.out.println("Saliendo del sistema...");
                 biblioteca.guardarDatosBinarios();
                break;
            default:
                System.out.println("Opcion invalida.");
        }
    }
}

