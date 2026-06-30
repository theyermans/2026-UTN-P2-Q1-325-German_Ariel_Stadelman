/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

// =========================================================================

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;


class Biblioteca implements Exportable {
    public Repositorio<Libro> repoRecursos = new Repositorio<>();
    public Repositorio<Socio> repoActores = new Repositorio<>();
    public Repositorio<Prestamo> repoTransacciones = new Repositorio<>();
    
    private int contadorTransacciones = 1;

    // --- LÓGICA DE NEGOCIO ---
    public void registrarTransaccion(String codRecurso, String dniActor) throws Exception {
        Libro recurso = repoRecursos.buscar(codRecurso);
        Socio actor = repoActores.buscar(dniActor);

        if (recurso == null) throw new Exception("El libro no existe en el sistema.");
        if (actor == null) throw new Exception("El socio no existe en el sistema.");
        if (!recurso.isDisponible()) throw new Exception("El libro no está disponible actualmente.");

        recurso.setDisponible(false); // Cambia el estado
        recurso.incremenarPrestamo();
        Prestamo nuevaTransaccion = new Prestamo(contadorTransacciones++, recurso, actor);
        repoTransacciones.agregar(nuevaTransaccion);
        
        System.out.println(" Transaccion registrada con exito. ID: " + nuevaTransaccion.getId());
    }

    public void finalizarTransaccion(String idTransaccion) throws Exception {
        Prestamo transaccion = repoTransacciones.buscar(idTransaccion);
        if (transaccion == null || !transaccion.isActivo()) {
            throw new Exception("Transaccion inexistente o ya finalizada.");
        }
        transaccion.finalizarTransaccion();
        System.out.println("Transaccion finalizada. El recurso vuelve a estar disponible.");
    }

    // --- ARCHIVOS DE TEXTO (Reporte) ---
    @Override
    public void exportar(String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            int totalSocios = repoActores.listar().size();
            int totalLibros = repoRecursos.listar().size();
            long prestamosActivos = repoTransacciones.listar().stream().filter(Prestamo::isActivo).count();
            long librosDispnibles = repoRecursos.listar().stream().filter(Libro::isDisponible).count() ;
            
            Libro libroMasPedido = repoRecursos.listar().stream().max(Comparator.comparingInt(Libro::getVecesPrestado)).orElse(null);
            
            bw.write("===== REPORTE DE TRANSACCIONES ACTIVAS =====");
            bw.write("Fecha de emision: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss" ))); bw.newLine();
            bw.write("Total mde socios registrados: " + totalSocios); bw.newLine();
            bw.write("Total de libros en catalogo "+ totalLibros); bw.newLine();
            bw.write("Libros actualemtne disponibles: " + librosDispnibles); bw.newLine();
            bw.write("Prestamos activos en curso: "+ prestamosActivos); bw.newLine();
            
            if (libroMasPedido != null && libroMasPedido.getVecesPrestado() > 0){
                bw.write("Libro mas solicitado "+ libroMasPedido.getTitulo() + " "+ (libroMasPedido.getVecesPrestado()+ " prestamos")); bw.newLine();
            }else{
                bw.write("Libro mas solicitaod: aun no hay registros"); bw.newLine();
            }
            
            System.out.println("Reporte exportado exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al exportar el reporte: " + e.getMessage());
        }
    }

    // --- SERIALIZACIÓN BINARIA ---
    public void guardarDatosBinarios() {
        try {
            ArchivoUtil.guardarDatos("recursos.dat", repoRecursos.listar());
            ArchivoUtil.guardarDatos("actores.dat", repoActores.listar());
            ArchivoUtil.guardarDatos("transacciones.dat", repoTransacciones.listar());
            System.out.println("Datos binarios guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error guardando datos binarios: " + e.getMessage());
        }
    }

    public void cargarDatosBinarios() {
        try {
            repoRecursos.cargarDesdeLista(ArchivoUtil.cargarDatos("recursos.dat"));
            repoActores.cargarDesdeLista(ArchivoUtil.cargarDatos("actores.dat"));
            repoTransacciones.cargarDesdeLista(ArchivoUtil.cargarDatos("transacciones.dat"));
            
            // Reconstruir el autoincremental buscando el ID más alto
            for (Prestamo t : repoTransacciones.listar()) {
                int idNum = Integer.parseInt(t.getId());
                if (idNum >= contadorTransacciones) contadorTransacciones = idNum + 1;
            }
            System.out.println("Datos binarios cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se encontraron datos previos o hubo un error al leer.");
        }
    }
}
