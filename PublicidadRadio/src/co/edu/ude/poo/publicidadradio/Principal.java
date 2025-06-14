package co.edu.ude.poo.publicidadradio;

import co.edu.ude.poo.publicidadradio.modelo.crud.*;
import co.edu.ude.poo.publicidadradio.modelo.entidades.*;
import java.util.List;

/**
 * Clase principal para demostrar el funcionamiento completo del sistema,
 * incluyendo las operaciones CRUD para todas las entidades.
 * @author Eduard David Barrios Padilla
 */
public class Principal {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO DEMOSTRACIÓN COMPLETA DEL SISTEMA DE GESTIÓN DE PUBLICIDAD ---");

        // 1. Instanciamos todas las clases CRUD que vamos a utilizar
        EmpresaMediosCrud empresaCrud = new EmpresaMediosCrud();
        PatrocinadorCrud patrocinadorCrud = new PatrocinadorCrud();
        EmisoraCrud emisoraCrud = new EmisoraCrud();
        CadenaRadioCrud cadenaCrud = new CadenaRadioCrud();
        ProgramaCrud programaCrud = new ProgramaCrud();
        ContratoPublicidadCrud contratoCrud = new ContratoPublicidadCrud();
        PublicidadCrud publicidadCrud = new PublicidadCrud();

        // =================================================================
        // SECCIÓN 1: GESTIÓN DE ENTIDADES BASE (Empresa y Patrocinador)
        // =================================================================
        System.out.println("\n===== [SECCIÓN 1: GESTIÓN DE EMPRESAS Y PATROCINADORES] =====");

        // --- Agregando Empresas ---
        System.out.println("\n--- Agregando Empresas de Medios ---");
        EmpresaMedios rcn = new EmpresaMedios("890.123.456-7", "RCN Radio", "Fernando Molina", "Calle 37 #13-42, Bogotá");
        EmpresaMedios caracol = new EmpresaMedios("860.014.949-4", "Caracol Radio", "Felipe Cabrales", "Calle 67 #7-37, Bogotá");
        try {
            empresaCrud.agregar(rcn);
            empresaCrud.agregar(caracol);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // --- Agregando Patrocinadores ---
        System.out.println("\n--- Agregando Patrocinadores ---");
        Patrocinador exito = new Patrocinador("Almacenes Éxito S.A.", "900.000.001-9");
        Patrocinador cocaCola = new Patrocinador("Coca-Cola FEMSA", "800.123.456-1");
        try {
            patrocinadorCrud.agregar(exito);
            patrocinadorCrud.agregar(cocaCola);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\n--- Listando todo lo creado ---");
        try {
            System.out.println("Total de empresas: " + empresaCrud.contar());
            empresaCrud.listarTodo().forEach(emp -> System.out.println(" -> " + emp));

            System.out.println("\nTotal de patrocinadores: " + patrocinadorCrud.contar());
            patrocinadorCrud.listarTodo().forEach(p -> System.out.println(" -> " + p));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // =================================================================
        // SECCIÓN 2: GESTIÓN DE ENTIDADES DEPENDIENTES (Emisora y Cadena)
        // =================================================================
        System.out.println("\n\n===== [SECCIÓN 2: GESTIÓN DE EMISORAS Y CADENAS DE RADIO] =====");

        // --- Agregando Emisoras y Cadenas ---
        Emisora laFMBogota = new Emisora("HJCK", "La FM Bogotá", "Cra 13A #38-33", "Luis Carlos Vélez", "94.9 MHz");
        Emisora laFMMedellin = new Emisora("HJDK", "La FM Medellín", "Calle 52 #47-28", "Director Medellín", "106.9 MHz");
        CadenaRadio cadenaLaFM = new CadenaRadio("La FM", laFMBogota, "Director General de Cadena", rcn);

        try {
            System.out.println("\n--- Agregando Emisoras ---");
            emisoraCrud.agregar(laFMBogota);
            emisoraCrud.agregar(laFMMedellin);

            System.out.println("\n--- Agregando Cadena de Radio ---");
            cadenaCrud.agregar(cadenaLaFM);

            // --- Editando una entidad para mostrar la funcionalidad ---
            System.out.println("\n--- Editando un Patrocinador ---");
            Patrocinador cocaColaEditado = new Patrocinador("Coca-Cola Company", "800.123.456-1");
            patrocinadorCrud.editar(cocaColaEditado);
            System.out.println("Buscando patrocinador editado...");
            System.out.println(" -> " + patrocinadorCrud.buscar("800.123.456-1"));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // =================================================================
        // SECCIÓN 3: GESTIÓN DEL NÚCLEO (Programa, Contrato, Publicidad)
        // =================================================================
        System.out.println("\n\n===== [SECCIÓN 3: GESTIÓN DE PROGRAMAS, CONTRATOS Y PUBLICIDAD] =====");

        Programa programaNoticias = new Programa("Noticias La FM", "Luis Carlos Vélez", "Lunes a Viernes", "05:00", 300);
        programaNoticias.setCadena(cadenaLaFM); // Asociamos el programa a una cadena

        ContratoPublicidad contratoAniversario = new ContratoPublicidad("CON-001", exito, 4, 50000000.0);

        Publicidad cunaAniversario = new Publicidad(30, 25000.0, programaNoticias, contratoAniversario);

        try {
            System.out.println("\n--- Agregando Programa, Contrato y Publicidad ---");
            programaCrud.agregar(programaNoticias);
            contratoCrud.agregar(contratoAniversario);
            publicidadCrud.agregar(cunaAniversario);

            System.out.println("\n--- Listando todo ---");
            System.out.println("Total de programas: " + programaCrud.contar());
            System.out.println("Total de contratos: " + contratoCrud.contar());
            System.out.println("Total de publicidades: " + publicidadCrud.contar());

            // --- Buscando la publicidad con su ID sintético ---
            System.out.println("\n--- Buscando una publicidad específica ---");
            String idSintetico = "CON-001-Noticias La FM";
            Publicidad publicidadEncontrada = publicidadCrud.buscar(idSintetico);
            System.out.println("Publicidad encontrada con ID '" + idSintetico + "':");
            System.out.println(" -> " + publicidadEncontrada);
            System.out.println(" -> Costo Total: $" + String.format("%,.2f", publicidadEncontrada.getCostoTotal()));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // =================================================================
        // SECCIÓN 4: ELIMINACIÓN DE DATOS
        // =================================================================
        System.out.println("\n\n===== [SECCIÓN 4: DEMOSTRACIÓN DE ELIMINACIÓN] =====");
        try {
            System.out.println("\n--- Eliminando una empresa y un patrocinador ---");
            System.out.println("Total de empresas antes de eliminar: " + empresaCrud.contar());
            empresaCrud.eliminar("860.014.949-4"); // Eliminamos a Caracol Radio
            System.out.println("Total de empresas después de eliminar: " + empresaCrud.contar());

            System.out.println("\nTotal de patrocinadores antes de eliminar: " + patrocinadorCrud.contar());
            patrocinadorCrud.eliminar("900.000.001-9"); // Eliminamos a Éxito
            System.out.println("Total de patrocinadores después de eliminar: " + patrocinadorCrud.contar());

            System.out.println("\n--- Intentando buscar un elemento eliminado (esperamos un error) ---");
            patrocinadorCrud.buscar("900.000.001-9");

        } catch (Exception e) {
            System.err.println("-> CAPTURADO ERROR ESPERADO: " + e.getMessage());
        }

        System.out.println("\n\n--- DEMOSTRACIÓN FINALIZADA ---");
    }
}