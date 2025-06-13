package co.edu.ude.poo.publicidadradio;

import co.edu.ude.poo.publicidadradio.modelo.entidades.*;
import java.util.List;

/**
 * Clase principal para probar el modelo del sistema de gestión de publicidad.
 * Aquí crearemos instancias de las clases y simularemos las relaciones entre ellas.
 * @author Eduard David Barrios Padilla
 */
public class Principal {

    public static void main(String[] args) {
        System.out.println("--- Iniciando Simulación del Sistema de Publicidad en Radio ---");

        // 1. Creación de Entidades Principales (las que no dependen de otras)
        System.out.println("\n[Paso 1: Creando Empresa de Medios y Patrocinador]");
        EmpresaMedios rcn = new EmpresaMedios("890.123.456-7", "RCN Radio", "Fernando Molina", "Calle 37 #13-42, Bogotá");
        Patrocinador exito = new Patrocinador("Almacenes Éxito S.A.", "900.000.001-9");
        System.out.println("Creado: " + rcn.toString());
        System.out.println("Creado: " + exito.toString());

        // 2. Creación de Entidades Dependientes y Establecimiento de Relaciones
        System.out.println("\n[Paso 2: Creando Cadena de Radio y Emisoras]");
        // Se crea una emisora que será la sede central
        Emisora laFM_Bogota = new Emisora("HJCK", "La FM Bogotá", "Cra 13A #38-33", "Luis Carlos Vélez", "94.9 MHz");

        // Se crea la cadena, se le asigna la empresa y la sede central
        CadenaRadio cadenaLaFM = new CadenaRadio("La FM", laFM_Bogota, "Director General de Cadena", rcn);

        // Se añade la cadena a la lista de cadenas de la empresa
        rcn.getCadenas().add(cadenaLaFM);

        // Se vincula la emisora a la cadena
        laFM_Bogota.setCadena(cadenaLaFM);

        // Se crea otra emisora y se añade a la cadena
        Emisora laFM_Medellin = new Emisora("HJDK", "La FM Medellín", "Calle 52 #47-28", "Director Medellín", "106.9 MHz");
        laFM_Medellin.setCadena(cadenaLaFM);

        // Agregamos las emisoras a la lista de la cadena
        cadenaLaFM.getEmisoras().add(laFM_Bogota);
        cadenaLaFM.getEmisoras().add(laFM_Medellin);

        System.out.println("Creada: " + cadenaLaFM.toString());
        System.out.println(" -> Emisora Sede: " + laFM_Bogota.toString());
        System.out.println(" -> Emisora Afiliada: " + laFM_Medellin.toString());
        System.out.println("Verificación: La empresa " + rcn.getNombre() + " ahora tiene " + rcn.getCadenas().size() + " cadena(s).");


        // 3. Creación de Programas y Contenido Publicitario
        System.out.println("\n[Paso 3: Creando Programa, Contrato y Publicidad]");
        // Se crea un programa para toda la cadena La FM
        Programa programaNoticias = new Programa("Noticias La FM", "Luis Carlos Vélez", "Lunes a Viernes", "05:00", 300);
        programaNoticias.setCadena(cadenaLaFM); // Se asocia a la cadena

        // La emisora de Bogotá añade el programa a su parrilla
        laFM_Bogota.getProgramas().add(programaNoticias);

        // Se crea un contrato para el patrocinador Éxito
        ContratoPublicidad contratoAniversario = new ContratoPublicidad("CON-001", exito, 4, 50000000.0);
        exito.getContratos().add(contratoAniversario);

        // Se crea la publicidad específica para el programa de noticias bajo el contrato del Éxito
        Publicidad cunaAniversario = new Publicidad(30, 25000.0, programaNoticias, contratoAniversario);

        // Se añade la publicidad al programa y al contrato
        programaNoticias.getPublicidades().add(cunaAniversario);
        contratoAniversario.getPublicidades().add(cunaAniversario);

        System.out.println("Creado: " + programaNoticias.toString());
        System.out.println(" -> Asociado a la cadena: " + programaNoticias.getCadena().getNombre());
        System.out.println("Creado: " + contratoAniversario.toString());
        System.out.println("Creada: " + cunaAniversario.toString());


        // 4. Verificación final de las relaciones
        System.out.println("\n--- Verificación Final del Modelo ---");
        System.out.println("El programa '" + programaNoticias.getNombre() + "' tiene " + programaNoticias.getPublicidades().size() + " publicidad(es).");
        Publicidad publicidadDelPrograma = programaNoticias.getPublicidades().get(0);
        System.out.println("Detalles de la publicidad: " + publicidadDelPrograma.toString());
        System.out.println(" -> Esta publicidad pertenece al contrato: " + publicidadDelPrograma.getContrato().getNumeroContrato());
        System.out.println(" -> Y fue firmada por el patrocinador: " + publicidadDelPrograma.getContrato().getPatrocinador().getNombre());
        System.out.println("El costo total de esta publicidad es: $" + String.format("%,.2f", publicidadDelPrograma.getCostoTotal()));

    }
}