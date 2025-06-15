package co.edu.ude.poo.publicidadradio.util;

import co.edu.ude.poo.publicidadradio.modelo.crud.*;
import co.edu.ude.poo.publicidadradio.modelo.entidades.*;

/**
 * Clase de utilidad para cargar datos de prueba en el sistema.
 * Contiene un método 'cargar()' para ser llamado desde el inicio de la aplicación.
 * @author Eduard David Barrios Padilla
 */
public class CargarDatosDePrueba {

    /**
     * Método público que realiza toda la carga de datos de prueba en memoria.
     */
    public void cargar() {
        System.out.println("--- INICIANDO CARGA DE DATOS DE PRUEBA ---");

        // 1. Instanciamos todas las clases CRUD
        EmpresaMediosCrud empresaCrud = new EmpresaMediosCrud();
        PatrocinadorCrud patrocinadorCrud = new PatrocinadorCrud();
        EmisoraCrud emisoraCrud = new EmisoraCrud();
        CadenaRadioCrud cadenaCrud = new CadenaRadioCrud();
        ProgramaCrud programaCrud = new ProgramaCrud();
        ContratoPublicidadCrud contratoCrud = new ContratoPublicidadCrud();
        PublicidadCrud publicidadCrud = new PublicidadCrud();
        EmisionCrud emisionCrud = new EmisionCrud();

        // Si las listas ya tienen datos, no hacemos nada para no duplicar.
        if (empresaCrud.contar() > 0) {
            System.out.println("INFO: Los datos de prueba ya fueron cargados previamente.");
            System.out.println("--- CARGA DE DATOS OMITIDA ---");
            return;
        }

        try {
            System.out.println("\n-> Cargando Empresas de Medios...");
            EmpresaMedios caracolRadio = new EmpresaMedios("860.014.949-4", "Caracol Radio", "Felipe Cabrales", "Calle 67 #7-37, Bogotá");
            EmpresaMedios rcnRadio = new EmpresaMedios("890.123.456-7", "RCN Radio", "Fernando Molina", "Calle 37 #13-42, Bogotá");
            empresaCrud.agregar(caracolRadio);
            empresaCrud.agregar(rcnRadio);

            System.out.println("-> Cargando Patrocinadores...");
            Patrocinador exito = new Patrocinador("Almacenes Éxito", "900.000.001-9");
            Patrocinador davivienda = new Patrocinador("Davivienda", "860.034.313-7");
            patrocinadorCrud.agregar(exito);
            patrocinadorCrud.agregar(davivienda);

            System.out.println("-> Cargando Emisoras...");
            Emisora laW = new Emisora("HJCZ", "W Radio", "Calle 67 #7-37, Bogotá", "Julio Sánchez Cristo", "99.9 MHz FM");
            Emisora laMega = new Emisora("HJQ22", "La Mega", "Calle 37 #13-42, Bogotá", "Alejandro Villalobos", "90.9 MHz FM");
            emisoraCrud.agregar(laW);
            emisoraCrud.agregar(laMega);

            System.out.println("-> Cargando Cadenas de Radio...");
            CadenaRadio cadenaW = new CadenaRadio("Cadena W", laW, "Director Cadena W", caracolRadio);
            cadenaCrud.agregar(cadenaW);
            laW.setCadena(cadenaW);

            System.out.println("-> Cargando Programas...");
            Programa laHoraDelRegreso = new Programa("La Hora del Regreso", "Carlos Montoya", "Lunes a Viernes", "17:00", 180);
            laHoraDelRegreso.setCadena(cadenaW);
            programaCrud.agregar(laHoraDelRegreso);

            System.out.println("-> Cargando Contratos de Publicidad...");
            ContratoPublicidad contratoExitoNavidad = new ContratoPublicidad("CON-EXITO-001", exito, 8, 80000000.0);
            contratoCrud.agregar(contratoExitoNavidad);

            System.out.println("-> Cargando Publicidad...");
            Publicidad cuñaExito = new Publicidad(60, 25000, laHoraDelRegreso, contratoExitoNavidad);
            publicidadCrud.agregar(cuñaExito);

            System.out.println("-> Cargando Emisiones...");
            Emision emisionLunesRegreso = new Emision("Lunes", "17:00", 182, laHoraDelRegreso);
            emisionCrud.agregar(emisionLunesRegreso);

        } catch (Exception e) {
            System.err.println("¡Ocurrió un error durante la carga de datos!");
            e.printStackTrace();
        }

        System.out.println("\n--- ¡CARGA DE DATOS DE PRUEBA FINALIZADA! ---");
    }


}