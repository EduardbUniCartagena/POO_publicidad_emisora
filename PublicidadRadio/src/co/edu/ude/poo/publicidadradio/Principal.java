package co.edu.ude.poo.publicidadradio;

import co.edu.ude.poo.publicidadradio.util.CargarDatosDePrueba;
import co.edu.ude.poo.publicidadradio.vistas.gui.VentanaPrincipal;
import javax.swing.JFrame;

/**
 * Clase principal que inicia la aplicación de gestión de publicidad.
 * Su única responsabilidad es crear y mostrar la ventana principal de la GUI.
 * @author Eduard David Barrios Padilla
 */
public class Principal {

    public static void main(String[] args) {
        // --- PASO 1: Cargar los datos de prueba en memoria ---
        // Se crea una instancia de la clase de carga y se ejecuta su método.
        new CargarDatosDePrueba().cargar();

        // Creamos una instancia de nuestra ventana principal
        VentanaPrincipal ventana = new VentanaPrincipal();

        // La hacemos visible
        ventana.setVisible(true);

        // Configuramos para que la aplicación se cierre al cerrar la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}